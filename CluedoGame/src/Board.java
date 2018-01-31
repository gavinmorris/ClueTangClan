import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/*
	Builds the board image into a JPanel
 */


public class Board extends Main {
	
	//import image as an ImageIcon
	ImageIcon image = new ImageIcon("cluedoboard.jpg");
	
	//Put image into a JLabel
    JLabel label = new JLabel(image);
    
    //Create a JPanel
	JPanel panel = new JPanel();

	
	//Constructor for the display panel
	public Board() {
		
		//Layout of JPanel
		panel.setLayout(new GridBagLayout());
		
		//add JLabel to the JPanel
        panel.add(label);	
	}
		
}




