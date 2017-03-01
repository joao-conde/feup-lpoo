package dk2.logic;

public class Game {

	private Map[] levels;
	private int currentMap;

	public Game() {

		Map1 map1 = new Map1(10);
		Map2 map2 = new Map2(10);

		map1.buildMaze();
		map2.buildMaze();

		this.levels = new Map[2];
		levels[0] = map1;
		levels[1] = map2;

		this.currentMap = 0;
	}

	public boolean hasHeroWon() {

		Hero h = levels[currentMap].getHero();
		Door[] nextLvLDoors = levels[currentMap].getDoor();

		for (int i = 0; i < nextLvLDoors.length; i++) {

			if (nextLvLDoors[i].getLin() == h.getLin() && nextLvLDoors[i].getCol() == h.getCol())
				if (currentMap == levels.length - 1)
					return true;
				else {
					currentMap++;
					return false;
				}

			if (currentMap == 0) {
				if (((Map1) levels[0]).isOnLever()) {
					for (int j = 0; j < nextLvLDoors.length; j++) {
						nextLvLDoors[j].openDoor();
						levels[0].setBoardCell(nextLvLDoors[j].getLin(), nextLvLDoors[j].getCol(),
								nextLvLDoors[j].getSymbol());
					}
				}

			}
		}

		return false;
	}

	public boolean isHeroDead() {

		char b[][] = levels[currentMap].getBoard();
		Hero h = levels[currentMap].getHero();

		// hero never is at the boundaries of the map, except when he wins so he
		// ain't dead
		if (h.getCol() == 0 || h.getLin() == 0)
			return false;

		// check 3 positions above hero

		if ((b[h.getLin() - 1][h.getCol() - 1] == 'G') | (b[h.getLin() - 1][h.getCol() - 1] == 'O')
				| (b[h.getLin() - 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol()] == 'G') | (b[h.getLin() - 1][h.getCol()] == 'O')
				| (b[h.getLin() - 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() - 1][h.getCol() + 1] == 'G') | (b[h.getLin() - 1][h.getCol() + 1] == 'O')
				| (b[h.getLin() - 1][h.getCol() + 1] == '*'))
			return true;

		// check 3 positions below hero

		if ((b[h.getLin() + 1][h.getCol() - 1] == 'G') | (b[h.getLin() + 1][h.getCol() - 1] == 'O')
				| (b[h.getLin() + 1][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol()] == 'G') | (b[h.getLin() + 1][h.getCol()] == 'O')
				| (b[h.getLin() + 1][h.getCol()] == '*'))
			return true;

		if ((b[h.getLin() + 1][h.getCol() + 1] == 'G') | (b[h.getLin() + 1][h.getCol() + 1] == 'O')
				| (b[h.getLin() + 1][h.getCol() + 1] == '*'))
			return true;

		// check remaining positions

		if ((b[h.getLin()][h.getCol() - 1] == 'G') | (b[h.getLin()][h.getCol() - 1] == 'O')
				| (b[h.getLin()][h.getCol() - 1] == '*'))
			return true;

		if ((b[h.getLin()][h.getCol() + 1] == 'G') | (b[h.getLin()][h.getCol() + 1] == 'O')
				| (b[h.getLin()][h.getCol() + 1] == '*'))
			return true;

		return false;

	}

	public Map getMap() {
		return levels[currentMap];
	}

	public void heroReachedKey() {

		if (levels[currentMap] instanceof Map1) {

			if (levels[currentMap].getHero().getLin() == ((Map1) levels[currentMap]).getLever().getLin()
					&& levels[currentMap].getHero().getCol() == ((Map1) levels[currentMap]).getLever().getCol()) {

				levels[currentMap].openDoors();

			}

			return;
		}

		if (levels[currentMap] instanceof Map2) {

			if (levels[currentMap].getHero().getLin() == ((Map2) levels[currentMap]).getKey().getLin()
					&& levels[currentMap].getHero().getCol() == ((Map2) levels[currentMap]).getKey().getCol())
				levels[currentMap].getHero().setHasKey(true);

			return;
		}

	}
}
