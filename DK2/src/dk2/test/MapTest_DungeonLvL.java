package dk2.test;

import java.util.Random;

import dk2.logic.Door;
import dk2.logic.Drunken;
import dk2.logic.Guard;
import dk2.logic.Lever;
import dk2.logic.Map;
import dk2.logic.Rookie;
import dk2.logic.Suspicious;

public class MapTest_DungeonLvL extends Map{
	
	private Door d1,d2;
	private Lever lever;
	private Guard guard;
	
	public MapTest_DungeonLvL(int size) {
		super(size);
		
		this.lever = new Lever();
			
		
		Random ranGen = new Random();
		
		switch(ranGen.nextInt(2)){
		
		case 0:
			this.guard = new Rookie();
			break;
			
		case 1:
			this.guard = new Drunken();
			break;
		
		case 2:
			this.guard = new Suspicious();
			break;
		}
		
				
		
		this.d1 = new Door();
		this.d2 = new Door();
				
		
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		
		// set Lever initial location 
		lever.setLin(3);
		lever.setCol(1);
		this.setBoardCell(lever.getLin(), lever.getCol(), lever.getSymbol());

		// set Hero initial location
		this.getHero().setLin(1);
		this.getHero().setCol(1);
		drawHero();

		// set Guard initial location
		guard.setLin(1);
		guard.setCol(3);
		this.setBoardCell(guard.getLin(), guard.getCol(), guard.getSymbol());

		// set Doors
		d1.setLin(2);
		d1.setCol(0);		
		d2.setLin(3);
		d2.setCol(0);
		

		// Placing doors in map1
		this.setDoors(d1);
		this.setDoors(d2);


	}
	
	public Lever getLever(){
		return this.lever;
	}
	
	public boolean isOnLever() {
		if (this.getHero().getLin() == lever.getLin() && this.getHero().getCol() == lever.getCol()) {
			lever.setActive(true);
			openDoors();
			return true;
		}
		return false;
	}
	
	public Door[] getDoor() {

		Door[] nextLvLDoors = { d1, d2 };

		return nextLvLDoors;
	}

	public boolean hasHeroWon() {

		for(Door d: getDoor()){
			
			if(getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() && d.isOpen()){
				return true;
			}
		}
		
		return false;

	}


	public void openDoors(){
		for (Door d: getDoor()){
			d.openDoor();
		}
	}
}
