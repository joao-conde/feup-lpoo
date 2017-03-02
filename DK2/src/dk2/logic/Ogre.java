package dk2.logic;

import java.util.Random;

public class Ogre extends MovingChar {

	// -------------ATTRIBUTES-------------//

	private boolean onKey;
	private OgreClub club;

	// --------------METHODS---------------//

	public Ogre() {
		super();
		this.setSymbol('O');
		this.club = new OgreClub();
		
		this.onKey = false;
	}
	
	public OgreClub getClub(){
		return this.club;
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
