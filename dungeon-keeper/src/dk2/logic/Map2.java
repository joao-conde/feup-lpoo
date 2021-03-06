package dk2.logic;

import java.io.Serializable;

public class Map2 extends Map implements Serializable {

	private Door[] d1;
	private Key key;
	private Ogre[] ogres;

	public Map2(int size, int nOgres, int nDoors) {
		super(size);
		d1 = new Door[nDoors];

		this.key = new Key();

		for (int i = 0; i < nDoors; i++) {
			Door d = new Door();
			d1[i] = d;
		}

		this.ogres = new Ogre[nOgres];
		for (int i = 0; i < nOgres; i++) {
			Ogre o = new Ogre();
			ogres[i] = o;
		}
		this.getHero().setSymbol('A');
	}

	public Key getKey() {
		return this.key;
	}

	public void setOgresSize(int n) {
		this.ogres = new Ogre[n];
	}

	public void setDoorsSize(int n) {
		this.d1 = new Door[n];
	}

	public Door[] getDoor() {

		return d1;
	}

	public Ogre[] getOgres() {
		return this.ogres;
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

	public void heroReachedKey() {
		if (getHero().getLin() == key.getLin() && getHero().getCol() == key.getCol()) {

			getHero().setHasKey(true);
			getHero().setSymbol('K');
			key.setSymbol(' ');

		}

	}

	public void openDoors() {

		for (int i = 0; i < d1.length; i++) {
			if (getHero().getLin() == d1[i].getLin() && (getHero().getCol() == d1[i].getCol() + 1)
					&& getHero().getHasKey()) {
				d1[i].openDoor();
			}
		}

	}

	public boolean hasHeroWon() {

		for (int i = 0; i < d1.length; i++) {
			if (getHero().getLin() == d1[i].getLin() && getHero().getCol() == d1[i].getCol() && d1[i].isOpen()
					&& getHero().getHasKey()) {
				return true;
			}
		}

		return false;

	}

	public void heroNoKey() {
		for (Ogre o : ogres) {
			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'A')
				o.setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'A')
				o.setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'A'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'A')
				o.setStunned(true);
		}
	}

	public void heroWithKey() {
		for (Ogre o : ogres) {
			if (this.getBoard()[o.getLin() - 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() - 1][o.getCol() + 1] == 'K')
				o.setStunned(true);

			if (this.getBoard()[o.getLin()][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin()][o.getCol() + 1] == 'K')
				o.setStunned(true);

			if (this.getBoard()[o.getLin() + 1][o.getCol() - 1] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol()] == 'K'
					|| this.getBoard()[o.getLin() + 1][o.getCol() + 1] == 'K')
				o.setStunned(true);

		}
	}

	public void stunOgres() {

		heroNoKey();
		heroWithKey();
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

		for (int i = 0; i < d1.length; i++) {
			this.setBoardCell(d1[i].getLin(), d1[i].getCol(), d1[i].getSymbol());
		}
		this.setBoardCell(getHero().getLin(), getHero().getCol(), getHero().getSymbol());

	}

	public void advanceTurn() {

		heroReachedKey();
		openDoors();

		int n = 0;
		while (n < ogres.length) {
			moveOgre(n);
			n++;
		}

		stunOgres();

	}

	public void placeOgres(){
		int li = 1, co = 4;
		
		for (Ogre o : ogres) {
			o.setLin(li);
			o.setCol(co);
			this.setBoardCell(o.getLin(), o.getCol(), o.getSymbol());

			o.getClub().setLin(o.getLin() + 1);
			o.getClub().setCol(o.getCol());
			li += 2;

		}
	}
	
	public void placeKey(){
		key.setLin(1);
		key.setCol(8);
		this.setBoardCell(key.getLin(), key.getCol(), key.getSymbol());

	}
	
	public void placeHero(){
		this.getHero().setLin(8);
		this.getHero().setCol(1);
		this.setBoardCell(this.getHero().getLin(), this.getHero().getCol(), this.getHero().getSymbol());
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		this.placeKey();
		
		this.placeHero();
		
		this.placeOgres();

		for (int i = 0; i < d1.length; i++) {
			d1[i].setLin(1);
			d1[i].setCol(0);
			this.setDoors(d1[i]);
		}

	}

	public void setKey(Key k) {
		this.key = k;
	}

	public void addOgre(Ogre o) {
		if (ogres.length != 0) {
			Ogre[] newOgres = new Ogre[this.ogres.length + 1];
			for (int i = 0; i < this.ogres.length; i++)
				newOgres[i] = this.ogres[i];

			newOgres[newOgres.length - 1] = o;
			this.ogres = newOgres;
		} else
			ogres[0] = o;

	}

	public void addDoor(Door d) {
		if (d1.length != 0) {
			Door[] newDoors = new Door[d1.length + 1];

			for (int i = 0; i < this.ogres.length; i++)
				newDoors[i] = this.d1[i];

			newDoors[newDoors.length - 1] = d;

			this.d1 = newDoors;
		} else
			d1[0] = d;
	}

	@Override
	public Door[] getAllDoors() {

		return d1;
	}

}
