package dk2.logic;

public class Map {
	private char[][] board;
	private int size;
	
	public Map(int mapSize){
		this.size  = mapSize;
		for(int i = 0; i < mapSize; i++){
			for(int j = 0; j < mapSize; j++){
				this.board[i][j] = ' ';
			}
		}
	}
	
	public char[][] getBoard(){
		return board;
	}
	public void buildExtWalls(){
		// filling off the top and bottom lines
		for (int i = 0; i < board[0].length; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < board[board.length - 1].length; i++) {
			board[board.length - 1][i] = 'X';
		}

		// filling right and left column
		for (int i = 0; i < board.length; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < board.length; i++) {
			board[i][board.length - 1] = 'X';
		}
	}
	
	
	
	
}
