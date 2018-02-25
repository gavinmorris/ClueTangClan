
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//declare textarea and scrollpane
    public JTextArea textarea = new JTextArea(20, 20);
    public JScrollPane scrollpane = new JScrollPane(textarea);
    TextualCommand textualcommand = new TextualCommand();
	
	public Display() {

	    String introText = "Display Panel";
		textarea.setEditable(false);	//so that the display panel cannot be changed without using the textual command
		textarea.setLineWrap(true);
		textarea.setText("\n --------------- "  + introText + " ---------------\n"+"Type start to begin.\n");
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(scrollpane);
        this.setBorder(new EmptyBorder(10, 10, 0, 10));
	}

}
