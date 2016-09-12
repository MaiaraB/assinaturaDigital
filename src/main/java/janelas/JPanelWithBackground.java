package janelas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel{
	private Image backgroundImage;
	private boolean tiled;
	
	public JPanelWithBackground (String fileName, boolean tiled) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		backgroundImage = ImageIO.read(new File(classLoader.getResource(fileName).getFile()));
		this.tiled = tiled;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		//backgroundImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		if (tiled) {
			int iw = backgroundImage.getWidth(this);
	         int ih = backgroundImage.getHeight(this);
	         if (iw > 0 && ih > 0) {
	             for (int x = 0; x < getWidth(); x += iw) {
	            	 for (int y = 0; y < getHeight(); y += ih) {
	                     g.drawImage(backgroundImage, x, y, iw, ih, this);
	                 }
	             }
	         }
		} else {
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}
}
