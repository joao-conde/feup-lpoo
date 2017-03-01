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

	public void moveNW(char[][] b) {
		this.moveUp(b);
		this.moveLeft(b);
	}

	public void moveNE(char[][] b) {
		this.moveUp(b);
		this.moveRight(b);
	}

	public void moveSW(char[][] b) {
		this.moveDown(b);
		this.moveLeft(b);
	}

	public void moveSE(char[][] b) {
		this.moveDown(b);
		this.moveRight(b);
	}

	// moves both the ogre and his massive club
	public void randomMove(char[][] b) {

		Random randomGen = new Random();

		// moving the ogre randomly
		switch (randomGen.nextInt(8)) {

		case 0:
			this.moveUp(b);
			break;

		case 1:
			this.moveNE(b);
			break;

		case 2:
			this.moveRight(b);
			break;

		case 3:
			this.moveSE(b);
			break;

		case 4:
			this.moveDown(b);
			break;

		case 5:
			this.moveSW(b);
			break;

		case 6:
			this.moveLeft(b);
			break;

		case 7:
			this.moveNW(b);
			break;

		}

		// moving ogre's club randomly AROUND him
		switch (randomGen.nextInt(8)) {

		case 0:
			this.club.moveTo(this.getLin() - 1, this.getCol(), b);
			break;

		case 1:
			this.club.moveTo(this.getLin() - 1, this.getCol() + 1, b);
			break;

		case 2:
			this.club.moveTo(this.getLin(), this.getCol() + 1, b);
			break;

		case 3:
			this.club.moveTo(this.getLin() + 1, this.getCol() + 1, b);
			break;

		case 4:
			this.club.moveTo(this.getLin() + 1, this.getCol(), b);
			break;

		case 5:
			this.club.moveTo(this.getLin() + 1, this.getCol() - 1, b);
			break;

		case 6:
			this.club.moveTo(this.getLin(), this.getCol() - 1, b);
			break;

		case 7:
			this.club.moveTo(this.getLin() - 1, this.getCol() - 1, b);
			break;

		}
	}

}
