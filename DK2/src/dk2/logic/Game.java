package dk2.logic;

import java.io.Serializable;
import java.util.Vector;

/**
 * This class contains all game data and methods to evolution of the game
 */
public class Game implements Serializable {

	private int nOgres;
	private Guard guard;
	private Vector<Map> levels;
	private int currentMap;

	/**
	 * Game constructor: initializes game components
	 */
	public Game() {

		this.levels = new Vector<Map>();

		this.currentMap = 0;

	}

	/**
	 * @return the vector of levels
	 */
	public Vector<Map> getLevels() {
		return this.levels;
	}

	/**
	 * Builds each level components
	 * 
	 * @param pers
	 *            guard personality
	 * @param nOgres
	 *            number of ogres
	 * @param nDoors
	 *            number of doors
	 */
	public void buildMaps(String pers, int nOgres, int nDoors) {

		createGuard(pers);

		Map1 map1 = new Map1(10, guard);
		Map2 map2 = new Map2(10, nOgres, 1);

		levels.add(map1);
		levels.add(map2);
		this.buildMazes();

	}

	/**
	 * 
	 * @param pers
	 *            guard personality
	 * 
	 * 
	 */
	public void createGuard(String pers) {

		switch (pers) {
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
	}

	/**
	 * Calls the buildMaze() method for each map in the game
	 */
	public void buildMazes() {
		for (int i = 0; i < levels.size(); i++) {
			levels.elementAt(i).buildMaze();
		}
	}

	/**
	 * @return number of ogres
	 */
	public int getNOgres() {
		return nOgres;
	}

	/**
	 * 
	 * @param m
	 *            map to be add
	 */
	public void addMap(Map m) {
		levels.add(m);
	}

	/**
	 * 
	 * @param index
	 *            indicates current map is levels[index]
	 */
	public void setCurrentMap(int index) {
		this.currentMap = index;
	}

	/**
	 * 
	 * @return number(position) of the current game map
	 */
	public int getCurrentMap() {
		return this.currentMap;
	}

	/**
	 * 
	 * @return the number of maps
	 */
	public int getNumberOfMaps() {
		return this.levels.size();
	}

	/**
	 * This method advances hero to next map (if there is one)
	 * 
	 * @return a boolean indicating if hero wins or advances level
	 */
	public boolean advance() {

		if (levels.elementAt(currentMap).hasHeroWon()) {
			if (this.currentMap == this.levels.size() - 1)
				return true;
			currentMap++;
		}

		return false;
	}

	/**
	 * Checks if hero is killed by an enemy above him
	 * 
	 * @return boolean indicating if hero is dead
	 */
	public boolean enemyAbove() {
		char b[][] = levels.elementAt(currentMap).getBoard();
		Hero h = levels.elementAt(currentMap).getHero();

		if ((b[h.getLin() - 1][h.getCol() - 1] == 'O') || (b[h.getLin() - 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol()] == 'G') || (b[h.getLin() - 1][h.getCol()] == 'O')
				|| (b[h.getLin() - 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol() + 1] == 'O') || (b[h.getLin() - 1][h.getCol() + 1] == '*'))
			return true;

		return false;
	}

	/**
	 * Checks if hero is killed by an enemy below him
	 * 
	 * @return boolean indicating if hero is dead
	 */
	public boolean enemyBelow() {
		char b[][] = levels.elementAt(currentMap).getBoard();
		Hero h = levels.elementAt(currentMap).getHero();

		if ((b[h.getLin() + 1][h.getCol() - 1] == 'O') || (b[h.getLin() + 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol()] == 'G') || (b[h.getLin() + 1][h.getCol()] == 'O')
				|| (b[h.getLin() + 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol() + 1] == 'O') || (b[h.getLin() + 1][h.getCol() + 1] == '*'))
			return true;

		return false;
	}

	/**
	 * Checks if hero is killed by an enemy side by side with him
	 * 
	 * @return boolean indicating if hero is dead
	 */
	public boolean enemySide() {

		char b[][] = levels.elementAt(currentMap).getBoard();
		Hero h = levels.elementAt(currentMap).getHero();

		if ((b[h.getLin()][h.getCol() - 1] == 'G') || (b[h.getLin()][h.getCol() - 1] == 'O')
				|| (b[h.getLin()][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin()][h.getCol() + 1] == 'G') || (b[h.getLin()][h.getCol() + 1] == 'O')
				|| (b[h.getLin()][h.getCol() + 1] == '*'))
			return true;

		return false;
	}

	/**
	 * Checks if hero lost the game (or died)
	 * 
	 * @return boolean indicating if hero died
	 */
	public boolean isHeroDead() {

		char b[][] = levels.elementAt(currentMap).getBoard();
		Hero h = levels.elementAt(currentMap).getHero();

		if (h.getCol() == 0 || h.getLin() == 0)	return false;

		if (enemyAbove()) return true;
		
		if(enemyBelow()) return true;
		
		if(enemySide()) return true;

		return false;

	}

	/**
	 * 
	 * @return the current game map
	 */
	public Map getMap() {
		return levels.elementAt(currentMap);
	}

}
