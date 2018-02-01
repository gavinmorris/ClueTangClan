import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Display extends JFrame {
	
	JPanel panel = new JPanel();
    JTextArea textarea = new JTextArea(16, 16);
    JScrollPane scrollpane = new JScrollPane(textarea);
    
    String username1 = "jem";
	
	public Display() {
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setText("<"  + username1 +  ">");
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.DARK_GRAY);
		panel.add(scrollpane);
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));	
	}

}
