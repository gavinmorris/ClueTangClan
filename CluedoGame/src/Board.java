import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagLayout;

public class Board extends Main {
	
	ImageIcon image = new ImageIcon("cluedoboard.jpg");
    JLabel label = new JLabel(image);
	JPanel panel = new JPanel();

	public Board() {
		panel.setLayout(new GridBagLayout());
        panel.add(label);	
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));
		panel.setBackground(Color.DARK_GRAY);
	}
		
}




