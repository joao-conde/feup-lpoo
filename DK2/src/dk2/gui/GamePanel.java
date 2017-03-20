package dk2.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dk2.logic.Game;
import java.awt.Color;

public class GamePanel extends JPanel {

	private Game dungeon = new Game(1,"Novice");
	
	private BufferedImage dFloor, bruceLee;
	private int offsetW, offsetH, gridH, gridW;
	
	
	
	public GamePanel(int width, int height) throws IOException {
		
		gridH = dungeon.getMap().getBoard()[0].length;
		gridW = dungeon.getMap().getBoard().length;
		
		offsetW = Math.round(width / gridW);
		offsetH = Math.round(height / gridH);
		
		
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/static/dungeon_ground.png")), offsetW);
		//this.dWall = Scalr.resize(ImageIO.read(new File("res/static/dungeon_wall.png")), offsetW);
		this.bruceLee = Scalr.resize(ImageIO.read(new File("res/mchar/hero_bruceLee.png")), offsetW);
		
		
	}

	
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		for(int i = 0; i < gridH; i++){
			for(int j = 0; j < gridW; j++){
				
				//g.drawImage(dWall, i*gridW, j*gridH, this);							

				g.drawImage(dFloor, i*gridW, j*gridH, this);							
			}
		}
		
		
		g.drawImage(bruceLee, dungeon.getMap().getHero().getLin(), dungeon.getMap().getHero().getCol(), this);
		
	}
	
}
