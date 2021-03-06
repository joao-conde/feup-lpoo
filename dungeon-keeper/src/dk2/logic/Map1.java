package dk2.logic;

import java.io.Serializable;
import java.util.Random;

public class Map1 extends Map implements Serializable {

	private Door d1, d2, d3, d4, d5, d6, d7;
	private Lever lever;
	private Guard guard;

	public Map1(int size, Guard pers) {
		super(size);

		this.lever = new Lever();

		this.guard = pers;

		this.d1 = new Door();
		this.d2 = new Door();
		this.d3 = new Door();
		this.d4 = new Door();
		this.d5 = new Door();
		this.d6 = new Door();
		this.d7 = new Door();

	}

	public Lever getLever() {
		return this.lever;
	}

	// returns the level winning doors
	public Door[] getDoor() {

		Door[] nextLvLDoors = { d1, d2 };

		return nextLvLDoors;
	}

	public void moveRookie() {
		
		char dir;
		dir = this.guard.getNextMove(false);
		this.guard.move(this.getBoard(), dir);
		
	}
	
	public void moveDrunken(){
		char dir;
		
		Random nowSleep = new Random();
		if (nowSleep.nextBoolean()) {
			((Drunken) this.guard).sleep();
		} else {
			dir = this.guard.getNextMove(false);
			this.guard.setSymbol('G');
			((Drunken) this.guard).setAsleep(false);
			this.guard.move(this.getBoard(), dir);
		}
	}
	
	public void moveSuspicious(){
		char dir;
		
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

	public void moveGuard() {
		
		if (guard instanceof Rookie) moveRookie();
		
		if (guard instanceof Drunken) moveDrunken();

		if (guard instanceof Suspicious) moveSuspicious();
		
	}

	public void openDoors() {
		for (Door d : getDoor()) {
			d.openDoor();
		}
	}

	public boolean hasHeroWon() {

		for (Door d : getDoor()) {

			if (getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() && d.isOpen()) {
				return true;
			}
		}

		return false;

	}

	public void buildInnerWalls(){
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
	}
	
	public void placeLever(){
		
		lever.setLin(8);
		lever.setCol(7);
		this.setBoardCell(lever.getLin(), lever.getCol(), lever.getSymbol());
	}
	
	public void placeHero(){
		
		this.getHero().setLin(1);
		this.getHero().setCol(1);
		
	}
	
	public void placeGuard(){
		
		guard.setLin(1);
		guard.setCol(8);
		this.setBoardCell(guard.getLin(), guard.getCol(), guard.getSymbol());
		
	}
	
	public void placeDoors(){
		
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

		
	}
	
	public void setDoors(){
		this.setDoors(d1);
		this.setDoors(d2);
		this.setDoors(d3);
		this.setDoors(d4);
		this.setDoors(d5);
		this.setDoors(d6);
		this.setDoors(d7);
		
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		this.buildInnerWalls();
		
		this.placeLever();
		
		this.placeHero();
		
		this.placeGuard();
		
		this.placeDoors();
		
		this.setDoors();
		
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
		for (Door d : getDoor()) {
			this.setBoardCell(d.getLin(), d.getCol(), d.getSymbol());
		}
		this.setBoardCell(this.getHero().getLin(), this.getHero().getCol(), this.getHero().getSymbol());

	}

	public void advanceTurn() {

		isOnLever();
		moveGuard();

	}

	@Override
	public Door[] getAllDoors() {

		Door[] doors = { d1, d2, d3, d4, d5, d6, d7 };
		return doors;
	}

	public Guard getGuard() {
		return this.guard;
	}

}
