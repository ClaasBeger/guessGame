package Cgames;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
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
    public Stats(Path path) {
    	JFrame stat = new JFrame();
    	stat.setTitle("Your statistics");
    	stat.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	stat.setVisible(true);
    	try {
			List<String> txt = Files.readAllLines(path);
			this.SingleHighscoren = txt.get(0).split(" ")[0];
			this.SingleHighscore = Integer.parseInt(txt.get(0).split(" ")[1]);
			this.DoubleHighscoren = txt.get(1).split(" ")[0];
			this.DoubleHighscore = Integer.parseInt(txt.get(1).split(" ")[1]);
			Arrays.asList(txt.get(2).split(" ")).stream().forEach(k -> Integer.parseInt(k));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

  public static void plot(JFrame frame) {
	    int high = 11;
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    frame.setBackground(Color.GRAY);
  	    final XYSeries series = new XYSeries("Guess Game History");
  	    series.add(1.0, 7);
  	    series.add(2.0, 2);
  	    series.add(3.0, 4);
  	    series.add(4.0, 4);
  	    series.add(5.0, 1);
  	    series.add(6.0, 0);
  	    series.add(7.0, 10);
  	    series.add(8.0, 11);
  	    series.add(9.0, 7);
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
	  JFrame frame = new JFrame();
	  plot(frame);
	  frame.setVisible(true);
  }
}

