import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;

public class SendMessageButtonListener implements ActionListener {
	
	
	public int i=0;
	public int moveCounter;
	public Board board;
	public Display display;
	public TextualCommand textualcommand;
	
	public SendMessageButtonListener(Board board, Display display, TextualCommand textualcommand) {
		this.board = board;
		this.display = display;
		this.textualcommand = textualcommand;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		buttonfunction(board, display, textualcommand);
		
	}
	
	
	public void buttonfunction(Board board, Display display, TextualCommand textualcommand) {

		//according to the text entered in the textual command, move each character one box up, down, left or right
		if(textualcommand.textfield.getText().equals("start")) {
			textualcommand.textfield.setText("");
			moveCounter = diceResult();	
			diceRoll(display, textualcommand);			
			textualcommand.textfield.setText("");
		}
		
		else if(textualcommand.textfield.getText().equals("u") && (i%Main.numPlayers == 0)) {
			board.green.setPosition(board.green.getX(), board.green.getY()-23);
				if(moveCounter == 1) whoseTurn(display, textualcommand);
				else moveCounter--;
					display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 
		else if(textualcommand.textfield.getText().equals("d") && (i%Main.numPlayers == 0)) {
			board.green.setPosition(board.green.getX(), board.green.getY()+23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 
		else if(textualcommand.textfield.getText().equals("l") && (i%Main.numPlayers == 0)) {
			board.green.setPosition(board.green.getX()-23, board.green.getY());	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r") && (i%Main.numPlayers == 0)) {
			board.green.setPosition(board.green.getX()+23, board.green.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}

		else if(textualcommand.textfield.getText().equals("u")&& (i%Main.numPlayers == 1)) {
			board.mustard.setPosition(board.mustard.getX(), board.mustard.getY()-23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("d")&& (i%Main.numPlayers == 1)) {
			board.mustard.setPosition(board.mustard.getX(), board.mustard.getY()+23);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");

		}
		else if(textualcommand.textfield.getText().equals("l")&& (i%Main.numPlayers == 1)) {
			board.mustard.setPosition(board.mustard.getX()-23, board.mustard.getY());	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r")&& (i%Main.numPlayers == 1)) {
			board.mustard.setPosition(board.mustard.getX()+23, board.mustard.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 

		else if(textualcommand.textfield.getText().equals("u")&& (i%Main.numPlayers == 2)) {
			board.peacock.setPosition(board.peacock.getX(), board.peacock.getY()-23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("d")&& (i%Main.numPlayers == 2)) {
			board.peacock.setPosition(board.peacock.getX(), board.peacock.getY()+23);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("l")&& (i%Main.numPlayers == 2)) {
			board.peacock.setPosition(board.peacock.getX()-23, board.peacock.getY());	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r")&& (i%Main.numPlayers == 2)) {
			board.peacock.setPosition(board.peacock.getX()+23, board.peacock.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}

		else if(textualcommand.textfield.getText().equals("u") && (i%Main.numPlayers == 3)) {
			board.plum.setPosition(board.plum.getX(), board.plum.getY()-23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("d")&& (i%Main.numPlayers == 3)) {
			board.plum.setPosition(board.plum.getX(), board.plum.getY()+23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
		}
		else if(textualcommand.textfield.getText().equals("l")&& (i%Main.numPlayers == 3)) {
			board.plum.setPosition(board.plum.getX()-23, board.plum.getY());	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r")&& (i%Main.numPlayers == 3)) {
			board.plum.setPosition(board.plum.getX()+23, board.plum.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 

		else if(textualcommand.textfield.getText().equals("u")&& (i%Main.numPlayers == 4)) {
			board.scarlett.setPosition(board.scarlett.getX(), board.scarlett.getY()-23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("d")&& (i%Main.numPlayers == 4)) {
			board.scarlett.setPosition(board.scarlett.getX(), board.scarlett.getY()+23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("l")&& (i%Main.numPlayers == 4)) {
			board.scarlett.setPosition(board.scarlett.getX()-23, board.scarlett.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r")&& (i%Main.numPlayers == 4)) {
			board.scarlett.setPosition(board.scarlett.getX()+23, board.scarlett.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 

		else if(textualcommand.textfield.getText().equals("u")&& (i%Main.numPlayers == 5)) {
			board.white.setPosition(board.white.getX(), board.white.getY()-23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("d")&& (i%Main.numPlayers == 5)) {
			board.white.setPosition(board.white.getX(), board.white.getY()+23);
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("l")&& (i%Main.numPlayers == 5)) {
			board.white.setPosition(board.white.getX()-23, board.white.getY());	
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		}
		else if(textualcommand.textfield.getText().equals("r")&& (i%Main.numPlayers == 5)) {
			board.white.setPosition(board.white.getX()+23, board.white.getY());
			if(moveCounter == 1) whoseTurn(display, textualcommand);
			else moveCounter--;
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + Main.playerNames[i%Main.numPlayers] +" ("+ moveCounterDisplay(moveCounter) +") "+">");
		} 
		
		//according to the text entered in the textual command, move each weapon to a given room
		
		else if(textualcommand.textfield.getText().equals("move candlestick to kitchen")) {
			moveRoom(board, board.candlestick, "kitchen");
			board.candlestick.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to ball room")) {
			moveRoom(board, board.candlestick, "ball room");
			board.candlestick.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to conservatory")) {
			moveRoom(board, board.candlestick, "conservatory");
			board.candlestick.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to billiard room")) {
			moveRoom(board, board.candlestick, "billiard room");
			board.candlestick.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to library")) {
			moveRoom(board, board.candlestick, "library");
			board.candlestick.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to study")) {
			moveRoom(board, board.candlestick, "study");
			board.candlestick.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move candlestick to hall")) {
			moveRoom(board, board.candlestick, "hall");
			board.candlestick.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		} 
		else if(textualcommand.textfield.getText().equals("move candlestick to lounge")) {
			moveRoom(board, board.candlestick, "lounge");
			board.candlestick.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		} 
		else if(textualcommand.textfield.getText().equals("move candlestick to dining room")) {
			moveRoom(board, board.candlestick, "dining room");
			board.candlestick.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}

		else if(textualcommand.textfield.getText().equals("move knife to kitchen")) {
			moveRoom(board, board.knife, "kitchen");
			board.knife.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		} 
		else if(textualcommand.textfield.getText().equals("move knife to ball room")) {
			moveRoom(board, board.knife, "ball room");
			board.knife.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move knife to conservatory")) {
			moveRoom(board, board.knife, "conservatory");
			board.knife.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move knife to billiard room")) {
			moveRoom(board, board.knife, "billiard room");
			board.knife.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move knife to library")) {
			moveRoom(board, board.knife, "library");
			board.knife.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move knife to study")) {
			moveRoom(board, board.knife, "study");
			board.knife.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move knife to hall")) {
			moveRoom(board, board.knife, "hall");
			board.knife.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move knife to lounge")) {
			moveRoom(board, board.knife, "lounge");
			board.knife.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move knife to dining room")) {
			moveRoom(board, board.knife, "dining room");
			board.knife.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}

		else if(textualcommand.textfield.getText().equals("move lead pipe to kitchen")) {
			moveRoom(board, board.leadpipe, "kitchen");
			board.leadpipe.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to ball room")) {
			moveRoom(board, board.leadpipe, "ball room");
			board.leadpipe.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to conservatory")) {
			moveRoom(board, board.leadpipe, "conservatory");
			board.leadpipe.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to billiard room")) {
			moveRoom(board, board.leadpipe, "billiard room");
			board.leadpipe.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to library")) {
			moveRoom(board, board.leadpipe, "library");
			board.leadpipe.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to study")) {
			moveRoom(board, board.leadpipe, "study");
			board.leadpipe.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to hall")) {
			moveRoom(board, board.leadpipe, "hall");
			board.leadpipe.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to lounge")) {
			moveRoom(board, board.leadpipe, "lounge");
			board.leadpipe.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move lead pipe to dining room")) {
			moveRoom(board, board.leadpipe, "dining room");
			board.leadpipe.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		
		else if(textualcommand.textfield.getText().equals("move revolver to kitchen")) {
			moveRoom(board, board.revolver, "kitchen");
			board.revolver.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to ball room")) {
			moveRoom(board, board.revolver, "ball room");
			board.revolver.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move revolver to conservatory")) {
			moveRoom(board, board.revolver, "conservatory");
			board.revolver.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move revolver to billiard room")) {
			moveRoom(board, board.revolver, "billiard room");
			board.revolver.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to library")) {
			moveRoom(board, board.revolver, "library");
			board.revolver.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to study")) {
			moveRoom(board, board.revolver, "study");
			board.revolver.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to hall")) {
			moveRoom(board, board.revolver, "hall");
			board.revolver.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to lounge")) {
			moveRoom(board, board.revolver, "lounge");
			board.revolver.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move revolver to dining room")) {
			moveRoom(board, board.revolver, "dining room");
			board.revolver.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		} 

		else if(textualcommand.textfield.getText().equals("move rope to kitchen")) {
			moveRoom(board, board.rope, "kitchen");
			board.rope.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to ball room")) {
			moveRoom(board, board.rope, "ball room");
			board.rope.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move rope to conservatory")) {
			moveRoom(board, board.rope, "conservatory");
			board.rope.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move rope to billiard room")) {
			moveRoom(board, board.rope, "billiard room");
			board.rope.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to library")) {
			moveRoom(board, board.rope, "library");
			board.rope.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to study")) {
			moveRoom(board, board.rope, "study");
			board.rope.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to hall")) {
			moveRoom(board, board.rope, "hall");
			board.rope.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to lounge")) {
			moveRoom(board, board.rope, "lounge");
			board.rope.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move rope to dining room")) {
			moveRoom(board, board.rope, "dining room");
			board.rope.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		
		else if(textualcommand.textfield.getText().equals("move wrench to kitchen")) {
			moveRoom(board, board.wrench, "kitchen");
			board.wrench.setPosition(Board.kitchenx, Board.kitcheny);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to ball room")) {
			moveRoom(board, board.wrench, "ball room");
			board.wrench.setPosition(Board.ballroomx, Board.ballroomy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;		
		}
		else if(textualcommand.textfield.getText().equals("move wrench to conservatory")) {
			moveRoom(board, board.wrench, "conservatory");
			board.wrench.setPosition(Board.conservatoryx, Board.conservatoryy);	
			if(moveCounter == 1) whoseTurn(display, textualcommand);;	
		}
		else if(textualcommand.textfield.getText().equals("move wrench to billiard room")) {
			moveRoom(board, board.wrench, "billiard room");
			board.wrench.setPosition(Board.billiardroomx, Board.billiardroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to library")) {
			moveRoom(board, board.wrench, "library");
			board.wrench.setPosition(Board.libraryx, Board.libraryy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to study")) {
			moveRoom(board, board.wrench, "study");
			board.wrench.setPosition(Board.studyx, Board.studyy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to hall")) {
			moveRoom(board, board.wrench, "hall");
			board.wrench.setPosition(Board.hallx, Board.hally);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to lounge")) {
			moveRoom(board, board.wrench, "lounge");
			board.wrench.setPosition(Board.loungex, Board.loungey);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		else if(textualcommand.textfield.getText().equals("move wrench to dining room")) {
			moveRoom(board, board.wrench, "dining room");
			board.wrench.setPosition(Board.diningroomx, Board.diningroomy);
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
		}
		
		
		else if(textualcommand.textfield.getText().length() > 0) {
			if(moveCounter == 1) whoseTurn(display, textualcommand);;
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
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "<-------"+Main.playerNames[i%Main.numPlayers]+" rolled the dice ------->\n");	
		display.textarea.append(textualcommand.textfield.getText() +"<" +Main.playerNames[i%Main.numPlayers]+" has "+ moveCounter +" moves.>" );
	}
	
	//outputs reminaing moves
	public String moveCounterDisplay(int moveCounter) {
		return (moveCounter) +" moves left";
	}
	
	//generates random int between 1-6
	public int diceResult() {
		Random rand = new Random();
		int  n = rand.nextInt(6) + 1;
		return n;
	}
	
	public void moveRoom(Board board, Weapon weapon, String room) {
		if (room.equals("kitchen")){
			whichRoom(board, weapon, Board.kitchenx, Board.kitcheny);
		} 
		else if (room.equals("ball room")){
			whichRoom(board, weapon, Board.ballroomx, Board.ballroomy);			
		}
		else if (room.equals("conservatory")){
			whichRoom(board, weapon, Board.conservatoryx, Board.conservatoryy);
		}
		else if (room.equals("billiard room")){
			whichRoom(board, weapon, Board.billiardroomx, Board.billiardroomy);
		}
		else if (room.equals("library")){
			whichRoom(board, weapon, Board.libraryx, Board.libraryy);
		}
		else if (room.equals("study")){
			whichRoom(board, weapon, Board.studyx, Board.studyy);
		}
		else if (room.equals("hall")){
			whichRoom(board, weapon, Board.hallx, Board.hally);
		}
		else if (room.equals("lounge")){
			whichRoom(board, weapon, Board.loungex, Board.loungey);
		}
		else if (room.equals("dining room")){
			whichRoom(board, weapon, Board.diningroomx, Board.diningroomy);
		}
	}
	
	
	public void whichRoom(Board board, Weapon weapon, int x, int y) {
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
	
	
	
}