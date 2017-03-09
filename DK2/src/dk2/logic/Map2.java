package dk2.logic;
import java.util.Random;

public class Map2 extends Map {

	// -------------ATTRIBUTES-------------//

	Door d1;
	Key key;
	Ogre[] ogres;

	// --------------METHODS---------------//

	public Map2(int size, int nOgres) {
		super(size);
		this.d1 = new Door();
		this.key = new Key();
		this.mieic_student = new Hero();
		this.ogres = new Ogre[nOgres];
		for (int i = 0; i < nOgres; i++){
			Ogre o = new Ogre();
			ogres[i] = o;
		}
	}


	
	public Key getKey(){
		return this.key;
	}
	
	public Door[] getDoor(){
		
		
		Door[] nextLvLDoors = {d1};
		
		return nextLvLDoors;
	}
	
//	public Ogre getOgre(){
//		return this.ogre;
//	}
	
	public void moveOgre(int index){
		
		char direction = ogres[index].calculateRandomMove();
		boolean re_calculate = true;
		
		
		if(!canMove(direction,ogres[index]))
			return;
		
		
		switch(direction){
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
		
		while(re_calculate){
					
			switch(this.ogres[index].getClub().calculateRandomMove()){
			
			case 'W':
				if(canMove('W',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveUp(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'E':
				if(canMove('E',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveNE(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'D':
				if(canMove('D',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveRight(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'C':
				if(canMove('C',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveSE(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'S':
				if(canMove('S',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveDown(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'Z':
				if(canMove('Z',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveSW(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'A':
				if(canMove('A',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveLeft(this.getBoard());
					re_calculate = false;
				}
				break;
				
			case 'Q':
				if(canMove('Q',this.ogres[index].getClub())){
					this.ogres[index].getClub().moveNW(this.getBoard());
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
		for (Ogre o: ogres){
			this.setBoardCell(o.getLin(), o.getCol(), o.getSymbol());
			
			this.setBoardCell(o.getClub().getLin(), o.getClub().getCol(), o.getClub().getSymbol());
			
			if(o.getLin() == key.getLin() && o.getCol() == key.getCol() && !mieic_student.getHasKey()){
				this.setBoardCell(o.getLin(), o.getCol(), '$');
			}
			
			if(o.getClub().getLin() == key.getLin() && o.getClub().getCol() == key.getCol() && !mieic_student.getHasKey()){
				this.setBoardCell(o.getLin(), o.getCol(), '$');
			}
		}
		if(!mieic_student.getHasKey())
		 this.setBoardCell(this.key.getLin(), this.key.getCol(), this.key.getSymbol());
		
		
		
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
		int li = 1;
		int	co = 4;
		// set Ogres initial locations
		for (Ogre o: ogres){
			o.setLin(li);
			o.setCol(co);
			this.setBoardCell(o.getLin(), o.getCol(), o.getSymbol());
			
			//set club location
			o.getClub().setLin(o.getLin()+1);
			o.getClub().setCol(o.getCol());
			li+=2;
			
		}
		
		

		// set Doors
		d1.setLin(1);
		d1.setCol(0);
		this.setDoors(d1);

	}
	

}
