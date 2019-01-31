package dk2.logic;

import java.io.Serializable;

public class Drunken extends Guard implements Serializable{

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
