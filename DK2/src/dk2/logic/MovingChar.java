package dk2.logic;

public abstract class MovingChar extends Character {

	// -------------ATTRIBUTES-------------//

	// whatever is "underneath" this character
	private char under;

	// --------------METHODS---------------//

	public MovingChar() {
		super();
		this.under = ' ';
	}

	public void setUnder(char u) {
		this.under = u;
	}

	public char getUnder() {
		return under;
	}

	public void moveTo(int lin, int col, char[][] b) {
		
		
		under = b[lin][col];
		b[lin][col] = ' ';
		
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
