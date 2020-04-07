package Cgames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
	private ArrayList<String> pics;
	private ArrayList<String> hints;
	private ArrayList<String> quests;
	private ArrayList<BigInteger> sol;
	private int pointer;
	private double tolerance;
	private JLabel quest;
	private BigInteger currentGuess;
	private int score;
	public SingPlayer(double d) {
		this.tolerance = d;
		this.pics = new ArrayList<String>();
		this.hints = new ArrayList<String>();
		this.quests = new ArrayList<String>();
		this.sol = new ArrayList<BigInteger>();
		this.pointer = 0;
		this.score= 0;
		URL url = getClass().getResource("Data.txt");
		File file = new File(url.getPath());
	    try {
			List<String> lines = Files.readAllLines(Path.of(file.getPath()));
			lines.stream().forEach(k->{
				if(lines.indexOf(k)%4==0) {
					pics.add(k);
				}
				else if(lines.indexOf(k)%4==1){
					quests.add(k);
				}
				else if(lines.indexOf(k)%4==2){
					hints.add(k);
				}
				else {
					sol.add(new BigInteger(k));
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	hint.addActionListener((event)->{
    		this.hint();
    	});
    	skip.setText("Wanna use a skip?");
    	skip.addActionListener((event)->{
    		this.skip();
    	});
    	JButton confirm = new JButton();
    	confirm.setText("Confirm your Guess");
    	confirm.addActionListener((event)->{
    		this.currentGuess = new BigInteger(guess.getText());
    		JFrame frame = new JFrame();
    		frame.setTitle("Result");
    		JLabel solu = new JLabel();
    		String result = "Oops that was too far off:/";
    		solu.setOpaque(true);
    		solu.setBackground(Color.RED);
//    		System.out.println("Guess: "+ new BigDecimal(guess.getText())+" Solution"+ new BigDecimal(this.sol.get(this.pointer)));
    		if((new BigDecimal(this.sol.get(this.pointer)).subtract(new BigDecimal(this.sol.get(this.pointer)).multiply(new BigDecimal(this.tolerance))).compareTo(new BigDecimal(guess.getText())))==-1 && (new BigDecimal(this.sol.get(this.pointer)).add(new BigDecimal(this.sol.get(this.pointer)).multiply(new BigDecimal(this.tolerance))).compareTo(new BigDecimal(guess.getText())))==1){
    			result = "You got it! +1";
    			this.score++;
    			solu.setBackground(Color.GREEN);
    		}
    		solu.setText("Your result: "+result);
    		solu.setFont(new Font("Serif", Font.PLAIN, 44));
    		frame.add(solu);
    		if(this.sol.get(this.pointer).equals(new BigInteger(guess.getText()))) {
            	JLabel txt = new JLabel("Bonus: Right on the spot ! +1", SwingConstants.CENTER);
            	this.score++;
            	txt.setFont(new Font("Serif", Font.PLAIN, 44));
            	txt.setOpaque(true);
            	txt.setBackground(Color.GREEN);
            	frame.add(txt, BorderLayout.SOUTH);
            }
    		//ss
    		if(result.equals("Oops that was too far off:/")) {
    			frame.setVisible(false);
    			EndGame end = new EndGame(this.score);
    		}
    		this.pointer++;
    		frame.setLocationRelativeTo(null);
    		frame.pack();
    		frame.setVisible(true);
    	});
    	sing.add(hint, BorderLayout.EAST);
    	sing.add(skip, BorderLayout.WEST);
    	Buttons.add(confirm);
    	Buttons.add(guess);
    	Buttons.add(pic);
    	sing.add(Buttons, BorderLayout.SOUTH);
    	guess.setFocusable(false);
    	sing.setVisible(true);
    	
	}
	
	public void skip() {
		this.pointer++;
	}
	public void hint() {
		JFrame hinter = new JFrame();
		hinter.setTitle("Your Hint");
		hinter.setSize(500, 300);
		hinter.setLayout(new BorderLayout());
		JLabel title = new JLabel("Your Hint:", SwingConstants.CENTER);
		JLabel hint = new JLabel(this.hints.get(this.pointer), SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 44));
		hint.setFont(new Font("Serif", Font.PLAIN, 30));
		hinter.add(title, BorderLayout.NORTH);
		hinter.add(hint, BorderLayout.CENTER);
		hinter.pack();
		hinter.setLocationRelativeTo(null);
		hinter.setVisible(true);
	}
	public void displayPicture(JFrame frame) throws MalformedURLException, IOException {
		JLabel Quest = new JLabel(this.quests.get(this.pointer), SwingConstants.CENTER);
		Quest.setOpaque(true);
		Quest.setFont(new Font("Serif", Font.PLAIN, 34));
		this.quest = Quest;
		frame.add(Quest, BorderLayout.NORTH);
		BufferedImage img = ImageIO.read(new URL(
				this.pics.get(this.pointer)));
//				"https://i7.pngguru.com/preview/310/650/573/5bbc43fbae155.jpg"));
	 ImageIcon icon = new ImageIcon(img);
     JLabel label = new JLabel(icon);
     JScrollPane scrollPane = new JScrollPane(label);
     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
     frame.add(scrollPane, BorderLayout.CENTER);
     frame.setVisible(true);
		  }
}
