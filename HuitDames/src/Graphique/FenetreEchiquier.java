package Graphique;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Interfaces.FenetreInterface;
import Model.Case;
import Model.Echiquier;

public class FenetreEchiquier extends JFrame implements FenetreInterface{
	
	private ContenuEchiquier contenuEchiquier = new ContenuEchiquier();
	private JTextField score = new JTextField();
	
	public FenetreEchiquier()
	{             
		this.setTitle("Echiquier");
		this.setSize(960, 1000);
		this.setLocationRelativeTo(null); 
		 
	    score.setSize(100, 50);
        score.setEditable(false);
        this.add(score);
        
		Color background = new Color(234,189,121);
		this.setBackground(background);
		this.getContentPane().add(contenuEchiquier);
		this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public void LancerDame(Echiquier echq)
	{
		//On supprime du tableau les cases contenant les dames de l'echiquier précédent
		contenuEchiquier.listeCase.removeAll(contenuEchiquier.getCase());
        	
		for(ArrayList<Case> key : echq.EnsembleEchiquier)
		{
			//On parcours les cases d'une colonne
			for(Case key2 : key){
				//On prends le x,y de la dame pour chaque colonne d'un echiquier
				if(key2.getOccupe())
				{
			        contenuEchiquier.setCase(key2);
					break;
				}
			}
		}
		
		//Affichage du score sur l'IHM pour vérification
		score.setText(String.valueOf(echq.getScore()));
		contenuEchiquier.repaint();
		
		try {
			  Thread.sleep(100);
			} catch (InterruptedException e) {
			  e.printStackTrace();
	    }
	    
	}
}
