package swingdudu;
 
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class TSwing extends JFrame {
 
    public static void main(String args[]) throws IOException
    {
    	JFrame frame = new JFrame("FRAME");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 150);
	    frame.setVisible(true);
	    JPanel panel = new JPanel();
    	JTextArea bob = new JTextArea(5,20);
    	Scanner in = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaBooks.txt"));
    	String b = "";
    	int x = 0;
    	while(in.hasNextLine())
    	{
    		b += in.nextLine() + "\n";
    		x++;
    		if(x % 3 == 0)
    			b += "\n";
    		
    	}
    	bob.setText(b);
    	bob.setEditable(false);
    	panel.add(bob);
    	
    	frame.add(panel);
    	
    	
    	
    	
    }
 
}