package Cgames;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Stats {
  private int SingleHighscore;
  private int DoubleHighscore;
  private String SingleHighscoren;
  private String DoubleHighscoren;
  private List<Integer> singleH;
  private JFrame fr;
    public Stats() {
    	JFrame stat = new JFrame();
    	stat.setTitle("Your statistics");
    	stat.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	this.fr = stat;
//    	try {
//			List<String> txt = Files.readAllLines(path);
//			this.SingleHighscoren = txt.get(0).split(" ")[0];
//			this.SingleHighscore = Integer.parseInt(txt.get(0).split(" ")[1]);
//			this.DoubleHighscoren = txt.get(1).split(" ")[0];
//			this.DoubleHighscore = Integer.parseInt(txt.get(1).split(" ")[1]);
//			Arrays.asList(txt.get(2).split(" ")).stream().forEach(k -> Integer.parseInt(k));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

  public  void plot() {
	    JFrame frame = this.fr;
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    frame.setBackground(Color.GRAY);
  	    final XYSeries series = new XYSeries("Guess Game History");
  	    Savefile s = new Savefile(7);
		List<String> scores = s.read();
		List<String> scorecarrier = scores;
		scorecarrier.forEach(k->series.add(scorecarrier.indexOf(k)+1, Integer.parseInt(k)));
		int high = Integer.parseInt(scores.stream().max((x,y)->Integer.compare(Integer.parseInt(x), Integer.parseInt(y))).get());
  	    final XYSeriesCollection data = new XYSeriesCollection(series);
  	    final JFreeChart chart = ChartFactory.createXYLineChart(
  	        "HighScore: "+high,
  	        "Games", 
  	        "Score", 
  	        data,
  	        PlotOrientation.VERTICAL,
  	        true,
  	        true,
  	        false
  	    );

  	    final ChartPanel chartPanel = new ChartPanel(chart);
  	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
  	    frame.setContentPane(chartPanel);
  	    frame.setVisible(true);

  	}
  public static void main(String[] args) {
	 Stats x = new Stats();
	  x.plot();
  }
}

