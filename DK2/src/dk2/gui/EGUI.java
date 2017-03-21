package dk2.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class EGUI {

	private JFrame frame;
	private GamePanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGUI window = new EGUI();
					window.frame.setVisible(true);
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
		
		frame = new JFrame();
		frame.setTitle("LPOO Master Race 8)");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(300, 25, 700, 700);
		frame.setResizable(false);
		
		panel = new GamePanel(700,700);
		panel.setBounds(0, 0, 700, 700);
		panel.addKeyListener(panel);
		panel.setFocusable(true);
		
		frame.getContentPane().add(panel);
		
		
		
		
	}



}
