package dk2.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import dk2.logic.Map;
import dk2.logic.Map2;

import dk2.gui.GamePanel;


public class EditorPanel extends GamePanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private char element;
	private int size;
	private Map customMap;
	
	public EditorPanel(int width, int height, int mapSize)  throws IOException  {
		
		super(width, height,5,"Novice",5);
		this.size = mapSize;
		buildCustomMap(mapSize, 5, 5);
							
	}
	
	
	public void setElement(char element){
		this.element = element;
	}
	
	
	public void buildCustomMap(int size, int nOgres, int nDoors){
		
		customMap = new Map2(size, nOgres, nDoors);
		customMap.buildExtWalls();
	}
	
	public Map getMap(){
		return customMap;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		
		
		//this.setOpaque(false);
		g.fillRect(0, 0, 500 , 500);
		
		// covering all floor
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				g.drawImage( getkFloor(), i *  getOffsetW(), j *  getOffsetH(), this);
			}
		}

		// drawing components
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				char component = customMap.getBoard()[i][j];

				switch (component) {

				case 'X':
					g.drawImage( getkWall(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				case 'A':
					g.drawImage( getArmedLee(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				case 'O':
					g.drawImage( getOgre(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				case 'S':
					g.drawImage( getkOpenGate(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				case 'I':
					g.drawImage( getkClosedGate(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				case 'k':
					g.drawImage( getKey(), j *  getOffsetH(), i *  getOffsetW(), this);
					break;
				}
			}

		}
		
		//repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		int lin = e.getX() /  getOffsetW();
		int col = e.getY() /  getOffsetH();
		customMap.setBoardCell(col, lin, element);;
		
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
