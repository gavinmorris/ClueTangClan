import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/*
	Display JPanel to show commentary of game.
 */

public class Display extends JFrame {
	
	//Create a JPanel
	JPanel panel = new JPanel();
	
	//Create JTextArea
    	JTextArea textarea = new JTextArea(16, 16);
    
    	//Create JScrollPane and add the JTextArea
    	JScrollPane scrollpane = new JScrollPane(textarea);
	
    	//Constructor for the display panel
	public Display() {
		
		//Cannot edit this area
		textarea.setEditable(false);
		
		//Print onto second line if longer then width
		textarea.setLineWrap(true);
		
		//Layout of JPanel
		panel.setLayout(new BorderLayout());
		
		//Set color
		panel.setBackground(Color.BLACK);
		
		//add scrollPane to the JPanel
		panel.add(scrollpane, BorderLayout.EAST);	
	}

}
