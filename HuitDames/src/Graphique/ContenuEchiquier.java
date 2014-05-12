package Graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ContenuEchiquier extends JPanel {
	
	private int posX = 35;
	private int posY = 35;
	
	static final int TailleCase = 100;
	static final int nbCaseX = 400;
	static final int nbCaseY = 400;
	
	public ContenuEchiquier(){
		JPanel jpanel = new JPanel();
	}
	
	public void paintComponent(Graphics g)
    {
		//x1, y1, width, height
		Color case1 = new Color(204,102,51);
		Color case2 = new Color(255,255,204);
		
        g.setColor(case1);
        g.fillRect(30, 30, 400, 400);
        g.drawRect(29, 29, 401, 401);
        g.setColor(case2);
        
        for (int stripeX = 30; stripeX < nbCaseX; stripeX += TailleCase) {
            for (int y = 30, row = 0; y < nbCaseY; y += TailleCase/2, ++row) {
                int x = (row % 2 == 0) ? stripeX : (stripeX + TailleCase/2);
                g.fillRect(x, y, TailleCase/2, TailleCase/2);
            }
        }
        
        for(int i=1; i<=8; i++)
        {
	        try {
	            Image dame = ImageIO.read(new File("res/Dame.png"));
	            g.drawImage(dame, posX, posY, 60, 60, this);
	          } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
    }
	

	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}	
}
