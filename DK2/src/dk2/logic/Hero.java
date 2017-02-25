package dk2.logic;

public class Hero extends MovingChar{
	
	
	//-------------ATTRIBUTES-------------//
	
	private boolean hasKey;
	
	
	//--------------METHODS---------------//
	
	public Hero(){
		super();
		this.hasKey = false;
	}
	
	public boolean getHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
	
}
