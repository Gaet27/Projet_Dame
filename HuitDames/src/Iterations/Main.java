package Iterations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import Controller.EchiquierController;
import Graphique.FenetreEchiquier;
import Model.Echiquier;

public class Main {


	private static ArrayList<Echiquier> tirageAuSort = new ArrayList<Echiquier>();
	public static HashMap<Integer, Echiquier> nouvellePopulation = new HashMap<Integer, Echiquier>();
	private static Integer cursor = 0;
	private static HashSet<Integer> nbAleatoires = new HashSet<Integer>();
	
	//////////////////////////////////////////////////////////////////////////////////////
	///////////////////--------VALEUR A MODIFIER ICI---------/////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	private static int popEchiquierTotale = 4000;
	private static int popEchiquierRetenue = 250;
	private static int afficheScoreAuDessus = 48;
	
	public static void main(String[] args) {
		
		
	    int score = 0;
	    
		//Creation d'une population d'echiquier avec 8 genes pouvant avoir 8 valeurs
	    for (int i=0; i<popEchiquierTotale; i++)
	    {
	    	//Creation d'un echiquier
	    	Echiquier echq = new EchiquierController();
	      	//On établit le score pour chaque echiquier, plus il y a de conflit moins le score est grand
	    	echq.score(echq);
	    	//On affiche l'echiquier avec son score sur l'IHM
	    	//fenetre.LancerDame(echq);
	    	//Si jamais le score est déjà optimum par chance on s'arrete
	    	if(echq.getScore() == 50)
	    	{
	    		score = 50;
	    		break;
	    	}
	    }
	    
	    //Si le score n'est pas de 50 : une des solution optimum, on continue le traitement
    	if(score < 50);
	    {
	    	//Si l'echiquier a un score supérieur à 30 on l'ajoute dans le tableau pour être tiré au sort, plus il a un score elevé et plus il a de cases dans le tableau
	    	//donc plus de chances d'être tiré au sort
	    	for(Echiquier key : EchiquierController.ListeEchiquier)
	    	{
	    		if(key.getScore() >= 30)
	    		{
		    		for(int i=1; i <= key.getScore(); i++)
		    		{
		    			tirageAuSort.add(key);
		    		}
	    		}
	    	}
	    	
	    	//On créé les nombres aléatoires avant pour s'assurer qu'il n'y aura pas de doublons et donc 2 fois le même echiquier
	    	do
	    	{
	    		int nbAleatoire = (int)(Math.random() * (tirageAuSort.size() - 0)) + 0;
	    		if (!nbAleatoires.contains(nbAleatoire))
	    		{
	    			nbAleatoires.add(nbAleatoire);
	    		}
	    	}
	    	while(nbAleatoires.size() < popEchiquierRetenue);
	    	
	    	//On tire les echiquiers retenus au sort, on les ajoute ensuite dans un nouveau tableau, en les récupérant grâce à l'index du tableau
    		for(int key : nbAleatoires)
    		{
    			nouvellePopulation.put(getAndIncrement(), tirageAuSort.get(key));
    		}
	    }
	    
	    FenetreEchiquier fenetre = new FenetreEchiquier();
	    while(score < 50)
	    {
    		//Couplage des echiquiers 2 par 2
    		Echiquier echiquier1 = null;
    		Echiquier echiquier2 = null;
    		int curseur = 0;
    		int curseur2 = 0;
    		
    		//Recuperation du premier echiquier
    		while(curseur < nouvellePopulation.size()) 
    		{
    			for(Entry<Integer, Echiquier> key1 : nouvellePopulation.entrySet()) {
    				
    				curseur = key1.getKey().intValue();
    				
    				if(curseur > curseur2)
	    			{
		    			echiquier1 = key1.getValue();
		    		    break;
	    			}
    			}
    		    
    			//Recuperation du deuxieme echiquier
    		    for(Entry<Integer, Echiquier> key2 : nouvellePopulation.entrySet()) {
    		    	
    		    	curseur2 = key2.getKey().intValue();
    		    	
    		    	if (curseur2 > curseur)
    		    	{
    		    		echiquier2 = key2.getValue();
    		    		break;
    		    	}
    		    }   
    		    
    		    //Couplage des deux echiquiers pour en faire deux nouveaux
    		    echiquier1.couplageEchiquier(echiquier1, echiquier2);
    		    
    		    //On recalcul le score
    		    echiquier1.score(echiquier1);
    		    //On affiche graphiquement les nouvelles positions
    		    if (echiquier1.getScore() >= afficheScoreAuDessus)
    		    {
    		    	fenetre.LancerDame(echiquier1);
    		    }
    		    //Vérification si le score n'est pas de 50, si oui on arrête
    	    	if(echiquier1.getScore() == 50)
    	    	{
    	    		score = 50;
    	    		break;
    	    	}
    	    	
    	    	//On recalcul le score
    		    echiquier2.score(echiquier2);
    		    //On affiche graphiquement les nouvelles positions
    		    if (echiquier2.getScore() >= afficheScoreAuDessus)
    		    {
    		    	fenetre.LancerDame(echiquier2);
    		    }
    		    //Vérification si le score n'est pas de 50, si oui on arrête
    	    	if(echiquier2.getScore() == 50)
    	    	{
    	    		score = 50;
    	    		break;
    	    	}
    		}
	    }
	}
	
	
	
	public static Integer getAndIncrement(){
		cursor++;
		return cursor;
	}
}
