package dk2.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {

	
	private BufferedImage background;
	//private JDialog settings;
	
	//private int width, heigth;
	
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public MenuPanel(int width, int heigth) throws IOException {

		this.background = ImageIO.read(new File("/res/menu_background.png"));
		//this.width = width;
		//this.heigth = heigth;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	
		g.drawImage(background, 0 , 0, this);
	}

}
