package lab3v1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulator extends Frame {

	private Svemir svemir = new Svemir(this);
	private Generator generator=new Generator(svemir);
	private Panel donjiPanel = new Panel();
	private Button dugme=new Button("Pokreni!");
	
	Panel gornjiPanel=new Panel();
	Label labela=new Label();
			
	
	private void populateWindow() {
		
		//dugme.setEnabled(true);
		dugme.addActionListener((ae)->{
			svemir.go();    // go cini da work postane true, i notify-uje kako bi se u run-u iskocilo iz onog wait-a.
			generator.go();
			svemir.setFocusable(true);
			dugme.setEnabled(false);
		});
		
		donjiPanel.add(dugme);
		add(donjiPanel,BorderLayout.SOUTH);
		
		//labela.setAlignment(BorderLayout.CENTER);
		gornjiPanel.add(labela);
		gornjiPanel.setBackground(Color.BLUE);
		add(gornjiPanel,BorderLayout.NORTH);
		
		
		//proba:
		//svemir.dodajNebeskoTelo(new Kometa(65,35,30));
		//svemir.dodajNebeskoTelo(new Kometa(150,130,10));
		//svemir.dodajNebeskoTelo(new Kometa(35,45,20));
		//Kometa k=new Kometa(113,35,30);
		//k.promeniX(-48);
		
		//svemir.repaint();

        add(svemir, BorderLayout.CENTER);
	}
	
	
	
	public Simulator() { //konstruktor Simulatora.
		setBounds(700,200,200,400);
		
		populateWindow();
		
		//pack(); niposto ovo ovde...
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (svemir != null) {
					svemir.finish();
				}  
				if (generator != null) {
					generator.finish();
				}                               
				dispose();
		    }
		});
		
		setResizable(false);
		setVisible(true);
		
		svemir.postaviIgraca(new Igrac(svemir.getWidth()/2,svemir.getHeight()-10/3-10)); //MORA OVO POSLE SETVISIBLE TRUE.

		
	}
	
	
	
	public static void main(String[]args) {
		new Simulator();
	}
	
}
