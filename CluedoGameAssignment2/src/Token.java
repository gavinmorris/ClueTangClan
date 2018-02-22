import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Token extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public String name;
	public int xcoordinate, ycoordinate;
	TextualCommand textualcommand = new TextualCommand();
	public ImageIcon imageIcon;
	
	public Token(String name) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    this.setIcon(imageIcon);
		setStartingPosition(name);
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

	
	public boolean moveUp(BoardStructure boardstructure, Display display) {
		boolean error = changeWithBoard(0, -23, 'u', boardstructure, display);
		return error;
	}
	public boolean moveDown(BoardStructure boardstructure, Display display) {
		boolean error = changeWithBoard(0, 23, 'd', boardstructure, display);
		return error;
	}
	public boolean moveLeft(BoardStructure boardstructure, Display display) {
		boolean error = changeWithBoard(-23, 0, 'l', boardstructure, display);
		return error;
	}
	public boolean moveRight(BoardStructure boardstructure, Display display) {
		boolean error = changeWithBoard(23, 0, 'r', boardstructure, display);
		return error;
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
			imageName = "green.png";
		}
		else if(name.equals("mustard")) {
			imageName = "mustard.png";
		}
		else if(name.equals("peacock")) {
			imageName = "peacock.png";
		}
		else if(name.equals("plum")) {
			imageName = "plum.png";
		}
		else if(name.equals("scarlett")) {
			imageName = "scarlett.png";
		}
		else if(name.equals("white")) {
			imageName = "white.png";
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
	
	
	public boolean changeWithBoard(int x, int y, char direction, BoardStructure boardstructure, Display display) {
		boolean error = false;
		char type = boardstructure.getCoordinatesType(this.xcoordinate+x, this.ycoordinate+y);
		
		if(type == 'x') {
			//no leaving the board exception
			display.textarea.append("\nError: Cannot leave the board.\n");
			error = true;
		}
		else if(type == 'R') {
			//no walking through walls
			display.textarea.append("\nError: Cannot enter room this way.\n");
			error = true;
		}
		else if(type == '0') {
			boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
			this.xcoordinate += x;
			this.ycoordinate += y;
			this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
			boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
		}
		else if(y == -23) {//up
			if(direction == 'u') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.ycoordinate -= 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("\nError: Cannot enter room this way.\n");
				error = true;
			}
		}
		else if(y == 23) {//down
			if(direction == 'd') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.ycoordinate += 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("\nError: Cannot enter room this way.\n");
				error = true;
			}
		}
		else if(x == -23) {//left
			if(direction == 'l') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.xcoordinate -= 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("\nError: Cannot enter room this way.\n");
				error = true;
			}
		}
		else if(x == 23) {
			if(direction == 'r') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.xcoordinate += 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("\nError: Cannot enter room this way.\n");	
				error = true;
			}
		}

		boardstructure.printTileTypeBoard();
		
		return error;
	}
	
	
	
}
