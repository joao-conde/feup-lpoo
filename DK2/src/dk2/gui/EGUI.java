package dk2.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

public class EGUI {

	private JFrame game_frame, options_frame, menu_frame, editor_frame;
	
	private GamePanel game_panel;
	private MenuPanel menupanel;
	private EditorPanel editorpanel;
	private NGOptionsPanel ngpanel;
	
	private JButton btnExit;
	
	//private ImageIcon image = new ImageIcon("res/static/keeper_floor.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGUI window = new EGUI();
					window.game_frame.setVisible(false);
					window.menu_frame.setVisible(true);
					window.options_frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
//	public String getPersonality(){
//		return this.personality;
//	}
//	
//	public String getOgres(){
//		return this.ogres;
//	}
	
	
	public JFrame getMenuFramel(){
		return this.menu_frame;
	}
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public EGUI() throws IOException {
		
		
		//creation of the menu frame
		menu_frame = new JFrame();
		menu_frame.setTitle("Main Menu");
		menu_frame.getContentPane().setLayout(null);
		menu_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu_frame.setBounds(90, 25, 1200, 700);
		menu_frame.setResizable(false);
		
		
		// creation of the options frame, where user selects the options of the game
		options_frame = new JFrame();
		options_frame.setTitle("Options");
		options_frame.getContentPane().setLayout(null);
		options_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		options_frame.setBounds(430, 336, 450, 170);
		options_frame.setResizable(false);
				
		
		
		//creation of the game frame, where player plays the game
		game_frame = new JFrame();
		game_frame.setTitle("Dungeon Escape");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.getContentPane().setLayout(null);
		game_frame.setBounds(300, 25, 700, 700);
		game_frame.setResizable(false);
		
		
		//creation of the game frame, where player plays the game
		editor_frame = new JFrame();
		editor_frame.setTitle("Edit Keeper Level");
		editor_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editor_frame.getContentPane().setLayout(null);
		editor_frame.setBounds(300, 25, 1000, 700);
		editor_frame.setResizable(false);
		
		JLabel lblDevelopedByJoo = new JLabel("Developed by Jo\u00E3o Conde and Nelson Costa  - FEUP/MIEIC/LPOO");
		lblDevelopedByJoo.setLabelFor(menu_frame);
		lblDevelopedByJoo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDevelopedByJoo.setBackground(Color.WHITE);
		lblDevelopedByJoo.setForeground(Color.RED);
		lblDevelopedByJoo.setBounds(765, 646, 419, 14);
		menu_frame.getContentPane().add(lblDevelopedByJoo);
		
		
		//Button's in Menu Panel
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewGame.setForeground(Color.RED);
		btnNewGame.setToolTipText("Start a new adventure");
		btnNewGame.setBounds(497, 362, 200, 23);
		menu_frame.getContentPane().add(btnNewGame);

		
		btnNewGame.addActionListener(
                new ActionListener() {
           
			public void actionPerformed(ActionEvent e) {
				ngpanel = new NGOptionsPanel();
				ngpanel.setBounds(0, 0, 400, 100);
				ngpanel.setVisible(true);
		
				options_frame.getContentPane().add(ngpanel);
				
				options_frame.setVisible(true);
				menu_frame.setFocusable(false);
				try {
					game_panel = new GamePanel(700, 700, ngpanel.getNOgres(),ngpanel.getPers());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				game_panel.setBounds(0, 0, 700, 700);
				game_panel.addKeyListener(game_panel);
				game_panel.setFocusable(true);
				game_frame.getContentPane().add(game_panel);
		
				
				

			}
		});
		
		JButton btnCustomLvL = new JButton("Custom LvL Editor");
		btnCustomLvL.setForeground(Color.RED);
		btnCustomLvL.setToolTipText("Create your own magical dungeon");
		btnCustomLvL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCustomLvL.setBounds(497, 411, 200, 23);
		
		btnCustomLvL.addActionListener(
                new ActionListener() {
           
			public void actionPerformed(ActionEvent e) {
			
				try {
					editorpanel = new EditorPanel(700,700,1,"Novice");
					editorpanel.setBounds(0, 0, 700, 700);
					//game_panel.addKeyListener(game_panel);
					//game_panel.setFocusable(true);
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				editor_frame.getContentPane().add(editorpanel);
				editor_frame.setVisible(true);
				
											
			}
		});
		
		menu_frame.getContentPane().add(btnCustomLvL);
		
		btnExit = new JButton("EXIT");
		btnExit.setToolTipText("Exit the game");
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setForeground(Color.RED);
		btnExit.setBounds(546, 570, 89, 23);
		menu_frame.getContentPane().add(btnExit);
		
		btnExit.addActionListener(
                new ActionListener() {
           
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
				
			}
		});
		
		

		
		
		
		
		
//		if (game_panel.getGameOver()){
//			this.game_frame.setVisible(false);
//			this.menu_frame.setVisible(true);
//		}
		menupanel = new MenuPanel(1200,700);
		menupanel.setBorder(UIManager.getBorder("ComboBox.border"));
		menupanel.setBackground(Color.WHITE);
		menupanel.setBounds(0, 0, 1194, 671);
		menupanel.setFocusable(true);
		
//		if (ogres == ""){
//			if(personality == "")
//				game_panel = new GamePanel
//		}
		
		
		
		menu_frame.getContentPane().add(menupanel);
		
		
	}
}
