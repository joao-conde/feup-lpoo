package dk2.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dk2.logic.*;

public class GamePanel extends JPanel {

	private Game dungeon = new Game(1, "Suspicious");

	private BufferedImage bruceLee, dFloor, dWall, dOpenDoor, dClosedDoor, leverOff, leverOn, rookie, drunken_asleep, drunken, suspicious;

	private int offsetW, offsetH, gridH, gridW;

	public GamePanel(int width, int height) throws IOException {

		gridH = dungeon.getMap().getBoard()[0].length;
		gridW = dungeon.getMap().getBoard().length;

		offsetW = Math.round(width / gridW);
		offsetH = Math.round(height / gridH);

		this.bruceLee = Scalr.resize(ImageIO.read(new File("res/mchar/hero_bruceLee.png")), offsetW);
		
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/static/dungeon_ground.png")), Scalr.Mode.FIT_EXACT,
				offsetW);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/static/dungeon_wall.png")), Scalr.Mode.FIT_EXACT, offsetW);
		
		this.dOpenDoor = Scalr.resize(ImageIO.read(new File("res/static/open_doors.png")), offsetW);
		this.dClosedDoor = Scalr.resize(ImageIO.read(new File("res/static/closed_doors.png")), Scalr.Mode.FIT_TO_HEIGHT,
				offsetW);
		
		this.leverOff = Scalr.resize(ImageIO.read(new File("res/static/lever_off.png")), offsetW);
		this.leverOn = Scalr.resize(ImageIO.read(new File("res/static/lever_on.png")), offsetW);
		
		this.rookie = Scalr.resize(ImageIO.read(new File("res/mchar/RookieGuard.png")), offsetW);
		
		this.drunken_asleep = Scalr.resize(ImageIO.read(new File("res/mchar/DrunkenGuard_asleep.png")), offsetW);
		this.drunken = Scalr.resize(ImageIO.read(new File("res/mchar/DrunkenGuard.png")), offsetW);
		
		this.suspicious = Scalr.resize(ImageIO.read(new File("res/mchar/SuspiciousGuard.png")), offsetW);




	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Map gamemap = dungeon.getMap();

		// covering all floor
		for (int i = 0; i < gridH; i++) {
			for (int j = 0; j < gridW; j++) {
				g.drawImage(dFloor, i * offsetW, j * offsetH, this);
			}
		}

		// drawing components
		for (int i = 0; i < gamemap.getBoard().length; i++) {
			for (int j = 0; j < gamemap.getBoard()[i].length; j++) {

				char component = gamemap.getBoard()[i][j];

				switch (component) {

				case 'X':
					g.drawImage(dWall, j * offsetH, i * offsetW, this);
					break;
					
				}
			}

		}

		// drawing doors
		for (int i = 0; i < gamemap.getAllDoors().length; i++) {

			if (gamemap.getAllDoors()[i].isOpen())
				g.drawImage(dOpenDoor, gamemap.getAllDoors()[i].getCol() * offsetH,
						gamemap.getAllDoors()[i].getLin() * offsetW, this);
			else
				g.drawImage(dClosedDoor, gamemap.getAllDoors()[i].getCol() * offsetH,
						gamemap.getAllDoors()[i].getLin() * offsetW, this);
		}

		if (gamemap instanceof Map1) {

			Lever l = ((Map1) gamemap).getLever();

			if (l.isActive())
				g.drawImage(leverOn, offsetH * l.getCol(), offsetW * l.getLin(), this);
			else
				g.drawImage(leverOff, offsetH * l.getCol(), offsetW * l.getLin(), this);
		}

		
		//drawing guard
		if(gamemap instanceof Map1){
			
			Guard guard = ((Map1) gamemap).getGuard();
			
			if(guard instanceof Rookie)
				g.drawImage(rookie, offsetH*guard.getCol(), offsetW*guard.getLin(), this);
			
			if(guard instanceof Suspicious)
				g.drawImage(suspicious, offsetH*guard.getCol(), gridW*guard.getLin(), this);
			
			if(guard instanceof Drunken){
				if(((Drunken) guard).isAsleep())
					g.drawImage(drunken_asleep, offsetH*guard.getCol(), offsetW*guard.getLin(), this);
				else
					g.drawImage(drunken, offsetH*guard.getCol(), offsetW*guard.getLin(), this);
			}
		}
		
		
		g.drawImage(bruceLee, dungeon.getMap().getHero().getLin() * offsetH,
				dungeon.getMap().getHero().getCol() * offsetW, this);

	}

}
