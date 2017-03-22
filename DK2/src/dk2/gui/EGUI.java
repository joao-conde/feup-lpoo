package dk2.gui;

import java.awt.BorderLayout;
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
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

public class EGUI {

	private JFrame game_frame, menu_frame, options_frame;
	private GamePanel game_panel;
	private MenuPanel menupanel;
	private JButton btnExit, btnOp;
	private JDialog op;
	private JTextField nOgres;
	private JComboBox guardsPers;
	private String personality;
	int ogres;
	
	private ImageIcon image = new ImageIcon("res/static/keeper_floor.png");

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
		
		
		//Button's in Menu Panel
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewGame.setForeground(Color.RED);
		btnNewGame.setToolTipText("Start a new adventure");
		btnNewGame.setBounds(497, 336, 200, 23);
		menu_frame.getContentPane().add(btnNewGame);

		
		btnNewGame.addActionListener(
                new ActionListener() {
           
			public void actionPerformed(ActionEvent e) {
				if (personality != null){
					menu_frame.dispose();
					game_frame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(menu_frame, "Select options first!");
					

			}
		});
		
		JButton btnCustomLvL = new JButton("Custom LvL Editor");
		btnCustomLvL.setForeground(Color.RED);
		btnCustomLvL.setToolTipText("Create your own magical dungeon");
		btnCustomLvL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCustomLvL.setBounds(497, 404, 200, 23);
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
		
		btnOp = new JButton("Game Options");
		btnOp.setToolTipText("Options of the game");
		btnOp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnOp.setForeground(Color.RED);
		btnOp.setBounds(497, 370, 200, 23);
		menu_frame.getContentPane().add(btnOp);
		
		

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres (1-5):");
		lblNumberOfOgres.setBounds(67, 28, 162, 16);
		options_frame.getContentPane().add(lblNumberOfOgres);
		
		JLabel lblTypeOfGuard = new JLabel("Type of Guard:");
		lblTypeOfGuard.setBounds(67, 65, 134, 25);
		options_frame.getContentPane().add(lblTypeOfGuard);
		
		
		
		nOgres = new JTextField();
		nOgres.setBounds(241, 23, 37, 26);
		options_frame.getContentPane().add(nOgres);
		nOgres.setColumns(10);
		
		guardsPers = new JComboBox();
		guardsPers.setBounds(196, 65, 141, 27);
		guardsPers.addItem("Novice");
		guardsPers.addItem("Drunken");
		guardsPers.addItem("Suspicious");
		options_frame.getContentPane().add(guardsPers);
		
		btnOp.addActionListener(
                new ActionListener() {
           
			public void actionPerformed(ActionEvent e) {
					options_frame.setVisible(true);
					menu_frame.setFocusable(false);
				
			}
		});
		
		JButton okBtn = new JButton("OK");
		okBtn.setToolTipText("OK");
		okBtn.setBounds(370, 115, 50, 23);
		options_frame.getContentPane().add(okBtn);
		
		okBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						personality = guardsPers.getSelectedItem().toString();
						ogres = Integer.parseInt(nOgres.getText());
						if (ogres < 1 || ogres > 5){
							JOptionPane.showMessageDialog(options_frame, "Invalid options!");
						}
						else{
							try {
								game_panel = new GamePanel(700,700,ogres,personality);
								game_panel.setBounds(0, 0, 700, 700);
								game_panel.addKeyListener(game_panel);
								game_panel.setFocusable(true);
								game_frame.getContentPane().add(game_panel);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(options_frame, "Invalid options!");
								e1.printStackTrace();
							}
							options_frame.setVisible(false);
							menu_frame.setFocusable(true);
						}
					}
				});
		
		
		
		
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
