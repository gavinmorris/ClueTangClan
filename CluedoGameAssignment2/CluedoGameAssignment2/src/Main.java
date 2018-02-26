import javax.swing.JOptionPane;


public class Main {
	
	static int  numPlayers;
	static String[] playerNames;
	
	public static void main(String[] args) {
			
			String numPlayersSTR;
			do {
				numPlayersSTR = JOptionPane.showInputDialog("Please enter the number of players you wish to use. (Between 2 - 6)");
			}while(Integer.parseInt(numPlayersSTR) <2 || Integer.parseInt(numPlayersSTR) > 6);
			
			numPlayers = Integer.parseInt(numPlayersSTR);
			
			playerNames = new String[numPlayers];
			
			for(int i=0;i<numPlayers;i++) {
				playerNames[i] = JOptionPane.showInputDialog("Please enter name of player "+(i+1));
			}
			
			//put all the panels into the jframe
			new PanelsInJFrame();
		
		}	  			 		
}



