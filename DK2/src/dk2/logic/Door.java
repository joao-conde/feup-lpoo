package dk2.logic;

import java.io.Serializable;

public class Door extends StaticChar implements Serializable{
	
	private boolean open;
	
	public Door(){
		super();
		this.open = false;
		this.setSymbol('I');
	}

	public void openDoor(){
		this.open = true;
		this.setSymbol('S');
	}
	
	public boolean isOpen(){
		return this.getSymbol() == 'S';
	}
	
}