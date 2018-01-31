import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

/*
	Run the damn thing and constructing the JFrame
 */

public class Main {
	
	//Create all objects for each JPanel Class
	static Board board;
	static Display display;
	static TextualCommand textualcommand;
	
	public static void main(String[] args) {

		//Create JFrame
		JFrame frame = new JFrame("Cluedo");
		
		//New Objects
		board = new Board();
		display = new Display();
		textualcommand = new TextualCommand();
		
		//Attach Listener to the input panel - TextualCommand
		textualcommand.button.addActionListener(new SendMessageButtonListener());		

		//Add TetxualCommand Panel and Board Panel to the DisplayPanel
		display.panel.add(textualcommand.panel, BorderLayout.SOUTH);
		display.panel.add(board.panel, BorderLayout.WEST);
		
		//Thick Border
		display.panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//Frame Options
		frame.add(display.panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(860, 720);
		frame.setResizable(false);
		
		
		
		//System.exit(0);
	}
	
	


																				
}


