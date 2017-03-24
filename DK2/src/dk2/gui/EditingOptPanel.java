/*package dk2.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.imgscalr.Scalr;

public class EditingOptPanel extends JPanel implements MouseListener{

	private char element;
	private int size, nOgres, nDoors, lin, col;
	
	private ImageIcon hero, kFloor, kWall, kOpenGate, kClosedGate, key, ogre;
	private JTextField insertSize;
	
	private EditorPanel editorpanel;
	
	
	public EditingOptPanel(){
		nOgres = 0;
		nDoors = 0;
		loadImages();
		scaleImages();
		createInterface();
	
	}
	
	public void loadImages(){
		hero = new ImageIcon("res/mchar/hero_bruceLee.png");
		kFloor = new ImageIcon("res/static/keeper_floor.png");
		kWall = new ImageIcon("res/static/keeper_wall.jpg");
		kOpenGate = new ImageIcon("res/static/gate_open.png");
		kClosedGate = new ImageIcon("res/static/gate_closed.png");
		key = new ImageIcon("res/static/Key.png");
		ogre = new ImageIcon("res/mchar/ogre.png");
		
		
	}
	
	public void scaleImages(){
		
		hero = new ImageIcon(hero.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kFloor = new ImageIcon(kFloor.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kWall = new ImageIcon(kWall.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		//kOpenGate = new ImageIcon(kOpenGate.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		kClosedGate = new ImageIcon(kClosedGate.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		key = new ImageIcon(key.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		ogre = new ImageIcon(ogre.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		
	}
	
	public void createInterface(){
		setLayout(null);
		
		JLabel lblSize = new JLabel("Insert Map size:");
		lblSize.setBounds(6,34,99, 16);
		this.add(lblSize);
		
		insertSize = new JTextField();
		insertSize.setBounds(117, 29, 34, 26);
		add(insertSize);
		insertSize.setColumns(10);
		
		
		JButton btnFloor = new JButton(kFloor);
		btnFloor.setBounds(16, 102, 100, 100);
		btnFloor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			element = ' ';
		}
	});
		add(btnFloor);
		
		JButton btnWall = new JButton(kWall);
		btnWall.setBounds(146, 102, 100, 100);
		add(btnWall);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'X';
			}
		});
		
		JButton btnHero = new JButton(hero);
		btnHero.setBounds(16, 219, 100, 100);
		btnHero.setBorderPainted(false);
		btnHero.setContentAreaFilled(false);
		btnHero.setFocusPainted(false); 
		btnHero.setOpaque(false);
		add(btnHero);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'H';
			}
		});
		
		JButton btnCGate = new JButton(kClosedGate);
		btnCGate.setBounds(146, 219, 100, 100);
		btnCGate.setBorderPainted(false);
		btnCGate.setContentAreaFilled(false);
		btnCGate.setFocusPainted(false); 
		btnCGate.setOpaque(false);
		add(btnCGate);
		btnCGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'I';
				nDoors++;
			}
		});
		
		JButton btnOGate = new JButton(kOpenGate);
		btnOGate.setBounds(16, 342, 100, 100);
		btnOGate.setBorderPainted(false);
		btnOGate.setContentAreaFilled(false);
		btnOGate.setFocusPainted(false); 
		btnOGate.setOpaque(false);
		add(btnOGate);
		btnOGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'S';
			}
		});
		
		JButton btnKey = new JButton(key);
		btnKey.setBounds(146, 342, 100, 100);
		btnKey.setBorderPainted(false);
		btnKey.setContentAreaFilled(false);
		btnKey.setFocusPainted(false); 
		btnKey.setOpaque(false);
		add(btnKey);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'k';
			}
		});
		
		JButton btnOgre = new JButton(ogre);
		btnOgre.setBounds(16, 465, 100, 100);
		btnOgre.setBorderPainted(false);
		btnOgre.setContentAreaFilled(false);
		btnOgre.setFocusPainted(false); 
		btnOgre.setOpaque(false);
		add(btnOgre);
		
		
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element = 'O';
				nOgres++;
			}
		});
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(129, 581, 117, 29);
		btnOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		add(btnOK);
		 
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(insertSize.getText() != null)
					size = Integer.parseInt(insertSize.getText());
				try {
					editorpanel = new EditorPanel(700,700,0,"Novice",size, 0, element);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				editorpanel.setBounds(0,0,700,700);
				editorpanel.setFocusable(true);
				editorpanel.setVisible(true);
				//this.getRootPane().getContentsPane().add(editorpanel);
			}
			
		});
		
		btnSet.setBounds(160, 29, 64, 29);
		add(btnSet);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		

//		
	}
	
	public char getElement(){
		return this.element;
	}
	
	public int getBoardSize(){
		return this.size;
	}
	
	public int getnOgres(){
		return this.nOgres;
	}
	
	public int getnDoors(){
		return this.nDoors;
	}
	
	public int getLin(){
		return lin;
	}
	
	public int getCol(){
		return col;
	}
	
	public EditorPanel getEditorPanel(){
		return this.editorpanel;
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		lin = e.getX();
		col = e.getY();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
	
	public JPanel getPanel(){
		return this.editorpanel;
	}
}*/