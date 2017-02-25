package dk2.logic;

import java.util.Random;

public class OgreClub extends MovingChar{
	
	//-------------ATTRIBUTES-------------//
	
	private boolean onKey;
		
		
	//--------------METHODS---------------//

	public OgreClub(){
		super();
		this.onKey = false;
	}



	public boolean isOnKey() {
		return onKey;
	}


	
	public void setOnKey(boolean onKey) {
		this.onKey = onKey;
	}

}
