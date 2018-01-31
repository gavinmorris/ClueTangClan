import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class Main {
	
	static Board board;
	static Display display;
	static TextualCommand textualcommand;
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("Cluedo");
		
		board = new Board();
		display = new Display();
		textualcommand = new TextualCommand();
		
		textualcommand.button.addActionListener(new SendMessageButtonListener());		

		display.panel.add(textualcommand.panel, BorderLayout.SOUTH);
		display.panel.add(board.panel, BorderLayout.WEST);
		display.panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.add(display.panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(860, 720);
		
		
		
		//System.exit(0);
	}
	
	


																				
}


