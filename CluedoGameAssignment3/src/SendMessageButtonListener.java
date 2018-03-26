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
		
		text = textualcommand.textfield.getText().trim();
		
		
		//according to the text entered in the textual command, move each character one box up, down, left or right
		if(gameStage == 0) {
			if(text.equalsIgnoreCase("start")) {
				display.textarea.append("start\n");
				textualcommand.textfield.setText("");
				whoGoesFirst();
				moveCounter = diceResult();
				diceRoll(display, textualcommand);
				gameStage++;

				PanelsInJFrame.layeredPane.remove(notes);
				notes = Board.tokenAL.get(i%Main.numPlayers).notes;
				notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
				PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
				notes.setVisible(false);
			}
		}
		
		if(gameStage == 1) {	
			if(Board.tokenAL.get(i%Main.numPlayers).slot != 9) {				
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
						"\n\n While in a room the available commands are:"+
						"\nexit\npassage"+
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
		display.textarea.append("\nRoll to see who goes first:");
		int[] saveResults = new int[Main.numPlayers];
		int[] drawedPlayers = new int[Main.numPlayers+1];
		for(int i=0;i<Main.numPlayers;i++) {
			int result = 7;
			saveResults[i] = result;
			display.textarea.append("\n"+Main.playerNames[i]+" rolled: " +result);
			drawedPlayers[i] = 0;
		}
		drawedPlayers[Main.numPlayers] = 0;
		int iterate = 1;
		int max = saveResults[0];
	    drawedPlayers[0] = 1;
		int bigIndex = 0;
		for (int i = 1; i < saveResults.length; i++) {
		    if (saveResults[i] > max) {
		      max = saveResults[i];
		      bigIndex = i;
				for(int j=0;j<Main.numPlayers;j++) {
					drawedPlayers[j] = 0;
				}
		      drawedPlayers[0] = i;
		      iterate = 1;
		    }
		    else if (saveResults[i] == max) {
		    		drawedPlayers[iterate] = i+1;
		    		iterate++;
		    }
		}
		
		if(drawedPlayers[1] != 0) {
			DrawedRoll(drawedPlayers);
		}
		else {
			i = bigIndex;
		}
		display.textarea.append("\n\n"+Main.playerNames[i]+" will go first.\n");
	}
	
	public void DrawedRoll(int[] drawedPlayers) {
		display.textarea.append("\n\nThere was a draw, roll again to decide:");
		int[] saveResults = new int[6];
		int l = 0;
		while(drawedPlayers[l] != 0) {
			int result = diceResult();
			saveResults[l] = result;
			display.textarea.append("\n"+drawedPlayers[l]+" rolled: " +result);
			l++;
		}
		for(int j=0;j<Main.numPlayers;j++) {
			drawedPlayers[j] = 0;
		}
		int iterate = 1;
		int max = saveResults[0];
		int bigIndex = 0;
		for (int i = 1; i < l; i++) {
		    if (saveResults[i] > max) {
		      max = saveResults[i];
		      bigIndex = i;
				for(int j=0;j<l;j++) {
					drawedPlayers[j] = 0;
				}
		      drawedPlayers[0] = i;
		      iterate = 1;
		    }
		    else if (saveResults[i] == max) {
		    		drawedPlayers[iterate] = i;
		    		iterate++;
		    }
		}
		
		if(drawedPlayers[1] != 0) {
			DrawedRoll(drawedPlayers);
		}
		
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
			notes = Board.tokenAL.get(i%Main.numPlayers).notes;
			notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
			PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
			notes.setVisible(false);
			notesVisible = false;
	}
	
	//dice text
	public void diceRoll(Display display, TextualCommand textualcommand) {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "<-------" + Main.playerNames[i%Main.numPlayers]+" rolled the dice ------->\n");	
		display.textarea.append("<" + Main.playerNames[i%Main.numPlayers] + " has " + moveCounter +" moves>" );
		
		if(Board.tokenAL.get(i%Main.numPlayers).slot != 9) {				
			PrintExitOption();
		}
	}
	
	public void PrintExitOption() {
		if(Board.tokenAL.get(i%Main.numPlayers).slot == 0 || Board.tokenAL.get(i%Main.numPlayers).slot == 2 || Board.tokenAL.get(i%Main.numPlayers).slot == 6 || Board.tokenAL.get(i%Main.numPlayers).slot == 8) {
			display.textarea.append("\nWould you like to exit or use a passage" );
		}
		else {
			display.textarea.append("\nChoose an exit (1-4)" );
		}
		
		display.textarea.append("\n<" + Main.playerNames[i%Main.numPlayers] + ">");
	}
	
	public void ExitRoom() {
		
		if(Board.tokenAL.get(i%Main.numPlayers).slot == 0 || Board.tokenAL.get(i%Main.numPlayers).slot == 2 || Board.tokenAL.get(i%Main.numPlayers).slot == 6 || Board.tokenAL.get(i%Main.numPlayers).slot == 8) {
			if(Board.tokenAL.get(i%Main.numPlayers).slot == 0) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("exit")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.KitchenEntrancex;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.KitchenEntrancey;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoRoom(8);
				}
			}
			else if(Board.tokenAL.get(i%Main.numPlayers).slot == 3) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("exit")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.ConservatoryEntrancex;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.ConservatoryEntrancey;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoRoom(6);
				}
			}
			else if(Board.tokenAL.get(i%Main.numPlayers).slot == 7) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("exit")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LoungeEntrancex;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LoungeEntrancey;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoRoom(2);
				}
			}
			else {
				RemovefromRoom();
				if(text.equalsIgnoreCase("exit")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.StudyEntrancex;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.StudyEntrancey;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					AddtoRoom(0);
				}
			}
		}

		else {
			if(Board.tokenAL.get(i%Main.numPlayers).slot == 2) {	
				RemovefromRoom();
				if(text.equalsIgnoreCase("1")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance1x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance1y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance2x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance2y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance3x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance3y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("4")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BallRoomEntrance4x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BallRoomEntrance4y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}

			else if(Board.tokenAL.get(i%Main.numPlayers).slot == 4) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("1")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance1x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance1y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance2x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance2y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(Board.tokenAL.get(i%Main.numPlayers).slot == 5) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("1")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance1x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance1y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance2x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance2y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(Board.tokenAL.get(i%Main.numPlayers).slot == 6) {
				RemovefromRoom();
				if(text.equalsIgnoreCase("1")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LibraryEntrance1x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LibraryEntrance1y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.LibraryEntrance2x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.LibraryEntrance2y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else {
				RemovefromRoom();
				if(text.equalsIgnoreCase("1")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance1x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance1y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance2x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance2y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.HallEntrance3x;
					Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.HallEntrance3y;
					Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
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
		Board.tokenAL.get(i%Main.numPlayers).type = boardstructure.getCoordinatesType(Board.tokenAL.get(i%Main.numPlayers).xcoordinate+x, Board.tokenAL.get(i%Main.numPlayers).ycoordinate+y);

		if(Board.tokenAL.get(i%Main.numPlayers).type == 'x') {
			//no leaving the board exception
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot leave the board.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'R') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'T') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Square already occupied.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == '0') {
			boardstructure.setCoordinatesType('0', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			Board.tokenAL.get(i%Main.numPlayers).xcoordinate += x;
			Board.tokenAL.get(i%Main.numPlayers).ycoordinate += y;
			Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
			boardstructure.setCoordinatesType('T', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'u') {
			if(direction == 'u') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				uRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("up" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'd') {//down
			if(direction == 'd') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				dRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("down" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'l') {//left
			if(direction == 'l') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				lRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("left" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[i%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).type == 'r') {
			if(direction == 'r') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				rRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
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
		if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.KitchenEntrancex && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.KitchenEntrancey) {
			AddtoRoom(0);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance2x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance2y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance3x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance3y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.ConservatoryEntrancex && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.ConservatoryEntrancey) {
			AddtoRoom(2);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance2x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance2y) {
			AddtoRoom(3);
		}
		else {
			AddtoRoom(4);
		}
	}
	
	public void dRoomCheck() {
		if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.LibraryEntrance1x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.LibraryEntrance1y) {
			AddtoRoom(5);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.LoungeEntrancex && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.LoungeEntrancey) {
			AddtoRoom(6);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.HallEntrance1x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.HallEntrance1y) {
			AddtoRoom(7);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.HallEntrance2x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.HallEntrance2y) {
			AddtoRoom(7);
		}
		else {
			AddtoRoom(8);
		}
	}
	
	public void lRoomCheck() {
		if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance4x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance4y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance1x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance1y) {
			AddtoRoom(3);
		}
		else {
			AddtoRoom(7);
		}
	}
	
	public void rRoomCheck() {
		if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BallRoomEntrance1x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BallRoomEntrance1y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(i%Main.numPlayers).xcoordinate == Board.BilliardRoomEntrance1x && Board.tokenAL.get(i%Main.numPlayers).ycoordinate == Board.BilliardRoomEntrance1y) {
			AddtoRoom(4);
		}
		else {
			AddtoRoom(5);
		}
	}
	
	public void AddtoRoom(int l) {
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.RoomSlots.get(l)[0][j] == 0) {
				//Set Token as in a Room
				Board.tokenAL.get(i%Main.numPlayers).slot = l;
				//Set slot as occupied
				Board.RoomSlots.get(l)[0][j] = 1;
				//Move the token to the slot
				Board.tokenAL.get(i%Main.numPlayers).xcoordinate = Board.RoomSlots.get(l)[1][j];
				Board.tokenAL.get(i%Main.numPlayers).ycoordinate = Board.RoomSlots.get(l)[2][j];

				//Move token
				Board.tokenAL.get(i%Main.numPlayers).setBounds(Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate, Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(i%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		appendTextAndTurnInfo();
	}
	
	public void RemovefromRoom() {
		//Find which room they're in
		int l = Board.tokenAL.get(i%Main.numPlayers).slot;
		for(int j = 0; j<6;j++) {
			if(Board.RoomSlots.get(l)[0][j] == 1) {
				//Set board back to S
				boardstructure.setCoordinatesType('S', Board.tokenAL.get(i%Main.numPlayers).xcoordinate, Board.tokenAL.get(i%Main.numPlayers).ycoordinate);
				//Set Token as not in a Room
				Board.tokenAL.get(i%Main.numPlayers).slot = 9;
				//Set slot as unoccupied
				Board.RoomSlots.get(l)[0][j] = 0;
				//Exit the loop
				break;
			}
		}
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