package lab3v1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;

public class Metak {

	int duzina,pocX,pocY,krajX,krajY;
	
	public Metak(int d,int px,int py,int kx,int ky) {
		duzina=d;
		pocX=px;
		pocY=py;
		krajX=kx;
		krajY=ky;
	}

	public int getDuzina() {
		return duzina;
	}

	public int getKrajX() {
		return krajX;
	}

	public int getKrajY() {
		return krajY;
	}
	
	
	public void paint(Graphics g) {
		Color c=g.getColor();
		
		g.setColor(Color.white);
		g.drawLine(pocX, pocY, krajX, krajY);
		
		g.setColor(c);
	}
	
	public void pomeriMetak(int pomeraj) {
		pocY+=pomeraj;
		krajY+=pomeraj;
	}
	
}
