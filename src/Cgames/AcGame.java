package Cgames;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.concurrent.TimeUnit;

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
	private BigInteger scoreOne;
	private BigInteger scoreTwo;
	private JFrame main_window;
	private JFrame frame;
	private BigInteger solution = BigInteger.ZERO;
	private JScrollPane pic = null;
	private JLabel picLabel;
	public AcGame(String POne,String PTwo, JFrame main) {
		this.scoreOne = BigInteger.ZERO;
		this.scoreTwo = BigInteger.ZERO;
		this.pOne = POne;
		this.pTwo = PTwo;
		this.setMain_window(main);
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
		int temp = pOne.length();
		if(pOne.length()<26) {
			for(int k = 0; k<36-temp;k++) {
				this.pOne = pOne+" ";
			}
		}
		temp = pTwo.length();
		if(pTwo.length()<26) {
			for(int k = 0; k<36-temp;k++) {
				this.pTwo = pTwo+" ";
			}
		}
		nameOne.setText(pOne);
		scoreO.setText("Score: "+scoreOne.toString());
		JButton confirmGuessOne = new JButton();
		JLabel scoreT = new JLabel();
		confirmGuessOne.addActionListener((event) -> {
			if(Playerone.getText().equals("")) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("Your input is empty :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
			try {
				this.firstGuess = BigInteger.valueOf(Long.parseLong(Playerone.getText()));
				this.scoreOne = this.solution.subtract(firstGuess).abs();
				String tempor = scoreOne.toString();
				scoreO.setText("Score: "+tempor);

				boxOne.setVisible(true);
				boxTwo.setVisible(true);
				main_window.setVisible(true);
			}
			catch(NumberFormatException e) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("One of the players input an invalid character :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
			if(this.solution.equals(BigInteger.ZERO)) {
				JFrame x = new JFrame();
				JLabel warn = new JLabel("Picture was not displayed yet!");
				x.add(warn);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				x.setVisible(true);
			}
			else if(!(this.pic == null)) {
				this.pic.setVisible(false);
				this.pic = null;
			}	
			else {
				JFrame x = new JFrame();
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				x.setLayout(new BorderLayout());
				JLabel theme = new JLabel("Results", SwingConstants.CENTER);
				theme.setFont(new Font("Serif", Font.PLAIN, 44));
				JPanel West = new JPanel();
				West.setLayout(new BoxLayout(West, BoxLayout.Y_AXIS));
				JLabel playerOneResult = new JLabel("Player One", SwingConstants.CENTER);
				playerOneResult.setFont(new Font("Serif", Font.PLAIN, 34));
				JLabel playerOneResultn = new JLabel("+ "+this.scoreOne, SwingConstants.CENTER);
				playerOneResultn.setFont(new Font("Serif", Font.PLAIN, 34));
				scoreO.setFont(new Font("Serif", Font.PLAIN, 34));
				x.add(West, BorderLayout.WEST);
				JPanel East = new JPanel();
				East.setLayout(new BoxLayout(East, BoxLayout.Y_AXIS));
				JLabel playerTwoResult = new JLabel("Player Two", SwingConstants.CENTER);
				playerTwoResult.setFont(new Font("Serif", Font.PLAIN, 34));
				JLabel playerTwoResultn = new JLabel("+ "+this.scoreTwo, SwingConstants.CENTER);
				playerTwoResultn.setFont(new Font("Serif", Font.PLAIN, 34));
				scoreT.setFont(new Font("Serif", Font.PLAIN, 34));
				x.add(East, BorderLayout.EAST);
				playerTwoResult.setOpaque(true);
				playerTwoResultn.setOpaque(true);
				scoreO.setOpaque(true);
				playerOneResult.setOpaque(true);
				playerOneResultn.setOpaque(true);
				scoreT.setOpaque(true);
				if(scoreTwo.compareTo(scoreOne) == 1) {
					East.setBackground(Color.GREEN);
					West.setBackground(Color.RED);
					playerTwoResult.setBackground(Color.GREEN);
					playerTwoResultn.setBackground(Color.GREEN);
					scoreT.setBackground(Color.GREEN);
					playerOneResult.setBackground(Color.RED);
					playerOneResultn.setBackground(Color.RED);
					scoreO.setBackground(Color.RED);
				}
				else {
					East.setBackground(Color.RED);
					West.setBackground(Color.GREEN);
					playerOneResult.setBackground(Color.GREEN);
					playerOneResultn.setBackground(Color.GREEN);
					scoreO.setBackground(Color.GREEN);
					playerTwoResult.setBackground(Color.RED);
					playerTwoResultn.setBackground(Color.RED);
					scoreT.setBackground(Color.RED);
				}
				x.setTitle("Result");
				x.setLocationRelativeTo(null);
				x.setSize(400, 200);
				x.setVisible(true);
				}
			boxOne.setVisible(true);
			boxTwo.setVisible(true);
			main_window.setVisible(true);
			
		});
		
	   confirmGuessOne.setText("Confirm your Guess");
		boxOne.add(nameOne);
		boxOne.add( Playerone );
		boxOne.add(scoreO);
		boxOne.add(confirmGuessOne);
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
		JButton confirmGuessTwo = new JButton();
		confirmGuessTwo.addActionListener((event) -> {
			if(Playertwo.getText().equals("")) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("Your input is empty :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
			try {
				this.secondGuess = BigInteger.valueOf(Long.parseLong(Playertwo.getText()));
				this.scoreTwo = this.solution.subtract(secondGuess).abs();
				String tempor = scoreTwo.toString();
				scoreT.setText("Score: "+tempor);
			}
			catch(NumberFormatException e) {
				JFrame inv = new JFrame();
				JLabel err = new JLabel("One of the players input an invalid character :(");
				inv.add(err);
				inv.pack();
				inv.setLocationRelativeTo(null);
				inv.setVisible(true);
			}
			if(this.solution.equals(BigInteger.ZERO)) {
				JFrame x = new JFrame();
				JLabel warn = new JLabel("Picture was not displayed yet!");
				x.add(warn);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				x.setVisible(true);
			}
			else if(!(this.pic == null)) {
				this.pic.setVisible(false);
				this.pic = null;
			}	
			else {
				JFrame x = new JFrame();
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				x.setLayout(new BorderLayout());
				JLabel theme = new JLabel("Results", SwingConstants.CENTER);
				theme.setFont(new Font("Serif", Font.PLAIN, 44));
				JPanel West = new JPanel();
				West.setLayout(new BoxLayout(West, BoxLayout.Y_AXIS));
				JLabel playerOneResult = new JLabel("Player One", SwingConstants.CENTER);
				playerOneResult.setFont(new Font("Serif", Font.PLAIN, 34));
				West.add(playerOneResult);
				JLabel playerOneResultn = new JLabel("+ "+this.scoreOne, SwingConstants.CENTER);
				playerOneResultn.setFont(new Font("Serif", Font.PLAIN, 34));
				West.add(playerOneResultn);
				scoreO.setFont(new Font("Serif", Font.PLAIN, 34));
				West.add(scoreO);
				x.add(West, BorderLayout.WEST);
				JPanel East = new JPanel();
				East.setLayout(new BoxLayout(East, BoxLayout.Y_AXIS));
				JLabel playerTwoResult = new JLabel("Player Two", SwingConstants.CENTER);
				playerTwoResult.setFont(new Font("Serif", Font.PLAIN, 34));
				East.add(playerTwoResult);
				JLabel playerTwoResultn = new JLabel("+ "+this.scoreTwo, SwingConstants.CENTER);
				playerTwoResultn.setFont(new Font("Serif", Font.PLAIN, 34));
				East.add(playerTwoResultn);
				scoreT.setFont(new Font("Serif", Font.PLAIN, 34));
				East.add(scoreT);
				x.add(East, BorderLayout.EAST);
				playerTwoResult.setOpaque(true);
				playerTwoResultn.setOpaque(true);
				scoreO.setOpaque(true);
				playerOneResult.setOpaque(true);
				playerOneResultn.setOpaque(true);
				scoreT.setOpaque(true);
				if(scoreTwo.compareTo(scoreOne) == 1) {
					East.setBackground(Color.GREEN);
					West.setBackground(Color.RED);
					playerTwoResult.setBackground(Color.GREEN);
					playerTwoResultn.setBackground(Color.GREEN);
					scoreT.setBackground(Color.GREEN);
					playerOneResult.setBackground(Color.RED);
					playerOneResultn.setBackground(Color.RED);
					scoreO.setBackground(Color.RED);
				}
				else {
					East.setBackground(Color.RED);
					West.setBackground(Color.GREEN);
					playerOneResult.setBackground(Color.GREEN);
					playerOneResultn.setBackground(Color.GREEN);
					scoreO.setBackground(Color.GREEN);
					playerTwoResult.setBackground(Color.RED);
					playerTwoResultn.setBackground(Color.RED);
					scoreT.setBackground(Color.RED);
				}
				x.setTitle("Result");
				x.setLocationRelativeTo(null);
				x.setSize(400, 200);
				x.setVisible(true);
				}

			boxOne.setVisible(true);
			boxTwo.setVisible(true);
			main_window.setVisible(true);
			
			
		});
		confirmGuessTwo.setText("Confirm your Guess");
//		confirmGuessTwo.setPreferredSize(new Dimension(50, 25));
		scoreT.setText("Score: "+scoreTwo.toString());
		nameTwo.setText(pTwo);
		boxTwo.add(nameTwo);
		boxTwo.add(Playertwo);
		boxTwo.add(scoreT);
		boxTwo.add(confirmGuessTwo);
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
				this.scoreOne = this.solution.subtract(firstGuess).abs();
				this.scoreTwo = this.solution.subtract(secondGuess).abs();
				scoreO.setText("Score: "+scoreOne.toString());
				scoreT.setText("Score: "+scoreTwo.toString());
				boxOne.setVisible(true);
				boxTwo.setVisible(true);
				main_window.setVisible(true);
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
				 this.displayPicture(main_window);
				 main_window.setVisible(true);
                 ImageScheduler sced = new ImageScheduler(this);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}); 
		main_window.setVisible(true);
	    return main_window;
	}
	public static void x (JScrollPane pane) {
		pane.setVisible(false);
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
     this.solution = BigInteger.valueOf(13);
     frame.setVisible(true);
     this.pic = scrollPane;
		  }

	public JLabel getPicLabel() {
		return picLabel;
	}

	public void setPicLabel(JLabel picLabel) {
		this.picLabel = picLabel;
	}

	public JFrame getMain_window() {
		return main_window;
	}

	public void setMain_window(JFrame main_window) {
		this.main_window = main_window;
	}
}
