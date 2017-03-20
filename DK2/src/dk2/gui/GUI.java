package dk2.gui;

import java.awt.EventQueue;
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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GUI {

	private JFrame frmDungeonKeeper;
	private JTextField fldNOgres;
	private JTextArea textArea = new JTextArea();
	private Game dungeon_keeper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	
	public void modifyTxtArea(Map m){
		
		String result = new String();
		
		char[][] mapToParse = Arrays.copyOf(m.getBoard(), m.getBoard().length);
		
		mapToParse[dungeon_keeper.getMap().getHero().getLin()][dungeon_keeper.getMap().getHero().getCol()] = 'H';
		

		for (int i = 0; i < mapToParse.length; i++) {
			
			for(int j = 0; j < mapToParse[i].length;j++){
				
				result += mapToParse[i][j] + " ";

			}
			
			result += '\n';
		}
	
		textArea.setText(result);
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeeper = new JFrame();
		frmDungeonKeeper.setResizable(false);
		frmDungeonKeeper.setTitle("Dungeon Keeper");
		frmDungeonKeeper.setBounds(100, 100, 500, 300);
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

		 
		textArea.setFont(new Font("Courier New", Font.BOLD, 17));
		textArea.setEditable(false);
		textArea.setBounds(31, 88, 283, 172);
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
				dungeon_keeper.getMap().moveHero('W');
				dungeon_keeper.getMap().advanceTurn();
				modifyTxtArea(dungeon_keeper.getMap());
			}
		});
		btnUp.setBounds(362, 77, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnUp);

		JButton btnLeft = new JButton("Left");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dungeon_keeper.getMap().moveHero('A');
				dungeon_keeper.getMap().advanceTurn();
				modifyTxtArea(dungeon_keeper.getMap());
			}
		});
		btnLeft.setBounds(324, 113, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dungeon_keeper.getMap().moveHero('D');
				dungeon_keeper.getMap().advanceTurn();
				modifyTxtArea(dungeon_keeper.getMap());
			}
		});
		btnRight.setBounds(394, 113, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnRight);

		JButton btnDown = new JButton("Down");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dungeon_keeper.getMap().moveHero('S');
				dungeon_keeper.getMap().advanceTurn();
				modifyTxtArea(dungeon_keeper.getMap());
			}
		});
		btnDown.setBounds(362, 151, 71, 39);
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

				dungeon_keeper = new Game(tmpOgres, tmpGuard);
				
				
				if (dungeon_keeper.isHeroDead())
					lblNewLabel.setText("Game is Over.");
				
				
				dungeon_keeper.getMap().advanceTurn();

				modifyTxtArea(dungeon_keeper.getMap());

			}
		});
		btnNewGame.setBounds(344, 14, 100, 29);
		frmDungeonKeeper.getContentPane().add(btnNewGame);

	}
}
