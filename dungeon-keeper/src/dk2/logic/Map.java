package dk2.logic;

import java.io.Serializable;

public abstract class Map implements Serializable {

	private char[][] board;
	private int size;
	private Hero mieic_student;

	public Map(int mapSize) {
		this.size = mapSize;

		char[][] b = new char[mapSize][mapSize];

		for (int i = 0; i < mapSize - 1; i++) {
			for (int j = 0; j < mapSize - 1; j++) {
				b[i][j] = ' ';
			}
		}

		this.board = b;
		this.mieic_student = new Hero();
	}

	public abstract boolean hasHeroWon();

	public char[][] getBoard() {
		return board;
	}

	public int getSize() {
		return this.size;
	}

	public void setBoardCell(int lin, int col, char symbol) {

		board[lin][col] = symbol;
	}

	public Hero getHero() {
		return this.mieic_student;
	};

	public void setBoard(char[][] b) {
		this.board = b;
	}

	public abstract Door[] getDoor();

	public abstract Door[] getAllDoors();

	public abstract void openDoors();

	public void buildExtWalls() {

		for (int i = 0; i < this.size; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[board.length - 1][i] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[i][board.length - 1] = 'X';
		}
	}

	public void setDoors(Door d) {
		setBoardCell(d.getLin(), d.getCol(), d.getSymbol());
	}

	public abstract void buildMaze();

	public void drawHero() {

		this.setBoardCell(this.mieic_student.getLin(), this.mieic_student.getCol(), this.mieic_student.getSymbol());
	}

	public void eraseHero() {
		this.setBoardCell(this.mieic_student.getLin(), this.mieic_student.getCol(), ' ');
	}

	public boolean canMoveUp(MovingChar obj) {

		if ((board[obj.getLin() - 1][obj.getCol()] == 'X') || (board[obj.getLin() - 1][obj.getCol()] == 'I')
				|| (board[obj.getLin() - 1][obj.getCol()] == 'O') || (board[obj.getLin() - 1][obj.getCol()] == '*')
				|| (board[obj.getLin() - 1][obj.getCol()] == '$') || (board[obj.getLin() - 1][obj.getCol()] == '8'))
			return false;

		return true;
	}
	
	public boolean canMoveDown(MovingChar obj){
		
		if ((board[obj.getLin() + 1][obj.getCol()] == 'X') || (board[obj.getLin() + 1][obj.getCol()] == 'I')
				|| (board[obj.getLin() + 1][obj.getCol()] == 'O') || (board[obj.getLin() + 1][obj.getCol()] == '*')
				|| (board[obj.getLin() + 1][obj.getCol()] == '$') || (board[obj.getLin() + 1][obj.getCol()] == '8'))
			return false;
		
		return true;
	}
	
	public boolean canMoveLeft(MovingChar obj){
		
		if ((board[obj.getLin()][obj.getCol() - 1] == 'X') || (board[obj.getLin()][obj.getCol() - 1] == 'I')
				|| (board[obj.getLin()][obj.getCol() - 1] == 'O') || (board[obj.getLin()][obj.getCol() - 1] == '*')
				|| (board[obj.getLin()][obj.getCol() - 1] == '$') || (board[obj.getLin()][obj.getCol() - 1] == '8'))
			return false;
		return true;
	}

	public boolean canMoveRight(MovingChar obj){
		
		if ((board[obj.getLin()][obj.getCol() + 1] == 'X') || (board[obj.getLin()][obj.getCol() + 1] == 'I')
				|| (board[obj.getLin()][obj.getCol() + 1] == 'O') || (board[obj.getLin()][obj.getCol() + 1] == '*')
				|| (board[obj.getLin()][obj.getCol() + 1] == '$') || (board[obj.getLin()][obj.getCol() + 1] == '8'))
			return false;
		return true;
	}
	
	public boolean canMoveUpperLeft(MovingChar obj){
		
		if ((board[obj.getLin() - 1][obj.getCol() - 1] == 'X') || (board[obj.getLin() - 1][obj.getCol() - 1] == 'I')
				|| (board[obj.getLin() - 1][obj.getCol() - 1] == 'O')
				|| (board[obj.getLin() - 1][obj.getCol() - 1] == '*')
				|| (board[obj.getLin() - 1][obj.getCol() - 1] == '$')
				|| (board[obj.getLin() - 1][obj.getCol() - 1] == '8'))
			return false;
		return true;
	}
	
	public boolean canMoveUpperRight(MovingChar obj){
	
		if ((board[obj.getLin() - 1][obj.getCol() + 1] == 'X') || (board[obj.getLin() - 1][obj.getCol() + 1] == 'I')
				|| (board[obj.getLin() - 1][obj.getCol()] + 1 == 'O')
				|| (board[obj.getLin() - 1][obj.getCol() + 1] == '*')
				|| (board[obj.getLin() - 1][obj.getCol() + 1] == '$')
				|| (board[obj.getLin() - 1][obj.getCol() + 1] == '8'))
			return false;
		return true;
	}
	
	public boolean canMoveLowerRight(MovingChar obj){
		
		if ((board[obj.getLin() + 1][obj.getCol() + 1] == 'X') || (board[obj.getLin() + 1][obj.getCol() + 1] == 'I')
				|| (board[obj.getLin() + 1][obj.getCol() + 1] == 'O')
				|| (board[obj.getLin() + 1][obj.getCol() + 1] == '*')
				|| (board[obj.getLin() + 1][obj.getCol() + 1] == '$')
				|| (board[obj.getLin() + 1][obj.getCol() + 1] == '8'))
			return false;
		
		return true;
	}
	
	public boolean canMoveLowerLeft(MovingChar obj){
		
		if ((board[obj.getLin() + 1][obj.getCol() - 1] == 'X') || (board[obj.getLin() + 1][obj.getCol() - 1] == 'I')
				|| (board[obj.getLin() + 1][obj.getCol() - 1] == 'O')
				|| (board[obj.getLin() + 1][obj.getCol() - 1] == '*')
				|| (board[obj.getLin() + 1][obj.getCol() - 1] == '$')
				|| (board[obj.getLin() + 1][obj.getCol() - 1] == '8'))
			return false;
		return true;
	}
	public boolean canMove(char direction, MovingChar obj) {

		switch (direction) {
		case 'W':	return canMoveUp(obj); 			
		case 'S':	return canMoveDown(obj);
		case 'A':	return canMoveLeft(obj);			
		case 'D':	return canMoveRight(obj);			
		case 'Q':	return canMoveUpperLeft(obj);			
		case 'E': 	return canMoveUpperRight(obj);			
		case 'C':	return canMoveLowerRight(obj);		
		case 'Z':	return canMoveLowerLeft(obj);	
		}
		
		return false;
	}

	public void moveHero(char direction) {

		char[][] b = this.getBoard();

		if (!canMove(direction, this.mieic_student))
			return;

		switch (direction) {

		case 'W':
			this.getHero().moveUp(b);
			break;

		case 'S':
			this.getHero().moveDown(b);
			break;

		case 'A':

			this.getHero().moveLeft(b);
			break;

		case 'D':

			this.getHero().moveRight(b);
			break;

		}
	}

	public void setHero(Hero h) {
		this.mieic_student = h;
	}

	public void heroReachedKey() {
	};

	public abstract void advanceTurn();

	public void placeChars() {
	};
}
