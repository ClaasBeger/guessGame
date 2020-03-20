package Cgames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
			e.printStackTrace();
		} catch (IOException e) {
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
		JPanel boxOne = new JPanel();
		boxOne.setLayout(new BoxLayout(boxOne, BoxLayout.Y_AXIS));
		JPanel boxTwo = new JPanel();
		boxTwo.setLayout(new BoxLayout(boxTwo, BoxLayout.Y_AXIS));
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
		Playerone.setText("<Player One take a Guess!>");
		JLabel nameOne = new JLabel();
		JLabel scoreO = new JLabel();
		nameOne.setText(pOne);
		scoreO.setText("Score: "+Integer.toString(scoreOne));
		boxOne.add(nameOne);
		boxOne.add( Playerone );
		boxOne.add(scoreO);
		main_window.add(boxOne, BorderLayout.WEST);
		JTextField Playertwo = new JTextField();
		Playertwo.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent e) {
		        JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		Playertwo.setText("<Player Two take a Guess!>");
		JLabel nameTwo = new JLabel();
		JLabel scoreT = new JLabel();
		scoreT.setText("Score: "+Integer.toString(scoreTwo));
		nameTwo.setText(pTwo);
		boxTwo.add(nameTwo);
		boxTwo.add(Playertwo);
		boxTwo.add(scoreT);
		main_window.add(boxTwo, BorderLayout.EAST);
		main_window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		JButton picturebutton = new JButton();
		picturebutton.setText("Display a Picture");
		JButton EndButton = new JButton();
		EndButton.addActionListener((event)->{
			main_window.setVisible(false);
		});
		EndButton.setText("End Game");
		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout(0, 0));
		panel.add(picturebutton);
		panel.add(EndButton);
		main_window.getContentPane().add(panel, BorderLayout.SOUTH);
		picturebutton.addActionListener((event) -> {
			 try {
				 displayPicture();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}); 
		main_window.setVisible(true);
	    return main_window;
	}
	public static void displayPicture() throws MalformedURLException, IOException {
		JFrame frame = new JFrame();
		BufferedImage img = ImageIO.read(new URL(
				"https://i7.pngguru.com/preview/310/650/573/5bbc43fbae155.jpg"));
		    ImageIcon icon = new ImageIcon(img);
		    JLabel lbl = new JLabel();
		    lbl.setHorizontalAlignment(JLabel.CENTER);
		    lbl.setVerticalAlignment(JLabel.CENTER);
		    lbl.setIcon(icon);
		    frame.add(lbl, BorderLayout.CENTER);
		    frame.pack();
			frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
}
