package dk2.logic;

public class Hero extends MovingChar{
	
	

	private boolean hasKey;
	private Lever l;
	
	
	public Hero(){
		super();
		this.hasKey = false;
		this.setSymbol('H');
	}
	
	public boolean getHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
		this.setSymbol('K');
	}
	
	
	
	
	
	
}
