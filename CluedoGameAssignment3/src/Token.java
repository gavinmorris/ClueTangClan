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
	
	public Token(String name) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    slot = 0;
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
	
	public void setStartingPosition(String name) {
		if(name.equals("Green")) {
			this.xcoordinate = Board.greenStartingx;
			this.ycoordinate = Board.greenStartingy;
		}
		else if(name.equals("Yellow")) {
			this.xcoordinate = Board.mustardStartingx;
			this.ycoordinate = Board.mustardStartingy;			
		}
		else if(name.equals("Blue")) {
			this.xcoordinate = Board.peacockStartingx;
			this.ycoordinate = Board.peacockStartingy;
		}
		else if(name.equals("Purple")) {
			this.xcoordinate = Board.plumStartingx;
			this.ycoordinate = Board.plumStartingy;
		}
		else if(name.equals("Red")) {
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
		else if(name.equals("Yellow")) {
			imageName = "TokenImages/Mustard.png";
		}
		else if(name.equals("Blue")) {
			imageName = "TokenImages/Peacock.png";
		}
		else if(name.equals("Purple")) {
			imageName = "TokenImages/Plum.png";
		}
		else if(name.equals("Red")) {
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
	
	
	
	public void displayXCards(int k) {
		for(int i=0; i<myCardsAL.size(); i++) {
			int b=0;
			
			if(myCardsAL.get(i).cardName.equals("green")) {
				b=0; 
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("mustard")) {					
				b=1;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("peacock")) {					
				b=2;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("plum")) {					
				b=3;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("scarlett")) {					
				b=4;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("white")) {					
				b=5;
				fillRowWithX(b, k);
			}
			

			else if(myCardsAL.get(i).cardName.equals("candlestick")) {					
				b=6;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("knife")) {					
				b=7;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("lead pipe")) {					
				b=8;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("revolver")) {					
				b=9;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("rope")) {					
				b=10;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("wrench")) {					
				b=11;
				fillRowWithX(b, k);
			}
			

			else if(myCardsAL.get(i).cardName.equals("ball room")) {					
				b=12;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("billiard room")) {					
				b=13;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("conservatory")) {					
				b=14;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("dining room")) {					
				b=15;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("hall")) {					
				b=16;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("kitchen")) {					
				b=17;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("library")) {					
				b=18;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("lounge")) {
				b=19;
				fillRowWithX(b, k);
			}
			else if(myCardsAL.get(i).cardName.equals("study")) {
				b=20;
				fillRowWithX(b, k);
			}	
			
		}
		
	}
	
	public void fillRowWithX(int b, int k) {
	
		for(int a=0; a<Main.numPlayers; a++) {
			if(a==k) {
				notes.notesArray[b][a] = new JLabel(notes.tickImageIcon);
			} else {
				notes.notesArray[b][a] = new JLabel(notes.XImageIcon);
			}
			notes.layeredPane.add(notes.notesArray[b][a], JLayeredPane.PALETTE_LAYER);
		    int i = (int) notes.notesPositionXArray[b][a];
		    int j = (int) notes.notesPositionYArray[b][a];
			notes.notesArray[b][a].setBounds(i, j, notes.XImageIcon.getIconWidth()-1, notes.XImageIcon.getIconHeight()-1); 
		}
		
	}
	
	
	public void displayACards(ArrayList<Card> visibleCardAL) {

		for(int i=0; i<visibleCardAL.size(); i++) {
			int b=0;
			
			if(visibleCardAL.get(i).cardName.equals("green")) {
				b=0; 
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("mustard")) {					
				b=1;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("peacock")) {					
				b=2;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("plum")) {					
				b=3;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("scarlett")) {					
				b=4;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("white")) {					
				b=5;
				fillRowWithA(b);
			}
			

			else if(visibleCardAL.get(i).cardName.equals("candlestick")) {					
				b=6;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("knife")) {					
				b=7;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("lead pipe")) {					
				b=8;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("revolver")) {					
				b=9;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("rope")) {					
				b=10;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("wrench")) {					
				b=11;
				fillRowWithA(b);
			}
			

			else if(visibleCardAL.get(i).cardName.equals("ball room")) {					
				b=12;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("billiard room")) {					
				b=13;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("conservatory")) {					
				b=14;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("dining room")) {					
				b=15;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("hall")) {					
				b=16;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("kitchen")) {					
				b=17;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("library")) {					
				b=18;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("lounge")) {
				b=19;
				fillRowWithA(b);
			}
			else if(visibleCardAL.get(i).cardName.equals("study")) {
				b=20;
				fillRowWithA(b);
			}
			
		}
		
	}
	
	public void fillRowWithA(int b) {
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
