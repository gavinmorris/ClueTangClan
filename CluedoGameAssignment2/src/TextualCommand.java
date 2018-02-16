import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TextualCommand extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public JTextField textfield = new JTextField(30);
	public final JButton button = new JButton("COMPLETE MOVE");
		
	public TextualCommand() {
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.DARK_GRAY);
		
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
		
		this.add(textfield, left);
		this.add(button, right);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
	}
}
