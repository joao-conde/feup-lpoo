package dk2.logic;

public abstract class MovingChar extends Character{
	
	
	//--------------METHODS---------------//
	
	public MovingChar(){
		super();
	}
	
	public void moveTo(int lin, int col){
		this.setLin(lin);
		this.setCol(col);
	}
	
	public void moveLeft(){
		moveTo(this.getLin(),this.getCol()-1);
	}
	
	public void moveRight(){
		moveTo(this.getLin(),this.getCol()+1);
	}
	
	public void moveUp(){
		moveTo(this.getLin()-1,this.getCol());
	}
	
	public void moveDown(){
		moveTo(this.getLin()+1,this.getCol());
	}
		
}
