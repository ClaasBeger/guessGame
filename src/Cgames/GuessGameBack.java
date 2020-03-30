package Cgames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//implement the new classes add exit code 0 after exit buttons
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
	JButton startbutton = new JButton();
	JPanel Center = new JPanel();
	JButton singPlayer = new JButton();
	JPanel settPanel = new JPanel();
	JPanel placeHolder = new JPanel();
	JLabel Title = new JLabel("The Guess Game", SwingConstants.CENTER);
	JButton badges = new JButton();
	JButton Statistic = new JButton();
	JButton sett = new JButton();
	
	main_window.setTitle("Starting the Guess Game");
	main_window.getContentPane().setBackground(Color.BLUE);
	main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	main_window.setSize(2000, 1000);
	main_window.setLayout(new BorderLayout());
	
	startbutton.setText("Start a new Game");
	startbutton.setBackground(Color.GREEN);
	
	Center.setLayout(new GridLayout(2,1));
	Center.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	main_window.add(Center, BorderLayout.CENTER);
	startbutton.setPreferredSize(new Dimension(400, 200));
	Center.add(startbutton);
	startbutton.addActionListener((event) -> {
		 playernames(main_window);
		 main_window.setVisible(false);
		}); 
	
	singPlayer.setText("Single Player Guesses");
	singPlayer.setBackground(Color.BLUE);
	singPlayer.addActionListener((event) -> {
		SingPlayer sin = new SingPlayer();
		sin.starter();
		main_window.setVisible(false);
	});
	
	placeHolder.setBackground(Color.CYAN);
	Title.setFont(new Font("Serif", Font.PLAIN, 44));
	Title.setBackground(Color.CYAN);
	Title.setOpaque(true);
	Title.setPreferredSize(new Dimension(800, 100));
	placeHolder.add(Title);
	placeHolder.setPreferredSize(new Dimension(800, 100));
	main_window.add(placeHolder, BorderLayout.NORTH);
	
	sett.setBackground(Color.GRAY);
	sett.addActionListener((event)-> {
		//TODO create settings method and frame (including class attributes)
	});
	sett.setPreferredSize(new Dimension(800,100));
	sett.setText("Settings");
	settPanel.add(sett);
	settPanel.setBackground(Color.CYAN);
	settPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	settPanel.setPreferredSize(new Dimension(800,100));
	main_window.add(settPanel, BorderLayout.SOUTH);
    
	Dimension d = singPlayer.getMaximumSize();
	singPlayer.setMaximumSize(d);//.setPreferredSize(new Dimension(400,200));
	Center.add(singPlayer);//, BorderLayout.NORTH);
	
	badges.setBackground(Color.YELLOW);
	badges.setText("Badges");
	badges.addActionListener((event)-> {
		Badges.starter();
		main_window.setVisible(false);
	});
	main_window.add(badges, BorderLayout.WEST);
	
	Statistic.setText("Statistics");
	Statistic.setBackground(Color.ORANGE);
	main_window.add(Statistic, BorderLayout.EAST);
	Statistic.addActionListener((event) -> {
		Stats.plot(new JFrame());
		main_window.setVisible(false);
		}); 
	main_window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	main_window.setVisible(true);
}
public static void playernames(JFrame main_window) {
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
		 AcGame.starter(Playerone.getText(), Playertwo.getText(), main_window);
		 players.setVisible(false);
		}); 
	JButton random = new JButton();
	random.setText("Generate random names");
	players.add(random);
	random.addActionListener((event) -> {
		AcGame.starter(randomAlphaNumeric(7), randomAlphaNumeric(7), main_window);
		players.setVisible(false);
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
public static void settings() {
	//implement settings frame and attributes
}
public static void main(String[] args) {
	startPage();
}
}
