package dk2.logic;

public abstract class MovingChar extends Character {

	// -------------ATTRIBUTES-------------//

	
	// --------------METHODS---------------//

	public MovingChar() {
		super();
	}


	public void moveTo(int lin, int col, char[][] b) {
		
		b[this.getLin()][this.getCol()] = ' ';
		
		this.setLin(lin);
		this.setCol(col);
	}

	public void moveLeft(char[][] b) {
		moveTo(this.getLin(), this.getCol() - 1, b);
	}

	public void moveRight(char[][] b) {
		moveTo(this.getLin(), this.getCol() + 1, b);
	}

	public void moveUp(char[][] b) {
		moveTo(this.getLin() - 1, this.getCol(), b);
	}

	public void moveDown(char[][] b) {
		moveTo(this.getLin() + 1, this.getCol(), b);
	}

}
