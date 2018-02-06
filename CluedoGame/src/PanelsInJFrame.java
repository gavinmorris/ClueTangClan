import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

public class PanelsInJFrame {
	
	JLayeredPane layeredPane;
	
	public PanelsInJFrame() {

		Main.frame = new JFrame("Cluedo");
		Main.frame.setLayout(new BorderLayout());
		
		Main.board = new Board();
		Main.display = new Display();
		Main.textualcommand = new TextualCommand();	
		
		Main.frame.add(Main.display, BorderLayout.EAST);
		Main.frame.add(Main.board, BorderLayout.WEST);
		Main.frame.add(Main.textualcommand, BorderLayout.SOUTH);
		Main.frame.setLocation(250, 0);
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setVisible(true);
		Main.frame.setSize(870, 730);
	}
	
}
