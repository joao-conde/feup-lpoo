package dk2.logic;

public abstract class Character {
	
	
	//-------------ATTRIBUTES-------------//
	private int lin, col;
	private char symbol;
	
	
	//--------------METHODS---------------//
	
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
