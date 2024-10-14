package lab3v1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Igrac extends NebeskoTelo {

	private int[] nizX=new int[3];
	private int[] nizY=new int[3];
	
	private int[] nizXPlavo=new int[3];
	private int[] nizYPlavo=new int[3];
	
	private int[] nizXBelo=new int[3];
	private int[] nizYBelo=new int[3];
	
	
	
	public Igrac(int x, int y) {
		super(x, y, Color.RED, 20);
	}
	

	@Override
	public void paint(Graphics g) {
		Color prevColor = g.getColor();
		
		double ugao=-Math.PI/2.0;
		g.setColor(boja);
		
		for (int i=0;i<3;i++) {
			nizX[i]= x + (int) (pp*Math.cos(ugao));  // pazi,mora i +x naravno.
		    nizY[i]= y + (int) (pp*Math.sin(ugao));  
		    nizXPlavo[i]= x + (int) (pp*2/3.0*Math.cos(ugao));  // pazi,mora i +x naravno.
		    nizYPlavo[i]= y + (int) (pp*2/3.0*Math.sin(ugao));
		    nizXBelo[i]= x + (int) (pp*1/3.0*Math.cos(ugao));  // pazi,mora i +x naravno.
		    nizYBelo[i]= y + (int) (pp*1/3.0*Math.sin(ugao));
		    ugao+=2*Math.PI/3.0;
		}
		g.fillPolygon(nizX,nizY,3);
		g.setColor(Color.BLUE);
		g.fillPolygon(nizXPlavo,nizYPlavo,3);
		g.setColor(Color.WHITE);
		g.fillPolygon(nizXBelo,nizYBelo,3);

		g.setColor(prevColor);
	}
	
	public int dohvVrhX(){
		return nizX[0];   // ili mozda nizX[1] , proveri posle.
	}
	
	public int dohvVrhY(){
		return nizY[0];
	}
	
	@Override
	public boolean sePreklapa(int a, int b) {
		return false;
	}

	
	
}