package Model;

import Interfaces.CaseInterface;

public abstract class Case implements CaseInterface{
	
	//ATTRIBUTS
	public int X;
	public int Y;
	public boolean Occupe = false;
	
	
	//CONSTRUCTOR
	public Case(){
	}
	
	
	//SETTERS AND GETTERS
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	//Valeur du gene
	public boolean getOccupe() {
		return Occupe;
	}
	public void setOccupe(boolean occupe) {
		Occupe = occupe;
	}
}
