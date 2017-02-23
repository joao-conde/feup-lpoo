package dk2.cli;
import dk2.logic.*;

public class main {
	public static void main(String[] args) {
		Map m1 = new Map(5); 
		m1.buildExtWalls();
		printMap(m1);
	}
	
	public static void printMap(Map m){
		char[][] b1 = m.getBoard();
		for (int i = 0; i < b1.length; i++){
			for(int j = 0 ; j < b1[i].length; j++){
				System.out.println(b1[i][j]);
			}
		}
	}
}
