package dk2.logic;

import java.util.Vector;

public class Game {
	private int nOgres = 1;
	private Vector<Map> levels;
	private int currentMap;

	public Game() {
		
		
		Map1 map1 = new Map1(10);
		Map2 map2 = new Map2(10, nOgres);
		

		map1.buildMaze();
		map2.buildMaze();
		

		this.levels = new Vector<Map>();
		levels.add(map1);
		levels.add(map2);
		
		
		this.currentMap = 0;
		
	}
	public int getNOgres(){
		return nOgres;
	}
	
	public void addMap(Map m){
		levels.add(m);
	}
	
	public void setCurrentMap(int index){
		this.currentMap = index;
	}
	
	public int getCurrentMap(){
		return this.currentMap;
	}
	
	public int getNumberOfMaps(){
		return this.levels.size();
	}
	public boolean hasHeroWon() {

		Hero h = levels.elementAt(currentMap).getHero();
		Door[] nextLvLDoors = levels.elementAt(currentMap).getDoor();

		for (int i = 0; i < nextLvLDoors.length; i++) {

			if (levels.elementAt(currentMap) instanceof Map1) {
				if (((Map1) levels.elementAt(currentMap)).isOnLever()) {
					for (int j = 0; j < nextLvLDoors.length; j++) {
						nextLvLDoors[j].openDoor(); 
						levels.elementAt(currentMap).setBoardCell(nextLvLDoors[j].getLin(), nextLvLDoors[j].getCol(),
								nextLvLDoors[j].getSymbol());
					}
				}

				if (nextLvLDoors[i].getLin() == h.getLin() && nextLvLDoors[i].getCol() == (h.getCol() - 1) && nextLvLDoors[i].isOpen()) {
					currentMap++;
					return false;
				}

			}
			
			
			if (levels.elementAt(currentMap) instanceof Map2) {
				
								
				if(h.getHasKey() && h.getLin() == nextLvLDoors[i].getLin() && h.getCol() == nextLvLDoors[i].getCol()+1){
					nextLvLDoors[i].openDoor();
				}
				
				
			}
			
						
				
			if (nextLvLDoors[i].getLin() == h.getLin() && nextLvLDoors[i].getCol() == h.getCol())
				if (currentMap == levels.size() - 1)
					return true;
				else {
					currentMap++;
					return false;
				}

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
