
import javax.swing.JOptionPane;

public class Main {
	
	static int  numPlayers;
	static String[] playerNames;
	
	public static void main(String[] args) {
		 java.awt.EventQueue.invokeLater(new Runnable() {
	          public void run() {
	              //put all the panels into the jframe
	        	  new PanelsInJFrame();
	          }
	    });
		
	}																			
}


