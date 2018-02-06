import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagLayout;

public class Board extends JPanel {
	
	ImageIcon image = new ImageIcon("cluedoboard.jpg");
    JLabel label = new JLabel(image);

	public Board() {
		this.setLayout(new GridBagLayout());
        this.add(label);	
        
        this.setBorder(new EmptyBorder(10, 10, 0, 10));
		this.setBackground(Color.DARK_GRAY);
	}
		
}




