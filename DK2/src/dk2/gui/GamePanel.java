package dk2.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dk2.logic.*;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private Game dungeon = new Game();

	private BufferedImage bruceLee, dFloor, dWall, dOpenDoor, dClosedDoor, leverOff, leverOn, rookie, drunken_asleep,
			drunken, suspicious, kFloor, kWall, kOpenGate, kClosedGate, key, grave, ogre, fireball;

	private int offsetW, offsetH, gridH, gridW;

	public GamePanel(int width, int height) throws IOException {

		gridH = dungeon.getMap().getBoard()[0].length;
		gridW = dungeon.getMap().getBoard().length;

		offsetW = Math.round(width / gridW);
		offsetH = Math.round(height / gridH);

		loadImages();

	}

	public void loadImages() throws IOException {

		this.grave = Scalr.resize(ImageIO.read(new File("res/static/ripGrave.png")), offsetW);
		loadDungeonImages();
		loadKeeperImages();
	}

	public void loadDungeonImages() throws IOException {

		this.bruceLee = Scalr.resize(ImageIO.read(new File("res/mchar/hero_bruceLee.png")), offsetW);

		this.dFloor = Scalr.resize(ImageIO.read(new File("res/static/dungeon_ground.png")), Scalr.Mode.FIT_EXACT,
				offsetW);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/static/dungeon_wall.png")), Scalr.Mode.FIT_EXACT, offsetW);

		this.dOpenDoor = Scalr.resize(ImageIO.read(new File("res/static/open_doors.png")), Scalr.Mode.FIT_TO_HEIGHT,
				offsetW);
		this.dClosedDoor = Scalr.resize(ImageIO.read(new File("res/static/closed_doors.png")), Scalr.Mode.FIT_TO_HEIGHT,
				offsetW);

		this.leverOff = Scalr.resize(ImageIO.read(new File("res/static/lever_off.png")), offsetW);
		this.leverOn = Scalr.resize(ImageIO.read(new File("res/static/lever_on.png")), offsetW);

		this.rookie = Scalr.resize(ImageIO.read(new File("res/mchar/RookieGuard.png")), offsetW);

		this.drunken_asleep = Scalr.resize(ImageIO.read(new File("res/mchar/DrunkenGuard_asleep.png")), offsetW);
		this.drunken = Scalr.resize(ImageIO.read(new File("res/mchar/DrunkenGuard.png")), offsetW);

		this.suspicious = Scalr.resize(ImageIO.read(new File("res/mchar/SuspiciousGuard.png")), offsetW);
	}

	public void loadKeeperImages() throws IOException {

		// this.bruceLee = Scalr.resize(ImageIO.read(new
		// File("res/mchar/hero_bruceLee.png")), offsetW);

		this.kFloor = Scalr.resize(ImageIO.read(new File("res/static/keeper_floor.png")), Scalr.Mode.FIT_EXACT,
				offsetW);
		this.kWall = Scalr.resize(ImageIO.read(new File("res/static/keeper_wall.jpg")), Scalr.Mode.FIT_EXACT, offsetW);

		this.kOpenGate = Scalr.resize(ImageIO.read(new File("res/static/gate_open.png")), Scalr.Mode.FIT_TO_HEIGHT,
				offsetW);
		this.kClosedGate = Scalr.resize(ImageIO.read(new File("res/static/gate_closed.png")), Scalr.Mode.FIT_TO_HEIGHT,
				offsetW);

		this.key = Scalr.resize(ImageIO.read(new File("res/static/Key.png")), offsetW);

		this.ogre = Scalr.resize(ImageIO.read(new File("res/mchar/ogre.png")), offsetW);
		this.fireball = Scalr.resize(ImageIO.read(new File("res/mchar/FIREBALL.png")), offsetW);

	}

	public void paintDungeonLvL(Graphics g) {

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

		Lever l = ((Map1) gamemap).getLever();

		if (l.isActive())
			g.drawImage(leverOn, offsetH * l.getCol(), offsetW * l.getLin(), this);
		else
			g.drawImage(leverOff, offsetH * l.getCol(), offsetW * l.getLin(), this);

		// drawing guard

		Guard guard = ((Map1) gamemap).getGuard();

		if (guard instanceof Rookie)
			g.drawImage(rookie, offsetH * guard.getCol(), offsetW * guard.getLin(), this);

		if (guard instanceof Suspicious)
			g.drawImage(suspicious, offsetH * guard.getCol(), gridW * guard.getLin(), this);

		if (guard instanceof Drunken) {
			if (((Drunken) guard).isAsleep())
				g.drawImage(drunken_asleep, offsetH * guard.getCol(), offsetW * guard.getLin(), this);
			else
				g.drawImage(drunken, offsetH * guard.getCol(), offsetW * guard.getLin(), this);
		}

		g.drawImage(bruceLee, dungeon.getMap().getHero().getCol() * offsetH,
				dungeon.getMap().getHero().getLin() * offsetW, this);

	}

	public void paintKeeperLvL(Graphics g) {

		Map gamemap = dungeon.getMap();

		// covering all floor
		for (int i = 0; i < gridH; i++) {
			for (int j = 0; j < gridW; j++) {
				g.drawImage(kFloor, i * offsetW, j * offsetH, this);
			}
		}

		// drawing components
		for (int i = 0; i < gamemap.getBoard().length; i++) {
			for (int j = 0; j < gamemap.getBoard()[i].length; j++) {

				char component = gamemap.getBoard()[i][j];

				switch (component) {

				case 'X':
					g.drawImage(kWall, j * offsetH, i * offsetW, this);
					break;

				}
			}

		}

		// drawing gates
		for (int i = 0; i < gamemap.getAllDoors().length; i++) {

			if (gamemap.getAllDoors()[i].isOpen())
				g.drawImage(kOpenGate, gamemap.getAllDoors()[i].getCol() * offsetH,
						gamemap.getAllDoors()[i].getLin() * offsetW, this);
			else
				g.drawImage(kClosedGate, gamemap.getAllDoors()[i].getCol() * offsetH,
						gamemap.getAllDoors()[i].getLin() * offsetW, this);
		}

		
		Key k = ((Map2) gamemap).getKey();

		if (!gamemap.getHero().getHasKey())
			g.drawImage(key, offsetH * k.getCol(), offsetW * k.getLin(), this);

		
		// drawing ogre
		Ogre[] ogres = ((Map2)gamemap).getOgres();
		
		for(Ogre o: ogres){
			g.drawImage(ogre, offsetH*o.getCol(), offsetW*o.getLin(), this);
			g.drawImage(fireball, offsetH*o.getClub().getCol(), offsetW*o.getClub().getLin(), this);
		}		

		g.drawImage(bruceLee, dungeon.getMap().getHero().getCol() * offsetH,
				dungeon.getMap().getHero().getLin() * offsetW, this);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		switch (dungeon.getCurrentMap()) {

		case 0:
			paintDungeonLvL(g);
			break;
			
		case 1:
			paintKeeperLvL(g);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();

		switch (key) {

		case KeyEvent.VK_UP:
			dungeon.getMap().moveHero('W');
			break;

		case KeyEvent.VK_DOWN:
			dungeon.getMap().moveHero('S');
			break;

		case KeyEvent.VK_LEFT:
			dungeon.getMap().moveHero('A');
			break;

		case KeyEvent.VK_RIGHT:
			dungeon.getMap().moveHero('D');
			break;

		}

		dungeon.getMap().advanceTurn();

		if (dungeon.isHeroDead())
			System.exit(0);

		dungeon.advance();

		repaint();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
