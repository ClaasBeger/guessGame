package Cgames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AcGame {
	private String pOne;
	private String pTwo;
	private BigInteger firstGuess;
	private BigInteger secondGuess;
	private int scoreOne;
	private int scoreTwo;
	private JFrame main_window;
	private JFrame frame;
	public AcGame(String POne,String PTwo, JFrame main) {
		this.scoreOne = 0;
		this.scoreTwo = 0;
		this.pOne = POne;
		this.pTwo = PTwo;
		this.main_window = main;
	}
	
	public static void starter(String one, String two, JFrame main) {
		AcGame game = new AcGame(one, two, main);
		try {
			game.frame = game.setup();
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
			System.exit(0);
		});
		EndButton.setText("End Game");
		JPanel panel = new JPanel();
		panel.add(picturebutton);
		panel.add(EndButton);
		JButton confirmButton = new JButton();
		confirmButton.setText("Confirm your Guesses");
		panel.add(confirmButton);
		confirmButton.addActionListener((event) ->{
			if(Playerone.getText().equals("")||Playertwo.getText().equals("")) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("One of the players input is empty :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
			try {
				this.firstGuess = BigInteger.valueOf(Long.parseLong(Playerone.getText()));
				this.secondGuess = BigInteger.valueOf(Long.parseLong(Playertwo.getText()));
			}
			catch(NumberFormatException e) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("One of the players input an invalid character :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
		});
		main_window.getContentPane().add(panel, BorderLayout.SOUTH);
		picturebutton.addActionListener((event) -> {
			 try {
				 displayPicture(main_window);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}); 
		main_window.setVisible(true);
	    return main_window;
	}
	public static void displayPicture(JFrame frame) throws MalformedURLException, IOException {
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
