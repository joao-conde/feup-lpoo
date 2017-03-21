package dk2.logic;


public class Drunken extends Guard{

	private boolean isAsleep;
	
	public void sleep(){
		isAsleep = true;
		this.setSymbol('g');
	}
	
	public boolean isAsleep(){
		return isAsleep;
	}
	
	public void setAsleep(boolean asleep){
		isAsleep = true;
	}
}
