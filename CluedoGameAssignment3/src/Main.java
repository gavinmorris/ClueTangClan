import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


public class Main {
	
	public static int numPlayers;
	public static String[] playerNames;
	static ArrayList<String> playerOption = new ArrayList<String>();
	static int tokenChoice;
	
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
			
			playerOption.add("Red");
			playerOption.add("Yellow");
			playerOption.add("Green");
			playerOption.add("White");
			playerOption.add("Purple");
			playerOption.add("Blue");
			
			String playerOptSTR = "";
			
			for(int x=0;x<6;x++) {
				playerOptSTR +=  (x+1)+". "+ playerOption.get(x)+"\n";
			}

			
			int i=0;
			while(i<numPlayers) {
				int curr = i;
				playerNames[i] = JOptionPane.showInputDialog("Please enter name of player " + (i+1) + " (player names must be unique)");
				if(!playerNames[i].equals("")) {
					for(int j=i; j>0; j--) {
						if(playerNames[i].equals(playerNames[j-1])) {
							i--;
						}
					}
				}
				
				if(curr == i) {
					String tokenChoiceSTR = null;
					boolean inRange = false;
					
					while(inRange == false) {
						do{
							tokenChoiceSTR = JOptionPane.showInputDialog("Which token would you like to be (enter number!):\n"+playerOptSTR);
							CheckParse(tokenChoiceSTR);
						}while(CheckParse(tokenChoiceSTR) == -1);
						
						if(Integer.parseInt(tokenChoiceSTR)<= playerOption.size() && Integer.parseInt(tokenChoiceSTR)>0) {
								tokenChoice = Integer.parseInt(tokenChoiceSTR);
								Board.tokenAL.add(new Token(playerOption.get(tokenChoice-1)));
								playerOptSTR = appendStr(tokenChoice, playerOption);
								inRange = true;
							}
						else {
							inRange = false;
						}
					
					}	
				}i++;
			}
			
			for(int c = 0 ;c<playerOption.size(); c++) {
				Board.tokenAL.add(new Token(playerOption.get(c)));
			}
			
		
			//put all the panels into the jframe
			new PanelsInJFrame();
	}

	
	public static String appendStr(int i, ArrayList<String> playerOption) {
		
		String output_str = "";
		playerOption.remove(i-1);
		for(int x=0;x<playerOption.size();x++) {
			output_str +=  (x+1)+". "+ playerOption.get(x)+"\n";
		}
		return output_str;
	}

	public static int  CheckParse(String tokenChoiceSTR) {
		 try {
		        return Integer.parseInt(tokenChoiceSTR);
		    } catch (NumberFormatException e) {
		        return -1;
		    } 
	}

}


