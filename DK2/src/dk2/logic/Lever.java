package dk2.logic;

import java.io.Serializable;

public class Lever extends StaticChar implements Serializable{
	
	private boolean active;
	
	
	
	public Lever(){
		super();
		this.active = false;
		this.setSymbol('k');
	}



	
	public boolean isActive() {
		return active;
	}



	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
