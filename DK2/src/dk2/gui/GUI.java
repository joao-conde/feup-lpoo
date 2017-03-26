package dk2.gui;

import java.awt.EventQueue;
import dk2.logic.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GUI implements SwingConstants{

	private JComboBox<String> guardsFld;
	private JFrame frmDungeonKeeper;
	private JTextField fldNOgres;
	private JTextArea textArea = new JTextArea();
	private JLabel lblLabel;
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
		
		mapToParse[dungeon_keeper.getMap().getHero().getLin()][dungeon_keeper.getMap().getHero().getCol()] = 
				dungeon_keeper.getMap().getHero().getSymbol();
		

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
	public void reprint(char dir){
		if (dungeon_keeper.advance()){
			lblLabel.setText("You win!");
			return;
		}
		
		if (dungeon_keeper.isHeroDead()){
			lblLabel.setText("Game Over!");
			return;
		}
		dungeon_keeper.getMap().moveHero(dir);
		dungeon_keeper.getMap().advanceTurn();
		dungeon_keeper.getMap().placeChars();
				
		modifyTxtArea(dungeon_keeper.getMap());
	}
	
	public void createFrame(){
		frmDungeonKeeper = new JFrame();
		frmDungeonKeeper.setResizable(false);
		frmDungeonKeeper.setTitle("Dungeon Keeper");
		frmDungeonKeeper.setBounds(100, 100, 500, 326);
		frmDungeonKeeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeeper.getContentPane().setLayout(null);
	}
	
	public void createLabels(){
		JLabel lblGuardsPersonality = new JLabel("Guard's Personality:");
		lblGuardsPersonality.setBounds(31, 47, 138, 16);
		frmDungeonKeeper.getContentPane().add(lblGuardsPersonality);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(31, 19, 138, 16);
		frmDungeonKeeper.getContentPane().add(lblNumberOfOgres);
		
		lblLabel = new JLabel("");
		lblLabel.setBounds(41, 270, 225, 16);
		lblLabel.setHorizontalAlignment(CENTER);
		frmDungeonKeeper.getContentPane().add(lblLabel);
	}
	
	public void createTxtField(){
		fldNOgres = new JTextField();
		fldNOgres.setBounds(163, 14, 27, 26);
		frmDungeonKeeper.getContentPane().add(fldNOgres);
		fldNOgres.setColumns(10);
	}
	
	public void createComboBox(){

		guardsFld = new JComboBox<String>();
		guardsFld.addItem("Novice");
		guardsFld.addItem("Drunken");
		guardsFld.addItem("Suspicious");
		guardsFld.setBounds(163, 43, 151, 27);
		frmDungeonKeeper.getContentPane().add(guardsFld);
	}
	
	public void createTxtArea(){
		textArea.setFont(new Font("Courier New", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBounds(81, 86, 151, 172);
		frmDungeonKeeper.getContentPane().add(textArea);
	}
	
	public void createExitBtn(){
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(344, 243, 100, 29);
		frmDungeonKeeper.getContentPane().add(btnExit);
	}
	
	public void createUpBtn(){
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reprint('W');
			}
		});
		btnUp.setBounds(362, 77, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnUp);
	}
	
	public void createLeftBtn(){
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reprint('A');
			}
		});
		btnLeft.setBounds(324, 113, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnLeft);
	}
	
	public void createRightBtn(){
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reprint('D');
			}
		});
		btnRight.setBounds(394, 113, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnRight);
	}
	
	public void createDownBtn(){
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reprint('S');
			}
		});
		btnDown.setBounds(362, 151, 71, 39);
		frmDungeonKeeper.getContentPane().add(btnDown);
	}
	
	public void createNewGameBtn(){
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int tmpOgres = Integer.parseInt(fldNOgres.getText());
				String tmpGuard = guardsFld.getSelectedItem().toString();
				
				
				if (tmpOgres > 5 || tmpOgres < 1)
					JOptionPane.showMessageDialog(frmDungeonKeeper, "Invalid Number of Ogres (min 1, max 5)");

				dungeon_keeper = new Game();
				dungeon_keeper.buildMaps(tmpGuard, tmpOgres, 0);
				
				lblLabel.setText("");
				dungeon_keeper.getMap().placeChars();
				modifyTxtArea(dungeon_keeper.getMap());

			}
		});
		btnNewGame.setBounds(344, 14, 100, 29);
		frmDungeonKeeper.getContentPane().add(btnNewGame);
	}
	
	public void createBtns(){
		createExitBtn();
		createUpBtn();
		createLeftBtn();
		createRightBtn();
		createDownBtn();
		createNewGameBtn();
	}
	
	private void initialize() {
		
		createFrame();
		createLabels();
		createTxtField();
		createComboBox();
		createTxtArea();
		createBtns();



	}
}
