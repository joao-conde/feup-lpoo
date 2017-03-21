package dk2.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;

public class EGUI {

	private JFrame game_frame, menu_frame;
	private GamePanel game_panel;
	private MenuPanel menupanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGUI window = new EGUI();
					//window.game_frame.setVisible(true);
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
		menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_frame.setBounds(90, 25, 1200, 700);
		menu_frame.setResizable(false);
		
		
		
		//creation of the game frame, where player plays the game
		/*game_frame = new JFrame();
		game_frame.setTitle("Dungeon Escape");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.getContentPane().setLayout(null);
		game_frame.setBounds(300, 25, 700, 700);
		game_frame.setResizable(false);*/
		
		
		
		menupanel = new MenuPanel(1200,700);
		menupanel.setBackground(Color.WHITE);
		menupanel.setBounds(0, 0, 1194, 671);
		
		
		game_panel = new GamePanel(700,700);
		game_panel.setBounds(0, 0, 700, 700);
		game_panel.addKeyListener(game_panel);
		game_panel.setFocusable(true);
		
		
		
		menu_frame.getContentPane().add(menupanel);
		//game_frame.getContentPane().add(game_panel);
	
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setToolTipText("New Game");
		btnNewButton.setBounds(597, 335, 89, 23);
		menu_frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
