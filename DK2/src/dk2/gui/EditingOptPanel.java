package dk2.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditingOptPanel extends JPanel{

	private char element;
	private int size = 5;
	
	private JButton btnHero, btnFloor, btnWall, btnOpenGate, btnClosedGate, btnKey, btnOgre; 
	private JTextField insertSize;
	
	private ImageIcon hero, kFloor, kWall, kOpenGate, kClosedGate, key, ogre;
	
	
	public EditingOptPanel(){
		
		loadImages();
		scaleImages();
		createInterface();
		
	}
	
	public void loadImages(){
		hero = new ImageIcon("res/mchar/hero_bruceLee.png");
		kFloor = new ImageIcon("res/static/keeper_floor.png");
		kWall = new ImageIcon("res/static/keeper_wall.jpg");
		kOpenGate = new ImageIcon("gate_open.png");
		kClosedGate = new ImageIcon("gate_closed.png");
		key = new ImageIcon("Key.png");
		ogre = new ImageIcon("res/mchar/ogre.png");
		
		
	}
	
	public void scaleImages(){
		
		hero = new ImageIcon(hero.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kFloor = new ImageIcon(kFloor.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kWall = new ImageIcon(kWall.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kOpenGate = new ImageIcon(kOpenGate.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kClosedGate = new ImageIcon(kClosedGate.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		key = new ImageIcon(key.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		ogre = new ImageIcon(ogre.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		
	}
	
	public void createInterface(){
		
		
		insertSize = new JTextField();
		insertSize.setBounds(0, 0, 20, 20);
		this.add(insertSize);
		
		
		btnHero = new JButton(hero);
		btnHero.setBounds(50, 50, 50, 50);
		
		btnHero.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'H';
			}
		});

		
		this.add(btnHero);
		
		btnFloor = new JButton(kFloor);
		btnFloor.setBounds(50,100,50,50);
		this.add(btnFloor);
		
		btnFloor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = ' ';
			}
		});
		
		btnWall = new JButton(kWall);
		btnWall.setBounds(50,150,50,50);
		this.add(btnWall);
		
		btnWall.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'X';
			}
		});
		
		btnOpenGate = new JButton(kOpenGate);
		btnOpenGate.setBounds(50,150,50,50);
		this.add(btnOpenGate);
		
		btnOpenGate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'S';
			}
		});
		
		btnClosedGate = new JButton(kClosedGate);
		btnClosedGate.setBounds(50,150,50,50);
		this.add(btnClosedGate);
		
		btnClosedGate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'I';
			}
		});
		
		btnKey = new JButton(key);
		btnKey.setBounds(50,150,50,50);
		this.add(btnKey);
		
		btnKey.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'k';
			}
		});
		
		btnOgre = new JButton(ogre);
		btnOgre.setBounds(50,150,50,50);
		this.add(btnOgre);
		
		btnOgre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				element = 'O';
			}
		});
		
	}
	
	public char getElement(){
		return this.element;
	}
	
	public int getBoardSize(){
		return this.size;
	}

}