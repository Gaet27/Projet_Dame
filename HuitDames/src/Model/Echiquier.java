package Model;

import java.util.ArrayList;

import Controller.CaseController;
import Interfaces.EchiquierInterface;

public abstract class Echiquier implements EchiquierInterface, Cloneable {
	
	//ATTRIBUTS
	public int score = 50;
	//1 membre de la population
	public ArrayList<ArrayList<Case>> EnsembleEchiquier = new ArrayList<ArrayList<Case>>();
	//1 gène du membre de la population
	public ArrayList<Case> ColonneEchiquier; 
	
		
	//CONSTRUCTEUR
	public Echiquier(){
		
		int posX = 0;
		int posY = 0;
		
		for(int i=1; i<9; i++)
		{
			posX = posX + 99;
			posY = 0;
			ColonneEchiquier = new ArrayList<Case>();
	
			//Placement de chaque dame aléatoirement
			int nbAleatoire = (int)(Math.random() * (9 - 1)) + 1;
			for(int j=1; j<9; j++)
			{
				posY = posY + 101;
				if(j==1){posY=60;}
				
				Case caseE = new CaseController();
				caseE.setX(posX);
				caseE.setY(posY);
				if(j == nbAleatoire)
				{
					caseE.setOccupe(true);	
				}
				ColonneEchiquier.add(caseE);
			}
			EnsembleEchiquier.add(ColonneEchiquier);
		}
	}

	
	//GETTERS AND SETTERS
	public ArrayList<ArrayList<Case>> getEnsembleCases() {
		return EnsembleEchiquier;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
