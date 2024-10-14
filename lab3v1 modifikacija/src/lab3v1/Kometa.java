package lab3v1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

public class Kometa extends NebeskoTelo {
	
	private double pocUgao=new Random().nextDouble()*2*Math.PI/5.0; //proizvoljan pocetni ugao koji kometa zaklapa sa X-osom.
	
	int[]nizX=new int[5];
	int[]nizY=new int[5];
	
	public Kometa(int x, int y, int pp) {
		super(x, y, Color.GRAY, pp);
	}

	@Override
	public void paint(Graphics g) {  //ovu paint cemo morati eksplicitno da pozivamo.
		Color prevColor = g.getColor();
		
		double ugao=pocUgao;
		g.setColor(boja);
		
		for (int i=0;i<5;i++) {
			nizX[i]= x + (int) (pp*Math.cos(ugao));  // pazi,mora i +x naravno.
		    nizY[i]= y + (int) (pp*Math.sin(ugao));  
		    ugao+=2*Math.PI/5.0;
		}
		g.fillPolygon(nizX,nizY,5);
		
		g.setColor(prevColor);
	}
	
	
	@Override
	public boolean sePreklapa(int a, int b) {
		return (new Polygon(nizX,nizY,5)).contains(new Point(a,b));
	}
	

}


	


