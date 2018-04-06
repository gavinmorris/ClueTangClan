import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Token extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public char type;
	
	public String name;
	public int slot;
	public int xcoordinate, ycoordinate;
	public ImageIcon imageIcon;
	
    public ArrayList<Card> myCardsAL = new ArrayList<Card>();
    
    public Notes notes;
    
    public boolean eliminated = false;
	
	public Token(String name) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    //0-8 are rooms, 9 is the basement, so we use 10 to say they are not in a room
	    slot = 10;
	    this.setIcon(imageIcon);
		setStartingPosition(name);
		
		notes = new Notes();
	}
	
	public String getName() {
	    return name;
	}
	public int getX() {
	    return xcoordinate;
	}
	public int getY() {
	    return ycoordinate;
	}
	
	public boolean isEliminated() {
		return eliminated;
	}
	
	public void setStartingPosition(String name) {
		if(name.equals("Green")) {
			this.xcoordinate = Board.greenStartingx;
			this.ycoordinate = Board.greenStartingy;
		}
		else if(name.equals("Mustard")) {
			this.xcoordinate = Board.mustardStartingx;
			this.ycoordinate = Board.mustardStartingy;			
		}
		else if(name.equals("Peacock")) {
			this.xcoordinate = Board.peacockStartingx;
			this.ycoordinate = Board.peacockStartingy;
		}
		else if(name.equals("Plum")) {
			this.xcoordinate = Board.plumStartingx;
			this.ycoordinate = Board.plumStartingy;
		}
		else if(name.equals("Scarlett")) {
			this.xcoordinate = Board.scarlettStartingx;
			this.ycoordinate = Board.scarlettStartingy;
		}
		else if(name.equals("White")) {
			this.xcoordinate = Board.whiteStartingx;
			this.ycoordinate = Board.whiteStartingy;
		}
	}
	
	public ImageIcon setLabelIcon(String name) {
		String imageName = null;
		ImageIcon icon = null;
		if(name.equals("Green")) {
			imageName = "TokenImages/Green.png";
		}
		else if(name.equals("Mustard")) {
			imageName = "TokenImages/Mustard.png";
		}
		else if(name.equals("Peacock")) {
			imageName = "TokenImages/Peacock.png";
		}
		else if(name.equals("Plum")) {
			imageName = "TokenImages/Plum.png";
		}
		else if(name.equals("Scarlett")) {
			imageName = "TokenImages/Scarlett.png";
		}
		else if(name.equals("White")) {
			imageName = "TokenImages/White.png";
		}

		BufferedImage imageBuffered = null;
		try {
			imageBuffered = ImageIO.read(this.getClass().getResource(imageName));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}

		icon = new ImageIcon(imageBuffered);
		return icon;
	}

	
	
	public static int findCharacter(String name) {
		int j = 0;
		
		for(j = 0;j<6;j++) {
			if(name == Board.tokenAL.get(j).name) {
				break;
			}
		}
		
		return j;
	}
	
	
	
	public void displayXCardsOnNotes(int playerNum) {
		for(int i=0; i<myCardsAL.size(); i++) {
			int cardNum=0;
			
			if(myCardsAL.get(i).cardName.equals("green")) {
				cardNum=0; 
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("mustard")) {					
				cardNum=1;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("peacock")) {					
				cardNum=2;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("plum")) {					
				cardNum=3;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("scarlett")) {					
				cardNum=4;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("white")) {					
				cardNum=5;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			

			else if(myCardsAL.get(i).cardName.equals("candlestick")) {					
				cardNum=6;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("knife")) {					
				cardNum=7;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("lead pipe")) {					
				cardNum=8;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("revolver")) {					
				cardNum=9;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("rope")) {					
				cardNum=10;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("wrench")) {					
				cardNum=11;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			

			else if(myCardsAL.get(i).cardName.equals("ball room")) {					
				cardNum=12;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("billiard room")) {					
				cardNum=13;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("conservatory")) {					
				cardNum=14;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("dining room")) {					
				cardNum=15;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("hall")) {					
				cardNum=16;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("kitchen")) {					
				cardNum=17;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("library")) {					
				cardNum=18;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("lounge")) {
				cardNum=19;
				fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(myCardsAL.get(i).cardName.equals("study")) {
				cardNum=20;
				fillRowWithXOnNotes(cardNum, playerNum);
			}	
			
		}
		
	}
	
	public void fillRowWithXOnNotes(int cardNum, int playerNum) {
	
		for(int a=0; a<Main.numPlayers; a++) {
			if(a==playerNum) {
				notes.notesArray[cardNum][a] = new JLabel(notes.tickImageIcon);
			} else {
				notes.notesArray[cardNum][a] = new JLabel(notes.XImageIcon);
			}
			notes.layeredPane.add(notes.notesArray[cardNum][a], JLayeredPane.PALETTE_LAYER);
		    int i = (int) notes.notesPositionXArray[cardNum][a];
		    int j = (int) notes.notesPositionYArray[cardNum][a];
			notes.notesArray[cardNum][a].setBounds(i, j, notes.XImageIcon.getIconWidth()-1, notes.XImageIcon.getIconHeight()-1); 
		}
		
	}
	
	public void fillCellWithXOnNotes(int cardNum, int playerNum) {
			
		notes.notesArray[cardNum][playerNum] = new JLabel(notes.XImageIcon);
		
		notes.layeredPane.add(notes.notesArray[cardNum][playerNum], JLayeredPane.PALETTE_LAYER);
	    int i = (int) notes.notesPositionXArray[cardNum][playerNum];
	    int j = (int) notes.notesPositionYArray[cardNum][playerNum];
		notes.notesArray[cardNum][playerNum].setBounds(i, j, notes.XImageIcon.getIconWidth()-1, notes.XImageIcon.getIconHeight()-1); 
		
	}
	
	
	public void displayACardsOnNotes(ArrayList<Card> visibleCardAL) {

		for(int i=0; i<visibleCardAL.size(); i++) {
			int cardNum=0;
			
			if(visibleCardAL.get(i).cardName.equals("green")) {
				cardNum=0; 
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("mustard")) {					
				cardNum=1;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("peacock")) {					
				cardNum=2;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("plum")) {					
				cardNum=3;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("scarlett")) {					
				cardNum=4;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("white")) {					
				cardNum=5;
				fillRowWithAOnNotes(cardNum);
			}
			

			else if(visibleCardAL.get(i).cardName.equals("candlestick")) {					
				cardNum=6;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("knife")) {					
				cardNum=7;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("lead pipe")) {					
				cardNum=8;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("revolver")) {					
				cardNum=9;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("rope")) {					
				cardNum=10;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("wrench")) {					
				cardNum=11;
				fillRowWithAOnNotes(cardNum);
			}
			

			else if(visibleCardAL.get(i).cardName.equals("ball room")) {					
				cardNum=12;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("billiard room")) {					
				cardNum=13;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("conservatory")) {					
				cardNum=14;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("dining room")) {					
				cardNum=15;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("hall")) {					
				cardNum=16;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("kitchen")) {					
				cardNum=17;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("library")) {					
				cardNum=18;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("lounge")) {
				cardNum=19;
				fillRowWithAOnNotes(cardNum);
			}
			else if(visibleCardAL.get(i).cardName.equals("study")) {
				cardNum=20;
				fillRowWithAOnNotes(cardNum);
			}
			
		}
		
	}
	
	public void fillRowWithAOnNotes(int b) {
		for(int k=0; k<Main.numPlayers; k++) {
			for(int a=0; a<Main.numPlayers; a++) {
				notes.notesArray[b][a] = new JLabel(notes.AImageIcon);
				notes.layeredPane.add(notes.notesArray[b][a], JLayeredPane.PALETTE_LAYER);
			    int i = (int) notes.notesPositionXArray[b][a];
			    int j = (int) notes.notesPositionYArray[b][a];
				notes.notesArray[b][a].setBounds(i, j, notes.XImageIcon.getIconWidth()-1, notes.XImageIcon.getIconHeight()-1); 
			}
		}
	}	
	
	
}
