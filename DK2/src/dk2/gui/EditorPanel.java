package dk2.gui;

import java.io.IOException;

public class EditorPanel extends GamePanel {

	public EditorPanel(int width, int height, int nOgres, String guardsPers) throws IOException {
		super(width, height, nOgres, guardsPers);
		this.dungeon.setCurrentMap(1);
	}

}
