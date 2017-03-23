package dk2.gui;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComboBox;

import dk2.logic.Map2;

public class EditorPanel extends GamePanel{
	
	
	private char element;
	private int size;
	
	
	public EditorPanel(int width, int height, int nOgres, String guardsPers, int mapSize) throws IOException {
		
		super(width, height, nOgres, guardsPers);
		super.dungeon.setCurrentMap(1);
		buildCustomMap(mapSize);
		
							
	}
	
	
	public void buildCustomMap(int size){
		
		Map2 customMap = new Map2(size,0);
		
		customMap.buildExtWalls();
		
		
		
		this.dungeon.getLevels().remove(dungeon.getCurrentMap());
		this.dungeon.getLevels().add(customMap);
		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

}
