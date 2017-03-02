package dk2.logic;

public abstract class Guard extends MovingChar {
	
	
	//-------------ATTRIBUTES-------------//
	
	private char[] patrolPath = { 'A', 'S', 'S', 'S', 'S', 'A', 'A', 'A', 'A', 'A', 'A', 'S', 'D', 'D', 'D', 'D', 'D',
			'D', 'D', 'W', 'W', 'W', 'W', 'W' };
	
	private int currentStep;
		
		
	//--------------METHODS---------------//

	public Guard(){
		super();
		this.currentStep = 0;
		this.setSymbol('G');
	}

	public char getNextMove(){

		char c = patrolPath[currentStep];
		this.currentStep++;
		
		return c;
	}
	
	public void move(){};

}
