package dk2.logic;

import java.io.Serializable;

public abstract class Guard extends MovingChar implements Serializable{
	
	

	private char[] patrolPath = { 'A', 'S', 'S', 'S', 'S', 'A', 'A', 'A', 'A', 'A', 'A', 'S', 'D', 'D', 'D', 'D', 'D',
			'D', 'D', 'W', 'W', 'W', 'W', 'W' };
	
	private int currentStep;
		
		

	public Guard(){
		super();
		this.setSymbol('G');
	}

	public char getNextMove(boolean info){
		
		char c;
		
		if (info == false){
			c = patrolPath[currentStep];
			if (currentStep == patrolPath.length - 1)
				currentStep = 0;
			else
				this.currentStep++;
		}
		else{
			c = patrolPath[currentStep];
			switch(c){
			case 'A':
				c='D';
				break;
			case 'S':
				c = 'W';
				break;
			case 'W':
				c = 'S';
				break;
			case 'D':
				c = 'A';
				break;
			}
			if (currentStep == 0)
				currentStep++;
			else
				this.currentStep--;
			
		}
		
		return c;
	}
	
	public void move(char[][] b, char dir) {
		switch (dir){
		case 'W':
			this.moveUp(b);
			break;
		case 'S':
			this.moveDown(b);
			break;
		case 'A':
			this.moveLeft(b);
			break;
		case 'D':
			this.moveRight(b);
			break;	
		}
	}

}
