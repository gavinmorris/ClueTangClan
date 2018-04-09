import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Log extends JPanel{

	
	public JLayeredPane layeredPane;

	private static final long serialVersionUID = 1L;
	public JTextArea logText = new JTextArea();
	public JScrollPane scrollpane = new JScrollPane(logText);
	
	public Log() {
		
		logText.setEditable(false);	//so that the display panel cannot be changed without using the textual command
		logText.setLineWrap(true);
		logText.append("\n ---------Question Log---------\n\n");
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(scrollpane);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
}
