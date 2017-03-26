package dk2.test;

import dk2.logic.*;

public class MapTest_KeeperLvL extends Map {

	private Door d1;
	private Key key;
	private Ogre[] ogres;

	public MapTest_KeeperLvL(int size) {

		super(size);

		this.key = new Key();

		Ogre o = new Ogre();
		this.ogres = new Ogre[1];
		this.ogres[0] = o;

		this.d1 = new Door();

	}

	public Door[] getDoor() {

		Door[] nextLvLDoors = { d1 };

		return nextLvLDoors;
	}

	public void buildMaze() {

		this.buildExtWalls();

		
		key.setLin(3);
		key.setCol(2);
		this.setBoardCell(key.getLin(), key.getCol(), key.getSymbol());

		
		this.getHero().setLin(2);
		this.getHero().setCol(2);
		drawHero();

		
		ogres[0].setLin(2);
		ogres[0].setCol(4);
		this.setBoardCell(ogres[0].getLin(), ogres[0].getCol(), ogres[0].getSymbol());

	
		ogres[0].getClub().setLin(2);
		ogres[0].getClub().setCol(5);
		this.setBoardCell(ogres[0].getClub().getLin(), ogres[0].getClub().getCol(), ogres[0].getClub().getSymbol());

		
		d1.setLin(2);
		d1.setCol(0);

		
		this.setDoors(d1);

	}

	public void heroReachedKey() {

		if (getHero().getLin() == getKey().getLin() && getHero().getCol() == getKey().getCol()) {

			getHero().setHasKey(true);
			getHero().setSymbol('K');
			getKey().setSymbol(' ');

			setBoardCell(getKey().getLin(), getKey().getCol(), 'K');
		}

	}

	public Key getKey() {
		return this.key;
	}

	public Ogre getOgre() {
		return this.ogres[0];
	}

	public void stunOgres() {

		Ogre o = getOgre();
			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'A')
				ogres[0].setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'A')
				ogres[0].setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'A')
				ogres[0].setStunned(true);

			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'K')
				ogres[0].setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'K')
				ogres[0].setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'K')
				ogres[0].setStunned(true);

		
	}

	public void openDoors() {

		for (Door d : getDoor()) {
			if (getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() + 1 && getHero().getHasKey()) {
				d.openDoor();
			}
		}

	}

	public boolean hasHeroWon() {

		for (Door d : getDoor()) {

			if (getHero().getLin() == d.getLin() && getHero().getCol() == d.getCol() && d.isOpen()
					&& getHero().getHasKey()) {
				return true;
			}
		}

		return false;

	}

	public void advanceTurn() {

		int n = 0;
		while (n < ogres.length) {
			moveOgre(n);
			n++;
		}

		stunOgres();

	}

	public void moveOgre(int index) {
		if (ogres[index].getStunned()) {
			ogres[index].increaseTurns();
			return;
		}
		char direction = ogres[index].calculateRandomMove();
		boolean re_calculate = true;

		if (!canMove(direction, ogres[index]))
			return;

		switch (direction) {
		case 'W':
			ogres[index].moveUp(this.getBoard());
			break;

		case 'E':
			ogres[index].moveNE(this.getBoard());
			break;

		case 'D':
			ogres[index].moveRight(this.getBoard());
			break;

		case 'C':
			ogres[index].moveSE(this.getBoard());
			break;

		case 'S':
			ogres[index].moveDown(this.getBoard());
			break;

		case 'Z':
			ogres[index].moveSW(this.getBoard());
			break;

		case 'A':
			ogres[index].moveLeft(this.getBoard());
			break;

		case 'Q':
			ogres[index].moveNW(this.getBoard());
			break;
		}

		this.getBoard()[ogres[index].getClub().getLin()][ogres[index].getClub().getCol()] = ' ';
		ogres[index].getClub().setLin(ogres[index].getLin());
		ogres[index].getClub().setCol(ogres[index].getCol());

		while (re_calculate) {

			switch (this.ogres[index].getClub().calculateRandomMove()) {

			case 'W':
				if (canMove('W', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveUp(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'E':
				if (canMove('E', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveNE(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'D':
				if (canMove('D', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveRight(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'C':
				if (canMove('C', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveSE(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'S':
				if (canMove('S', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveDown(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'Z':
				if (canMove('Z', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveSW(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'A':
				if (canMove('A', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveLeft(this.getBoard());
					re_calculate = false;
				}
				break;

			case 'Q':
				if (canMove('Q', this.ogres[index].getClub())) {
					this.ogres[index].getClub().moveNW(this.getBoard());
					re_calculate = false;
				}
				break;
			}

		}

	}

	public void placeChars() {
		if (!this.getHero().getHasKey())
			this.setBoardCell(this.key.getLin(), this.key.getCol(), this.key.getSymbol());
		for (Ogre o : ogres) {
			this.setBoardCell(o.getLin(), o.getCol(), o.getSymbol());
			if (!o.getStunned())
				this.setBoardCell(o.getClub().getLin(), o.getClub().getCol(), o.getClub().getSymbol());

			if (o.getLin() == key.getLin() && o.getCol() == key.getCol() && !this.getHero().getHasKey()) {
				this.setBoardCell(o.getLin(), o.getCol(), '$');
			}

			if (o.getClub().getLin() == key.getLin() && o.getClub().getCol() == key.getCol()
					&& !this.getHero().getHasKey()) {
				this.setBoardCell(o.getClub().getLin(), o.getClub().getCol(), '$');
			}
		}
	
 
		this.setBoardCell(d1.getLin(), d1.getCol(), d1.getSymbol());
		this.setBoardCell(getHero().getLin(), getHero().getCol(), getHero().getSymbol());
		
		this.setBoardCell(this.getHero().getLin(), this.getHero().getCol(), this.getHero().getSymbol());

	}
	
	@Override
	public Door[] getAllDoors() {
		Door[] doors = {d1};
		return doors;
	}
	

}
