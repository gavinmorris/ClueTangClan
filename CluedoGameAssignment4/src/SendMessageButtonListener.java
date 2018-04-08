import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SendMessageButtonListener implements ActionListener {
	
	public static int currentPlayer=0;
	public int done=0;
	public int gameStage = 0;
	public String text;
	public String[] question = new String[4];
	public String[] found = new String[3];
	public int store;
	public int storeCardNo;
	public String storeName;
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
	JTextArea log = new JTextArea("-----------Question Log-----------\n");
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
				notes = Board.tokenAL.get(currentPlayer%Main.numPlayers).notes;
				notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
				PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
				notes.setVisible(false);
			}
		}
		
		if(gameStage == 1) {	
			if(notesVisible == true) {
				notes.setVisible(false);
				notesVisible = false;
			}
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot != 10) {				
				ExitRoom();
			}
			else {
				if(text.equalsIgnoreCase("u")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("up");
						error = moveUp(currentPlayer, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				} 
				else if(text.equalsIgnoreCase("d")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("down");
						error = moveDown(currentPlayer, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				} 
				else if(text.equalsIgnoreCase("l")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("left");
						error = moveLeft(currentPlayer, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				}
				
				else if(text.equalsIgnoreCase("r")) {
					if(moveCounter > 0) {
						textualcommand.textfield.setText("right");
						error = moveRight(currentPlayer, moveCounter, boardstructure, display, textualcommand);
						if(error == false) {
							moveCounter--;
						}
					}
					appendTextAndTurnInfo();
				}
				
			}
			
			
			 if(text.equalsIgnoreCase("help")) {
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
			 
			else if(text.equalsIgnoreCase("log")) {
				JOptionPane.showMessageDialog(null, log);
				textualcommand.textfield.setText("");
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
				if(moveCounter == 0) {
					textualcommand.textfield.setText("");
					whoseTurn(display, textualcommand);
					done=0;
				} 
				else {
					display.textarea.append(textualcommand.textfield.getText()+"\n" + "It is not your turn yet\n");
					display.textarea.append("< "  + Main.playerNames[currentPlayer%Main.numPlayers] + " ("+ moveCounterDisplay(moveCounter) +") "+"> ");
					textualcommand.textfield.setText("");
				}
			}
			 
			else if(text.equalsIgnoreCase("done")) {
				if(moveCounter == 0) {
					clearInfo();
					gameStage = 5;
					display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
				}
				
				else {
					display.textarea.append(textualcommand.textfield.getText()+"\n" +"You still have moves left\n."+"<"+ Main.playerNames[currentPlayer%Main.numPlayers] + " ("+ moveCounterDisplay(moveCounter) +") " +">");
					textualcommand.textfield.setText("");
				}
			}
			
			updateNotes();
		}
		if(gameStage == 2) {
			if(text.equalsIgnoreCase("question")) {
				clearInfo();
				display.textarea.append(textualcommand.textfield.getText());
				textualcommand.textfield.setText("");
				question[0] = Main.playerNames[(currentPlayer)%Main.numPlayers];
				store = currentPlayer;
				question[3] = Board.Rooms[Board.tokenAL.get(currentPlayer%Main.numPlayers).slot];
				display.textarea.append("\nEnter the name of the character: " );
				log.append("\nPlayer name: " + Main.playerNames[(currentPlayer)%Main.numPlayers] + "\n");
				gameStage = 3;
			}
			
			else if(text.equalsIgnoreCase("done")) {
				clearInfo();
				gameStage = 5;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" Has the screen been handed over? ");
			}
			
		}
		if(gameStage == 3) {
			if(notesVisible == true) {
				notes.setVisible(false);
				notesVisible = false;
			}
			if(text.equalsIgnoreCase("notes")) {
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
			else if(text.equalsIgnoreCase("scarlett")) {
				AddPlayertoRoom("Scarlett");
				log.append("Accused : Scarlett\n");
			}
			else if(text.equalsIgnoreCase("mustard")) {
				AddPlayertoRoom("Mustard");
				log.append("Accused : Mustard\n");
			}
			else if(text.equalsIgnoreCase("green")) {
				AddPlayertoRoom("Green");
				log.append("Accused : Green\n");
			}
			else if(text.equalsIgnoreCase("white")) {
				AddPlayertoRoom("White");
				log.append("Accused : White\n");
			}
			else if(text.equalsIgnoreCase("peacock")) {
				AddPlayertoRoom("Peacock");
				log.append("Accused : Peacock\n");
			}
			else if(text.equalsIgnoreCase("plum")) {
				AddPlayertoRoom("Plum");
				log.append("Accused : Plum\n");
			}
		}
		if(gameStage == 4) {
			if(notesVisible == true) {
				notes.setVisible(false);
				notesVisible = false;
			}
			if(text.equalsIgnoreCase("notes")) {
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
			else if(text.equalsIgnoreCase("candlestick")) {
				Weapon weapon = board.candlestick;
				question[2] = "candlestick";
				moveWeapon(weapon);
				log.append("Weapon of choice: Candlestick\n");
			}
			else if(text.equalsIgnoreCase("knife")) {
				Weapon weapon = board.knife;
				question[2] = "knife";
				moveWeapon(weapon);
				log.append("Weapon of choice: Knife\n");
			}
			else if(text.equalsIgnoreCase("lead pipe")) {
				Weapon weapon = board.leadpipe;
				question[2] = "lead pipe";
				moveWeapon(weapon);
				log.append("Weapon of choice: Lead Pipe\n");
			}
			else if(text.equalsIgnoreCase("revolver")) {
				Weapon weapon = board.revolver;
				question[2] = "revolver";
				moveWeapon(weapon);
				log.append("Weapon of choice: Revolver\n");
			}
			else if(text.equalsIgnoreCase("rope")) {
				Weapon weapon = board.rope;
				question[2] = "rope";
				moveWeapon(weapon);
				log.append("Weapon of choice: Rope\n");
			}
			else if(text.equalsIgnoreCase("wrench")) {
				Weapon weapon = board.wrench;
				question[2] = "wrench";
				moveWeapon(weapon);
				log.append("Weapon of choice: Wrench\n");
			}
		}
		if(gameStage == 5) {
			if(text.equalsIgnoreCase("yes")) {
				gameStage = 1;
				if(moveCounter == 0) {
					display.textarea.append("\n" + textualcommand.textfield.getText()+"\n\n" + "It is now "+ Main.playerNames[(currentPlayer+1)%Main.numPlayers] +"'s turn.");
					display.textarea.append("\n" + "Type 'roll' to roll the dice.");
					textualcommand.textfield.setText("");
				} 
				else {
					display.textarea.append(textualcommand.textfield.getText()+"\n" + "You still have moves left\n");
					display.textarea.append("< "  + Main.playerNames[currentPlayer%Main.numPlayers] + " ("+ moveCounterDisplay(moveCounter) +") "+"> ");
					textualcommand.textfield.setText("");
				}
			}
		}
		if(gameStage == 6) {
			if(text.equalsIgnoreCase("yes")) {
				display.textarea.append("\n" + textualcommand.textfield.getText());
				textualcommand.textfield.setText("");
				display.textarea.append("\n\n"+ question[0] +" asked: Was it "+ question[1] +" with the "+ question[2] +" in the "+ question[3]);
				//Check if a player has any of the cards
				currentPlayer++;
				QuestionTime();
			}
		}
		if(gameStage == 7) {
			if(text.equalsIgnoreCase("done")) {
				clearInfo();
				gameStage = 6;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
		}
		if(gameStage == 8) {
			//Ask which card to show the questioner
			Show(storeCardNo);
		}
		if(gameStage == 9) {
			//tell questioner then type done
			if(text.equalsIgnoreCase("yes")) {
				display.textarea.append("\n" + textualcommand.textfield.getText());
				display.textarea.append("\n\n" + storeName + " had the card "+ found[storeCardNo]);
				display.textarea.append("\nType done to finish your turn");
				gameStage = 1;
				currentPlayer = store;
			}
		}
		if(gameStage == 10) {
			//tell questioner then type done
			if(text.equalsIgnoreCase("done")) {
				clearInfo();
				gameStage = 6;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
		}
	}		
	
	int MovesinTurn;
	
	public void whoGoesFirst() {
		display.textarea.append("\nRoll to see who goes first:");
		int[] saveResults = new int[Main.numPlayers];
		int[] drawedPlayers = new int[Main.numPlayers+1];
		for(int i=0;i<Main.numPlayers;i++) {
			int result = diceResult();
			saveResults[i] = result;
			display.textarea.append("\n"+Main.playerNames[i]+" rolled: " + result);
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
			currentPlayer = bigIndex;
		}
		display.textarea.append("\n\n"+Main.playerNames[currentPlayer]+" will go first.\n");
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
		
		currentPlayer = bigIndex;
	}
	
	//alternates whose turn it is to make a move
	public void whoseTurn(Display display, TextualCommand textualcommand) {
		currentPlayer++;
		boolean playerEliminatedCond = true;
		while(playerEliminatedCond) {
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).isEliminated()) {
				currentPlayer++;
			}
			else {
				playerEliminatedCond = false;
			}
		}
		
		moveCounter = diceResult();
		MovesinTurn = moveCounter;
		diceRoll(display, textualcommand);		
		textualcommand.textfield.setText("");

		PanelsInJFrame.layeredPane.remove(notes);
		notes = Board.tokenAL.get(currentPlayer%Main.numPlayers).notes;
		notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
		PanelsInJFrame.layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
		notes.setVisible(false);
		notesVisible = false;
	}
	
	//dice text
	public void diceRoll(Display display, TextualCommand textualcommand) {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "<-------" + Main.playerNames[currentPlayer%Main.numPlayers]+" rolled the dice ------->\n");	
		display.textarea.append("<" + Main.playerNames[currentPlayer%Main.numPlayers] + " has " + moveCounter +" moves>" );
		
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot != 10) {				
			PrintExitOption();
		}
	}
	
	public void PrintExitOption() {
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 0 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 2 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 6 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 8) {
			display.textarea.append("\nWould you like to exit or use a passage" );
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 9) {
			display.textarea.append("\nType exit to leave the basement" );
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 3 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 3 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 5){
			display.textarea.append("\nChoose an exit (1 or 2)" );
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 7){
			display.textarea.append("\nChoose an exit (1-3)" );
		}
		else{
			display.textarea.append("\nChoose an exit (1-4)" );
		}
		
		display.textarea.append("\n<" + Main.playerNames[currentPlayer%Main.numPlayers] + ">");
	}
	
	public void ExitRoom() {
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 0 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 2 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 6 || Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 8) {
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 0) {
				if(text.equalsIgnoreCase("exit")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.KitchenEntrancex;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.KitchenEntrancey;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					appendAndRemove();
					RemovefromRoom();
					AddtoRoom(8);
				}
			}
			else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 2) {
				if(text.equalsIgnoreCase("exit")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.ConservatoryEntrancex;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.ConservatoryEntrancey;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					appendAndRemove();
					RemovefromRoom();
					AddtoRoom(6);
				}
			}
			else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 6) {
				if(text.equalsIgnoreCase("exit")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.LoungeEntrancex;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.LoungeEntrancey;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					appendAndRemove();
					RemovefromRoom();
					AddtoRoom(2);
				}
			}
			else {
				if(text.equalsIgnoreCase("exit")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.StudyEntrancex;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.StudyEntrancey;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("passage")) {
					appendAndRemove();
					RemovefromRoom();
					AddtoRoom(0);
				}
			}
		}

		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 9) {
			if(text.equalsIgnoreCase("exit")) {
				RemovefromRoom();
				Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BasementEntrancex;
				Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BasementEntrancey;
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				appendAndRemove();
			}
		}
		
		else {
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 1) {
				if(text.equalsIgnoreCase("1")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BallRoomEntrance1x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BallRoomEntrance1y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BallRoomEntrance2x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BallRoomEntrance2y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BallRoomEntrance3x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BallRoomEntrance3y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("4")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BallRoomEntrance4x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BallRoomEntrance4y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}

			else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 3) {
				if(text.equalsIgnoreCase("1")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance1x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance1y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.DiningRoomEntrance2x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.DiningRoomEntrance2y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 4) {
				if(text.equalsIgnoreCase("1")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance1x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance1y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.BilliardRoomEntrance2x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.BilliardRoomEntrance2y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 5) {
				if(text.equalsIgnoreCase("1")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.LibraryEntrance1x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.LibraryEntrance1y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.LibraryEntrance2x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.LibraryEntrance2y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
			}
			else {
				if(text.equalsIgnoreCase("1")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.HallEntrance1x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.HallEntrance1y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("2")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.HallEntrance2x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.HallEntrance2y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
					appendAndRemove();
				}
				else if(text.equalsIgnoreCase("3")) {
					RemovefromRoom();
					Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.HallEntrance3x;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.HallEntrance3y;
					Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
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
	
	public void moveWeaponToRoom(Weapon weapon, String room) {
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
		weapon.setPosition(x, y);
	}
	
	//display panel text when player is making moves
	public void appendAndRemove() {
		display.textarea.append(textualcommand.textfield.getText() + "\n" + "< "  + Main.playerNames[currentPlayer%Main.numPlayers] + " ("+ moveCounterDisplay(moveCounter) +") "+"> ");
		textualcommand.textfield.setText("");
		display.moveScrollPaneWithText();
	}
	
	//Display text depending on number of moves left,
	public void appendTextAndTurnInfo() {
		//prompts user to type done
		if(moveCounter == 0) {
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).slot == 10) {
				if(done == 0) {
					for(int z=0;z<Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.size();z++) {
						System.out.println(Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.get(z).cardName);
					}
					System.out.println();
					
					display.textarea.append(textualcommand.textfield.getText()+"\n" + "No moves left, type done.");
					display.textarea.append("\n" + "<"  + Main.playerNames[currentPlayer%Main.numPlayers] +">");
					done=1;
				}
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

	public void clearInfo() {
		display.textarea.setText(" ");
		textualcommand.textfield.setText("");
		display.playerInfo();
	}
	public boolean changeWithBoard(int i, int moveCounter, int x, int y, char direction, BoardStructure boardstructure, Display display, TextualCommand textualcommand) {
		boolean error = false;
		Board.tokenAL.get(currentPlayer%Main.numPlayers).type = boardstructure.getCoordinatesType(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate+x, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate+y);

		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'x') {
			//no leaving the board exception
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot leave the board.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'R') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'T') {
			//no walking through walls
			display.textarea.append(textualcommand.textfield.getText() + "\nError: Square already occupied.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
					+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
			textualcommand.textfield.setText("");
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == '0') {
			boardstructure.setCoordinatesType('0', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
			Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate += x;
			Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate += y;
			Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
			boardstructure.setCoordinatesType('T', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'u') {
			if(direction == 'u') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				uRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("up" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'd') {//down
			if(direction == 'd') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				dRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("down" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'l') {//left
			if(direction == 'l') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				lRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("left" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'r') {
			if(direction == 'r') {
				//Set board state back to 0
				boardstructure.setCoordinatesType('0', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
				//Put Token into room slot
				rRoomCheck();
				//Move Token icon 
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				//Set board state
				boardstructure.setCoordinatesType('T', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
			} 
			else {
				//cannot enter room this way
				display.textarea.append("right" + "\nError: Cannot enter room this way.\n" + "< " + Main.playerNames[currentPlayer%Main.numPlayers] 
						+ " (" + " moves left : " + moveCounter + " " +") " + "> ");
				textualcommand.textfield.setText("");
			}
			error = true;
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).type == 'b') {
			display.textarea.append("Get Ready...");
			textualcommand.textfield.setText("");
		}

		//boardstructure.printTileTypeBoard();
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
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.KitchenEntrancex && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.KitchenEntrancey) {
			AddtoRoom(0);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BallRoomEntrance2x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BallRoomEntrance2y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BallRoomEntrance3x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BallRoomEntrance3y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.ConservatoryEntrancex && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.ConservatoryEntrancey) {
			AddtoRoom(2);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance2x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance2y) {
			AddtoRoom(3);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BasementEntrancex && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BasementEntrancey) {
			AddtoRoom(9);
		}
		else {
			AddtoRoom(4);
		}
	}
	
	public void dRoomCheck() {
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.LibraryEntrance1x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.LibraryEntrance1y) {
			AddtoRoom(5);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.LoungeEntrancex && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.LoungeEntrancey) {
			AddtoRoom(6);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.HallEntrance1x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.HallEntrance1y) {
			AddtoRoom(7);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.HallEntrance2x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.HallEntrance2y) {
			AddtoRoom(7);
		}
		else {
			AddtoRoom(8);
		}
	}
	
	public void lRoomCheck() {
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BallRoomEntrance4x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BallRoomEntrance4y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.DiningRoomEntrance1x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.DiningRoomEntrance1y) {
			AddtoRoom(3);
		}
		else {
			AddtoRoom(7);
		}
	}
	
	public void rRoomCheck() {
		if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BallRoomEntrance1x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BallRoomEntrance1y) {
			AddtoRoom(1);
		}
		else if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.BilliardRoomEntrance1x && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.BilliardRoomEntrance1y) {
			AddtoRoom(4);
		}
		else {
			AddtoRoom(5);
		}
	}
	
	public void AddtoRoom(int l) {
		display.textarea.append(textualcommand.textfield.getText());
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.RoomSlots.get(l)[0][j] == 0) {
				//Set Token as in a Room
				Board.tokenAL.get(currentPlayer%Main.numPlayers).slot = l;
				//Set slot as occupied
				Board.RoomSlots.get(l)[0][j] = 1;
				//Move the token to the slot
				Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate = Board.RoomSlots.get(l)[1][j];
				Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate = Board.RoomSlots.get(l)[2][j];

				//Move token
				Board.tokenAL.get(currentPlayer%Main.numPlayers).setBounds(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer%Main.numPlayers).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		//Next persons go
		moveCounter = 0;
		//Question or next persons go
		display.textarea.append("\nType question to make a guess, or type done to finish" );
		display.textarea.append("\n" + "<"  + Main.playerNames[currentPlayer%Main.numPlayers] +">");
		gameStage = 2;
	}
	
	public void AddPlayertoRoom(String CharName) {
		display.textarea.append(textualcommand.textfield.getText());
		textualcommand.textfield.setText("");
		question[1] = CharName;
		
		int l = Board.tokenAL.get(currentPlayer%Main.numPlayers).slot;
		
		int store = currentPlayer;
		currentPlayer = Token.findCharacter(CharName);
		
		if(Board.tokenAL.get(currentPlayer).slot != 10) {
			RemovefromRoom();
		}
		
		//Find an available slot
		for(int j = 0; j<6;j++) {
			if(Board.RoomSlots.get(l)[0][j] == 0) {
				//Set Token as in a Room
				Board.tokenAL.get(currentPlayer).slot = l;
				//Set slot as occupied
				Board.RoomSlots.get(l)[0][j] = 1;
				//Move the token to the slot
				Board.tokenAL.get(currentPlayer).xcoordinate = Board.RoomSlots.get(l)[1][j];
				Board.tokenAL.get(currentPlayer).ycoordinate = Board.RoomSlots.get(l)[2][j];

				//Move token
				Board.tokenAL.get(currentPlayer).setBounds(Board.tokenAL.get(currentPlayer).xcoordinate, Board.tokenAL.get(currentPlayer).ycoordinate, Board.tokenAL.get(currentPlayer).imageIcon.getIconWidth(), Board.tokenAL.get(currentPlayer).imageIcon.getIconHeight());
				
				//Exit the loop
				break;
			}
		}
		
		display.textarea.append("\nEnter weapon name: " );
		gameStage = 4;
		currentPlayer = store;
	}
	
	public void RemovefromRoom() {
		//Find which room they're in
		int l = Board.tokenAL.get(currentPlayer%Main.numPlayers).slot;
		for(int j=0; j<6; j++) {
			if(Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate == Board.RoomSlots.get(l)[1][j] && Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate == Board.RoomSlots.get(l)[2][j]) {
				//Set board back to S
				boardstructure.setCoordinatesType('S', Board.tokenAL.get(currentPlayer%Main.numPlayers).xcoordinate, Board.tokenAL.get(currentPlayer%Main.numPlayers).ycoordinate);
				//Set Token as not in a Room
				Board.tokenAL.get(currentPlayer%Main.numPlayers).slot = 10;
				//Set slot as unoccupied
				Board.RoomSlots.get(l)[0][j] = 0;
				//Exit the loop
				break;
			}
		}
	}
	
	public void moveWeapon(Weapon weapon) {
		display.textarea.append(textualcommand.textfield.getText());
		textualcommand.textfield.setText("");
		
		moveWeaponToRoom(weapon, Board.Rooms[Board.tokenAL.get(currentPlayer%Main.numPlayers).slot]);
		clearInfo();
		//Add stuff for more questions
		gameStage = 6;
		display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
	}
	
	public void QuestionTime() {
		int iterate = 0;
		
		if(question[0] == Main.playerNames[(currentPlayer)%Main.numPlayers]) {
			display.textarea.append("\n\n" + "Nobody had a matching card. \nType done to finish your turn");
			gameStage = 1;
		}
		
		else {
			for(int i = 1;i<4;i++) {
				for(int j = 0;j<Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.size();j++) {
					if(Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.get(j).cardName.equalsIgnoreCase(question[i])) {
						System.out.println(Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.get(j).cardName + ", " + question[i]);
						found[iterate] = Board.tokenAL.get(currentPlayer%Main.numPlayers).myCardsAL.get(j).cardName;
						iterate++;
					}
				}
			}
			
			//Type show to show card
			//Type 1,2,3 to show
			if(iterate == 0) {
				display.textarea.append("\n\n" + "You do not have any of these cards");
				display.textarea.append("\n" + "Type 'done' to continue.");
				gameStage = 7;
				currentPlayer++;
			}
			else if(iterate == 1) {
				storeName = Main.playerNames[(currentPlayer)%Main.numPlayers];
				display.textarea.append("\n\n" + "You have the card " + found[0]);
				display.textarea.append("\n" + "Type 'show' to show the card to player: " + question[0]);
				gameStage = 8;
			}
			else if(iterate == 2) {
				storeName = Main.playerNames[(currentPlayer)%Main.numPlayers];
				display.textarea.append("\n\n" + "You have the cards (1 = " + found[0] + ") and (2 = " + found[1] + ")");
				display.textarea.append("\n" + "Type 1 or 2 to show a card to player: " + question[0]);
				gameStage = 8;
			}
			else {
				storeName = Main.playerNames[(currentPlayer)%Main.numPlayers];
				display.textarea.append("\n\n" + "You have the cards (1 = " + found[0] + "), (2 = " + found[1] + ") and (3 = " + found[2] + ")");
				display.textarea.append("\n" + "Type 1, 2 or 3 to show the card to player: " + question[0]);
				gameStage = 8;
			}
			storeCardNo = iterate;
		}
	}
	
	public void Show(int iterate) {
		if(iterate == 1) {
			if(text.equalsIgnoreCase("show")) {
				storeCardNo = 0;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
		}
		else if(iterate == 2) {
			if(text.equalsIgnoreCase("1")) {
				storeCardNo = 0;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
			else if(text.equalsIgnoreCase("2")) {
				storeCardNo = 1;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
		}
		else {
			if(text.equalsIgnoreCase("1")) {
				storeCardNo = 0;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
			else if(text.equalsIgnoreCase("2")) {
				storeCardNo = 1;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
			else if(text.equalsIgnoreCase("3")) {
				storeCardNo = 2;
				clearInfo();
				gameStage = 9;
				display.textarea.append(textualcommand.textfield.getText()+"\n\n" +  Main.playerNames[(currentPlayer+1)%Main.numPlayers] +" has the screen been handed over? ");
			}
		}
	}
	
	public void updateNotes() {
		String card="";
		int x=0, y=0;
		int playerNum=0;
		int cardNum=0;
		int k=0;
		boolean isCommand = false;
		boolean hasCard=false;
		boolean doesNotHaveCard=false;
		
		String[] words = text.split("\\s+"); // splits by whitespace
		
		if(words.length > 3 && words.length < 8) {
			if(words[k++].equals("player")) {
				if(words[k].equals("1")) {
					playerNum = 0;
					k++;
				}
				else if(words[k].equals("2")) {
					playerNum = 1;
					k++;
				}
				else if(words[k].equals("3")) {
					playerNum = 2;
					k++;
				}
				else if(words[k].equals("4")) {
					playerNum = 3;
					k++;
				}
				else if(words[k].equals("5")) {
					playerNum = 4;
					k++;
				}
				else if(words[k].equals("6")) {
					playerNum = 5;
					k++;
				}
				
				if(words[k].equals("has")) {
					isCommand = true;
					hasCard = true;
					k++;
				}
				else if(words[k].equals("does")) {
					if(words[k+1].equals("not")) {
						if(words[k+2].equals("have")) {
							isCommand = true;
							doesNotHaveCard = true;
							k+=3;
						}
					}
				}
				if(isCommand) {
					if(words[k].equals("green")) {
						card = "green";
						cardNum=0;
					} 
					else if(words[k].equals("mustard")) {
						card = "mustard";
						cardNum=1;
					}
					else if(words[k].equals("peacock")) {
						card = "peacock";
						cardNum=2;
					}
					else if(words[k].equals("plum")) {
						card = "plum";
						cardNum=3;
					}
					else if(words[k].equals("scarlett")) {
						card = "scarlett";
						cardNum=4;
					}
					else if(words[k].equals("white")) {
						card = "white";
						cardNum=5;
					}
					
					else if(words[k].equals("candlestick")) {
						card = "candlestick";
						cardNum=6;
					}
					else if(words[k].equals("knife")) {
						card = "knife";
						cardNum=7;
					}
					else if(words[k].equals("lead")) {
						if(words[k+1].equals("pipe")) {
							card = "leadpipe";
							cardNum=8;
						}
					}
					else if(words[k].equals("revolver")) {
						card = "revolver";
						cardNum=9;
					}
					else if(words[k].equals("rope")) {
						card = "rope";
						cardNum=10;
					}
					else if(words[k].equals("wrench")) {
						card = "wrench";
						cardNum=11;
					}
					
					else if(words[k].equals("ball")) {
						if(words[k+1].equals("room")) {
							card = "ball room";
							cardNum=12;
						}
					}
					else if(words[k].equals("billiard")) {
						if(words[k+1].equals("room")) {
							card = "billiard room";
							cardNum=13;
						}
					}
					else if(words[k].equals("conservatory")) {
						card = "conservatory";
						cardNum=14;
					}
					else if(words[k].equals("dining")) {
						if(words[k+1].equals("room")) {
							card = "dining room";
							cardNum=15;
						}
					}
					else if(words[k].equals("hall")) {
						card = "hall";
						cardNum=16;
					}
					else if(words[k].equals("kitchen")) {
						card = "kitchen";
						cardNum=17;
					}
					else if(words[k].equals("library")) {
						card = "library";
						cardNum=18;
					}
					else if(words[k].equals("lounge")) {
						card = "lounge";
						cardNum=19;
					}
					else if(words[k].equals("study")) {
						card = "study";
						cardNum=20;
					}
				}
				
			}
		}
		
		if(!card.equals("")) {
			if(hasCard) {
				Board.tokenAL.get(currentPlayer%Main.numPlayers).fillRowWithXOnNotes(cardNum, playerNum);
			}
			else if(doesNotHaveCard) {
				Board.tokenAL.get(currentPlayer%Main.numPlayers).fillCellWithXOnNotes(cardNum, playerNum);
			}
			textualcommand.textfield.setText("");
		}
	}
	
	public Token whichToken(String name, Token token) {
		for(int t=0; t<6; t++) {
			if(Board.tokenAL.get(t).name.equals(name)) {
				token = Board.tokenAL.get(t);
			}
		}
		return token;
	}
}