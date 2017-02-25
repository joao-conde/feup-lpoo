package dk2.logic;

public class Guard extends MovingChar {
	
	
	//-------------ATTRIBUTES-------------//
	
	private char[] patrolPath;
	private int currentStep;
		
		
	//--------------METHODS---------------//

	public Guard(){
		super();
		this.currentStep = 0;
	}

}
