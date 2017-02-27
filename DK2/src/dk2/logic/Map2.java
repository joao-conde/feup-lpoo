package dk2.logic;

public class Map2 extends Map {

	// -------------ATTRIBUTES-------------//

	Door d1;
	Key key;
	Ogre ogre;

	// --------------METHODS---------------//

	public Map2(int size) {
		super(size);
		this.d1 = new Door();
		this.key = new Key();
		this.mieic_student = new Hero();
		this.ogre = new Ogre();
	}


	
	public Key getKey(){
		return this.key;
	}
	
	public void buildMaze() {

		this.buildExtWalls();

		
		// placing key elements//

		// set key initial location
		key.setLin(1);
		key.setCol(8);
		this.setBoardCell(key.getLin(), key.getCol(), key.getSymbol());

		// set Hero initial location
		mieic_student.setLin(8);
		mieic_student.setCol(1);
		this.setBoardCell(mieic_student.getLin(), mieic_student.getCol(), mieic_student.getSymbol());

		// set Guard initial location
		ogre.setLin(1);
		ogre.setCol(4);
		this.setBoardCell(ogre.getLin(), ogre.getCol(), ogre.getSymbol());

		// set Doors
		d1.setLin(1);
		d1.setCol(0);
		this.setDoors(d1);

	}
	

}
