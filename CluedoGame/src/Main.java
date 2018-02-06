import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class Main {
	
	static Board board;
	static Display display;
	static TextualCommand textualcommand;
	
	static JFrame frame;
	
	public static void main(String[] args) {
		
		new PanelsInJFrame();
		
//		ImageIcon image = new ImageIcon("Candlestick.jpg");
//	    JLabel label = new JLabel(image);
//		
		textualcommand.button.addActionListener(new SendMessageButtonListener());		
//
//	    frame.add(label);
		
	    
	    
	    
	    
//		frame.setSize(870, 730);
		
//		JFrame jframe = new JFrame("Rectangles");
//		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jframe.setVisible(true);
//		jframe.setSize(870, 730);
		
		
		
		//System.exit(0);
	}
	
																				
}


