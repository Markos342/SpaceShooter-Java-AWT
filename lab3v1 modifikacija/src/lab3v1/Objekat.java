package lab3v1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;

public abstract class Objekat {
	
	int x,y;   //koordinate centra objekta 
	Color boja;
	
	
	
	public int dohvX() {
		return x;
	}

	public int dohvY() {
		return y;
	}

	public void promeniX(int pomeraj) {
		x+=pomeraj;
	}
	
	public void promeniY(int pomeraj) {
		y+=pomeraj;
	}

	public abstract void paint(Graphics g);
		
	public abstract boolean sePreklapa(int a, int b);

	public Objekat(int x, int y, Color boja) {
		this.x=x;
		this.y=y;
		this.boja=boja;
	}
	
	

}
