import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SendMessageButtonListener implements ActionListener {
	
	public int i=0;
	public int done=0;
	public int moveCounter;
	public boolean error=false;
	
	public Board board;
	public Display display;
	public TextualCommand textualcommand;
	public BoardStructure boardstructure;
	
	public SendMessageButtonListener(Board board, Display display, TextualCommand textualcommand, BoardStructure boardstructure) {
		this.board = board;
		this.display = display;
		this.textualcommand = textualcommand;
		this.boardstructure = boardstructure;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		buttonfunction();
		
	}
	
	
	public void buttonfunction() {

		//according to the text entered in the textual command, move each character one box up, down, left or right
		String text = textualcommand.textfield.getText().trim();
				
		
		//according to the text entered in the textual command, move each character one box up, down, left or right
		if(text.equalsIgnoreCase("start")) {
			moveCounter = diceResult();	
			diceRoll(display, textualcommand);
			textualcommand.textfield.setText("");	
		}
		
		else if(text.equalsIgnoreCase("u")) {
			if(moveCounter > 0) {
				error = board.tokenAL.get(i%Main.numPlayers).moveUp(i, moveCounter, boardstructure, display, textualcommand);
				textualcommand.textfield.setText("up");
				if(error == false) {
					moveCounter--;
				}
			}
			appendTextAndTurnInfo();
		} 
		else if(text.equalsIgnoreCase("d")) {
			if(moveCounter > 0) {
				error = board.tokenAL.get(i%Main.numPlayers).moveDown(i, moveCounter, boardstructure, display, textualcommand);
				textualcommand.textfield.setText("down");
				if(error == false) {
					moveCounter--;
				}
			}
			appendTextAndTurnInfo();
		} 
		else if(text.equalsIgnoreCase("l")) {
			if(moveCounter > 0) {
				error = board.tokenAL.get(i%Main.numPlayers).moveLeft(i, moveCounter, boardstructure, display, textualcommand);
				textualcommand.textfield.setText("left");
				if(error == false) {
					moveCounter--;
				}
			}
			appendTextAndTurnInfo();
		}
		
		else if(text.equalsIgnoreCase("r")) {
			if(moveCounter > 0) {
				error = board.tokenAL.get(i%Main.numPlayers).moveRight(i, moveCounter, boardstructure, display, textualcommand);
				textualcommand.textfield.setText("right");
				if(error == false) {
					moveCounter--;
				}
			}
			appendTextAndTurnInfo();
		}
		
		else if(text.equalsIgnoreCase("done")) {
			if(moveCounter == 0) {
				whoseTurn(display, textualcommand);
				done--;
			} else {
				display.textarea.append(textualcommand.textfield.getText()+"\n" + "You still have moves left");
			}
		}

		
		
		//according to the text entered in the textual command, move each weapon to a given room
		
		else if(text.equalsIgnoreCase("move candlestick to kitchen")) {
			moveRoom(board.candlestick, "kitchen");
			board.candlestick.setPosition(Board.kitchenx, Board.kitcheny);	
			textualcommand.textfield.setText("");
			
		}
		else if(text.equalsIgnoreCase("move candlestick to ball room")) {
			moveRoom(board.candlestick, "ball room");
			board.candlestick.setPosition(Board.ballroomx, Board.ballroomy);		
			textualcommand.textfield.setText("");
					
		}
		else if(text.equalsIgnoreCase("move candlestick to conservatory")) {
			moveRoom(board.candlestick, "conservatory");
			board.candlestick.setPosition(Board.conservatoryx, Board.conservatoryy);	
			textualcommand.textfield.setText("");	
				
		}
		else if(text.equalsIgnoreCase("move candlestick to billiard room")) {
			moveRoom(board.candlestick, "billiard room");
			board.candlestick.setPosition(Board.billiardroomx, Board.billiardroomy);	
			textualcommand.textfield.setText("");
			
		}
		else if(text.equalsIgnoreCase("move candlestick to library")) {
			moveRoom(board.candlestick, "library");
			board.candlestick.setPosition(Board.libraryx, Board.libraryy);	
			textualcommand.textfield.setText("");
			
		}
		else if(text.equalsIgnoreCase("move candlestick to study")) {
			moveRoom(board.candlestick, "study");
			board.candlestick.setPosition(Board.studyx, Board.studyy);	
			textualcommand.textfield.setText("");
			
		}
		else if(text.equalsIgnoreCase("move candlestick to hall")) {
			moveRoom(board.candlestick, "hall");
			board.candlestick.setPosition(Board.hallx, Board.hally);	
			textualcommand.textfield.setText("");
			
		} 
		else if(text.equalsIgnoreCase("move candlestick to lounge")) {
			moveRoom(board.candlestick, "lounge");
			board.candlestick.setPosition(Board.loungex, Board.loungey);	
			textualcommand.textfield.setText("");
			
		} 
		else if(text.equalsIgnoreCase("move candlestick to dining room")) {
			moveRoom(board.candlestick, "dining room");
			board.candlestick.setPosition(Board.diningroomx, Board.diningroomy);	
			textualcommand.textfield.setText("");
		}

		else if(text.equalsIgnoreCase("move knife to kitchen")) {
			moveRoom(board.knife, "kitchen");
			board.knife.setPosition(Board.kitchenx, Board.kitcheny);
		} 
		else if(text.equalsIgnoreCase("move knife to ball room")) {
			moveRoom(board.knife, "ball room");
			board.knife.setPosition(Board.ballroomx, Board.ballroomy);	
		}
		else if(text.equalsIgnoreCase("move knife to conservatory")) {
			moveRoom(board.knife, "conservatory");
			board.knife.setPosition(Board.conservatoryx, Board.conservatoryy);
		}
		else if(text.equalsIgnoreCase("move knife to billiard room")) {
			moveRoom(board.knife, "billiard room");
			board.knife.setPosition(Board.billiardroomx, Board.billiardroomy);
		}
		else if(text.equalsIgnoreCase("move knife to library")) {
			moveRoom(board.knife, "library");
			board.knife.setPosition(Board.libraryx, Board.libraryy);
		}
		else if(text.equalsIgnoreCase("move knife to study")) {
			moveRoom(board.knife, "study");
			board.knife.setPosition(Board.studyx, Board.studyy);
		}
		else if(text.equalsIgnoreCase("move knife to hall")) {
			moveRoom(board.knife, "hall");
			board.knife.setPosition(Board.hallx, Board.hally);
		}
		else if(text.equalsIgnoreCase("move knife to lounge")) {
			moveRoom(board.knife, "lounge");
			board.knife.setPosition(Board.loungex, Board.loungey);
		}
		else if(text.equalsIgnoreCase("move knife to dining room")) {
			moveRoom(board.knife, "dining room");
			board.knife.setPosition(Board.diningroomx, Board.diningroomy);
		}

		else if(text.equalsIgnoreCase("move lead pipe to kitchen")) {
			moveRoom(board.leadpipe, "kitchen");
			board.leadpipe.setPosition(Board.kitchenx, Board.kitcheny);
		}
		else if(text.equalsIgnoreCase("move lead pipe to ball room")) {
			moveRoom(board.leadpipe, "ball room");
			board.leadpipe.setPosition(Board.ballroomx, Board.ballroomy);	
		}
		else if(text.equalsIgnoreCase("move lead pipe to conservatory")) {
			moveRoom(board.leadpipe, "conservatory");
			board.leadpipe.setPosition(Board.conservatoryx, Board.conservatoryy);
		}
		else if(text.equalsIgnoreCase("move lead pipe to billiard room")) {
			moveRoom(board.leadpipe, "billiard room");
			board.leadpipe.setPosition(Board.billiardroomx, Board.billiardroomy);
		}
		else if(text.equalsIgnoreCase("move lead pipe to library")) {
			moveRoom(board.leadpipe, "library");
			board.leadpipe.setPosition(Board.libraryx, Board.libraryy);
		}
		else if(text.equalsIgnoreCase("move lead pipe to study")) {
			moveRoom(board.leadpipe, "study");
			board.leadpipe.setPosition(Board.studyx, Board.studyy);
		}
		else if(text.equalsIgnoreCase("move lead pipe to hall")) {
			moveRoom(board.leadpipe, "hall");
			board.leadpipe.setPosition(Board.hallx, Board.hally);
		}
		else if(text.equalsIgnoreCase("move lead pipe to lounge")) {
			moveRoom(board.leadpipe, "lounge");
			board.leadpipe.setPosition(Board.loungex, Board.loungey);
		}
		else if(text.equalsIgnoreCase("move lead pipe to dining room")) {
			moveRoom(board.leadpipe, "dining room");
			board.leadpipe.setPosition(Board.diningroomx, Board.diningroomy);
		}
		
		else if(text.equalsIgnoreCase("move revolver to kitchen")) {
			moveRoom(board.revolver, "kitchen");
			board.revolver.setPosition(Board.kitchenx, Board.kitcheny);
		}
		else if(text.equalsIgnoreCase("move revolver to ball room")) {
			moveRoom(board.revolver, "ball room");
			board.revolver.setPosition(Board.ballroomx, Board.ballroomy);
		}
		else if(text.equalsIgnoreCase("move revolver to conservatory")) {
			moveRoom(board.revolver, "conservatory");
			board.revolver.setPosition(Board.conservatoryx, Board.conservatoryy);
		}
		else if(text.equalsIgnoreCase("move revolver to billiard room")) {
			moveRoom(board.revolver, "billiard room");
			board.revolver.setPosition(Board.billiardroomx, Board.billiardroomy);
		}
		else if(text.equalsIgnoreCase("move revolver to library")) {
			moveRoom(board.revolver, "library");
			board.revolver.setPosition(Board.libraryx, Board.libraryy);
		}
		else if(text.equalsIgnoreCase("move revolver to study")) {
			moveRoom(board.revolver, "study");
			board.revolver.setPosition(Board.studyx, Board.studyy);
		}
		else if(text.equalsIgnoreCase("move revolver to hall")) {
			moveRoom(board.revolver, "hall");
			board.revolver.setPosition(Board.hallx, Board.hally);
		}
		else if(text.equalsIgnoreCase("move revolver to lounge")) {
			moveRoom(board.revolver, "lounge");
			board.revolver.setPosition(Board.loungex, Board.loungey);
		}
		else if(text.equalsIgnoreCase("move revolver to dining room")) {
			moveRoom(board.revolver, "dining room");
			board.revolver.setPosition(Board.diningroomx, Board.diningroomy);
		} 

		else if(text.equalsIgnoreCase("move rope to kitchen")) {
			moveRoom(board.rope, "kitchen");
			board.rope.setPosition(Board.kitchenx, Board.kitcheny);
		}
		else if(text.equalsIgnoreCase("move rope to ball room")) {
			moveRoom(board.rope, "ball room");
			board.rope.setPosition(Board.ballroomx, Board.ballroomy);
		}
		else if(text.equalsIgnoreCase("move rope to conservatory")) {
			moveRoom(board.rope, "conservatory");
			board.rope.setPosition(Board.conservatoryx, Board.conservatoryy);
		}
		else if(text.equalsIgnoreCase("move rope to billiard room")) {
			moveRoom(board.rope, "billiard room");
			board.rope.setPosition(Board.billiardroomx, Board.billiardroomy);
		}
		else if(text.equalsIgnoreCase("move rope to library")) {
			moveRoom(board.rope, "library");
			board.rope.setPosition(Board.libraryx, Board.libraryy);
		}
		else if(text.equalsIgnoreCase("move rope to study")) {
			moveRoom(board.rope, "study");
			board.rope.setPosition(Board.studyx, Board.studyy);
		}
		else if(text.equalsIgnoreCase("move rope to hall")) {
			moveRoom(board.rope, "hall");
			board.rope.setPosition(Board.hallx, Board.hally);
		}
		else if(text.equalsIgnoreCase("move rope to lounge")) {
			moveRoom(board.rope, "lounge");
			board.rope.setPosition(Board.loungex, Board.loungey);
		}
		else if(text.equalsIgnoreCase("move rope to dining room")) {
			moveRoom(board.rope, "dining room");
			board.rope.setPosition(Board.diningroomx, Board.diningroomy);
		}
		
		else if(text.equalsIgnoreCase("move wrench to kitchen")) {
			moveRoom(board.wrench, "kitchen");
			board.wrench.setPosition(Board.kitchenx, Board.kitcheny);
		}
		else if(text.equalsIgnoreCase("move wrench to ball room")) {
			moveRoom(board.wrench, "ball room");
			board.wrench.setPosition(Board.ballroomx, Board.ballroomy);	
		}
		else if(text.equalsIgnoreCase("move wrench to conservatory")) {
			moveRoom(board.wrench, "conservatory");
			board.wrench.setPosition(Board.conservatoryx, Board.conservatoryy);	
		}
		else if(text.equalsIgnoreCase("move wrench to billiard room")) {
			moveRoom(board.wrench, "billiard room");
			board.wrench.setPosition(Board.billiardroomx, Board.billiardroomy);
		}
		else if(text.equalsIgnoreCase("move wrench to library")) {
			moveRoom(board.wrench, "library");
			board.wrench.setPosition(Board.libraryx, Board.libraryy);
		}
		else if(text.equalsIgnoreCase("move wrench to study")) {
			moveRoom(board.wrench, "study");
			board.wrench.setPosition(Board.studyx, Board.studyy);
		}
		else if(text.equalsIgnoreCase("move wrench to hall")) {
			moveRoom(board.wrench, "hall");
			board.wrench.setPosition(Board.hallx, Board.hally);
		}
		else if(text.equalsIgnoreCase("move wrench to lounge")) {
			moveRoom(board.wrench, "lounge");
			board.wrench.setPosition(Board.loungex, Board.loungey);
		}
		else if(text.equalsIgnoreCase("move wrench to dining room")) {
			moveRoom(board.wrench, "dining room");
			board.wrench.setPosition(Board.diningroomx, Board.diningroomy);
		}
		
		else if(text.equalsIgnoreCase("quit")) {
			display.textarea.append("\nGame Status: terminated" );
			System.exit(0);
		}
		else if(text.equalsIgnoreCase("help")) {
			JTextArea help = new JTextArea(
					"-----------Command Instructions-----------\n\n"+
					"Weapon commands work like this:\n\n"+
					"move candlestick to library\n\n"+
					"Player commands work like this:\n\n"+
					"u = move up\nd = move down\nr = move right\nl = move left"+
					"\n\nOther Commands:\nhelp --> opens this panel"+
					"\nquit --> terminates the game"
			);
			JOptionPane.showMessageDialog(null, help);
		}
	}
	
	//alternates whose turn it is to make a move
	public void whoseTurn(Display display, TextualCommand textualcommand) {
			i++;
			moveCounter = diceResult();	
			diceRoll(display, textualcommand);		
			textualcommand.textfield.setText("");
	}
	
	//dice text
	public void diceRoll(Display display, TextualCommand textualcommand) {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "<-------" + Main.playerNames[i%Main.numPlayers]+" rolled the dice ------->\n");	
		display.textarea.append("<" + Main.playerNames[i%Main.numPlayers] + " has " + moveCounter +" moves.>" );
	}
	
	//outputs remaining moves
	public String moveCounterDisplay(int x) {
		return " moves left : " + x + " ";
	}
	
	//generates 2 random ints between 1-6
	public int diceResult() {
		Random rand = new Random();
		Random rand1 = new Random();
		int m = rand.nextInt(6) + 1;
		int n = rand1.nextInt(6) + 1;
		return m + n;
	}
	
	public void moveRoom(Weapon weapon, String room) {
		if (room.equalsIgnoreCase("kitchen")){
			whichRoom(weapon, Board.kitchenx, Board.kitcheny);
		} 
		else if (room.equalsIgnoreCase("ball room")){
			whichRoom(weapon, Board.ballroomx, Board.ballroomy);			
		}
		else if (room.equalsIgnoreCase("conservatory")){
			whichRoom(weapon, Board.conservatoryx, Board.conservatoryy);
		}
		else if (room.equalsIgnoreCase("billiard room")){
			whichRoom(weapon, Board.billiardroomx, Board.billiardroomy);
		}
		else if (room.equalsIgnoreCase("library")){
			whichRoom(weapon, Board.libraryx, Board.libraryy);
		}
		else if (room.equalsIgnoreCase("study")){
			whichRoom(weapon, Board.studyx, Board.studyy);
		}
		else if (room.equalsIgnoreCase("hall")){
			whichRoom(weapon, Board.hallx, Board.hally);
		}
		else if (room.equalsIgnoreCase("lounge")){
			whichRoom(weapon, Board.loungex, Board.loungey);
		}
		else if (room.equalsIgnoreCase("dining room")){
			whichRoom(weapon, Board.diningroomx, Board.diningroomy);
		}
	}
	
	
	public void whichRoom(Weapon weapon, int x, int y) {
		if(board.candlestick.getX() == x && board.candlestick.getY() == y) {
			board.candlestick.setPosition(weapon.getX(), weapon.getY());
		} 
		else if(board.knife.getX() == x && board.knife.getY() == y) {
			board.knife.setPosition(weapon.getX(), weapon.getY());
		} 
		else if(board.leadpipe.getX() == x && board.leadpipe.getY() == y) {
			board.leadpipe.setPosition(weapon.getX(), weapon.getY());
		}
		else if(board.revolver.getX() == x && board.revolver.getY() == y) {
			board.revolver.setPosition(weapon.getX(), weapon.getY());
		}
		else if(board.rope.getX() == x && board.rope.getY() == y) {
			board.rope.setPosition(weapon.getX(), weapon.getY());				
		}
		else if(board.wrench.getX() == x && board.wrench.getY() == y) {
			board.wrench.setPosition(weapon.getX(), weapon.getY());	
		}
	}
	
	//display panel text when player is making moves
	public void appendAndRemove() {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "< "  + Main.playerNames[i%Main.numPlayers] 
																		+ " ("+ moveCounterDisplay(moveCounter) +") "+"> ");
		textualcommand.textfield.setText("");
	}
	
	//Display text depending on number of moves left,
	public void appendTextAndTurnInfo() {

		//prompts user to type done
		if(moveCounter == 0) {
			if(done == 0) {
				display.textarea.append(textualcommand.textfield.getText()+"\n" + "No moves left, type done.");
				display.textarea.append("\n" + "<"  + Main.playerNames[i%Main.numPlayers] +">");
				done++;
			}
		}
		
		//if user inputs commands before pressing start
		else if(moveCounter<0){
			display.textarea.append("\n" + "You must type start to begin!");
		}
		
		//moves still left
		else { 
			if(error == false) {
				appendAndRemove();
			}
		}
		
		textualcommand.textfield.setText("");
	}	
	
}