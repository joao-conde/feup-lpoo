package dk2.logic;

public class Lever extends StaticChar{
	
	private boolean active;
	
	
	
	public Lever(){
		super();
		this.active = false;
	}



	
	public boolean isActive() {
		return active;
	}



	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
