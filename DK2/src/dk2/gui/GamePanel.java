package dk2.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.imgscalr.Scalr;

import dk2.logic.*;

import dk2.gui.EGUI;

public class GamePanel extends JPanel implements  KeyListener {
	private static final long serialVersionUID = 1L;
	protected Game dungeon = new Game();
	private boolean gameOver = false;
	private boolean won = false;

	private BufferedImage bruceLee, dFloor, dWall, dOpenDoor, dClosedDoor, leverOff, leverOn, rookie, drunken_asleep,
			drunken, suspicious, kFloor, kWall, kOpenGate, kClosedGate, key, grave, ogre, ogreStunned, fireball;

	protected int offsetW, offsetH, gridH, gridW;
	
	private EGUI ng;

	public GamePanel(int width, int height, int nOgres, String guardsPers, int nDoors) throws IOException {
		
				
		dungeon.buildMaps(guardsPers, nOgres, nDoors);
		
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

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setBruceLee(BufferedImage bruceLee) {
		this.bruceLee = bruceLee;
	}

	public void setdFloor(BufferedImage dFloor) {
		this.dFloor = dFloor;
	}

	public void setdWall(BufferedImage dWall) {
		this.dWall = dWall;
	}

	public void setdOpenDoor(BufferedImage dOpenDoor) {
		this.dOpenDoor = dOpenDoor;
	}

	public void setdClosedDoor(BufferedImage dClosedDoor) {
		this.dClosedDoor = dClosedDoor;
	}

	public void setLeverOff(BufferedImage leverOff) {
		this.leverOff = leverOff;
	}

	public void setLeverOn(BufferedImage leverOn) {
		this.leverOn = leverOn;
	}

	public void setRookie(BufferedImage rookie) {
		this.rookie = rookie;
	}

	public void setDrunken_asleep(BufferedImage drunken_asleep) {
		this.drunken_asleep = drunken_asleep;
	}

	public void setDrunken(BufferedImage drunken) {
		this.drunken = drunken;
	}

	public void setSuspicious(BufferedImage suspicious) {
		this.suspicious = suspicious;
	}

	public void setkFloor(BufferedImage kFloor) {
		this.kFloor = kFloor;
	}

	public void setkWall(BufferedImage kWall) {
		this.kWall = kWall;
	}

	public void setkOpenGate(BufferedImage kOpenGate) {
		this.kOpenGate = kOpenGate;
	}

	public void setkClosedGate(BufferedImage kClosedGate) {
		this.kClosedGate = kClosedGate;
	}

	public void setKey(BufferedImage key) {
		this.key = key;
	}

	public void setGrave(BufferedImage grave) {
		this.grave = grave;
	}

	public void setOgre(BufferedImage ogre) {
		this.ogre = ogre;
	}

	public void setFireball(BufferedImage fireball) {
		this.fireball = fireball;
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

		this.kFloor = Scalr.resize(ImageIO.read(new File("res/static/keeper_floor.png")), Scalr.Mode.FIT_EXACT,
				offsetW);
		this.kWall = Scalr.resize(ImageIO.read(new File("res/static/keeper_wall.jpg")), Scalr.Mode.FIT_EXACT, offsetW);

		this.kOpenGate = Scalr.resize(ImageIO.read(new File("res/static/gate_open.png")), Scalr.Mode.FIT_TO_HEIGHT,	offsetW);
		this.kClosedGate = Scalr.resize(ImageIO.read(new File("res/static/gate_closed.png")), Scalr.Mode.FIT_TO_HEIGHT,	offsetW);

		this.key = Scalr.resize(ImageIO.read(new File("res/static/Key.png")), offsetW);

		this.ogre = Scalr.resize(ImageIO.read(new File("res/mchar/ogre.png")), offsetW);
		this.ogreStunned = Scalr.resize(ImageIO.read(new File("res/mchar/ogreStunned.png")), offsetW);
		
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

			if (gamemap.getAllDoors()[i].isOpen()){
				g.drawImage(dOpenDoor, gamemap.getAllDoors()[i].getCol() * offsetH,
						gamemap.getAllDoors()[i].getLin() * offsetW, this);
				if (gamemap.getHero().distanceTo(gamemap.getAllDoors()[i]) <= 1){
					//this.setVisible(false);
					dungeon.setCurrentMap(1);
					repaint(); 
				}
			}
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
			g.drawImage(suspicious, offsetH * guard.getCol(), offsetW * guard.getLin(), this);

		if (guard instanceof Drunken) {
			if (((Drunken) guard).isAsleep())
				g.drawImage(drunken_asleep, offsetH * guard.getCol(), offsetW * guard.getLin(), this);
			else
				g.drawImage(drunken, offsetH * guard.getCol(), offsetW * guard.getLin(), this);
		}
		if(gamemap.getHero().distanceTo(guard) <= 1){
			g.drawImage(grave, dungeon.getMap().getHero().getCol() * offsetH,
					dungeon.getMap().getHero().getLin() * offsetW, this);
			this.gameOver = true;
		}
		else
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
		if(gamemap.getAllDoors().length != 0){
			// drawing gates
			for (int i = 0; i < gamemap.getAllDoors().length; i++) {
	
				if (gamemap.getAllDoors()[i].isOpen()){
					g.drawImage(kOpenGate, gamemap.getAllDoors()[i].getCol() * offsetH,
							gamemap.getAllDoors()[i].getLin() * offsetW, this);
					if (gamemap.getHero().distanceTo(gamemap.getAllDoors()[i]) <= 1 )
						won = true;
				}
				else
					g.drawImage(kClosedGate, gamemap.getAllDoors()[i].getCol() * offsetH,
							gamemap.getAllDoors()[i].getLin() * offsetW, this);
				
				}
			}
		

		
		Key k = ((Map2) gamemap).getKey();

		if (!gamemap.getHero().getHasKey())
			g.drawImage(key, offsetH * k.getCol(), offsetW * k.getLin(), this);

		
		// drawing ogre
		Ogre[] ogres = ((Map2)gamemap).getOgres();
		((Map2)gamemap).stunOgres();
		
		for(Ogre o: ogres){
			if(!o.getStunned())
				g.drawImage(ogre, offsetH*o.getCol(), offsetW*o.getLin(), this);
			else
				g.drawImage(ogreStunned, offsetH*o.getCol(), offsetW*o.getLin(), this);
			
			g.drawImage(fireball, offsetH*o.getClub().getCol(), offsetW*o.getClub().getLin(), this);
			
			
			if((gamemap.getHero().distanceTo(o) <= 1 && !o.getStunned())|| gamemap.getHero().distanceTo(o.getClub()) <= 1){
				g.drawImage(grave, dungeon.getMap().getHero().getCol() * offsetH,
						dungeon.getMap().getHero().getLin() * offsetW, this);
				this.gameOver = true;
				return;
			}	
			else
				g.drawImage(bruceLee, dungeon.getMap().getHero().getCol() * offsetH,
						dungeon.getMap().getHero().getLin() * offsetW, this);
		}

		
		

	}

	public Game getDungeon() {
		return dungeon;
	}

	public BufferedImage getBruceLee() {
		return bruceLee;
	}

	public BufferedImage getdFloor() {
		return dFloor;
	}

	public BufferedImage getdWall() {
		return dWall;
	}

	public BufferedImage getdOpenDoor() {
		return dOpenDoor;
	}

	public BufferedImage getdClosedDoor() {
		return dClosedDoor;
	}

	public BufferedImage getLeverOff() {
		return leverOff;
	}

	public BufferedImage getLeverOn() {
		return leverOn;
	}

	public BufferedImage getRookie() {
		return rookie;
	}

	public BufferedImage getDrunken_asleep() {
		return drunken_asleep;
	}

	public BufferedImage getDrunken() {
		return drunken;
	}

	public BufferedImage getSuspicious() {
		return suspicious;
	}

	public BufferedImage getkFloor() {
		return kFloor;
	}

	public BufferedImage getkWall() {
		return kWall;
	}

	public BufferedImage getkOpenGate() {
		return kOpenGate;
	}

	public BufferedImage getkClosedGate() {
		return kClosedGate;
	}

	public BufferedImage getKey() {
		return key;
	}

	public BufferedImage getGrave() {
		return grave;
	}

	public BufferedImage getOgre() {
		return ogre;
	}

	public BufferedImage getFireball() {
		return fireball;
	}

	public EGUI getNg() {
		return ng;
	}

	public int getOffsetW() {
		return offsetW;
	}

	public void setOffsetW(int offsetW) {
		this.offsetW = offsetW;
	}

	public int getOffsetH() {
		return offsetH;
	}

	public void setOffsetH(int offsetH) {
		this.offsetH = offsetH;
	}

	public int getGridH() {
		return gridH;
	}

	public void setGridH(int gridH) {
		this.gridH = gridH;
	}

	public int getGridW() {
		return gridW;
	}

	public void setGridW(int gridW) {
		this.gridW = gridW;
	}
	
	public boolean getGameover(){
		return gameOver;
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
		
		dungeon.getMap().advanceTurn();
		
		if (gameOver){
			JOptionPane.showMessageDialog(this.getRootPane(), "Game Over!");
			try {
				ng = new EGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ng.getMenuFramel().setVisible(true);
			SwingUtilities.getWindowAncestor(this).dispose();
			
		}
		
		if (won){
			JOptionPane.showMessageDialog(this.getRootPane(), "You Won!");
			SwingUtilities.windowForComponent(this).dispose();
			try{ ng = new EGUI(); } catch (IOException e) {e.printStackTrace();}
			ng.getMenuFramel().setVisible(true);
		}
		
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

	
	public boolean getGameOver(){
		return this.gameOver;
	}
	
	public void setGame(Game g){
		this.dungeon = g;
	}

}
