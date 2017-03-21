package dk2.cli;

import java.util.Scanner;
import java.lang.Character;

import dk2.logic.*;

public class Main {
	
	public static void main(String[] args) {
		
		int nOgres, guard;
		Game dungeon_keeper = new Game();
		guard = readGuard();
		nOgres = readOgres();
		
		switch(guard){
		case 1:
			dungeon_keeper.buildMaps("Novice", nOgres);
			break;
		case 2:
			dungeon_keeper.buildMaps("Drunken", nOgres);
			break;
		case 3:
			dungeon_keeper.buildMaps("Suspicious", nOgres);
			break;
		}
				
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
	public static int readGuard(){
		String op;
		int res;
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("Type of guard? \n 1: Rookie\n 2: Drunken \n 3: Suspicious");
			op = scan.next();
			res = Integer.parseInt(op);
			if (res < 1 || res > 3)
				System.out.println("Invalid Option.");
			else
				break;

		}
		return res;
	}
	
	public static int readOgres(){
		Scanner scan = new Scanner(System.in);
		String op;
		int res;
		while(true){
			System.out.println("Number of ogres (1-5):");
			op = scan.next();
			res = Integer.parseInt(op);
			if (res < 1 || res > 5)
				System.out.println("Invalid Number");
			else
				break;
		}
		return res;
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
				/*res +=*/ System.out.print(board[i][j]);
				/*res += */System.out.print(" ");
			}
			System.out.println();
			//res += "\n";
		}
		//System.out.print(res);
		return res;
		
	}
}
