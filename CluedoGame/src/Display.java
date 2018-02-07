import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Display extends JPanel {

    JTextArea textarea = new JTextArea(16, 16);
    JScrollPane scrollpane = new JScrollPane(textarea);
    
    String introText = "Display Panel";
	
	public Display() {
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setText(" ----------- "  + introText + " -----------\n" + "<" + SendMessageButtonListener.username1 + ">");
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(scrollpane);
        this.setBorder(new EmptyBorder(10, 10, 0, 10));	
	}

}
