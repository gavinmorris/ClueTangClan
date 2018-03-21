import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SendMessageButtonListener implements ActionListener {
	
	public static int i=0;
	public int done=0;
	public int gameStage = 0;
	public String text;
	public static int moveCounter;
	public boolean error=false;
	public boolean notesVisible=false;
	public boolean murderEnvelopeVisible = false;
	
	public Board board;
	public Display display;
	public TextualCommand textualcommand;
	public BoardStructure boardstructure;
	public MurderEnvelope murderenvelope;
	public Notes notes;
	
	public SendMessageButtonListener(Board board, Display display, TextualCommand textualcommand, BoardStructure boardstructure, MurderEnvelope murderenvelope, Notes notes) {
		this.board = board;
		this.display = display;
		this.textualcommand = textualcommand;
		this.boardstructure = boardstructure;
		this.murderenvelope = murderenvelope;
		this.notes = notes;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		//according to the text entered in the textual command, move each character one box up, down, left or right
		text = textualcommand.textfield.getText().trim();
		
		
		//according to the text entered in the textual command, move each character one box up, down, left or right
		if(gameStage == 0) {
			if(text.equalsIgnoreCase("start")) {
				whoGoesFirst();
				moveCounter = diceResult();
				diceRoll(display, textualcommand);
				textualcommand.textfield.setText("");
				gameStage++;

				PanelsInJFrame.layeredPane.remove(notes);
				notes = board.tokenAL.get(i%Main.numPlayers).notes;
				notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
				PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
				notes.setVisible(false);
			}
		}
		
		if(gameStage == 1) {	
			if(board.tokenAL.get(i%Main.numPlayers).slot != 0) {				
				ExitRoom();
			}
			else {
				if(text.equalsIgnoreCase("u")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("up");
						error = moveUp(i, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				} 
				else if(text.equalsIgnoreCase("d")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("down");
						error = moveDown(i, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				} 
				else if(text.equalsIgnoreCase("l")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("left");
						error = moveLeft(i, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				}
				
				else if(text.equalsIgnoreCase("r")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("right");
						error = moveRight(i, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				}
				
			}
			
			if(text.equalsIgnoreCase("done")) {
				if(moveCounter == 0) {
					i++;
					display.textarea.append(textualcommand.textfield.getText()+"\n" + "It is now "+ Main.playerNames[i%Main.numPlayers] +"'s turn.\n");
					display.textarea.append("\n" + "Type 'roll' to roll the dice.");
					textualcommand.textfield.setText("");
				} else {
					display.textarea.append(textualcommand.textfield.getText()+"\n" + "You still have moves left");
					textualcommand.textfield.setText("");
				}
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
				textualcommand.textfield.setText("");
			}
			else if(text.equalsIgnoreCase("notes")) {
				if(notesVisible == false) {
					notes.setVisible(true);
					notesVisible = true;
				}
				else if(notesVisible == true) {
					notes.setVisible(false);
					notesVisible = false;
					textualcommand.textfield.setText("");
				}
			}
			else if(text.equalsIgnoreCase("cheat")) {
				if(murderEnvelopeVisible == false) {
					murderenvelope.setVisible(true);
					murderEnvelopeVisible = true;
				}
				else if(murderEnvelopeVisible == true) {
					murderenvelope.setVisible(false);
					murderEnvelopeVisible = false;
					textualcommand.textfield.setText("");
				}
			}

			else if(text.equalsIgnoreCase("quit")) {
				display.textarea.append("\nGame Status: terminated" );
				System.exit(0);
			}
			else if(text.equalsIgnoreCase("roll")) {
				textualcommand.textfield.setText("");
				whoseTurn(display, textualcommand);
				done--;
			}
			
			weaponMoves();
		}	
	}		
	
	
	
	int MovesinTurn;
	
	public void whoGoesFirst() {
		int[] saveResults = new int[4];
		for(int i=0;i<Main.numPlayers;i++) {
			int result = diceResult();
			saveResults[i] = result;
			display.textarea.append("\n"+Main.playerNames[i]+" rolled: " +result);
		}
		int max = saveResults[0];
		int bigIndex = 0;
		for (int i = 1; i < saveResults.length; i++) {
		    if (saveResults[i] > max) {
		      max = saveResults[i];
		      bigIndex = i;
		    }
		}
		
		display.textarea.append("\n"+Main.playerNames[bigIndex]+" will go first.\n");
		i = bigIndex;
		
	}
	
	
	//alternates whose turn it is to make a move
	public void whoseTurn(Display display, TextualCommand textualcommand) {
			i++;
			moveCounter = diceResult();
			MovesinTurn = moveCounter;
			diceRoll(display, textualcommand);		
			textualcommand.textfield.setText("");

			PanelsInJFrame.layeredPane.remove(notes);
			notes = board.tokenAL.get(i%Main.numPlayers).notes;
			notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
			PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
			notes.setVisible(false);
			notesVisible = false;
	}
	
	//dice text
	public void diceRoll(Display display, TextualCommand textualcommand) {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "<-------" + Main.playerNames[i%Main.numPlayers]+" rolled the dice ------->\n");	
		display.textarea.append("<" + Main.playerNames[i%Main.numPlayers] + " has " + moveCounter +" moves>" );
		
		if(board.tokenAL.get(i%Main.numPlayers).slot != 0) {				
			PrintExitOption();
		}
	}
	
	public void PrintExitOption() {
		if(board.tokenAL.get(i%Main.numPlayers).slot == 1 || board.tokenAL.get(i%Main.numPlayers).slot == 3 || board.tokenAL.get(i%Main.numPlayers).slot == 7 || board.tokenAL.get(i%Main.numPlayers).slot == 9) {
			display.textarea.append("\nWould you like to exit or use a passage" );
		}
		else {
			display.textarea.append("\nChoose an exit (1-4)" );
		}
		
		display.textarea.append("\n<" + Main.playerNames[i%Main.numPlayers] + ">");
	}
	
	public void ExitRoom() {
		
		if(board.tokenAL.get(i%Main.numPlayers).slot == 1 || board.tokenAL.get(i%Main.numPlayers).slot == 3 || board.tokenAL.get(i%Main.numPlayers).slot == 7 || board.tokenAL.get(i%Main.numPlayers).slot == 9) {
			
			if(board.tokenAL.get(i%Main.numPlayers).slot == 1) {
				if(text.equalsIgnoreCase("exit")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.KitchenEntrancex;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.KitchenEntrancey;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoStudy();
				}
			}
			else if(board.tokenAL.get(i%Main.numPlayers).slot == 3) {
				if(text.equalsIgnoreCase("exit")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.ConservatoryEntrancex;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.ConservatoryEntrancey;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoLounge();
				}
			}
			else if(board.tokenAL.get(i%Main.numPlayers).slot == 7) {
				if(text.equalsIgnoreCase("exit")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LoungeEntrancex;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LoungeEntrancey;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoConservatory();
				}
			}
			else {
				if(text.equalsIgnoreCase("exit")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.StudyEntrancex;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.StudyEntrancey;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoKitchen();
				}
			}
		}

		else {
			if(board.tokenAL.get(i%Main.numPlayers).slot == 2) {	
				if(text.equalsIgnoreCase("1")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance1x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance1y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance2x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance2y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance3x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance3y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("4")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance4x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance4y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}

			else if(board.tokenAL.get(i%Main.numPlayers).slot == 4) {
				if(text.equalsIgnoreCase("1")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance1x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance1y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance2x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance2y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(board.tokenAL.get(i%Main.numPlayers).slot == 5) {
				if(text.equalsIgnoreCase("1")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance1x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance1y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance2x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance2y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(board.tokenAL.get(i%Main.numPlayers).slot == 6) {
				if(text.equalsIgnoreCase("1")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LibraryEntrance1x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LibraryEntrance1y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LibraryEntrance2x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LibraryEntrance2y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else {
				text = textualcommand.textfield.getText().trim();
				
				if(text.equalsIgnoreCase("1")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance1x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance1y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance2x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance2y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					board.tokenAL.get(i%Main.numPlayers).slot = 0;
					board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance3x;
					board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance3y;
					board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
		}
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
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "< "  + Main.playerNames[i%Main.numPlayers] + " ("+ moveCounterDisplay(moveCounter) +") "+"> ");
		textualcommand.textfield.setText("");
		display.moveScrollPaneWithText();
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
		
		//moves still left
		else { 
			if(error == false) {
				appendAndRemove();
			}
		}
			
		textualcommand.textfield.setText("");
		display.moveScrollPaneWithText();
	}
	
	public boolean changeWithBoard(int i, int moveCounter, int x, int y, char direction, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = false;
		board.tokenAL.get(i%Main.numPlayers).type = boardstructure.getCoordinatesType(board.tokenAL.get(i%Main.numPlayers).xcoordinate+x, board.tokenAL.get(i%Main.numPlayers).ycoordinate+y);

		if(board.tokenAL.get(i%Main.numPlayers).type == 'x') {
			//no leaving the board exception
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot leave the board.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'R') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'T') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Square already occupied.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == '0') {
			boardstructure.setCoordinatesType('0', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			board.tokenAL.get(i%Main.numPlayers).xcoordinate += x;
			board.tokenAL.get(i%Main.numPlayers).ycoordinate += y;
			board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
			boardstructure.setCoordinatesType('T', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'u') {
			if(direction == 'u') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				uRoomCheck();
				//Move Token icon 
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("up" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'd') {//down
			if(direction == 'd') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				dRoomCheck();
				//Move Token icon 
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("down" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'l') {//left
			if(direction == 'l') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				lRoomCheck();
				//Move Token icon 
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("left" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(board.tokenAL.get(i%Main.numPlayers).type == 'r') {
			if(direction == 'r') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				rRoomCheck();
				//Move Token icon 
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("right" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}

		boardstructure.printTileTypeBoard();
		
		return error;
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
	
	public void uRoomCheck() {
		if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.KitchenEntrancex && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.KitchenEntrancey) {
			AddtoKitchen();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance2x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance2y) {
			AddtoBallRoom();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance3x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance3y) {
			AddtoBallRoom();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.ConservatoryEntrancex && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.ConservatoryEntrancey) {
			AddtoConservatory();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance2x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance2y) {
			AddtoDiningRoom();
		}
		else {
			AddtoBilliardRoom();
		}
	}
	
	public void dRoomCheck() {
		if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.LibraryEntrance1x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.LibraryEntrance1y) {
			AddtoLibrary();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.LoungeEntrancex && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.LoungeEntrancey) {
			AddtoLounge();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.HallEntrance1x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.HallEntrance1y) {
			AddtoHall();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.HallEntrance2x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.HallEntrance2y) {
			AddtoHall();
		}
		else {
			AddtoStudy();
		}
	}
	
	public void lRoomCheck() {
		if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance4x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance4y) {
			AddtoBallRoom();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance1x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance1y) {
			AddtoDiningRoom();
		}
		else {
			AddtoHall();
		}
	}
	
	public void rRoomCheck() {
		if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance1x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance1y) {
			AddtoBallRoom();
		}
		else if(board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BilliardRoomEntrance1x && board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BilliardRoomEntrance1y) {
			AddtoBilliardRoom();
		}
		else {
			AddtoLibrary();
		}
	}
	
	public void AddtoKitchen() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.KitchenSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 1;
				//Set slot as occupied
				Board.KitchenSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.KitchenSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.KitchenSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoBallRoom() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.BallRoomSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 2;
				//Set slot as occupied
				Board.BallRoomSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoConservatory() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.ConservatorySlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 3;
				//Set slot as occupied
				Board.ConservatorySlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.ConservatorySlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.ConservatorySlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoDiningRoom() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.DiningRoomSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 4;
				//Set slot as occupied
				Board.DiningRoomSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.DiningRoomSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.DiningRoomSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoBilliardRoom() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.BilliardRoomSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 5;
				//Set slot as occupied
				Board.BilliardRoomSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BilliardRoomSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BilliardRoomSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoLibrary() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.LibrarySlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 6;
				//Set slot as occupied
				Board.LibrarySlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LibrarySlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LibrarySlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoLounge() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.LoungeSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 7;
				//Set slot as occupied
				Board.LoungeSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LoungeSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LoungeSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
		
	}
	
	public void AddtoHall() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.HallSlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 8;
				//Set slot as occupied
				Board.HallSlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallSlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallSlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void AddtoStudy() {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.StudySlots[0][j] == 0) {
				//Set Token as in Room
				board.tokenAL.get(i%Main.numPlayers).slot = 9;
				//Set slot as occupied
				Board.StudySlots[0][j] = 1;
				//Move the token to the slot
				board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.StudySlots[1][j];
				board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.StudySlots[2][j];

				//Move token
				board.tokenAL.get(i%Main.numPlayers).setBounds(board.tokenAL.get(i%Main.numPlayers).xcoordinate, board.tokenAL.get(i%Main.numPlayers).ycoordinate, board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	
	public void weaponMoves() {
		Weapon weapon=null;
		String room="";
		int x=0, y=0;
		
		String[] words = text.split("\\s+"); // splits by whitespace
		
		if(words.length == 4) {
			if(words[0].equals("move") && words[2].equals("to")) {
				if(words[1].equals("candlestick")) {
					weapon = board.candlestick;
				}
				else if(words[1].equals("knife")) {
					weapon = board.knife;
				}
				else if(words[1].equals("lead pipe")) {
					weapon = board.leadpipe;
				}
				else if(words[1].equals("revolver")) {
					weapon = board.revolver;
				}
				else if(words[1].equals("rope")) {
					weapon = board.rope;
				}
				else if(words[1].equals("wrench")) {
					weapon = board.wrench;
				}
				
				if(words[3].equals("kitchen")) {
					room = "kitchen";
					x = Board.kitchenx;
					y = Board.kitcheny;
				}
				else if(words[3].equals("ball room")) {
					room = "ball room";
					x = Board.ballroomx;
					y = Board.ballroomy;
				}
				else if(words[3].equals("conservatory")) {
					room = "conservatory";
					x = Board.conservatoryx;
					y = Board.conservatoryy;
				}
				else if(words[3].equals("billiard room")) {
					room = "billiard room";
					x = Board.billiardroomx;
					y = Board.billiardroomy;
				}
				else if(words[3].equals("library")) {
					room = "library";
					x = Board.libraryx;
					y = Board.libraryy;
				}
				else if(words[3].equals("study")) {
					room = "study";
					x = Board.studyx;
					y = Board.studyy;
				}
				else if(words[3].equals("hall")) {
					room = "hall";
					x = Board.hallx;
					y = Board.hally;
				}
				else if(words[3].equals("lounge")) {
					room = "lounge";
					x = Board.loungex;
					y = Board.loungey;
				}
				else if(words[3].equals("dining room")) {
					room = "dining room";
					x = Board.diningroomx;
					y = Board.diningroomy;
				}
			}
		}
		
		
		if(weapon!=null && !room.equals("") && x!=0 && y!=0) {
			moveRoom(weapon, room);
			weapon.setPosition(x, y);
			textualcommand.textfield.setText("");
		}
		
		
	}
	
}