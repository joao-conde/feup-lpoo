package dk2.logic;

import java.io.Serializable;

public class Suspicious extends Guard implements Serializable{
	
	private boolean heardSomething;
	
	
	public void turnBack(){
		this.heardSomething = true;
	}
	
	public boolean getInfo(){
		return heardSomething;
	}
}
