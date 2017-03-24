package dk2.logic;

import java.io.Serializable;
import java.util.Random;

public class Ogre extends MovingChar implements Serializable{

	

	private boolean stunned;
	private OgreClub club;
	private int turnsStunned;

	

	public Ogre() {
		super();
		this.setSymbol('O');
		this.club = new OgreClub();
		
		this.stunned = false;
		this.turnsStunned = 0;
	}
	
	public OgreClub getClub(){
		return this.club;
	}


	public void increaseTurns(){
		this.turnsStunned++;
		if (this.turnsStunned == 2){
			this.turnsStunned = 0;
			this.setSymbol('O');
			this.stunned = false;
		}
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
	
	public void setStunned(boolean stun){
		this.stunned = stun;
		this.setSymbol('8');
	}
	
	public boolean getStunned(){
		return stunned;
	}
	

}
