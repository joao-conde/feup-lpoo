package dk2.logic;

import java.util.Random;

public class Map1 extends Map {

	//-------------ATTRIBUTES-------------//

	Door d1, d2, d3, d4, d5, d6, d7;
	Lever lever;
	Guard guard;

	//--------------METHODS---------------//

	// Map1 constructor calling Map constructor
	public Map1(int size) {
		super(size);
		
		this.lever = new Lever();
						
		this.mieic_student = new Hero();
		
		
		Random ranGen = new Random();
		
		switch(0/*ranGen.nextInt(3)*/){
		
		case 0:
			this.guard = new Rookie();
			break;
		/*	
		case 1:
			this.guard = new Drunken();
			break;
			
		case 2:
			this.guard = new Suspicious();
			break;*/
		}
		
				
		
		this.d1 = new Door();
		this.d2 = new Door();
		this.d3 = new Door();
		this.d4 = new Door();
		this.d5 = new Door();
		this.d6 = new Door();
		this.d7 = new Door();
		
		
	}

	public Lever getLever(){
		return this.lever;
	}
	
	//returns the level winning door
	public Door[] getDoor(){
		
		
		Door[] nextLvLDoors = {d1,d2};
		
		return nextLvLDoors;
	}
	
	public void moveGuard(){
		
		//this.setBoardCell(this.guard.getLin(), this.guard.getCol(), ' ');
		
		if(guard instanceof Rookie)
			((Rookie)this.guard).move(this.getBoard());
		
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		// Placing inner walls
		this.setBoardCell(2, 2, 'X');
		this.setBoardCell(2, 1, 'X');
		this.setBoardCell(2, 4, 'X');
		this.setBoardCell(2, 5, 'X');
		this.setBoardCell(1, 6, 'X');
		this.setBoardCell(2, 6, 'X');
		this.setBoardCell(3, 6, 'X');
		this.setBoardCell(4, 1, 'X');
		this.setBoardCell(4, 2, 'X');
		this.setBoardCell(4, 4, 'X');
		this.setBoardCell(4, 5, 'X');
		this.setBoardCell(4, 6, 'X');
		this.setBoardCell(7, 1, 'X');
		this.setBoardCell(7, 2, 'X');
		this.setBoardCell(7, 4, 'X');
		this.setBoardCell(7, 5, 'X');
		this.setBoardCell(7, 6, 'X');
		this.setBoardCell(7, 7, 'X');
		this.setBoardCell(8, 6, 'X');
		
		// placing key elements//

		// set Lever initial location 
		lever.setLin(8);
		lever.setCol(7);
		this.setBoardCell(lever.getLin(), lever.getCol(), lever.getSymbol());

		// set Hero initial location
		mieic_student.setLin(1);
		mieic_student.setCol(1);
		drawHero();

		// set Guard initial location
		guard.setLin(1);
		guard.setCol(8);
		this.setBoardCell(guard.getLin(), guard.getCol(), guard.getSymbol());

		// set Doors
		d1.setLin(5);
		d1.setCol(0);		
		d2.setLin(6);
		d2.setCol(0);
		d3.setLin(3);
		d3.setCol(2);
		d4.setLin(3);
		d4.setCol(4);
		d5.setLin(1);
		d5.setCol(4);
		d6.setLin(8);
		d6.setCol(2);
		d7.setLin(8);
		d7.setCol(4);

		// Placing doors in map1
		this.setDoors(d1);
		this.setDoors(d2);
		this.setDoors(d3);
		this.setDoors(d4);
		this.setDoors(d5);
		this.setDoors(d6);
		this.setDoors(d7);



	}
	
	public boolean isOnLever(){
		if (mieic_student.getLin() == lever.getLin() && mieic_student.getCol() == lever.getCol()){
			lever.setActive(true);
			return true;
		}
		return false;
	}

}
