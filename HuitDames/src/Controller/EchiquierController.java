package Controller;

import java.util.ArrayList;

import Interfaces.EchiquierInterface;
import Model.Case;
import Model.Echiquier;

public class EchiquierController extends Echiquier implements EchiquierInterface{

	
	//Constructeur
	public EchiquierController(){
		super();
	}
	
	
	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Echiquier> ListeEchiquier = new ArrayList<Echiquier>();
	{
		ListeEchiquier.add(this);
	}
	
	
	//Detection des confilts pour en déduire un score
	public void score(Echiquier echiquier)
	{
		//2 : position des dames, 0 : pour les emplacement libres
		int[][] positionsDames = new int[8][8];
		int i = 0;
		int j = 0;
		
		//Reinitialisation des scores pour partir de 50 à chaque fois
		echiquier.setScore(50);
		
		//on parcours les colonnes de l'echiquier pour réaliser une matrice ayant pour valeur 2 à l'emplacement des dames
		//et 0 pour un emplacement libre
		for(ArrayList<Case> key : echiquier.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				//Si la case est occupée par une dame on initialise le tableau à la valeur 2
				if(key2.getOccupe())
				{
					positionsDames[i][j] = 2;
					break;
				}
				//Si la case est libre on l'initialise à 0
				positionsDames[i][j] = 0;
				j++;
			}
			i++;
		}		
		
		i=0;
		//Pour chaque dame on vérifie le nombre de conflit
		for(ArrayList<Case> key : echiquier.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				if(key2.getOccupe())
				{
					positionsDeplacements(i, j, positionsDames, echiquier);
					break;
				}
				j++;
			}
			i++;
		}
	}
	
	//A chaque fois qu'une dame rencontre une autre dame sur ses trajectoires (la valeur 2) on diminue son score de 1
	public void positionsDeplacements(int colonne, int ligne, int[][] positionsDames, Echiquier echiquier){
		
		int i=0, j=0;
		//diagonale 1
		for(i=colonne,j=ligne; i>=0 && j<8; i--,j++)
		{
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
		}
		//diagonale 2
	    for(i=colonne,j=ligne; i>=0 && j>=0; i--,j--)
	    {
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
	    }
	    //diagonale 3
	    for(i=colonne,j=ligne; i<8 && j>=0; i++,j--)
	    {
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
	    }
	    //diagonale 4
	    for(i=colonne,j=ligne; i<8 && j<8; i++,j++)
	    {
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
	    }
	    //ligne en avant
	    for(i=colonne,j=ligne; i<8 ; i++)
	    {
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
	    }
	    //ligne en arrière
	    for(i=colonne,j=ligne; i>=0 ; i--)
	    {
			if(positionsDames[i][j] == 2 && colonne != i)
			{
				echiquier.setScore(echiquier.getScore()-1);
			}
	    }
	    //pas de vérification sur la colonne car on place une dame par colonne
	}
	
	
	public void couplageEchiquier(Echiquier echiquier1, Echiquier echiquier2)
	{   
		//2 : position des dames, 0 : pour les emplacement libres
		int[] positionsDamesEchiquier1 = new int[8];
		int[] positionsDamesEchiquier2 = new int[8];
		int j = 0;
		int i = 0;
		
		//On parcours les colonnes de l'echiquier pour réaliser un tableau avec les positions de chaque reine sur leur colonne
		for(ArrayList<Case> key : echiquier1.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				//Si la case est occupée par une dame on initialise le tableau à la valeur 2
				if(key2.getOccupe())
				{
					positionsDamesEchiquier1[i] = j;
					break;
				}
				j++;
			}
			i++;
		}	
		
		//On fait la même chose pour le 2ème echiquier
		i=0;
		for(ArrayList<Case> key : echiquier2.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				//Si la case est occupée par une dame on initialise le tableau à la valeur 2
				if(key2.getOccupe())
				{
					positionsDamesEchiquier2[i] = j;
					break;
				}
				j++;
			}
			i++;
		}
		
		//Generation de la coupe
	    int coupeAleatoire = (int)(Math.random() * (7 - 0)) + 0;
	    
	    //On donne 30% de chance de modifier la valeur d'une colonne aléatoirement
	    int chanceModification = (int)(Math.random() * (9 - 0)) + 0;
	    boolean modif = false;
	    if(chanceModification <= 2)
	    {
	    	modif = true;
	    }

	    
	    //Couplage des valeurs
	    for(i=0; i<8; i++)
	    {
	    	if(i > coupeAleatoire)
	    	{
	    		int valeurEchiquier1 = positionsDamesEchiquier1[i];
	    		int valeurEchiquier2 = positionsDamesEchiquier2[i];
	    		positionsDamesEchiquier1[i] = valeurEchiquier2;
	    		positionsDamesEchiquier2[i] = valeurEchiquier1;
	    	}
	    }
	    if(modif)
    	{
		    //Valeur de la colonne à modifier
		    int ColonneEchequier1 = (int)(Math.random() * (7 - 0)) + 0;
		    int ColonneEchequier2 = (int)(Math.random() * (7 - 0)) + 0;
		    //Valeur du gene
		    int valeurGeneEchq1 = (int)(Math.random() * (7 - 0)) + 0;
		    int valeurGeneEchq2 = (int)(Math.random() * (7 - 0)) + 0;
		    
    		positionsDamesEchiquier1[ColonneEchequier1] = valeurGeneEchq1;
    		positionsDamesEchiquier2[ColonneEchequier2] = valeurGeneEchq2;
    	}
	    
	    //Modification du premier echiquier en conséquence
	    //On replace les dames avec les nouvelles valeurs
	    i=0;
		for(ArrayList<Case> key : echiquier1.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				if(j == positionsDamesEchiquier1[i])
				{
					key2.setOccupe(true);
				}
				else{
					key2.setOccupe(false);
				}
				j++;
			}
			i++;
		}
		
		//Modification du deuxième echiquier
		//On replace les dames avec les nouvelles valeurs
	    i=0;
		for(ArrayList<Case> key : echiquier2.EnsembleEchiquier)
		{
			j=0;
			//On parcours les cases d'une colonne
			for(Case key2 : key)
			{
				if(j == positionsDamesEchiquier2[i])
				{
					key2.setOccupe(true);
				}
				else{
					key2.setOccupe(false);
				}
				j++;
			}
			i++;
		}	
	    
	}
}
