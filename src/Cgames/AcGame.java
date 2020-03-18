package Cgames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AcGame {
	private String pOne;
	private String pTwo;
	private BigInteger firstGuess;
	private BigInteger secondGuess;
	private int scoreOne;
	private int scoreTwo;
	private JFrame main_window;
	public AcGame(String POne,String PTwo) {
		this.scoreOne = 0;
		this.scoreTwo = 0;
		this.pOne = POne;
		this.pTwo = PTwo;
	}
	
	public static void starter(String one, String two) {
		AcGame game = new AcGame(one, two);
		try {
			game.main_window = game.setup();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BigInteger getFirstGuess() {
		return firstGuess;
	}
	public BigInteger getSecondGuess() {
		return secondGuess;
	}
	
	public JFrame setup() throws MalformedURLException, IOException {
//TODO wrap guessers in boxlayout with score and maybe other components (hints etc.) 
//make texts on Textfields disappear when u put into in
		JFrame main_window = new JFrame();
		main_window.setTitle("Guess Game");
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_window.setSize(2000, 1000);
		main_window.setLayout(new BorderLayout());
		JTextField Playerone = new JTextField();
		Playerone.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent e) {
		        JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		Playerone.setPreferredSize(new Dimension(200, 24));
		Playerone.setText("<Player One take a Guess!>");
		JPanel wrapper = new JPanel( new FlowLayout(0, 0, FlowLayout.LEADING) );
		wrapper.add( Playerone );
		main_window.add(wrapper, BorderLayout.WEST);
		JTextField Playertwo = new JTextField();
		Playertwo.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent e) {
		        JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		Playertwo.setPreferredSize(new Dimension(200, 24));
		Playertwo.setText("<Player Two take a Guess!>");
		JPanel wrappertwo = new JPanel( new FlowLayout(0, 0, FlowLayout.LEADING) );
		wrappertwo.add(Playertwo);
		main_window.add(wrappertwo, BorderLayout.EAST);
		main_window.setVisible(true);
	    return main_window;
	}
	public static void displayPicture(JFrame frame) throws MalformedURLException, IOException {
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
}
