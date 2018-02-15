import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {
	
	static Board board;
	static Display display;
	static TextualCommand textualcommand;
	
	static JFrame frame;
	
	public static void main(String[] args) {
		
		//put all the panels into the jframe
		new PanelsInJFrame();
		
		//activate action listener for transfering text from the textual command to the display panel
		textualcommand.button.addActionListener(new SendMessageButtonListener());

		textualcommand.textfield.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Main.textualcommand.button.doClick();
				}
			}
		});	}
	
																				
}


