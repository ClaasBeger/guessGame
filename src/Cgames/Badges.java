package Cgames;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Badges extends Stats{
    public static void starter() {
    	JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(5,5));
		for(int k = 0; k<25;k++) { 
			try {
				displayPicture(frame);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.setTitle("Your guessing Badges");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setVisible(true);
    }
	public Badges(Path path) {

		// TODO Auto-generated constructor stub
	}
	public static void displayPicture(JFrame frame) throws MalformedURLException, IOException {
		BufferedImage img = ImageIO.read(new URL(
//				"https://i7.pngguru.com/preview/310/650/573/5bbc43fbae155.jpg"));
				"https://upload.wikimedia.org/wikipedia/commons/0/06/Question-mark.jpg"));
	 ImageIcon icon = new ImageIcon(img);
	 Image cImage = icon.getImage();
     icon = new ImageIcon(createResizedCopy(cImage, 250, 130, true));
     JLabel label = new JLabel(icon);

     JScrollPane scrollPane = new JScrollPane(label);
     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
     frame.add(scrollPane, BorderLayout.CENTER);
		  }

	static BufferedImage createResizedCopy(Image originalImage, 
            int scaledWidth, int scaledHeight, 
            boolean preserveAlpha)
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose();
        return scaledBI;
    }
}
