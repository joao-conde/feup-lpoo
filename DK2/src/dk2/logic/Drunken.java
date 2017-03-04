package dk2.logic;


public class Drunken extends Guard{

	private boolean isASleep;
	
	public void sleep(){
		isASleep = true;
		this.setSymbol('g');
	}
}
