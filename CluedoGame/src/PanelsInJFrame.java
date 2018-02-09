import javax.swing.*;
import java.awt.BorderLayout;

public class PanelsInJFrame {
	
	JLayeredPane layeredPane;
	
	public PanelsInJFrame() {

		//declare new jframe and panels, ie board, display and texualcommand
		Main.frame = new JFrame("Cluedo");
		Main.frame.setLayout(new BorderLayout());
		
		Main.board = new Board();
		Main.display = new Display();
		Main.textualcommand = new TextualCommand();	
		
		//add the panels to the jframe
		Main.frame.add(Main.display, BorderLayout.EAST);
		Main.frame.add(Main.board, BorderLayout.WEST);
		Main.frame.add(Main.textualcommand, BorderLayout.SOUTH);
		Main.frame.setLocation(0, 0);
		Main.frame.setResizable(false);
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setVisible(true);
		Main.frame.setSize(862, 720);
	}
	
}