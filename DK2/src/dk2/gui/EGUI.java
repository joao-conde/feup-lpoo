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

	private JFrame game_frame, options_frame, menu_frame, edit_frame;

	private GamePanel game_panel;
	private MenuPanel menupanel;
	private EditorPanel editorpanel;
	private EditingOptPanel editingOpts;

	private JButton btnExit;

	private JTextField nOgres;
	private JComboBox<String> guardsPers;

	private String personality;
	private int number_ogres;

	// private ImageIcon image = new ImageIcon("res/static/keeper_floor.png");

	/**
	 * Launch the application.
	 */
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

	// public String getPersonality(){
	// return this.personality;
	// }
	//
	// public String getOgres(){
	// return this.ogres;
	// }

	public JFrame getMenuFramel() {
		return this.menu_frame;
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public EGUI() throws IOException {

		// creation of the menu frame
		menu_frame = new JFrame();
		menu_frame.setTitle("Main Menu");
		menu_frame.getContentPane().setLayout(null);
		menu_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu_frame.setBounds(90, 25, 1200, 700);
		menu_frame.setResizable(false);

		// creation of the options frame, where user selects the options of the
		// game
		options_frame = new JFrame();
		options_frame.setTitle("Options");
		options_frame.getContentPane().setLayout(null);
		options_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		options_frame.setBounds(430, 336, 450, 170);
		options_frame.setResizable(false);

		// creation of the editor frame
		edit_frame = new JFrame();
		edit_frame.setTitle("Custom Level");
		edit_frame.getContentPane().setLayout(null);
		edit_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		edit_frame.setBounds(175, 25, 1000, 700);
		edit_frame.setResizable(false);

		// creation of the game frame, where player plays the game
		game_frame = new JFrame();
		game_frame.setTitle("Dungeon Escape");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.getContentPane().setLayout(null);
		game_frame.setBounds(300, 25, 700, 700);
		game_frame.setResizable(false);

		JLabel DevelopedBy = new JLabel("Developed by Jo\u00E3o Conde and Nelson Costa - FEUP/MIEIC/LPOO");
		DevelopedBy.setLabelFor(menu_frame);
		DevelopedBy.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DevelopedBy.setBackground(Color.WHITE);
		DevelopedBy.setForeground(Color.RED);
		DevelopedBy.setBounds(765, 646, 419, 14);
		menu_frame.getContentPane().add(DevelopedBy);

		// Button's in Menu Panel
		JButton btnNewGame = new JButton("New Game");
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
					// JOptionPane.showMessageDialog(menu_frame, "Select options
					// first!");
					options_frame.setVisible(true);
					menu_frame.setFocusable(false);
				}

			}
		});

		JButton btnCustomLvL = new JButton("Custom LvL Editor");
		btnCustomLvL.setForeground(Color.RED);
		btnCustomLvL.setToolTipText("Create your own magical dungeon");
		btnCustomLvL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCustomLvL.setBounds(497, 411, 200, 23);

		btnCustomLvL.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				edit_frame.setVisible(true);
				
				menu_frame.setVisible(false);
				menu_frame.setFocusable(false);

			}

		});

		menu_frame.getContentPane().add(btnCustomLvL);

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

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres (1-5):");
		lblNumberOfOgres.setBounds(67, 28, 162, 16);
		options_frame.getContentPane().add(lblNumberOfOgres);

		JLabel lblTypeOfGuard = new JLabel("Type of Guard:");
		lblTypeOfGuard.setBounds(67, 65, 134, 25);
		options_frame.getContentPane().add(lblTypeOfGuard);

		nOgres = new JTextField();
		nOgres.setBounds(241, 23, 37, 26);
		nOgres.setColumns(10);
		options_frame.getContentPane().add(nOgres);

		guardsPers = new JComboBox<String>();
		guardsPers.setBounds(196, 65, 141, 27);
		guardsPers.addItem("Novice");
		guardsPers.addItem("Drunken");
		guardsPers.addItem("Suspicious");
		options_frame.getContentPane().add(guardsPers);

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
						game_panel = new GamePanel(700, 700, number_ogres, personality);
						game_panel.setBounds(0, 0, 700, 700);
						game_panel.addKeyListener(game_panel);
						game_panel.setFocusable(true);
						game_frame.getContentPane().add(game_panel);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(options_frame, "Invalid options!");
						e1.printStackTrace();
					}
					options_frame.setVisible(false);
					menu_frame.setVisible(false);
					game_frame.setVisible(true);
				}
			}
		});


		menupanel = new MenuPanel(1200, 700);
		menupanel.setBorder(UIManager.getBorder("ComboBox.border"));
		menupanel.setBackground(Color.WHITE);
		menupanel.setBounds(0, 0, 1194, 671);
		menupanel.setFocusable(true);
		
		menu_frame.getContentPane().add(menupanel);
		
		/*editorpanel = new EditorPanel(700,700,this.number_ogres,this.personality);
		editorpanel.setBorder(UIManager.getBorder("ComboBox.border"));
		editorpanel.setBackground(Color.WHITE);
		editorpanel.setBounds(0,0,700,700);
		editorpanel.setFocusable(true);
		
		edit_frame.getContentPane().add(editorpanel);*/
		
		// if (ogres == ""){
		// if(personality == "")
		// game_panel = new GamePanel
		// }
		
		editingOpts = new EditingOptPanel();
		editingOpts.setBounds(700,0,300,700);
		editingOpts.setFocusable(true);
		
		editorpanel = new EditorPanel(700,700,this.number_ogres,"Novice", editingOpts.getBoardSize());
		editorpanel.setBounds(0,0,700,700);
		editorpanel.setFocusable(true);
		
		
		
		
		edit_frame.getContentPane().add(editingOpts);
		edit_frame.getContentPane().add(editorpanel);

	}
}
