package dk2.gui;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComboBox;

import dk2.logic.Key;
import dk2.logic.Map;
import dk2.logic.Map2;
import dk2.logic.Ogre;

import dk2.gui.EditingOptPanel;


public class EditorPanel extends GamePanel{
	
	
	private char element;
	private int size;
	
	
	public EditorPanel(int width, int height, int nOgres, String guardsPers, int mapSize, int nDoors) throws IOException {
		
		super(width, height, nOgres, guardsPers);
		super.dungeon.setCurrentMap(1);
		buildCustomMap(mapSize, nDoors);
		this.size = mapSize;
		
							
	}
	
	
	public void buildCustomMap(int size, int nDoors){
		
		Map customMap = new Map2(size, 0, 0);
		
		customMap.buildExtWalls();
		
		this.dungeon.getLevels().remove(dungeon.getCurrentMap());
		this.dungeon.getLevels().add(customMap);
		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g){

		Map gamemap = dungeon.getMap();

		// covering all floor
		for (int i = 0; i < getGridH(); i++) {
			for (int j = 0; j < getGridW(); j++) {
				g.drawImage(getkFloor(), i * getOffsetW(), j * getOffsetH(), this);
			}
		}

		// drawing components
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				char component = gamemap.getBoard()[i][j];

				switch (component) {

				case 'X':
					g.drawImage(getkWall(), j * getOffsetH(), i * getOffsetW(), this);
					break;

				}
			}

		}

		// drawing gates
		for (int i = 0; i < gamemap.getAllDoors().length; i++) {

			if (gamemap.getAllDoors()[i].isOpen())
				g.drawImage(getkOpenGate(), gamemap.getAllDoors()[i].getCol() * getOffsetH(),
						gamemap.getAllDoors()[i].getLin() * getOffsetW(), this);
			else
				g.drawImage(getkClosedGate(), gamemap.getAllDoors()[i].getCol() * getOffsetH(),
						gamemap.getAllDoors()[i].getLin() * getOffsetW(), this);
		}

		
//		Key k = getKey();
//
//		if (!gamemap.getHero().getHasKey())
//			g.drawImage(getKey(), getOffsetH() * k.getCol(), getOffsetW() * k.getLin(), this);
//
//		
//		// drawing ogre
//		Ogre[] ogres = ((Map2)gamemap).getOgres();
//		
//		for(Ogre o: ogres){
//			g.drawImage(getOgre(), getOffsetH()*o.getCol(), getOffsetW()*o.getLin(), this);
//			g.drawImage(getFireball(), getOffsetH()*o.getClub().getCol(), getOffsetW()*o.getClub().getLin(), this);
//			
//			
//			if(gamemap.getHero().distanceTo(o) <= 1 || gamemap.getHero().distanceTo(o.getClub()) <= 1){
//				g.drawImage(getGrave(), dungeon.getMap().getHero().getCol() * getOffsetH(),
//						dungeon.getMap().getHero().getLin() * getOffsetW(), this);
//				setGameOver(true);
//				//this.getRootPane().setVisible(false);
//				try {
//					ng = new EGUI();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}	
//			else
//				g.drawImage(bruceLee, dungeon.getMap().getHero().getCol() * offsetH,
//						dungeon.getMap().getHero().getLin() * offsetW, this);
//		}

		
		
	}

}
