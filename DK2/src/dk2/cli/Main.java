package dk2.cli;

import java.util.Scanner;
import java.lang.Character;

import dk2.logic.*;

public class Main {
	
	public static void main(int n, String p) {
		
		
		Game dungeon_keeper = new Game(n, p);
				
		while(true){
			
			//dungeon_keeper.getMap().placeChars();
			
			printMaze(dungeon_keeper);
			
			if(dungeon_keeper.advance()){
				System.out.println("Good job you win!");
				return;
			}
			
			if(dungeon_keeper.isHeroDead()){
				System.out.println("Unlucky! Try again!");
				return;
			}
			
			dungeon_keeper.getMap().moveHero(readUserMove());
			
			dungeon_keeper.getMap().advanceTurn();
			
			
		}
	} 
	
	public static boolean gameIsOver(Game g){
		if(g.advance()){
			System.out.println("Good job you win!");
			return true;
		}
		
		if(g.isHeroDead()){
			System.out.println("Unlucky! Try again!");
			return true;
		}
		return false;
	}

	public static char readUserMove(){
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your move:");
		
		char move = scan.next().charAt(0);
		move = Character.toUpperCase(move); 
		
		//scan.close();
		
		return move;

	}
	
	public static String printMaze(Game g){
		g.getMap().placeChars();
		String res = "";
		Map m = g.getMap();
		char[][] board = m.getBoard();
		
		for(int i=0; i < m.getSize(); i++){
			for(int j=0; j < m.getSize(); j++){
				res += /*System.out.print(*/board[i][j];
				res += /*System.out.print(*/" ";
			}
			//System.out.println();
			res += "\n";
		}
		System.out.print(res);
		return res;
		
	}
}
