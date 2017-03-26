package dk2.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import java.io.*;

import dk2.logic.*;

public class EGUI {

	private JFrame game_frame, options_frame, menu_frame, edit_frame;

	private GamePanel game_panel;
	private MenuPanel menupanel;
	private EditorPanel editorpanel;

	private JButton btnExit, btnSaveGame, btnNewGame, btnLoadGame, btnCustomLvL, btnUp, btnDown, btnLeft, btnRight;

	private JTextField nOgres, insertSize;
	private JComboBox<String> guardsPers;

	private String personality;
	private int number_ogres = 0;

	private char element;
	private int size;
	private int nDoors = 0;
	int nHero = 0;

	private ImageIcon hero, kFloor, kWall, kOpenGate, kClosedGate, key, ogre, logo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGUI window = new EGUI();
					window.menu_frame.setVisible(true);
					window.game_frame.setVisible(false);
					window.options_frame.setVisible(false);
					window.edit_frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getMenuFramel() {
		return this.menu_frame;
	}

	public void createMenuFrame() {
		menu_frame = new JFrame();
		menu_frame.setTitle("Main Menu");
		menu_frame.getContentPane().setLayout(null);
		menu_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu_frame.setBounds(90, 25, 1200, 700);
		menu_frame.setResizable(false);
	}

	public void createOptionFrame() {
		options_frame = new JFrame();
		options_frame.setTitle("Options");
		options_frame.getContentPane().setLayout(null);
		options_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		options_frame.setBounds(430, 336, 450, 170);
		options_frame.setResizable(false);
	}

	public void createEditFrame() {
		edit_frame = new JFrame();
		edit_frame.setTitle("Custom Level");
		edit_frame.getContentPane().setLayout(null);
		edit_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		edit_frame.setBounds(175, 25, 1000, 700);
		edit_frame.setResizable(false);
	}

	public void createGameFrame() {
		game_frame = new JFrame();
		game_frame.setTitle("Dungeon Escape");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.getContentPane().setLayout(null);
		game_frame.setBounds(300, 25, 900, 700);
		game_frame.setResizable(false);
	}

	public void createDevelopedByLabel() {
		JLabel DevelopedBy = new JLabel("Developed by Jo\u00E3o Conde and Nelson Costa - FEUP/MIEIC/LPOO");
		DevelopedBy.setLabelFor(menu_frame);
		DevelopedBy.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DevelopedBy.setBackground(Color.WHITE);
		DevelopedBy.setForeground(Color.RED);
		DevelopedBy.setBounds(765, 646, 419, 14);
		menu_frame.getContentPane().add(DevelopedBy);
	}

	public void createMenuElements() throws IOException {
		createDevelopedByLabel();
		createNewGameBtn();
		createLoadGame();
		createCustomBtn();
		createExitBtn();
		createMenuPanel();
	}

	public void createNewGameBtn() {
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewGame.setForeground(Color.RED);
		btnNewGame.setToolTipText("Start a new adventure");
		btnNewGame.setBounds(497, 362, 200, 23);
		menu_frame.getContentPane().add(btnNewGame);

		btnNewGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (personality != null) {
					menu_frame.dispose();
					game_frame.setVisible(true);
				} else {
					options_frame.setVisible(true);
					menu_frame.setFocusable(false);
				}

			}
		});
	}

	public void createLoadGame() {
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.setForeground(Color.RED);
		btnLoadGame.setToolTipText("Load previous game");
		btnLoadGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLoadGame.setBounds(497, 411, 200, 23);
		btnLoadGame.setVisible(true);

		btnLoadGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadGame();
			}
		});

		menu_frame.getContentPane().add(btnLoadGame);
	}

	public void createCustomBtn() {

		btnCustomLvL = new JButton("Custom LvL Editor");
		btnCustomLvL.setForeground(Color.RED);
		btnCustomLvL.setToolTipText("Create your own magical dungeon");
		btnCustomLvL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCustomLvL.setBounds(497, 460, 200, 23);

		btnCustomLvL.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				edit_frame.setVisible(true);
				edit_frame.setFocusable(true);
				edit_frame.requestFocus();

				menu_frame.setVisible(false);
				menu_frame.setFocusable(false);

			}

		});

		menu_frame.getContentPane().add(btnCustomLvL);
	}

	public void createExitBtn() {

		btnExit = new JButton("EXIT");
		btnExit.setToolTipText("Exit the game");
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setForeground(Color.RED);
		btnExit.setBounds(546, 570, 89, 23);
		menu_frame.getContentPane().add(btnExit);

		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}

	public void createFrames() {
		createMenuFrame();
		createOptionFrame();
		createEditFrame();
		createGameFrame();
	}

	public void createGameElements() {

		createMoveBtns();
		createSaveGameBtn();
	}

	public void createOptionElements() {
		createLabels();
		createTxtField();
		createComboBox();
		createOkBtn();
	}

	public void createLabels() {
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres (1-5):");
		lblNumberOfOgres.setBounds(67, 28, 162, 16);
		options_frame.getContentPane().add(lblNumberOfOgres);

		JLabel lblTypeOfGuard = new JLabel("Type of Guard:");
		lblTypeOfGuard.setBounds(67, 65, 134, 25);
		options_frame.getContentPane().add(lblTypeOfGuard);
	}

	public void createTxtField() {
		nOgres = new JTextField();
		nOgres.setBounds(241, 23, 37, 26);
		nOgres.setColumns(10);
		options_frame.getContentPane().add(nOgres);
	}

	public void createComboBox() {
		guardsPers = new JComboBox<String>();
		guardsPers.setBounds(196, 65, 141, 27);
		guardsPers.addItem("Novice");
		guardsPers.addItem("Drunken");
		guardsPers.addItem("Suspicious");
		options_frame.getContentPane().add(guardsPers);
	}

	public void createOkBtn() {
		JButton okBtn = new JButton("OK");
		okBtn.setToolTipText("OK");
		okBtn.setBounds(190, 105, 75, 23);
		options_frame.getContentPane().add(okBtn);

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personality = guardsPers.getSelectedItem().toString();
				number_ogres = Integer.parseInt(nOgres.getText());
				if (number_ogres < 1 || number_ogres > 5) {
					JOptionPane.showMessageDialog(options_frame, "Invalid options!");
				} else {
					try {
						game_panel = new GamePanel(700, 700, number_ogres, personality, 1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						newGame(game_panel);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	public void createMenuPanel() throws IOException {
		menupanel = new MenuPanel(1200, 700);
		menupanel.setBorder(UIManager.getBorder("ComboBox.border"));
		menupanel.setBackground(Color.WHITE);
		menupanel.setBounds(0, 0, 1194, 671);
		menupanel.setFocusable(true);
		menu_frame.setIconImage(logo.getImage());
		menu_frame.getContentPane().add(menupanel);
	}

	public EGUI() throws IOException {

		loadImages();
		scaleImages();

		createFrames();
		createMenuElements();
		createGameElements();
		createOptionElements();
		createEditorElements();
	}

	public void createSaveGameBtn() {
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSaveGame.setForeground(Color.RED);
		btnSaveGame.setToolTipText("Save current game");
		btnSaveGame.setBounds(725, 100, 150, 50);
		btnSaveGame.setVisible(true);

		game_frame.getContentPane().add(btnSaveGame);
		game_frame.setIconImage(logo.getImage());

		btnSaveGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveOptions();
			}
		});
	}

	public void createUpBtn() {
		btnUp = new JButton("W");
		btnUp.setToolTipText("Move Hero Up");
		btnUp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUp.setBounds(775, 250, 50, 50);
		btnUp.setVisible(true);
		game_frame.getContentPane().add(btnUp);

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_panel.dungeon.getMap().moveHero('W');
				game_panel.dungeon.getMap().advanceTurn();
				game_panel.repaint();
				game_panel.requestFocus();
			}
		});
	}

	public void createDownBtn() {
		btnDown = new JButton("S");
		btnDown.setToolTipText("Move Hero Down");
		btnDown.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDown.setBounds(775, 300, 50, 50);
		game_frame.getContentPane().add(btnDown);

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_panel.dungeon.getMap().moveHero('S');
				game_panel.dungeon.getMap().advanceTurn();
				game_panel.repaint();
				game_panel.requestFocus();
			}
		});
	}

	public void createLeftBtn() {
		btnLeft = new JButton("A");
		btnLeft.setToolTipText("Move Hero Left");
		btnLeft.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLeft.setBounds(725, 300, 50, 50);
		game_frame.getContentPane().add(btnLeft);

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_panel.dungeon.getMap().moveHero('A');
				game_panel.dungeon.getMap().advanceTurn();
				game_panel.repaint();
				game_panel.requestFocus();
			}
		});
	}

	public void createRightBtn() {
		btnRight = new JButton("D");
		btnRight.setToolTipText("Move Hero Right");
		btnRight.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRight.setBounds(825, 300, 50, 50);
		game_frame.getContentPane().add(btnRight);

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_panel.dungeon.getMap().moveHero('D');
				game_panel.dungeon.getMap().advanceTurn();
				game_panel.repaint();
				game_panel.requestFocus();
			}
		});
	}

	public void createMoveBtns() {
		createUpBtn();
		createDownBtn();
		createLeftBtn();
		createRightBtn();
	}

	public void createEditLabel() {
		JLabel lblSize = new JLabel("Insert Map size:");
		lblSize.setBounds(6, 34, 99, 16);
		edit_frame.getContentPane().add(lblSize);

		insertSize = new JTextField();
		insertSize.setBounds(117, 29, 34, 26);
		edit_frame.getContentPane().add(insertSize);
		insertSize.setColumns(10);
	}

	public void createFloorBtn() {
		JButton btnFloor = new JButton(kFloor);
		btnFloor.setBounds(16, 102, 100, 100);
		btnFloor.setOpaque(false);
		btnFloor.setContentAreaFilled(false);
		btnFloor.setBorderPainted(false);

		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement(' ');
			}
		});

		edit_frame.getContentPane().add(btnFloor);
	}
	
	public void createWallBtn(){
		JButton btnWall = new JButton(kWall);
		btnWall.setBounds(146, 102, 100, 100);
		btnWall.setOpaque(false);
		btnWall.setContentAreaFilled(false);
		btnWall.setBorderPainted(false);
		edit_frame.getContentPane().add(btnWall);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement('X');
			}
		});
	}
	
	public void createHeroBtn(){
		JButton btnHero = new JButton(hero);
		btnHero.setBounds(16, 342, 100, 100);
		btnHero.setFocusPainted(false); 
		btnHero.setOpaque(false);
		btnHero.setContentAreaFilled(false);
		btnHero.setBorderPainted(false);
		edit_frame.getContentPane().add(btnHero);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nHero == 0){ editorpanel.setElement('A'); nHero++;}
				else{
					JOptionPane.showMessageDialog(edit_frame,"Only one Hero!");
				}
			}
		});
	}
	
	public void createClosedGateBtn(){
		JButton btnCGate = new JButton(kClosedGate);
		btnCGate.setBounds(146, 219, 100, 100);
		btnCGate.setFocusPainted(false); 
		btnCGate.setOpaque(false);
		btnCGate.setContentAreaFilled(false);
		btnCGate.setBorderPainted(false);
		edit_frame.getContentPane().add(btnCGate);
		btnCGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement('I');
			}
		});
	}
	
	public void createOpenGateBtn(){
		JButton btnOGate = new JButton(kOpenGate);
		btnOGate.setBounds(16, 219, 100, 100);
		btnOGate.setFocusPainted(false); 
		btnOGate.setOpaque(false);
		btnOGate.setContentAreaFilled(false);
		btnOGate.setBorderPainted(false);
		edit_frame.getContentPane().add(btnOGate);
		btnOGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement('S');
			}
		});
	}
	
	public void createKeyBtn(){
		JButton btnKey = new JButton(key);
		btnKey.setBounds(146, 342, 100, 100);
		btnKey.setFocusPainted(false); 
		btnKey.setOpaque(false);
		btnKey.setContentAreaFilled(false);
		btnKey.setBorderPainted(false);
		edit_frame.getContentPane().add(btnKey);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement('k');
			}
		});
	}
	
	public void createOgreBtn(){

		JButton btnOgre = new JButton(ogre);
		btnOgre.setBounds(16, 453, 100, 100);
		btnOgre.setFocusPainted(false); 
		btnOgre.setOpaque(false);
		btnOgre.setContentAreaFilled(false);
		btnOgre.setBorderPainted(false);
		edit_frame.getContentPane().add(btnOgre);
		
		
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorpanel.setElement('O');
				
			}
		});
	}
	
	public void createEditOkBtn(){
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(129, 581, 117, 29);
		btnOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				Map customMap = editorpanel.getMap();
				
				for(int i = 0; i < customMap.getBoard().length; i++){
					for(int j = 0; j < customMap.getBoard()[i].length; j++){
						
						switch(customMap.getBoard()[i][j]){
						
						case 'A':
							Hero h = new Hero();
							h.setLin(i);
							h.setCol(j);
							h.setSymbol('A');
							customMap.setHero(h);
							break;
							
						case 'O':
							Ogre o = new Ogre();
							o.setLin(i);
							o.setCol(j);
							((Map2)customMap).addOgre(o);
							number_ogres++;
							break;
							
						case 'I':
							Door l = new Door();
							l.setLin(i);
							l.setCol(j);
							l.setSymbol('I');
							((Map2)customMap).addDoor(l);
							nDoors++;
							break;
							
						case 'S':
							Door s = new Door();
							s.setLin(i);
							s.setCol(j);
							s.setSymbol('S');
							((Map2)customMap).addDoor(s);
							nDoors++;
							break;
							
						case 'k':
							Key k = new Key();
							k.setLin(i);
							k.setCol(j);
							((Map2)customMap).setKey(k);
							break;
						}
					}
				}
				try {
					game_panel = new GamePanel(700, 700, number_ogres, "Novice", nDoors);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				game_panel.dungeon.getLevels().remove(1);
				((Map2)customMap).setDoorsSize(nDoors);
				((Map2)customMap).setOgresSize(number_ogres);
				game_panel.dungeon.addMap(customMap);
				game_panel.dungeon.setCurrentMap(0);
				try {
					newGame(game_panel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				edit_frame.setVisible(false);
				game_frame.setVisible(true);
				game_frame.getContentPane().add(game_panel);
				game_panel.setVisible(true);
				edit_frame.dispose();
			}
		});
		
		edit_frame.getContentPane().add(btnOK);

	}
	
	public void createSetBtn(){
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				size = Integer.parseInt(insertSize.getText());
				
				if(size < 0 || size > 10){
					JOptionPane.showMessageDialog(editorpanel, "Invalid map size: must be between 0 and 10", "Invalid Map Size", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					editorpanel = new EditorPanel(500,500,size);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				editorpanel.setBackground(edit_frame.getBackground());
				editorpanel.setBounds(400,50,500,500);
				editorpanel.setFocusable(true);
				editorpanel.setVisible(true);
				editorpanel.requestFocus();
				editorpanel.addMouseListener(editorpanel);
				edit_frame.getContentPane().add(editorpanel);
				editorpanel.repaint();
				
			}
			
		});
		
		btnSet.setBounds(160, 29, 64, 29);
		edit_frame.getContentPane().add(btnSet);
	
	}
	
	public void createEditorElements(){
		
		createEditLabel();
		createWallBtn();
		createHeroBtn();
		createClosedGateBtn();
		createOpenGateBtn();
		createKeyBtn();
		createOgreBtn();
		createEditOkBtn();
		createSetBtn();		
	
	}

	public JFrame getEditorFrame() {
		return this.edit_frame;
	}

	public void loadImages() {

		hero = new ImageIcon("res/mchar/bruceLeeArmed.png");
		kFloor = new ImageIcon("res/static/keeper_floor.png");
		kWall = new ImageIcon("res/static/keeper_wall.jpg");
		kOpenGate = new ImageIcon("res/static/gate_open.png");
		kClosedGate = new ImageIcon("res/static/gate_closed.png");
		key = new ImageIcon("res/static/Key.png");
		ogre = new ImageIcon("res/mchar/ogre.png");
		logo = new ImageIcon("res/static/logo.png");

	}

	public void scaleImages() {

		hero = new ImageIcon(hero.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kFloor = new ImageIcon(kFloor.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		kWall = new ImageIcon(kWall.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		// kOpenGate = new ImageIcon(kOpenGate.getImage().getScaledInstance(100,
		// 100, Image.SCALE_DEFAULT));
		kClosedGate = new ImageIcon(kClosedGate.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		key = new ImageIcon(key.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		ogre = new ImageIcon(ogre.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

	}

	public void loadGame() {

		Game g = new Game();

		try {
			game_panel = new GamePanel(700, 700, 0, "Novice", 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		game_panel.setBounds(0, 0, 700, 700);
		game_panel.addKeyListener(game_panel);
		game_panel.setFocusable(true);
		game_frame.getContentPane().add(game_panel);

		try {

			FileInputStream fileIn = new FileInputStream("res/saved/game.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			g = (Game) in.readObject();
			game_panel.setGame(g);

			in.close();
			fileIn.close();

			game_panel.setVisible(true);
			menu_frame.setFocusable(false);
			game_frame.requestFocus();
			game_frame.setVisible(true);

			menu_frame.setVisible(false);

		} catch (Exception i) {
			i.printStackTrace();
			JOptionPane.showMessageDialog(menu_frame, "No save files found...");
			menu_frame.setVisible(true);
		}

	}

	public void newGame(GamePanel game_panel) throws IOException {
		// game_panel = new GamePanel(700, 700, n, p, nDoors);
		game_panel.setBounds(0, 0, 700, 700);
		game_panel.addKeyListener(game_panel);
		game_panel.setFocusable(true);
		game_frame.getContentPane().add(game_panel);
		options_frame.setVisible(false);
		menu_frame.setVisible(false);
		game_frame.setVisible(true);
		// }
	}

	public void saveOptions() {

		switch (JOptionPane.showConfirmDialog(game_frame, "Game saved successfully. Do you wish to quit?")) {

		case JOptionPane.NO_OPTION:
			saveGame();
			break;

		case JOptionPane.OK_OPTION:
			saveGame();
			game_frame.setVisible(false);
			menu_frame.setVisible(true);
			break;

		}
	}

	public void saveGame() {
		try {

			FileOutputStream fileOut = new FileOutputStream("res/saved/game.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game_panel.dungeon);
			out.close();
			fileOut.close();
			game_panel.requestFocus();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
