package dk2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;

public class EGUI {

	private JFrame game_frame, menu_frame;
	private GamePanel game_panel;
	private MenuPanel menupanel;
	private JButton btnExit;
	
	private ImageIcon image = new ImageIcon("res/static/keeper_floor.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGUI window = new EGUI();
					window.game_frame.setVisible(true);
					window.menu_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		
		
		//creation of the game frame, where player plays the game
		game_frame = new JFrame();
		game_frame.setTitle("Dungeon Escape");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.getContentPane().setLayout(null);
		game_frame.setBounds(300, 25, 700, 700);
		game_frame.setResizable(false);
		
		
		game_panel = new GamePanel(700,700);
		game_panel.setBounds(0, 0, 700, 700);
		game_panel.addKeyListener(game_panel);
		game_panel.setFocusable(true);
		
		
		
	
		
		
		//Button's in Menu Panel
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewGame.setForeground(Color.RED);
		btnNewGame.setToolTipText("Start a new adventure");
		btnNewGame.setBounds(497, 336, 200, 23);
		menu_frame.getContentPane().add(btnNewGame);
		
		JButton btnCustomLvL = new JButton("Custom LvL Editor");
		btnCustomLvL.setForeground(Color.RED);
		btnCustomLvL.setToolTipText("Create your own magical dungeon");
		btnCustomLvL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCustomLvL.setBounds(497, 370, 200, 23);
		menu_frame.getContentPane().add(btnCustomLvL);
		
		btnExit = new JButton("EXIT",image);
		btnExit.setToolTipText("Exit the game");
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setForeground(Color.RED);
		btnExit.setBounds(546, 470, 89, 23);
		menu_frame.getContentPane().add(btnExit);
		
		
		
		menupanel = new MenuPanel(1200,700);
		menupanel.setBorder(UIManager.getBorder("ComboBox.border"));
		menupanel.setBackground(Color.WHITE);
		menupanel.setBounds(0, 0, 1194, 671);
		
		
		menu_frame.getContentPane().add(menupanel);
		game_frame.getContentPane().add(game_panel);
		
	}
}
