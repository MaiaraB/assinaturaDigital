package janelas;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelHeader extends JPanel{
	private Image backgroundImage;
	private Image sigadexImage;
	
	public JPanelHeader () throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		backgroundImage = ImageIO.read(new File(classLoader.getResource("imagens/banner_fundo.gif").getFile()));
		sigadexImage = ImageIO.read(new File(classLoader.getResource("imagens/sigadex2.png").getFile()));
		//System.out.println(sigadexImage.getWidth(this)+", "+sigadexImage.getHeight(this));
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		int iw = backgroundImage.getWidth(this);
		int ih = backgroundImage.getHeight(this);
		if (iw > 0 && ih > 0) {
			for (int x = 0; x < getWidth(); x += iw) {
				for (int y = 0; y < getHeight(); y += ih) {
					g.drawImage(backgroundImage, x, y, iw, ih, this);
				}
			}
		}
		//g.drawImage(sigadexImage, 5, 5, this);
	}
}
