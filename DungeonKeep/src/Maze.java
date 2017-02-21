import java.util.Scanner;
import java.util.Random;

public class Maze {
	
	//class attributes
	private char[][] map1 = new char[10][10];
	private char[][] map2 = new char[9][9];

	int invalid = 1000;
	private int[] hero = { invalid, invalid }, guard = { invalid, invalid }, lever = { invalid, invalid },
			ogre = { invalid, invalid };

	private char[] guardMoves = { 'A', 'S', 'S', 'S', 'S', 'A', 'A', 'A', 'A', 'A', 'A', 'S', 'D', 'D', 'D', 'D', 'D',
			'D', 'D', 'W', 'W', 'W', 'W', 'W' };
	private int currentGuardMove = 0;

	private char[][][] mapList = { map1, map2 };
	private int currentMapIndex = 0;
	
	
	//class methods
	public void run() {

		boolean end = false;

		buildMap(1);

		while (!end) {
			if (play()) {
				currentMapIndex++;

				if (currentMapIndex == mapList.length) {
					System.out.println("\nGG! You beat the game!\n");
					return;
				}

				else
					buildMap(currentMapIndex + 1);
			}

			end = isGameOver();
		}

	}

	public void buildWalls(char[][] board) {

		// filling off the top and bottom lines
		for (int i = 0; i < board[0].length; i++) {
			board[0][i] = 'X';
		}

		for (int i = 0; i < board[board.length - 1].length; i++) {
			board[board.length - 1][i] = 'X';
		}

		// filling right and left collumn
		for (int i = 0; i < board.length; i++) {
			board[i][0] = 'X';
		}

		for (int i = 0; i < board.length; i++) {
			board[i][board.length - 1] = 'X';
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

		case 'O':
			if (ogre[0] != invalid || ogre[1] != invalid) {
				changeCell(ogre[0], ogre[1], ' ', board);
			}
			changeCell(lin, col, 'O', board);
			ogre[0] = lin;
			ogre[1] = col;
			break;
		}

	}

	public void openDoors() {

		switch (currentMapIndex) {

		case 0:
			changeCell(5, 0, 'S', mapList[currentMapIndex]);
			changeCell(6, 0, 'S', mapList[currentMapIndex]);
			break;

		case 1:
			changeCell(1, 0, 'S', mapList[currentMapIndex]);

		}

	}

	public void buildMap(int mapNumber) {

		hero[0] = hero[1] = invalid;
		guard[0] = guard[1] = invalid;
		ogre[0] = ogre[1] = invalid;
		lever[0] = lever[1] = invalid;

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

			// Building external walls for map2
			buildWalls(map2);

			// Placing doors in map2
			changeCell(1, 0, 'I', map2);

			// placing lever
			moveCharacter(1, 7, 'k', map2);

			// set Hero initial location
			moveCharacter(7, 1, 'H', map2);

			// set ogre initial location
			moveCharacter(1, 4, 'O', map2);
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

		// Guard game over
		// check if guard is in the 3 above positions of the hero
		if (hero[0] - 1 == guard[0] && (hero[1] - 1 == guard[1] || hero[1] == guard[1] || hero[1] + 1 == guard[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		// check if guard is in the 3 below position of the hero
		if (hero[0] + 1 == guard[0] && (hero[1] - 1 == guard[1] || hero[1] == guard[1] || hero[1] + 1 == guard[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		// check if guard is in the 3 remaining positions
		if (hero[0] == guard[0] && (hero[1] - 1 == guard[1] || hero[1] + 1 == guard[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		// Ogre game over
		// check if ogre is in the 3 above positions of the hero
		if (hero[0] - 1 == ogre[0] && (hero[1] - 1 == ogre[1] || hero[1] == ogre[1] || hero[1] + 1 == ogre[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		// check if guard is in the 3 below position of the hero
		if (hero[0] + 1 == ogre[0] && (hero[1] - 1 == ogre[1] || hero[1] == ogre[1] || hero[1] + 1 == ogre[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		// check if guard is in the 3 remaining positions
		if (hero[0] == ogre[0] && (hero[1] - 1 == ogre[1] || hero[1] + 1 == ogre[1])) {
			printMaze(mapList[currentMapIndex]);
			System.out.println("\nGAME OVER!\nBetter luck next time!\n");
			return true;
		}

		return false;
	}

	public boolean isLeverOverlapse() {

		if ((lever[0] == hero[0] && lever[1] == hero[1]) || (lever[0] == ogre[0] && lever[1] == ogre[1]))
			return true;

		return false;
	}

	public char readUserInput() {
		// reading user move
		System.out.println("Enter your move:");
		Scanner scan = new Scanner(System.in);

		char move = scan.next().charAt(0);
		move = Character.toUpperCase(move);

		return move;
	}

	public void randomOgreMove() {

		Random randomGenerator = new Random();

		switch (randomGenerator.nextInt(3)) {

		// up move
		case 0:
			moveCharacter(ogre[0] - 1, ogre[1], 'O', mapList[currentMapIndex]);
			break;

		// down move
		case 1:
			moveCharacter(ogre[0] + 1, ogre[1], 'O', mapList[currentMapIndex]);
			break;

		// left move
		case 2:
			moveCharacter(ogre[0], ogre[1] - 1, 'O', mapList[currentMapIndex]);
			break;

		// right move
		case 3:
			moveCharacter(ogre[0], ogre[1] + 1, 'O', mapList[currentMapIndex]);
			break;
		}

	}

	public boolean play() {

		boolean leverHidden = isLeverOverlapse();
		boolean advancelvl = false;

		printMaze(mapList[currentMapIndex]);

		char move = readUserInput();

		switch (move) {

		case 'W':
			if (mapList[currentMapIndex][hero[0] - 1][hero[1]] == 'S')
				advancelvl = true;
			moveCharacter(hero[0] - 1, hero[1], 'H', mapList[currentMapIndex]);
			break;

		case 'A':
			if (mapList[currentMapIndex][hero[0]][hero[1] - 1] == 'S')
				advancelvl = true;
			moveCharacter(hero[0], hero[1] - 1, 'H', mapList[currentMapIndex]);
			break;

		case 'S':
			if (mapList[currentMapIndex][hero[0] + 1][hero[1]] == 'S')
				advancelvl = true;
			moveCharacter(hero[0] + 1, hero[1], 'H', mapList[currentMapIndex]);
			break;

		case 'D':
			if (mapList[currentMapIndex][hero[0]][hero[1] + 1] == 'S')
				advancelvl = true;
			moveCharacter(hero[0], hero[1] + 1, 'H', mapList[currentMapIndex]);
			break;

		}

		// move guard OBS.: he moves regardless of hero "moving", e.g if hero
		// hits a wall, guard moves

		if (currentMapIndex == 0) {
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
		}

		if (currentGuardMove != guardMoves.length - 1)
			currentGuardMove++;
		else
			currentGuardMove = 0;

		if (hero[0] == lever[0] && hero[1] == lever[1])
			openDoors();

		if (leverHidden)
			moveCharacter(lever[0], lever[1], 'k', mapList[currentMapIndex]);

		if (currentMapIndex == 1)
			randomOgreMove();

		return advancelvl;
	}

}
