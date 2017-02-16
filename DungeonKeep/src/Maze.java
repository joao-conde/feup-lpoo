import java.util.Scanner;

public class Maze {

	private char[][] board = new char[10][10];
	int[] hero = { 1, 1 };
	int[] guard = { 1, 8 };

	public static void main(String[] args) {
		Maze m = new Maze();
		m.run();
	}

	public void run() {

		// Building external walls
		buildWalls();

		// Placing doors
		changeCell(5, 0, 'I');
		changeCell(6, 0, 'I');
		changeCell(3, 2, 'I');
		changeCell(3, 4, 'I');
		changeCell(1, 4, 'I');
		changeCell(8, 2, 'I');
		changeCell(8, 4, 'I');

		// Placing inner walls
		changeCell(2, 1, 'X');
		changeCell(2, 2, 'X');
		changeCell(2, 4, 'X');
		changeCell(2, 5, 'X');
		changeCell(1, 6, 'X');
		changeCell(2, 6, 'X');
		changeCell(3, 6, 'X');
		changeCell(4, 1, 'X');
		changeCell(4, 2, 'X');
		changeCell(4, 4, 'X');
		changeCell(4, 5, 'X');
		changeCell(4, 6, 'X');
		changeCell(7, 1, 'X');
		changeCell(7, 2, 'X');
		changeCell(7, 4, 'X');
		changeCell(7, 5, 'X');
		changeCell(7, 6, 'X');
		changeCell(7, 7, 'X');
		changeCell(8, 6, 'X');

		// placing key
		changeCell(8, 7, 'k');

		// set Hero initial location
		setCharacter(1, 1, 'H');

		// set guard initial location
		setCharacter(1, 8, 'G');

		while (true) {
			printMaze();
		}
	}

	public void buildWalls() {

		// filling off the top and bottom lines
		for (int i = 0; i < board[0].length; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < board[9].length; i++) {
			board[9][i] = 'X';
		}

		// filling right and left collumn
		for (int i = 0; i < board.length; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < board.length; i++) {
			board[i][9] = 'X';
		}

	}

	public void changeCell(int line, int col, char cell) {
		board[line][col] = cell;
	}

	public void setCharacter(int lin, int col, char character) {

		if (board[lin][col] == 'X')
			return;

		switch (character) {

		case 'H':
			changeCell(lin, col, 'H');
			hero[0] = lin;
			hero[1] = col;
			break;

		case 'G':
			changeCell(lin, col, 'G');
			guard[0] = lin;
			guard[1] = col;
			break;
		}

	}

	public void printMaze() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}
	}
	
	public boolean play(){
		
		String move;
		
		printMaze();
		
		move = System.console().readLine();
		
		switch(move){
		
		case: 
		
		}
		
		
		
	}
}
