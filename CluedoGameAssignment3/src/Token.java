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
		if(name.equals("green")) {
			this.xcoordinate = Board.greenStartingx;
			this.ycoordinate = Board.greenStartingy;
		}
		else if(name.equals("mustard")) {
			this.xcoordinate = Board.mustardStartingx;
			this.ycoordinate = Board.mustardStartingy;			
		}
		else if(name.equals("peacock")) {
			this.xcoordinate = Board.peacockStartingx;
			this.ycoordinate = Board.peacockStartingy;
		}
		else if(name.equals("plum")) {
			this.xcoordinate = Board.plumStartingx;
			this.ycoordinate = Board.plumStartingy;
		}
		else if(name.equals("scarlett")) {
			this.xcoordinate = Board.scarlettStartingx;
			this.ycoordinate = Board.scarlettStartingy;
		}
		else if(name.equals("white")) {
			this.xcoordinate = Board.whiteStartingx;
			this.ycoordinate = Board.whiteStartingy;
		}
	}
	
	public ImageIcon setLabelIcon(String name) {
		String imageName = null;
		ImageIcon icon = null;
		if(name.equals("green")) {
			imageName = "TokenImages/Green.png";
		}
		else if(name.equals("mustard")) {
			imageName = "TokenImages/Mustard.png";
		}
		else if(name.equals("peacock")) {
			imageName = "TokenImages/Peacock.png";
		}
		else if(name.equals("plum")) {
			imageName = "TokenImages/Plum.png";
		}
		else if(name.equals("scarlett")) {
			imageName = "TokenImages/Scarlett.png";
		}
		else if(name.equals("white")) {
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
	
	
	
	
	
}
