package lab3v1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class NebeskoTelo extends Objekat {
	
	int pp; //poluprecnik opisane kruznice u pikselima

	public NebeskoTelo(int x, int y, Color boja, int pp) {
		super(x, y, boja);
		this.pp=pp;
	}

	public int dohvPp() {
		return pp;
	}

	

}
