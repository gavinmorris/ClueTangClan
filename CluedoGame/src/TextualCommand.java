import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TextualCommand extends JFrame {

	JPanel panel = new JPanel();
	JTextField textfield = new JTextField(30);
	JButton button = new JButton("COMPLETE MOVE");
		
	public TextualCommand() {
		
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 512.0D;
		left.weighty = 1.0D;
		
		GridBagConstraints right = new GridBagConstraints();
		right.insets = new Insets(0, 10, 0, 0);
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;
		
		panel.add(textfield, left);
		panel.add(button, right);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
	}
}
