package dk2.cli;

import java.util.Scanner;
import java.lang.Character;

import dk2.logic.*;

public class Main {
	
	public static void main(String[] args) {
		
		Game dungeon_keeper = new Game();
		
		//TODO: "under" variable atualizada conforme andam
		
		while(true){
			
			printMaze(dungeon_keeper);
			
			if(dungeon_keeper.hasHeroWon()){
				System.out.println("Good job you win!");
				return;
			}
			
			if(dungeon_keeper.isHeroDead()){
				System.out.println("Unlucky! Try again!");
				return;
			}
			
			dungeon_keeper.getMap().moveHero(readUserMove());
				
			
		}
	}
	
	
	public static char readUserMove(){
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your move:");
		
		char move = scan.next().charAt(0);
		move = Character.toUpperCase(move);
		
		//scan.close();
		
		return move;

	}
	
	public static void printMaze(Game g){
		
		Map m = g.getMap();
		char[][] board = m.getBoard();
		
		for(int i=0; i < m.getSize(); i++){
			for(int j=0; j < m.getSize(); j++){
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
}
