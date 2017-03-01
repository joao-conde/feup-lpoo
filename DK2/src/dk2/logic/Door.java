package dk2.logic;

public class Door extends StaticChar{
	
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
		return this.open;
	}
	
}