import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelsInJFrame {
	
	public Board board;
	public Display display;
	public TextualCommand textualcommand;
	
	public JFrame frame;
	
	public JLayeredPane layeredPane;
	
	public PanelsInJFrame() {

		//declare new jframe and panels, ie board, display and texualcommand
		frame = new JFrame("Cluedo");
		frame.setLayout(new BorderLayout());
		
		board = new Board();
		display = new Display();
		textualcommand = new TextualCommand();	
		
		//add the panels to the jframe
		frame.add(display, BorderLayout.EAST);
		frame.add(board, BorderLayout.WEST);
		frame.add(textualcommand, BorderLayout.SOUTH);
		frame.setLocation(0, 0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(900, 720);
		

		
		//activate action listener for transfering text from the textual command to the display panel
		textualcommand.button.addActionListener(new SendMessageButtonListener(board, display, textualcommand));

		textualcommand.textfield.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					textualcommand.button.doClick();
				}
			}
		});	
	}
	
}
