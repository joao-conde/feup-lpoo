package dk2.cli;

import dk2.logic.*;

public class Main {
	
	public static void main(String[] args) {
		
		Map1 m = new Map1(10); 
		
		m.buildMaze();
		char[][] board = m.getBoard();
		int size = m.getSize();
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.println(board[i][j]);
			}
		}
	}
	
}
