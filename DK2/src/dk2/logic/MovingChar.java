package dk2.logic;

public class MovingChar extends Character{
	public void moveTo(int lin, int col){
		this.setLin(lin);
		this.setCol(col);
	}
}
