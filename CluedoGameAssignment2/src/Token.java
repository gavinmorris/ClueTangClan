import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Token extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public String name;
	public int xcoordinate, ycoordinate;
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

	
	public boolean moveUp(int i, int moveCounter, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = changeWithBoard(i, moveCounter, 0, -23, 'u', boardstructure, display, textualcommand);
		return error;
	}
	public boolean moveDown(int i, int moveCounter, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = changeWithBoard(i, moveCounter, 0, 23, 'd', boardstructure, display, textualcommand);
		return error;
	}
	public boolean moveLeft(int i, int moveCounter, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = changeWithBoard(i, moveCounter, -23, 0, 'l', boardstructure, display, textualcommand);
		return error;
	}
	public boolean moveRight(int i, int moveCounter, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = changeWithBoard(i, moveCounter, 23, 0, 'r', boardstructure, display, textualcommand);
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
	
	
	public boolean changeWithBoard(int i, int moveCounter, int x, int y, char direction, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = false;
		char type = boardstructure.getCoordinatesType(this.xcoordinate+x, this.ycoordinate+y);
		
		if(type == 'x') {
			//no leaving the board exception
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot leave the board.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(type == 'R') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(type == 'T') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Square already occupied.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(type == '0') {
			boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
			this.xcoordinate += x;
			this.ycoordinate += y;
			this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
			boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
		}
		else if(type == 'u') {
			if(direction == 'u') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.ycoordinate -= 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("up" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
				error = true;
			}
		}
		else if(type == 'd') {//down
			if(direction == 'd') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.ycoordinate += 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("down" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
				error = true;
			}
		}
		else if(type == 'l') {//left
			if(direction == 'l') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.xcoordinate -= 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("left" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
				error = true;
			}
		}
		else if(type == 'r') {
			if(direction == 'r') {
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//fill into slots in rooms
				this.xcoordinate += 46;
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				boardstructure.setCoordinatesType('T', this.xcoordinate, this.ycoordinate);
			} else {
				//cannot enter room this way
				display.textarea.append("right" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
				error = true;
			}
		}

		boardstructure.printTileTypeBoard();
		
		return error;
	}
	
	
	
}
