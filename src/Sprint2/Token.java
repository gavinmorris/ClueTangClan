package Sprint2;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Token extends JLabel{

	private static final long serialVersionUID = 1L;
	public static BufferedImage imageBuffered;
	public char type;
	
	public String name;
	public int slot;
	public int xcoordinate, ycoordinate;
	public ImageIcon imageIcon;
	
	public Token(String name) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    slot = 0;
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
	public int getSlot() {
		return slot;
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
			imageName = "Green.png";
		}
		else if(name.equals("mustard")) {
			imageName = "Mustard.png";
		}
		else if(name.equals("peacock")) {
			imageName = "Peacock.png";
		}
		else if(name.equals("plum")) {
			imageName = "Plum.png";
		}
		else if(name.equals("scarlett")) {
			imageName = "Scarlett.png";
		}
		else if(name.equals("white")) {
			imageName = "White.png";
		}

		
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
		type = boardstructure.getCoordinatesType(this.xcoordinate+x, this.ycoordinate+y);

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
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//Put Token into room slot
				uRoomCheck();
				//Move Token icon 
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				//Set board state
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
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//Put Token into room slot
				dRoomCheck();
				//Move Token icon 
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				//Set board state
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
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//Put Token into room slot
				lRoomCheck();
				//Move Token icon 
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				//Set board state
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
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', this.xcoordinate, this.ycoordinate);
				//Put Token into room slot
				rRoomCheck();
				//Move Token icon 
				this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				//Set board state
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
	
	public void uRoomCheck() {
		if(xcoordinate == Board.KitchenEntrancex && ycoordinate == Board.KitchenEntrancey) {
			AddtoKitchen();
		}
		else if(xcoordinate == Board.BallRoomEntrance2x && ycoordinate == Board.BallRoomEntrance2y) {
			AddtoBallRoom();
		}
		else if(xcoordinate == Board.BallRoomEntrance3x && ycoordinate == Board.BallRoomEntrance3y) {
			AddtoBallRoom();
		}
		else if(xcoordinate == Board.ConservatoryEntrancex && ycoordinate == Board.ConservatoryEntrancey) {
			AddtoConservatory();
		}
		else if(xcoordinate == Board.DiningRoomEntrance2x && ycoordinate == Board.DiningRoomEntrance2y) {
			AddtoDiningRoom();
		}
		else {
			AddtoBilliardRoom();
		}
	}
	
	public void dRoomCheck() {
		if(xcoordinate == Board.LibraryEntrance1x && ycoordinate == Board.LibraryEntrance1y) {
			AddtoLibrary();
		}
		else if(xcoordinate == Board.LoungeEntrancex && ycoordinate == Board.LoungeEntrancey) {
			AddtoLounge();
		}
		else if(xcoordinate == Board.HallEntrance1x && ycoordinate == Board.HallEntrance1y) {
			AddtoHall();
		}
		else if(xcoordinate == Board.HallEntrance2x && ycoordinate == Board.HallEntrance2y) {
			AddtoHall();
		}
		else {
			AddtoStudy();
		}
	}
	
	public void lRoomCheck() {
		if(xcoordinate == Board.BallRoomEntrance1x && ycoordinate == Board.BallRoomEntrance1y) {
			AddtoBallRoom();
		}
		else if(xcoordinate == Board.BilliardRoomEntrance1x && ycoordinate == Board.BilliardRoomEntrance1y) {
			AddtoBilliardRoom();
		}
		else {
			AddtoLibrary();
		}
	}
	
	public void rRoomCheck() {
		if(xcoordinate == Board.BallRoomEntrance4x && ycoordinate == Board.BallRoomEntrance4y) {
			AddtoBallRoom();
		}
		else if(xcoordinate == Board.DiningRoomEntrance1x && ycoordinate == Board.DiningRoomEntrance1y) {
			AddtoDiningRoom();
		}
		else {
			AddtoHall();
		}
	}
	
	public void AddtoKitchen() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.KitchenSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 1;
				//Set slot as occupied
				Board.KitchenSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.KitchenSlots[1][i];
				this.ycoordinate = Board.KitchenSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoBallRoom() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.BallRoomSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 2;
				//Set slot as occupied
				Board.BallRoomSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.BallRoomSlots[1][i];
				this.ycoordinate = Board.BallRoomSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoConservatory() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.ConservatorySlots[0][i] == 0) {
				//Set Token as in Room
				slot = 3;
				//Set slot as occupied
				Board.ConservatorySlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.ConservatorySlots[1][i];
				this.ycoordinate = Board.ConservatorySlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoDiningRoom() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.DiningRoomSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 4;
				//Set slot as occupied
				Board.DiningRoomSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.DiningRoomSlots[1][i];
				this.ycoordinate = Board.DiningRoomSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoBilliardRoom() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.BilliardRoomSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 5;
				//Set slot as occupied
				Board.BilliardRoomSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.BilliardRoomSlots[1][i];
				this.ycoordinate = Board.BilliardRoomSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoLibrary() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.LibrarySlots[0][i] == 0) {
				//Set Token as in Room
				slot = 6;
				//Set slot as occupied
				Board.LibrarySlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.LibrarySlots[1][i];
				this.ycoordinate = Board.LibrarySlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoLounge() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.LoungeSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 7;
				//Set slot as occupied
				Board.LoungeSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.LoungeSlots[1][i];
				this.ycoordinate = Board.LoungeSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoHall() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.HallSlots[0][i] == 0) {
				//Set Token as in Room
				slot = 8;
				//Set slot as occupied
				Board.HallSlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.HallSlots[1][i];
				this.ycoordinate = Board.HallSlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
	
	public void AddtoStudy() {
		//Find an available slot
		for(int i = 0; i<6;i++) {
			if(Board.StudySlots[0][i] == 0) {
				//Set Token as in Room
				slot = 9;
				//Set slot as occupied
				Board.StudySlots[0][i] = 1;
				//Move the token to the slot
				this.xcoordinate = Board.StudySlots[1][i];
				this.ycoordinate = Board.StudySlots[2][i];
				
				//Exit the loop
				break;
			}
		}
	}
}
