package dk2.logic;

public class Suspicious extends Guard {
	private boolean heardSomething;
	
	public void turnBack(){
		this.heardSomething = true;
	}
	public boolean getInfo(){
		return heardSomething;
	}
}
