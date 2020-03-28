package Cgames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SingPlayer {
	public SingPlayer() {
    	starter();
    }
	public void starter() {
		JFrame sing = new JFrame();
    	sing.setTitle("Single Player - Guess Game");
    	sing.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	sing.setLayout(new BorderLayout());
    	JPanel Buttons = new JPanel();
    	JTextField guess = new JTextField("Take your Guess!");
    	JButton hint = new JButton();
    	JButton skip = new JButton();
    	JButton pic = new JButton();
    	pic.addActionListener((event)->{
    		try {
				this.displayPicture(sing);
				guess.setFocusable(true);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    	pic.setText("Display a picture");
    	
    	guess.addFocusListener(new FocusAdapter() {
    	    public void focusGained(FocusEvent e) {
    	        JTextField source = (JTextField)e.getComponent();
    	        source.setText("");
    	        source.removeFocusListener(this);
    	    }
    	});
    	hint.setText("Wanna use a hint?");
    	skip.setText("Wanna use a skip?");
    	sing.add(hint, BorderLayout.EAST);
    	sing.add(skip, BorderLayout.WEST);
    	Buttons.add(guess);
    	Buttons.add(pic);
    	sing.add(Buttons, BorderLayout.SOUTH);
    	guess.setFocusable(false);
    	sing.setVisible(true);
    	
	}
	public void displayPicture(JFrame frame) throws MalformedURLException, IOException {
		JLabel Quest = new JLabel("How many Green M&Ms are there ?", SwingConstants.CENTER);
		Quest.setFont(new Font("Serif", Font.PLAIN, 34));
		frame.add(Quest, BorderLayout.NORTH);
		BufferedImage img = ImageIO.read(new URL(
				"https://i7.pngguru.com/preview/310/650/573/5bbc43fbae155.jpg"));
	 ImageIcon icon = new ImageIcon(img);
     JLabel label = new JLabel(icon);
     JScrollPane scrollPane = new JScrollPane(label);
     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
     frame.add(scrollPane, BorderLayout.CENTER);
     frame.setVisible(true);
		  }
}
