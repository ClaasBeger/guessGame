package Cgames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
//new writing and reading class works w this setup
public class Wrtertest {
	public int randomvariable;
    public Wrtertest(int x) {
    	this.randomvariable = x;
    }
    public void write() {
    	URL url = getClass().getResource("History.txt");
    	String mainPath = null;
    	try {
			URI uri = url.toURI();
			mainPath = Paths.get(uri).toString();
			Path ath = Paths.get(mainPath);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File file = new File(url.getPath()); //url.getPath()
    	try(FileWriter fw = new FileWriter(mainPath, true);//file
    	    BufferedWriter bw = new BufferedWriter(fw);
    	    PrintWriter out = new PrintWriter(bw)) {
    	    out.println("Test");
    	    //more code
    	    out.println("Testtwo");
    	    //more code
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
//		List<String> lines;
//		try {
//			FileWriter fw = new FileWriter(file, true);
//		    BufferedWriter bw = new BufferedWriter(fw);
//		    PrintWriter out = new PrintWriter(bw);
//		    out.println(endscore);
//		    //more code
//			lines = Files.readAllLines(Path.of(file.getPath()));
//			System.out.println(lines.get(0));
//			Highscore = Integer.parseInt(lines.get(0));
//			lines.remove(0);
//			this.hist = lines.stream().map(k->Integer.parseInt(k)).collect(Collectors.toList());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	try {
			List<String> txt = Files.readAllLines(Path.of(mainPath));//url.getPath()
			txt.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void read() {
    	try {
    		URL url = getClass().getResource("History.txt");
    		File file = new File(url.getPath());
    		String mainPath = null;
        	try {
    			URI uri = url.toURI();
    			mainPath = Paths.get(uri).toString();
    			Path path = Paths.get(mainPath);
    		} catch (URISyntaxException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
			List<String> txt = Files.readAllLines(Path.of(mainPath));
			txt.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
    	Wrtertest x = new Wrtertest(7);
//        x.write();
    	x.read();
    }
}
