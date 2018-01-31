import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Display extends JFrame {
	
	JPanel panel = new JPanel();
    JTextArea textarea = new JTextArea(16, 16);
    JScrollPane scrollpane = new JScrollPane(textarea);
	
	public Display() {
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.BLACK);
		panel.add(scrollpane, BorderLayout.EAST);	
	}

}
