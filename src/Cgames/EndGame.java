package Cgames;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndGame {
	private int score;
	private int scoretwo;
	private int Highscore;
	private List<Integer> hist;
	
	//singPlayer
    public EndGame(int endscore) {
    	this.score = endscore;
    	URL url = getClass().getResource("History.txt");
		File file = new File(url.getPath());
    	List<String> linesw = Arrays.asList("The first line", "The second line");
    	Path filen = Paths.get("the-file-name.txt");
    	try {
			Files.write(filen, linesw, StandardCharsets.UTF_8);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		List<String> lines;
		try {
			FileWriter fw = new FileWriter(file, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    out.println(endscore);
		    //more code
			lines = Files.readAllLines(Path.of(file.getPath()));
			System.out.println(lines.get(0));
			Highscore = Integer.parseInt(lines.get(0));
			lines.remove(0);
			this.hist = lines.stream().map(k->Integer.parseInt(k)).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.starter();   
	    }
    //duo
    public EndGame(int scoreone, int scoreT) {
    	
    }
    public void starter() {
    	Double[] percentilesarr = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0};
    	List<Double> percentiles = Arrays.asList(percentilesarr);
    	LinkedList<Integer> percentileborders = new LinkedList<Integer>();
    	JFrame fr = new JFrame();
    	fr.setTitle("Your game results");
    	fr.setLayout(new BorderLayout());
    	JLabel title = new JLabel("Results", SwingConstants.CENTER);
    	title.setFont(new Font("Serif", Font.PLAIN, 44));
    	fr.add(title, BorderLayout.NORTH);
    	JPanel center = new JPanel();
    	center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
    	JLabel score = new JLabel("Your score: "+this.score, SwingConstants.CENTER);
    	score.setFont(new Font("Serif", Font.PLAIN, 34));
    	JLabel hscore = new JLabel("Your Highscore: "+this.Highscore, SwingConstants.CENTER);
    	hscore.setFont(new Font("Serif", Font.PLAIN, 34));
    	
    	this.hist.remove(0);
    	this.hist.sort((x,y)->Integer.compare(x, y));
    	percentileborders.add(0);
    	for(int k = 0; k<percentiles.size();k++) {
    	percentileborders.add(this.hist.get((int) Math.ceil(percentiles.get(k) * this.hist.size()) - 1));}
    	percentileborders.forEach(System.out::println);
    	int leftIndex = Math.abs((Math.abs(Collections.binarySearch(percentileborders, this.score))-2));// - 2
    	int rightIndex = Math.abs((Math.abs(Collections.binarySearch(percentileborders, this.score))- 1));// 
    	System.out.println(leftIndex+", "+rightIndex);
    	JLabel tiles = new JLabel("Compared to your game history, you're between the "+percentiles.get(leftIndex)*100+"th and the "+ percentiles.get(rightIndex)*100+"th percentile!");
        tiles.setFont(new Font("Serif", Font.PLAIN, 30));
    	center.add(score);
        center.add(hscore);
        center.add(tiles);
        fr.add(center, BorderLayout.CENTER);
    	if(rightIndex<3) {
        	fr.setBackground(Color.RED);
        	center.setOpaque(true);
        	center.setBackground(Color.RED);
        	score.setOpaque(true);
        	score.setBackground(Color.RED);
        	hscore.setOpaque(true);
        	hscore.setBackground(Color.RED);
        	tiles.setOpaque(true);
        	tiles.setBackground(Color.RED);
        	title.setOpaque(true);
        	title.setBackground(Color.RED);
        }
        else if(leftIndex>7) {
        	fr.setBackground(Color.GREEN);
        	center.setOpaque(true);
        	center.setBackground(Color.GREEN);
        	score.setOpaque(true);
        	score.setBackground(Color.GREEN);
        	hscore.setOpaque(true);
        	hscore.setBackground(Color.GREEN);
        	tiles.setOpaque(true);
        	tiles.setBackground(Color.GREEN);
        	title.setOpaque(true);
        	title.setBackground(Color.GREEN);
        }
        else {
        	fr.setBackground(Color.YELLOW);
        	center.setOpaque(true);
        	center.setBackground(Color.YELLOW);
        	score.setOpaque(true);
        	score.setBackground(Color.YELLOW);
        	hscore.setOpaque(true);
        	hscore.setBackground(Color.YELLOW);
        	tiles.setOpaque(true);
        	tiles.setBackground(Color.YELLOW);
        	title.setOpaque(true);
        	title.setBackground(Color.YELLOW);
        }
    	fr.pack();
    	fr.setLocationRelativeTo(null);
    	fr.setVisible(true);
    }
    
    public static void main(String[] args) {
    	EndGame x = new EndGame(3);
    }
}
