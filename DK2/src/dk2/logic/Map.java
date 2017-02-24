package dk2.logic;

public abstract class Map {
	
	//-------------ATTRIBUTES-------------//
	private char[][] board;
	private int size;
	
	
	//--------------METHODS---------------//
	
	//Map constructor
	public Map(int mapSize){
		this.size  = mapSize;
		
		char[][] b = new char[mapSize][mapSize];
		
		for(int i = 0; i < mapSize-1; i++){
			for(int j = 0; j < mapSize-1; j++){
				b[i][j] = ' ';
			}
		}
		
		this.board = b;
	}
	
	//Getters
	public char[][] getBoard(){
		return board;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setBoardCell(int lin, int col, char symbol){
		
		board[lin][col] = symbol;
	}
	
	//builds external map walls
	public void buildExtWalls(){
		
		// filling off the top and bottom lines
		for (int i = 0; i < this.size; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[board.length - 1][i] = 'X';
		}

		// filling right and left column
		for (int i = 0; i < size; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < size; i++) {
			board[i][board.length - 1] = 'X';
		}
	}
	
	public void buildMaze(){};
}
