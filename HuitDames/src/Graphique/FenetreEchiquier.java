package Graphique;

import java.awt.Color;

import javax.swing.JFrame;

public class FenetreEchiquier extends JFrame {
	
	private ContenuEchiquier contenuEchiquier = new ContenuEchiquier();
	
	public FenetreEchiquier()
	{             
		this.setTitle("Echiquier");
		this.setSize(480, 500);
		this.setLocationRelativeTo(null);   
		      
		Color background = new Color(234,189,121);
		this.setBackground(background);
		this.getContentPane().add(contenuEchiquier);
		this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    LancerDame();
	} 
	
	private void LancerDame()
	{
		for(int i = 0; i < contenuEchiquier.getWidth()-120; i++){
	      int x = (int)(Math.random() * (300 - 30)) + 30, y = (int)(Math.random() * (300 - 30)) + 30;
	      contenuEchiquier.setPosX(x);
	      contenuEchiquier.setPosY(y); 
	      contenuEchiquier.repaint();
	      try {
	        Thread.sleep(200);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
		}
	}

}
