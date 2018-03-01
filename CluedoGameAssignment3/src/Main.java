import javax.swing.JOptionPane;


public class Main {
	
	public static int numPlayers;
	public static String[] playerNames;
	
	public static void main(String[] args) {
			
			String numPlayersSTR = null;
			boolean isInt = false;
			while(isInt == false) {
				numPlayersSTR = JOptionPane.showInputDialog("Please enter the number of players you wish to use. (Between 2 - 6)");
				if(numPlayersSTR.equals("2") || numPlayersSTR.equals("3") || numPlayersSTR.equals("4") || numPlayersSTR.equals("5") || numPlayersSTR.equals("6")){
					isInt = true;
				}
			}			
			numPlayers = Integer.parseInt(numPlayersSTR);
			
			playerNames = new String[numPlayers];
			
			int i=0;
			while(i<numPlayers) {
				playerNames[i] = JOptionPane.showInputDialog("Please enter name of player "+(i+1));
				if(!playerNames[i].equals("")) {
					for(int j=i; j>0; j--) {
						if(playerNames[i].equals(playerNames[j-1])) {
							i--;
						}
					}
					i++;
				}
			}
			
			//put all the panels into the jframe
			new PanelsInJFrame();
		
		}	  			 		
}



