package dk2.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import dk2.logic.Map;
import dk2.logic.Map2;

import dk2.gui.GamePanel;


public class EditorPanel /*extends GamePanel*/extends JPanel implements MouseListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char element;
	private int size;
	private GamePanel gp;
	
	public EditorPanel(int width, int height, int mapSize)  throws IOException  {
		
		gp = new GamePanel(width, height,5,"Novice",5);
		this.size = mapSize;
		buildCustomMap(mapSize, 5, 5);
		
							
	}
	
	
	public void setElement(char element){
		this.element = element;
	}
	
	public GamePanel getGP(){
		return gp;
	}
	
	public void buildCustomMap(int size, int nOgres, int nDoors){
		
		Map customMap = new Map2(size, nOgres, nDoors);
		
		customMap.buildExtWalls();
		gp.dungeon.setCurrentMap(1);
		gp.dungeon.getLevels().remove(gp.dungeon.getCurrentMap());
		gp.dungeon.getLevels().add(customMap);
		
	repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		Map gamemap = gp.dungeon.getMap();
		
		//this.setOpaque(false);
		g.fillRect(0, 0, 500 , 500);
		
		// covering all floor
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				g.drawImage(gp.getkFloor(), i * gp.getOffsetW(), j * gp.getOffsetH(), this);
			}
		}

		// drawing components
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				char component = gamemap.getBoard()[i][j];

				switch (component) {

				case 'X':
					g.drawImage(gp.getkWall(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				case 'A':
					g.drawImage(gp.getArmedLee(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				case 'O':
					g.drawImage(gp.getOgre(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				case 'S':
					g.drawImage(gp.getkOpenGate(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				case 'I':
					g.drawImage(gp.getkClosedGate(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				case 'k':
					g.drawImage(gp.getKey(), j * gp.getOffsetH(), i * gp.getOffsetW(), this);
					break;
				}
			}

		}
		
		//repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		int lin = e.getX() / gp.getOffsetW();
		int col = e.getY() / gp.getOffsetH();
		gp.dungeon.setCurrentMap(1);
		gp.dungeon.getMap().setBoardCell(col, lin, element);
		
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
