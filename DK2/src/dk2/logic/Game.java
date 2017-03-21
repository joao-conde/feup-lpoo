package dk2.logic;

import java.util.Vector;



public class Game {
	private int nOgres;
	private Guard guard;
	private Vector<Map> levels;
	private int currentMap;
	private String personality = "Novice";

	public Game() {
		
		switch(personality){
		case "Novice":
			this.guard = new Rookie();
			break;
		case "Drunken":
			this.guard = new Drunken();
			break;
		case "Suspicious":
			this.guard = new Suspicious();
			break;
		}
		Map1 map1 = new Map1(10, guard);
		Map2 map2 = new Map2(10, nOgres);

		map1.buildMaze();
		map2.buildMaze();

		this.levels = new Vector<Map>();
		levels.add(map1);
		levels.add(map2);

		this.currentMap = 1;

	}

	public int getNOgres() {
		return nOgres;
	}

	public void addMap(Map m) {
		levels.add(m);
	}

	public void setCurrentMap(int index) {
		this.currentMap = index;
	}

	public int getCurrentMap() {
		return this.currentMap;
	}

	public int getNumberOfMaps() {
		return this.levels.size();
	}

	public boolean advance() {

		if (levels.elementAt(currentMap).hasHeroWon()) {
			if (this.currentMap == this.levels.size() - 1)
				return true;
			currentMap++;
		}

		return false;
	}

	public boolean isHeroDead() {

		char b[][] = levels.elementAt(currentMap).getBoard();
		Hero h = levels.elementAt(currentMap).getHero();

		// hero never is at the boundaries of the map, except when he wins so he
		// ain't dead
		if (h.getCol() == 0 || h.getLin() == 0)
			return false;

		// check 3 positions above hero

		if ((b[h.getLin() - 1][h.getCol() - 1] == 'G') || (b[h.getLin() - 1][h.getCol() - 1] == 'O')
				|| (b[h.getLin() - 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol()] == 'G') || (b[h.getLin() - 1][h.getCol()] == 'O')
				|| (b[h.getLin() - 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol() + 1] == 'G') || (b[h.getLin() - 1][h.getCol() + 1] == 'O')
				|| (b[h.getLin() - 1][h.getCol() + 1] == '*'))
			return true;

		// check 3 positions below hero

		if ((b[h.getLin() + 1][h.getCol() - 1] == 'G') || (b[h.getLin() + 1][h.getCol() - 1] == 'O')
				|| (b[h.getLin() + 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol()] == 'G') || (b[h.getLin() + 1][h.getCol()] == 'O')
				|| (b[h.getLin() + 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol() + 1] == 'G') || (b[h.getLin() + 1][h.getCol() + 1] == 'O')
				|| (b[h.getLin() + 1][h.getCol() + 1] == '*'))
			return true;

		// check remaining positions

		if ((b[h.getLin()][h.getCol() - 1] == 'G') || (b[h.getLin()][h.getCol() - 1] == 'O')
				|| (b[h.getLin()][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin()][h.getCol() + 1] == 'G') || (b[h.getLin()][h.getCol() + 1] == 'O')
				|| (b[h.getLin()][h.getCol() + 1] == '*'))
			return true;

		return false;

	}

	public Map getMap() {
		return levels.elementAt(currentMap);
	}

}
