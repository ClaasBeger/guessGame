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

public class Savefile {
	public int randomvariable;
    public Savefile(int x) {
    	this.randomvariable = x;
    }
    public void write(String s) {
    	URL url = getClass().getResource("History.txt");
    	String mainPath = null;
    	try {
			URI uri = url.toURI();
			mainPath = Paths.get(uri).toString();
            mainPath = mainPath.replace("\\bin\\", "\\src\\");
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	try(FileWriter fw= new FileWriter(mainPath, true);//file
    	    BufferedWriter bw = new BufferedWriter(fw);
    	    PrintWriter out = new PrintWriter(bw)) {
    		out.println(s);
    		out.flush();
    		out.close();
    		bw.close();
    		fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	public List<String> read() {
    	List<String> txt = null;
    	try {
    		URL url = getClass().getResource("History.txt");
    		String mainPath = null;
        	try {
    			URI uri = url.toURI();
    			mainPath = Paths.get(uri).toString();
    			mainPath = mainPath.replace("\\bin\\", "\\src\\");
    			System.out.println(mainPath);
    		} catch (URISyntaxException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
			txt = Files.readAllLines(Path.of(mainPath));
			return txt;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
//    public static void main(String[] args) {
//    	Savefile x = new Savefile(7);
//        x.write("testfin");
////    	x.read().forEach(System.out::println);
//    }
}
