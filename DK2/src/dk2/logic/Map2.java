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
	
	public Ogre getOgre(){
		return this.ogre;
	}
	
	public void moveOgre(){
		
		this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] = ' ';
		
		int tempLin_ogre = ogre.getLin(), tempCol_ogre = ogre.getCol();
		int tempLin_club = ogre.getClub().getLin(), tempCol_club = ogre.getClub().getCol();
		
		this.ogre.randomMove(this.getBoard());
		
		if(this.getBoard()[ogre.getLin()][ogre.getCol()] == 'X' || this.getBoard()[ogre.getLin()][ogre.getCol()] == 'I'){
			this.getBoard()[ogre.getLin()][ogre.getCol()] = 'X';
			
			this.ogre.setLin(tempLin_ogre);
			this.ogre.setCol(tempCol_ogre);
			return;
		}
		
		if(this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] == 'X' || this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] == 'I'){
			this.ogre.setLin(tempLin_ogre);
			this.ogre.setCol(tempCol_ogre);
			this.ogre.getClub().setLin(tempLin_club);
			this.ogre.getClub().setCol(tempCol_club);
			return;
		}
		
		
		if(this.getBoard()[ogre.getLin()][ogre.getCol()] == 'k'){
			
			
			ogre.setSymbol('$');
		}
		
		if(this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] == 'k'){
			
		
			ogre.getClub().setSymbol('$');
		}
		
		
		this.setBoardCell(tempLin_ogre, tempLin_ogre, ' ');
		this.setBoardCell(tempLin_club, tempCol_club, ' ');
		
		this.setBoardCell(ogre.getLin(), ogre.getCol(), ogre.getSymbol());
		this.setBoardCell(tempLin_club, tempCol_club, ogre.getClub().getSymbol());
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
