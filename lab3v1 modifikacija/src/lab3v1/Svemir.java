package lab3v1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


public class Svemir extends Canvas implements Runnable{ 
	
	private Color bgColor = Color.BLACK;
	private long sleepTime = 100;
	Thread thread;
	private ArrayList<NebeskoTelo> listaTela=new ArrayList<>(); //same radnje se moraju vrsiti u okviru metoda/konstr,ne ovde.
	//OVDE DODAJES JOS STVARI KOJE PRIPADAJU SVEMIRU A TREBA DA SE ISCRTAJU:
	private Igrac igrac;
	private ArrayList<Metak> listaMetaka=new ArrayList<>();
	private int brojPoena=0;
	private Simulator owner;

	
	
	boolean work;  //po defaultu je false.
	
	public void dodajNebeskoTelo(NebeskoTelo nt) {
		listaTela.add(nt);
	}
	
	public void postaviIgraca(Igrac ig) {
		igrac=ig;
	}
	
	public void dodajMetak() {
		listaMetaka.add(new Metak(8, igrac.dohvVrhX(),igrac.dohvVrhY(),igrac.dohvVrhX(),igrac.dohvVrhY()-8));
	}
	
	
	
	
	@Override
	public void paint(Graphics g) { //ovo je vec onaj pravi paint,onaj koji se implicitno poziva kad treba ili kroz repaint.
			
			if (work) {  //ova provera uslova je ako hoces na pocetku crn ekran
				/*for (NebeskoTelo nt : listaTela) {  //bolje radi ako je ova petlja ovde, u paint-u jer nju onda radi awt nit.
					nt.paint(g);
				}*/
				
				for (int i=0;i<listaTela.size();i++) {
					listaTela.get(i).paint(g);
				}
				//OVDE PISI AKO SE JOS NEKI OBJEKTI BUDU ISCRTAVALI,tu pozivaj njihovu paint metodu: noviobjekat.paint(g);
				igrac.paint(g);
				for (int i=0;i<listaMetaka.size();i++) {
					listaMetaka.get(i).paint(g);
				}
				
		    }
			
	}
	
	public synchronized void finish() {
		thread.interrupt();	
		work=false;
	}
	
	public synchronized void go() {
		work = true;
		notify();
	}
	

	@Override
	public void run() {
		
		try {
			
			while(!thread.interrupted()) {
			
				synchronized (this) {
					while(!work){
						wait();
					}
				}
				
				thread.sleep(sleepTime);
				
				repaint();
				
				/*for (NebeskoTelo nt : listaTela) {
					nt.promeniY(5);
				}*/
				
				for (int i=0;i<listaTela.size();i++) {
					listaTela.get(i).promeniY(5);
					
					if (listaTela.get(i).sePreklapa(igrac.dohvVrhX(),igrac.dohvVrhY())) {
						this.finish();
						owner.labela.setText("GAME OVER.");
						owner.labela.setBackground(Color.RED);
						owner.gornjiPanel.setBackground(Color.RED);
						finish();
					}
				}
				
				for (int i=0;i<listaMetaka.size();i++) {
					Metak posmatraniMetak=listaMetaka.get(i);
					posmatraniMetak.pomeriMetak(-8);
					for (int j=0;j<listaTela.size();j++) {
						NebeskoTelo posmatranoTelo=listaTela.get(j);
						if (posmatranoTelo.sePreklapa(posmatraniMetak.krajX,posmatraniMetak.krajY)){
							if (posmatranoTelo instanceof Kometa) {
								Kometa posmatranaKometa=(Kometa)posmatranoTelo;
								if(posmatranaKometa.dohvPp()<=20) {
									listaTela.remove(posmatranaKometa);
									brojPoena+=10;
									owner.labela.setText("POENI: "+brojPoena);
									owner.labela.revalidate();
								}
								else {
									listaTela.remove(posmatranaKometa);
			
									listaTela.add(new Kometa(posmatranaKometa.dohvX()-15,posmatranaKometa.dohvY(),(11))); 
									listaTela.add(new Kometa(posmatranaKometa.dohvX()+13,posmatranaKometa.dohvY()-6,(13))); 

									 
									brojPoena+=10;
									owner.labela.setText("POENI: "+brojPoena);
									owner.labela.revalidate();
								}
							}
						}
					}
				}
				
				
				
			}
			
		} catch (InterruptedException e) {}
		
	}
	
	
	public Svemir(Simulator own) {
		
		addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		    	char c=e.getKeyChar();
		    	if (c=='a') {
		    		igrac.promeniX(-5);
		    	}
		    	else if(c=='d') {
		    		igrac.promeniX(5);
		    	}
		    	if (c=='w') {
		    		igrac.promeniY(-5);
		    	}
		    	else if(c=='s') {
		    		igrac.promeniY(5);

		    	}
		    	else if(c==KeyEvent.VK_SPACE) {
			    	dodajMetak();
		    	}
		    	
		    }
		});
		
		addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	dodajMetak();
		    }

		
		});
		
		
		/*addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char key = Character.toUpperCase(e.getKeyChar());
				switch(key) {
				case KeyEvent.VK_W: {
					int y = player.getY() - squareWidth;
					player.setY(y < 0 ? player.getY() : y);
					break;
				}
				case KeyEvent.VK_S: {
					int y = player.getY() + squareWidth;
					player.setY(y > getHeight() ? player.getY() : y);
					break;
				}
				case KeyEvent.VK_A: {
					int x = player.getX() - squareWidth;
					player.setX(x < 0 ? player.getX() : x);
					break;
				}
				case KeyEvent.VK_D: {
					int x = player.getX() + squareWidth;
					player.setX(x > getWidth() ? player.getX() : x);
					break;
				}
				}
				repaint();
			}
			
		});*/
		
		owner=own;
		setBackground(bgColor);
		thread = new Thread(this);
		thread.start();
		
	}
	
	
}
