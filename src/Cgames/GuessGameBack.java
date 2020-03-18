package Cgames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessGameBack {
public static JFrame setup() throws MalformedURLException, IOException {
	JFrame main_window = new JFrame();
	main_window.setTitle("Guess Game");
	main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	main_window.setSize(2000, 1000);
	main_window.setLayout(new BorderLayout());
	JTextField Playerone = new JTextField();
	Playerone.setPreferredSize(new Dimension(200, 24));
	Playerone.setText("<Player One take a Guess!>");
	JPanel wrapper = new JPanel( new FlowLayout(0, 0, FlowLayout.LEADING) );
	wrapper.add( Playerone );
	main_window.add(wrapper, BorderLayout.WEST);
	JTextField Playertwo = new JTextField();
	Playertwo.setPreferredSize(new Dimension(200, 24));
	Playertwo.setText("<Player Two take a Guess!>");
	JPanel wrappertwo = new JPanel( new FlowLayout(0, 0, FlowLayout.LEADING) );
	wrappertwo.add(Playertwo);
	main_window.add(wrappertwo, BorderLayout.EAST);
	main_window.setVisible(true);
    return main_window;
}
public static void x(JFrame frame) throws MalformedURLException, IOException {
	BufferedImage img = ImageIO.read(new URL(
			"https://i7.pngguru.com/preview/310/650/573/5bbc43fbae155.jpg"));
	    ImageIcon icon = new ImageIcon(img);
	    JLabel lbl = new JLabel();
	    lbl.setHorizontalAlignment(JLabel.CENTER);
	    lbl.setVerticalAlignment(JLabel.CENTER);
	    lbl.setIcon(icon);
	    frame.add(lbl, BorderLayout.CENTER);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
public static void main(String[] args) {
	try {
		x(setup());
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
