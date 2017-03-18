package dk2.logic;

import java.util.Random;

import dk2.test.MapTest_DungeonLvL;

public class Map1 extends Map {

	// -------------ATTRIBUTES-------------//

	private Door d1, d2, d3, d4, d5, d6, d7;
	private Lever lever;
	private Guard guard;

	// --------------METHODS---------------//

	// Map1 constructor calling Map constructor
	public Map1(int size) {
		super(size);

		this.lever = new Lever();

		Random ranGen = new Random();

		switch (ranGen.nextInt(2)) {

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
		this.d3 = new Door();
		this.d4 = new Door();
		this.d5 = new Door();
		this.d6 = new Door();
		this.d7 = new Door();

	}

	/*public Lever getLever() {
		return this.lever;
	}
    */
	// returns the level winning door
	public Door[] getDoor() {

		Door[] nextLvLDoors = { d1, d2 };

		return nextLvLDoors;
	}

	public void moveGuard() {
		char dir;
		
		if (guard instanceof Rookie) {
			dir = this.guard.getNextMove(false);
			this.guard.move(this.getBoard(), dir);
		}

		if (guard instanceof Drunken) {

			Random nowSleep = new Random();
			if (nowSleep.nextBoolean()) {
				((Drunken) this.guard).sleep();
			} else {
				dir = this.guard.getNextMove(false);
				this.guard.setSymbol('G');
				this.guard.move(this.getBoard(), dir);
			}

		}

		if (guard instanceof Suspicious) {
			Random changeDir = new Random();
			switch (changeDir.nextInt(2)) {
			case 0:
				dir = this.guard.getNextMove(false);
				if (!canMove(dir, this.guard))
					return;
				this.guard.move(this.getBoard(), dir);
				break;
			case 1:
				dir = this.guard.getNextMove(true);
				if (!canMove(dir, this.guard))
					return;

				((Suspicious) this.guard).turnBack();
				this.guard.move(this.getBoard(), dir);
				break;

			}
		}

	}

	public void openDoors(){
		for (Door d: getDoor()){
			d.openDoor();
		}
	}

	public boolean hasHeroWon() {

		for(Door d: getDoor()){
			
			if(getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() && d.isOpen()){
				return true;
			}
		}
		
		return false;

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
		this.getHero().setLin(1);
		this.getHero().setCol(1);
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

	public boolean isOnLever() {
		if (this.getHero().getLin() == lever.getLin() && this.getHero().getCol() == lever.getCol()) {
			lever.setActive(true);
			openDoors();
			return true;
		}
		return false;
	}

	public void placeChars() {

		this.setBoardCell(this.guard.getLin(), this.guard.getCol(), this.guard.getSymbol());
		this.setBoardCell(this.lever.getLin(), this.lever.getCol(), this.lever.getSymbol());
		for(Door d: getDoor()){
			this.setBoardCell(d.getLin(),d.getCol(),d.getSymbol());
		}
		this.setBoardCell(this.getHero().getLin(), this.getHero().getCol(), this.getHero().getSymbol());
		
		

	}

	public void advanceTurn() {
		
		isOnLever();
		moveGuard();

	}
}
