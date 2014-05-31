package Graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Case;

public class ContenuEchiquier extends JPanel {
	
	public ArrayList<Case> listeCase = new ArrayList<Case>();
	static final int TailleCase = 200;
	static final int nbCaseX = 800;
	static final int nbCaseY = 800;
	
	
	public void paintComponent(Graphics g)
    {
		//x1, y1, width, height
		Color case1 = new Color(204,102,51);
		Color case2 = new Color(255,255,204);
		//faire un jtextfield ou equivalent pour afficher le score
		
        g.setColor(case1);
        g.fillRect(60, 60, 800, 800);
        g.drawRect(59, 59, 801, 801);
        g.setColor(case2);
        
        
        for (int stripeX = 60; stripeX < nbCaseX; stripeX += TailleCase) {
            for (int y = 60, row = 0; y < nbCaseY; y += TailleCase/2, ++row) {
                int x = (row % 2 == 0) ? stripeX : (stripeX + TailleCase/2);
                g.fillRect(x, y, TailleCase/2, TailleCase/2);
            }
        }
        
        for(Case key : getCase())
        {
	        try {
	            Image dame = ImageIO.read(new File("res/Dames.png"));
	            g.drawImage(dame, key.getX(), key.getY(), 31, 88, this);
	          } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    }
        
    public ArrayList<Case> getCase()
    {
    	return listeCase;
    }
    
    public void setCase(Case caseE){
    	listeCase.add(caseE);
    }
		
		//Test de placement : une dame par case
//		int posX = 0;
//		int posY = 0;
//		
//		for(int i=0; i<8; i++)
//		{
//			posX = posX + 99;
//			posY = 0;
//
//			for(int j=0; j<8; j++)
//			{
//				posY = posY + 101;
//				if(j==0){posY=60;}
//
//		        try {
//		            Image dame = ImageIO.read(new File("res/Dames.png"));
//		            g.drawImage(dame, posX, posY, 31, 88, this);
//		          } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//			}
//		}
}
