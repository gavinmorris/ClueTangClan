import javax.swing.*;
import java.awt.GridBagLayout;

public class Board extends Main {
	
	ImageIcon image = new ImageIcon("cluedoboard.jpg");
    JLabel label = new JLabel(image);
	JPanel panel = new JPanel();

	public Board() {
		panel.setLayout(new GridBagLayout());
        panel.add(label);	
	}
		
}




