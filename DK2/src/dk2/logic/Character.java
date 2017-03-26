package dk2.logic;

import java.io.Serializable;

public abstract class Character implements Serializable{
	
	
	
	private int lin, col;
	private char symbol;
	

	public Character(){}
	
	public int getLin() {
		return lin;
	}

	public void setLin(int lin) {
		this.lin = lin;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
	public void setSymbol(char c){
		symbol = c;
	}
}
