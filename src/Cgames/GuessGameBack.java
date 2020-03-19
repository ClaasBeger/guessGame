package Cgames;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessGameBack {
	private String pOne;
	private String pTwo;
	private BigInteger firstGuess;
	private BigInteger secondGuess;
	private int scoreOne;
	private int scoreTwo;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public GuessGameBack() {
		this.scoreOne = 0;
		this.scoreTwo = 0;
	}
public static void startPage() {
	JFrame main_window = new JFrame();
	main_window.setTitle("Starting the Guess Game");
	main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	main_window.setSize(2000, 1000);
	main_window.setLayout(new BorderLayout());
	JButton startbutton = new JButton();
	startbutton.setText("Start a new Game");
	main_window.add(startbutton);
	startbutton.addActionListener((event) -> {
		 playernames();
		 main_window.setVisible(false);
		}); 
	main_window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//	main_window.setUndecorated(true);
	main_window.setVisible(true);
}
public static void playernames() {
	JFrame players = new JFrame();
	players.setTitle("Guess Game Names");
	players.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	players.setSize(400, 200);
	players.setLayout(new BorderLayout());
	JTextField Playerone = new JTextField();
	Playerone.addFocusListener(new FocusAdapter() {
	    public void focusGained(FocusEvent e) {
	        JTextField source = (JTextField)e.getComponent();
	        source.setText("");
	        source.removeFocusListener(this);
	    }
	});
	Playerone.setPreferredSize(new Dimension(100, 50));
	Playerone.setText("<Player One choose a Name>");
	players.add(Playerone, BorderLayout.WEST);
	JTextField Playertwo = new JTextField();
	Playertwo.addFocusListener(new FocusAdapter() {
	    public void focusGained(FocusEvent e) {
	        JTextField source = (JTextField)e.getComponent();
	        source.setText("");
	        source.removeFocusListener(this);
	    }
	});
	Playertwo.setPreferredSize(new Dimension(100, 50));
	Playertwo.setText("<Player Two choose a Name>"); //create new Game with custom names
	players.add(Playertwo, BorderLayout.EAST);
	JButton startbutton = new JButton();
	startbutton.setText("Starting Page");
	players.add(startbutton, BorderLayout.SOUTH);
	startbutton.addActionListener((event) -> {
		 startPage();
		 players.setVisible(false);
		}); 
	JButton confirmbutton = new JButton();
	confirmbutton.setText("Bestätige Eingabe");
	players.add(confirmbutton, BorderLayout.NORTH);
	confirmbutton.addActionListener((event) -> {
		 AcGame.starter(Playerone.getText(), Playertwo.getText());
		 
		}); 
	JButton random = new JButton();
	random.setText("Generate random names");
	players.add(random);
	random.addActionListener((event) -> {
		AcGame.starter(randomAlphaNumeric(7), randomAlphaNumeric(7));
		}); 
	players.pack();
	players.setLocationRelativeTo(null);
	players.setVisible(true);
}
public static String randomAlphaNumeric(int count) {
StringBuilder builder = new StringBuilder();
while (count-- != 0) {
int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
builder.append(ALPHA_NUMERIC_STRING.charAt(character));
}
return builder.toString();
}

public static void main(String[] args) {
	startPage();
}
}
