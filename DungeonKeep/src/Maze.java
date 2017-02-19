import java.util.Scanner;

public class Maze { 

	private char[][] map1 = new char[10][10];
	private char[][] map2 = new char[10][10];

	int invalid = 1000;
	private int[] hero = { invalid, invalid }, guard = { invalid, invalid }, lever = { invalid, invalid },
			ogre = { invalid, invalid };
	
	private char[] guardMoves = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	private int currentGuardMove = 0;	
	
	private char[][][] mapList = { map1, map2 }; 
	private int currentMapIndex = 0;

	public void run() {

		boolean end = false;

		buildMap(1);

		while (!end) { 
			play();
			end = isGameOver();
		}
		
	}

	public void buildWalls(char[][] board) {

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

	public void changeCell(int line, int col, char cell, char[][] board) {
		board[line][col] = cell;
	}

	public void moveCharacter(int lin, int col, char character, char[][] board) {

		if (board[lin][col] == 'X' || board[lin][col] == 'I')
			return;

		switch (character) {

		case 'H':
			if (hero[0] != invalid || hero[1] != invalid) {
				changeCell(hero[0], hero[1], ' ', board);
			}

			changeCell(lin, col, 'H', board);
			hero[0] = lin;
			hero[1] = col;

			break;

		case 'G':
			if (guard[0] != invalid || guard[1] != invalid) {
				changeCell(guard[0], guard[1], ' ', board);
			}
			changeCell(lin, col, 'G', board);
			guard[0] = lin;
			guard[1] = col;
			break;

		case 'k':
			if (lever[0] != invalid || lever[1] != invalid) {
				changeCell(lever[0], lever[1], ' ', board);
			}
			changeCell(lin, col, 'k', board);
			lever[0] = lin;
			lever[1] = col;
			break;
		}

	}

	public void openDoors(){
	
		switch(currentMapIndex){
		
		case 0:
			changeCell(5,0,'S',mapList[currentMapIndex]);
			changeCell(6,0,'S',mapList[currentMapIndex]);
			break;
		
		}
		
	}

	public void buildMap(int mapNumber) {

		switch (mapNumber) {

		case 1:

			// Building external walls for map1
			buildWalls(map1);

			// Placing doors in map1
			changeCell(5, 0, 'I', map1);
			changeCell(6, 0, 'I', map1);
			changeCell(3, 2, 'I', map1);
			changeCell(3, 4, 'I', map1);
			changeCell(1, 4, 'I', map1);
			changeCell(8, 2, 'I', map1);
			changeCell(8, 4, 'I', map1);

			// Placing inner walls in map1
			changeCell(2, 1, 'X', map1);
			changeCell(2, 2, 'X', map1);
			changeCell(2, 4, 'X', map1);
			changeCell(2, 5, 'X', map1);
			changeCell(1, 6, 'X', map1);
			changeCell(2, 6, 'X', map1);
			changeCell(3, 6, 'X', map1);
			changeCell(4, 1, 'X', map1);
			changeCell(4, 2, 'X', map1);
			changeCell(4, 4, 'X', map1);
			changeCell(4, 5, 'X', map1);
			changeCell(4, 6, 'X', map1);
			changeCell(7, 1, 'X', map1);
			changeCell(7, 2, 'X', map1);
			changeCell(7, 4, 'X', map1);
			changeCell(7, 5, 'X', map1);
			changeCell(7, 6, 'X', map1);
			changeCell(7, 7, 'X', map1);
			changeCell(8, 6, 'X', map1);

			// placing lever
			moveCharacter(8, 7, 'k', map1);

			// set Hero initial location
			moveCharacter(1, 1, 'H', map1);

			// set guard initial location
			moveCharacter(1, 8, 'G', map1);

			break;

		case 2:
			break;
		}
	}

	public void printMaze(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}

	}

	public boolean isGameOver() {

		// check if guard is in the 3 above positions of the hero
		if (hero[0] - 1 == guard[0] && (hero[1] - 1 == guard[1] || hero[1] == guard[1] || hero[1] + 1 == guard[1])){
			printMaze(mapList[currentMapIndex]);
			System.out.println("GAME OVER! Better luck next time!");
			return true;
		}

		// check if guard is in the 3 below position of the hero
		if (hero[0] + 1 == guard[0] && (hero[1] - 1 == guard[1] || hero[1] == guard[1] || hero[1] + 1 == guard[1])){
			printMaze(mapList[currentMapIndex]);
			System.out.println("GAME OVER! Better luck next time!");
			return true;
		}

		// check if guard is in the 3 remaining positions
		if (hero[0] == guard[0] && (hero[1] - 1 == guard[1] || hero[1] + 1 == guard[1])){
			printMaze(mapList[currentMapIndex]);
			System.out.println("/nGAME OVER!/nBetter luck next time!");
			return true;
		}

		return false;
	}

	public boolean isLeverOverlapse() {

		if ((lever[0] == hero[0] && lever[1] == hero[1]) || (lever[0] == ogre[0] && lever[1] == ogre[1]))
			return true;

		return false;
	}

	public void play() {

		boolean leverHidden = isLeverOverlapse();

		printMaze(mapList[currentMapIndex]);

		// reading user move
		System.out.println("Enter your move:");
		Scanner scan = new Scanner(System.in);

		char move = scan.next().charAt(0);
		move = Character.toUpperCase(move);

		switch (move) {

		case 'W':
			moveCharacter(hero[0] - 1, hero[1], 'H', mapList[currentMapIndex]);
			break;

		case 'A':
			moveCharacter(hero[0], hero[1] - 1, 'H', mapList[currentMapIndex]);
			break;

		case 'S':
			moveCharacter(hero[0] + 1, hero[1], 'H', mapList[currentMapIndex]);
			break;

		case 'D':
			moveCharacter(hero[0], hero[1] + 1, 'H', mapList[currentMapIndex]);
			break;

		}
		
		//move guard OBS.: he moves regardless of hero "moving", e.g if hero hits a wall, guard moves
		
		
		switch (guardMoves[currentGuardMove]) {

		case 'W':
			moveCharacter(guard[0] - 1, guard[1], 'G', mapList[currentMapIndex]);
			break;

		case 'A':
			moveCharacter(guard[0], guard[1] - 1, 'G', mapList[currentMapIndex]);
			break;

		case 'S':
			moveCharacter(guard[0] + 1, guard[1], 'G', mapList[currentMapIndex]);
			break;

		case 'D':
			moveCharacter(guard[0], guard[1] + 1, 'G', mapList[currentMapIndex]);
			break;

		}
		
		if(currentGuardMove != guardMoves.length-1)
			currentGuardMove++;
		else
			currentGuardMove = 0;
		
		
		if(hero[0] == lever[0] && hero[1] == lever[1])
			openDoors();
			
		
		if (leverHidden)
			moveCharacter(lever[0], lever[1], 'k', mapList[currentMapIndex]);

	}
}
