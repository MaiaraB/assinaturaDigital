package janelas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel{
	private Image backgroundImage;
	private boolean tiled;
	private int width;
	private int height;
	private int x = 0;
	private int y = 0;
	
	public JPanelWithBackground (String fileName, boolean tiled) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		backgroundImage = ImageIO.read(new File(classLoader.getResource(fileName).getFile()));
		this.tiled = tiled;
		this.width = backgroundImage.getWidth(this);
		this.height = backgroundImage.getHeight(this);
		//this.setPreferredSize(new Dimension(this.width, this.height));
		System.out.println(backgroundImage.getWidth(this)+", "+backgroundImage.getHeight(this));
	}
	
	public JPanelWithBackground (String fileName, int x, int y, boolean tiled) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		backgroundImage = ImageIO.read(new File(classLoader.getResource(fileName).getFile()));
		this.tiled = tiled;
		this.width = backgroundImage.getWidth(this);
		this.height = backgroundImage.getHeight(this);
		this.x = x;
		this.y = y;
		//this.setPreferredSize(new Dimension(this.width + x, this.height + y));
		//System.out.println(backgroundImage.getWidth(this)+", "+backgroundImage.getHeight(this));
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		Image drawImage = backgroundImage.getScaledInstance((width>this.getWidth())?this.getWidth():width, (height>this.getHeight())?this.getHeight():height, Image.SCALE_SMOOTH);
		if (tiled) {
			int iw = drawImage.getWidth(this);
	         int ih = drawImage.getHeight(this);
	         if (iw > 0 && ih > 0) {
	             for (int x = 0; x < getWidth(); x += iw) {
	            	 //for (int y = 0; y < getHeight(); y += ih) {
	                     g.drawImage(drawImage, x + this.x, this.y, iw, ih, this);
	                 //}
	             }
	         }
		} else {
			g.drawImage(drawImage, this.x, this.y, this);
		}
	}
}
