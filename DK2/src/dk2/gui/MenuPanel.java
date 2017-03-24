package dk2.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;


public class MenuPanel extends JPanel{

	
	private BufferedImage background;
		
	
	public MenuPanel(int width, int heigth) throws IOException {

		this.background = Scalr.resize(ImageIO.read(new File("res/static/menu_background.png")),Mode.FIT_TO_WIDTH, width);		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0 , 0, this);
	}

	

}
