package dk2.logic;

public abstract class MovingChar extends Character {

	

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
	
	public void moveNW(char[][] b) {
		this.moveUp(b);
		this.moveLeft(b);
	}

	public void moveNE(char[][] b) {
		this.moveUp(b);
		this.moveRight(b);
	}

	public void moveSW(char[][] b) {
		this.moveDown(b);
		this.moveLeft(b);
	}

	public void moveSE(char[][] b) {
		this.moveDown(b);
		this.moveRight(b);
	}
	
	public int distanceTo(Character mc) {
		int dx = Math.abs(this.getLin()
				- mc.getLin());
		int dy = Math.abs(this.getCol()
				- mc.getCol());

		return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
	}
	

}
