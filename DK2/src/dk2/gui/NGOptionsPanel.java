package dk2.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NGOptionsPanel extends JPanel{

	private JTextField nOgres;
	private JComboBox<String> guardsPers;
	private JButton okBtn;
	private String personality;
	private int number_ogres;
	
	public NGOptionsPanel(){

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres (1-5):");
		lblNumberOfOgres.setBounds(67, 28, 162, 16);
		
		
		JLabel lblTypeOfGuard = new JLabel("Type of Guard:");
		lblTypeOfGuard.setBounds(67, 65, 134, 25);
		
		
		nOgres = new JTextField();
		nOgres.setBounds(241, 23, 37, 26);
		
		nOgres.setColumns(10);
		
		guardsPers = new JComboBox<String>();
		guardsPers.setBounds(196, 65, 141, 27);
		guardsPers.addItem("Novice");
		guardsPers.addItem("Drunken");
		guardsPers.addItem("Suspicious");
		
		okBtn = new JButton("OK");
		okBtn.setToolTipText("OK");
		okBtn.setBounds(190, 105, 75, 23);
		okBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						personality = guardsPers.getSelectedItem().toString();
						number_ogres = Integer.parseInt(nOgres.getText());
						if (number_ogres < 1 || number_ogres > 5){
							JOptionPane.showMessageDialog(getRootPane(), "Invalid options!");
						}
//						else{
//							try {
//								game_panel = new GamePanel(700,700,number_ogres,personality);
//								game_panel.setBounds(0, 0, 700, 700);
//								game_panel.addKeyListener(game_panel);
//								game_panel.setFocusable(true);
//								game_frame.getContentPane().add(game_panel);
//							} catch (IOException e1) {
//								JOptionPane.showMessageDialog(options_frame, "Invalid options!");
//								e1.printStackTrace();
//							}
//							options_frame.setVisible(false);
//							menu_frame.setVisible(false);
//						
//							game_frame.setVisible(true);
//						}
					}
				});
		
	}
	
	public String getPers(){
		return this.personality;
	}
	
	public int getNOgres(){
		return this.number_ogres;
	}
	
}
