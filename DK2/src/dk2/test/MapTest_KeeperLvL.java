package dk2.test;

import dk2.logic.*;

public class MapTest_KeeperLvL extends Map {
	
	private Door d1;
	private Key key;
	private Ogre ogre;
	
	public MapTest_KeeperLvL(int size) {
		
		super(size);
		
		this.key = new Key();
			
		this.ogre = new Ogre();				
		
		this.d1 = new Door();		
		
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		
		// set Key initial location 
		key.setLin(3);
		key.setCol(2);
		this.setBoardCell(key.getLin(), key.getCol(), key.getSymbol());

		// set Hero initial location
		this.getHero().setLin(2);
		this.getHero().setCol(2);
		drawHero();

		// set Ogre initial location
		ogre.setLin(2);
		ogre.setCol(4);
		this.setBoardCell(ogre.getLin(), ogre.getCol(), ogre.getSymbol());
		
		// set Ogre's club initial location
		ogre.getClub().setLin(2);
		ogre.getClub().setCol(5);
		this.setBoardCell(ogre.getClub().getLin(), ogre.getClub().getCol(), ogre.getClub().getSymbol());

		// set Doors
		d1.setLin(2);
		d1.setCol(0);				

		// Placing doors in map1
		this.setDoors(d1);

	}
	
}
