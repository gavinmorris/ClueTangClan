import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
/*
	Builds the input field for the textual commands
 */
public class TextualCommand extends JFrame {

	
	//Create JPanel
	JPanel panel = new JPanel();
	
	//Create JTextField - max char set to 30
	JTextField textfield = new JTextField(30);
	
	//Create JButton with label
	JButton button = new JButton("COMPLETE MOVE");
		
	//Constructor for TetxualCommand input field
	public TextualCommand() {
		
		//toggle JPanel layout
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.BLACK);
		
		/*
		 	Creating GridBag objects (left + right) to fix JTextField + JPanel issues
		 */
		
		GridBagConstraints west = new GridBagConstraints();
		west.anchor = GridBagConstraints.LINE_START;
		west.fill = GridBagConstraints.HORIZONTAL;
		west.weightx = 512.0D;
		west.weighty = 1.0D;
		
		GridBagConstraints east = new GridBagConstraints();
		east.insets = new Insets(0, 10, 0, 0);
		east.anchor = GridBagConstraints.LINE_END;
		east.fill = GridBagConstraints.NONE;
		east.weightx = 1.0D;
		east.weighty = 1.0D;
		
		//adding button and text field to left and right of panel
		panel.add(textfield, west);
		panel.add(button, east);
        
	}
}
