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

	public void moveNW() {
		this.moveUp();
		this.moveLeft();
	}

	public void moveNE() {
		this.moveUp();
		this.moveRight();
	}

	public void moveSW() {
		this.moveDown();
		this.moveLeft();
	}

	public void moveSE() {
		this.moveDown();
		this.moveRight();
	}

	// moves both the ogre and his massive club
	public void randomMove() {

		Random randomGen = new Random();

		// moving the ogre randomly
		switch (randomGen.nextInt(8)) {

		case 0:
			this.moveUp();
			break;

		case 1:
			this.moveNE();
			break;

		case 2:
			this.moveRight();
			break;

		case 3:
			this.moveSE();
			break;

		case 4:
			this.moveDown();
			break;

		case 5:
			this.moveSW();
			break;

		case 6:
			this.moveLeft();
			break;

		case 7:
			this.moveNW();
			break;
			
		}

		// moving ogre's club randomly AROUND him
		switch (randomGen.nextInt(8)) {
		
		case 0:
			this.club.moveTo(this.getLin()-1, this.getCol());
			break;
		
		case 1:
			this.club.moveTo(this.getLin()-1, this.getCol()+1);
			break;
			
		case 2:
			this.club.moveTo(this.getLin(), this.getCol()+1);
			break;
			
		case 3:
			this.club.moveTo(this.getLin()+1, this.getCol()+1);
			break;
			
		case 4:
			this.club.moveTo(this.getLin()+1, this.getCol());
			break;
			
		case 5:
			this.club.moveTo(this.getLin()+1, this.getCol()-1);
			break;
			
		case 6:
			this.club.moveTo(this.getLin(), this.getCol()-1);
			break;
			
		case 7:
			this.club.moveTo(this.getLin()-1, this.getCol()-1);
			break;
			
		}
	}

}
