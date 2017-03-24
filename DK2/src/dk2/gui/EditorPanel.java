package dk2.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import dk2.logic.Door;
import dk2.logic.Map;
import dk2.logic.Map2;
import dk2.logic.Ogre;


public class EditorPanel extends GamePanel implements MouseListener{
	
	
	private char element;
	private int size;
	
	
	public EditorPanel(int width, int height, int nOgres, String guardsPers, int mapSize, int nDoors, char toDraw)  throws IOException  {
		
		super(width, height, nOgres, guardsPers);
		super.dungeon.setCurrentMap(1);
		buildCustomMap(mapSize, nDoors);
		this.size = mapSize;
		this.element = toDraw;
		
							
	}
	
	
	public void setElement(char element){
		this.element = element;
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

		
		g.fillRect(0, 0, 500 , 500);
		
		// covering all floor
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
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
				case 'A':
					g.drawImage(getBruceLee(), j * getOffsetH(), i * getOffsetW(), this);
					break;
				case 'O':
					g.drawImage(getOgre(), j * getOffsetH(), i * getOffsetW(), this);
					break;
				case 'S':
					g.drawImage(getkOpenGate(), j * getOffsetH(), i * getOffsetW(), this);
					break;
				case 'I':
					g.drawImage(getkClosedGate(), j * getOffsetH(), i * getOffsetW(), this);
					break;
				case 'k':
					g.drawImage(getKey(), j * getOffsetH(), i * getOffsetW(), this);
					break;
				}
			}

		}

//		// drawing gates
//		for (int i = 0; i < gamemap.getAllDoors().length; i++) {
//
//			if (gamemap.getAllDoors()[i].isOpen())
//				g.drawImage(getkOpenGate(), gamemap.getAllDoors()[i].getCol() * getOffsetH(),
//						gamemap.getAllDoors()[i].getLin() * getOffsetW(), this);
//			else
//				g.drawImage(getkClosedGate(), gamemap.getAllDoors()[i].getCol() * getOffsetH(),
//						gamemap.getAllDoors()[i].getLin() * getOffsetW(), this);
//		}
//
//			g.drawImage(getBruceLee(), gamemap.getHero().getCol() * offsetH,
//				gamemap.getHero().getLin() * offsetW, this);
	}


	@Override
	public void keyPressed(KeyEvent event) {};


	@Override
	public void mousePressed(MouseEvent e) {
		
		int lin = e.getX() / this.offsetW;
		int col = e.getY() / this.offsetH;
		
		dungeon.getMap().setBoardCell(col, lin, element);
		
		repaint();
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
