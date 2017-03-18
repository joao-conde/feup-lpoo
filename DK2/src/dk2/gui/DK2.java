package dk2.gui;

import java.awt.EventQueue;
import dk2.cli.*;
import dk2.logic.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DK2 extends Main{

	private JFrame frmDungeonKeeper;
	private JTextField fldNOgres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DK2 window = new DK2();
					window.frmDungeonKeeper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DK2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeeper = new JFrame();
		frmDungeonKeeper.setResizable(false);
		frmDungeonKeeper.setTitle("Dungeon Keeper");
		frmDungeonKeeper.setBounds(100, 100, 450, 300);
		frmDungeonKeeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeeper.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(31, 19, 138, 16);
		frmDungeonKeeper.getContentPane().add(lblNumberOfOgres);
		
		fldNOgres = new JTextField();
		fldNOgres.setBounds(163, 14, 27, 26);
		frmDungeonKeeper.getContentPane().add(fldNOgres);
		fldNOgres.setColumns(10);
		
		JLabel lblGuardsPersonality = new JLabel("Guard's Personality:");
		lblGuardsPersonality.setBounds(31, 47, 138, 16);
		frmDungeonKeeper.getContentPane().add(lblGuardsPersonality);
		
		JComboBox guardsFld = new JComboBox();
		guardsFld.addItem("Novice");
		guardsFld.addItem("Drunken");
		guardsFld.addItem("Suspicious");
		guardsFld.setBounds(163, 43, 151, 27);
		frmDungeonKeeper.getContentPane().add(guardsFld);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBounds(31, 88, 283, 159);
		frmDungeonKeeper.getContentPane().add(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(344, 243, 100, 29);
		frmDungeonKeeper.getContentPane().add(btnExit);
		
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveHero('W');
			}
		});
		btnUp.setBounds(363, 102, 48, 29);
		frmDungeonKeeper.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(331, 126, 58, 29);
		frmDungeonKeeper.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(386, 126, 58, 29);
		frmDungeonKeeper.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(359, 153, 62, 29);
		frmDungeonKeeper.getContentPane().add(btnDown);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(31, 259, 283, 16);
		frmDungeonKeeper.getContentPane().add(lblNewLabel);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpOgres = Integer.parseInt(fldNOgres.getText());
				String tmpGuard = guardsFld.getSelectedItem().toString();
				if (tmpOgres > 5 || tmpOgres < 1)
					JOptionPane.showMessageDialog(frmDungeonKeeper, "Invalid Number of Ogres (min 1, max 5)");
				Game dungeon_keeper = new Game(tmpOgres, tmpGuard);
				while(true){
					textArea.setText("");
					textArea.setText(printMaze(dungeon_keeper));
					if(gameIsOver(dungeon_keeper))
						break;
					dungeon_keeper.getMap().moveHero(readUserMove());
					dungeon_keeper.getMap().advanceTurn();
				}
				lblNewLabel.setText("Game is Over.");
				
				
				
			}
		});
		btnNewGame.setBounds(344, 14, 100, 29);
		frmDungeonKeeper.getContentPane().add(btnNewGame);
		
		
	}
}
