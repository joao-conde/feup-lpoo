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
	
	public Door[] getDoor(){
		
		
		Door[] nextLvLDoors = {d1};
		
		return nextLvLDoors;
	}
	
	public Ogre getOgre(){
		return this.ogre;
	}
	
	public void moveOgre(){
		
		char direction = ogre.calculateRandomMove();
		boolean re_calculate = true;
		
		
		if(!canMove(direction,ogre))
			return;
		
		
		switch(direction){
			case 'W':
				ogre.moveUp(this.getBoard());
				break;
			
			case 'E':
				ogre.moveNE(this.getBoard());
				break;
				
			case 'D':
				ogre.moveRight(this.getBoard());
				break;
				
			case 'C':
				ogre.moveSE(this.getBoard());
				break;
				
			case 'S':
				ogre.moveDown(this.getBoard());
				break;
				
			case 'Z':
				ogre.moveSW(this.getBoard());
				break;
				
			case 'A':
				ogre.moveLeft(this.getBoard());
				break;
				
			case 'Q':
				ogre.moveNW(this.getBoard());
				break;
		}
		
		this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] = ' ';
		ogre.getClub().setLin(ogre.getLin());
		ogre.getClub().setCol(ogre.getCol());
		
		while(re_calculate){
					
			switch(this.ogre.getClub().calculateRandomMove()){
			
			case 'W':
				if(canMove('W',this.ogre.getClub())){
					this.ogre.getClub().moveUp(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'E':
				if(canMove('E',this.ogre.getClub())){
					this.ogre.getClub().moveNE(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'D':
				if(canMove('D',this.ogre.getClub())){
					this.ogre.getClub().moveRight(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'C':
				if(canMove('C',this.ogre.getClub())){
					this.ogre.getClub().moveSE(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'S':
				if(canMove('S',this.ogre.getClub())){
					this.ogre.getClub().moveDown(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'Z':
				if(canMove('Z',this.ogre.getClub())){
					this.ogre.getClub().moveSW(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'A':
				if(canMove('A',this.ogre.getClub())){
					this.ogre.getClub().moveLeft(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'Q':
				if(canMove('Q',this.ogre.getClub())){
					this.ogre.getClub().moveNW(this.getBoard());
					re_calculate = false;
				}
				break;
			}			
			
		}
		
			
		
		/*if(ogre.getLin() == key.getLin() && ogre.getCol() == key.getCol()){
						
			ogre.setSymbol('$');
		}
		
		if(this.getBoard()[ogre.getClub().getLin()][ogre.getClub().getCol()] == 'k'){
			
		
			ogre.getClub().setSymbol('$');
		}
		*/
		
		
	}
	
	public void placeChars(){
		
		this.setBoardCell(this.ogre.getLin(), this.ogre.getCol(), this.ogre.getSymbol());
		
		this.setBoardCell(this.ogre.getClub().getLin(), this.ogre.getClub().getCol(), this.ogre.getClub().getSymbol());
				
		if(!mieic_student.getHasKey())
		 this.setBoardCell(this.key.getLin(), this.key.getCol(), this.key.getSymbol());
		
		if(ogre.getLin() == key.getLin() && ogre.getCol() == key.getCol()){
			this.setBoardCell(ogre.getLin(), ogre.getCol(), '$');
		}
		
		if(ogre.getClub().getLin() == key.getLin() && ogre.getClub().getCol() == key.getCol()){
			this.setBoardCell(ogre.getLin(), ogre.getCol(), '$');
		}
		
		this.setBoardCell(d1.getLin(), d1.getCol(), d1.getSymbol());
		
		this.setBoardCell(this.mieic_student.getLin(), this.mieic_student.getCol(), this.mieic_student.getSymbol());
		
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

		// set Ogre initial location
		ogre.setLin(1);
		ogre.setCol(4);
		this.setBoardCell(ogre.getLin(), ogre.getCol(), ogre.getSymbol());
		
		//set club location
		this.ogre.getClub().setLin(ogre.getLin()+1);
		this.ogre.getClub().setCol(ogre.getCol());

		// set Doors
		d1.setLin(1);
		d1.setCol(0);
		this.setDoors(d1);

	}
	

}
