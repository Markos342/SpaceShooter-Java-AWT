package lab3v1;

import java.util.Random;

public class Generator extends Thread {
	
	Svemir svemir;
	boolean work;  //po defaultu je false.

	public Generator(Svemir svemir) {
		this.svemir=svemir;
		start();
	}
	
	public synchronized void go() {
		work = true;
		notify();        
	}
	
	public synchronized void finish() {
		interrupt();	
		work=false;
	}
	
	@Override
	public void run() {
		
		try {
			
			while(!interrupted()) {
				synchronized (this) {
					while(!work){
						wait();
					}
				}
			
				sleep(900);
				int x=new Random().nextInt(200);
				int y=0;
				int pp= 10 + new Random().nextInt(20);
				synchronized (svemir) {
					svemir.dodajNebeskoTelo(new Kometa(x,y,pp));
				}
				
			
			}
			
			
		} catch (InterruptedException e) {}
		
	}
	
	
	
	
}
