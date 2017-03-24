package dk2.logic;

import java.io.Serializable;
import java.util.Random;

public class OgreClub extends MovingChar implements Serializable{
			
	

	public OgreClub(){
		super();
		this.setSymbol('*');
	}


	
	public char calculateRandomMove() {

		
		Random randomGen = new Random();

		
		switch (randomGen.nextInt(8)) {

		case 0:
			return 'W';

		case 1:
			return 'E';

		case 2:
			return 'D';

		case 3:
			return 'C';

		case 4:
			return 'S';

		case 5:
			return 'Z';

		case 6:
			return 'A';

		case 7:
			return 'Q';
			
		default:
			return 'F';
		}

		
	}

}
