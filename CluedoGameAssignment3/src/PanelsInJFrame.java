import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PanelsInJFrame {
	
	public Board board;
	public Display display;
	public TextualCommand textualcommand;
	public BoardStructure boardstructure;
	
	public MurderEnvelope murderenvelope;
	
	public static Notes notes;
	
	public JFrame frame;
	public int frameWidth=1050;
	public int frameHeight=720;
	
	public static JLayeredPane layeredPane;
	
    public ArrayList<Card> cardAL = new ArrayList<Card>();
    public ArrayList<Card> envelopeCardAL = new ArrayList<Card>();
    public ArrayList<Card> visibleCardAL = new ArrayList<Card>();
    public int noOfCards=21;
	
	public PanelsInJFrame() {

		//declare new jframe and panels, ie board, display and texualcommand
		frame = new JFrame("Cluedo");
		frame.setLayout(new BorderLayout());
		board = new Board();
		display = new Display();
		textualcommand = new TextualCommand();	
		
		notes = new Notes();
		
	    cardAL.add(new Card("green"));
	    cardAL.add(new Card("mustard"));
	    cardAL.add(new Card("peacock"));
	    cardAL.add(new Card("plum"));
	    cardAL.add(new Card("scarlett"));
	    cardAL.add(new Card("white"));
	    cardAL.add(new Card("candlestick"));
	    cardAL.add(new Card("knife"));
	    cardAL.add(new Card("lead pipe"));
	    cardAL.add(new Card("revolver"));
	    cardAL.add(new Card("rope"));
	    cardAL.add(new Card("wrench"));
	    cardAL.add(new Card("ball room"));
	    cardAL.add(new Card("billiard room"));
	    cardAL.add(new Card("conservatory"));
	    cardAL.add(new Card("dining room"));
	    cardAL.add(new Card("hall"));
	    cardAL.add(new Card("kitchen"));
	    cardAL.add(new Card("library"));
	    cardAL.add(new Card("lounge"));
	    cardAL.add(new Card("study"));
	    
	    dealCards(cardAL);
		
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
		
		//add the panels to the jframe
		layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(display, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(textualcommand, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(notes, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(murderenvelope, JLayeredPane.PALETTE_LAYER);
		
		board.setBounds(5, 3, board.boardImage.getIconWidth()+10, board.boardImage.getIconHeight()+10);
		display.setBounds(board.boardImage.getIconWidth()+10, 4, 390, board.boardImage.getIconHeight()+6);
		textualcommand.setBounds(1, board.boardImage.getIconHeight()+10, frameWidth-10, 40);
		notes.setBounds(100, 100, notes.notesImageIcon.getIconWidth()+10, notes.notesImageIcon.getIconHeight()+10);
		notes.setVisible(false);
		murderenvelope.setBounds(100, 100, murderenvelope.getWidth(), murderenvelope.getHeight());
		murderenvelope.setVisible(false);
		
		frame.add(layeredPane);
		frame.setLocation(0, 0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(frameWidth, frameHeight);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		boardstructure = new BoardStructure();

	    System.out.println("Murder envelope:");
	    for(int i=0; i<envelopeCardAL.size(); i++) {
			System.out.println(envelopeCardAL.get(i).cardName + " ");
	    }
	    System.out.println("");
	    for(int k=0; k<Main.numPlayers; k++) {
	    	System.out.println("Player " + (k+1));
	    	for(int i=0; i < board.tokenAL.get(k).myCardsAL.size(); i++) {
				System.out.println(board.tokenAL.get(k).myCardsAL.get(i).cardName + " ");
			}
	    	System.out.print("\n");
	    }
    	System.out.println("Cards visible to all:");
	    for(int i=0; i<visibleCardAL.size(); i++) {
			System.out.println(visibleCardAL.get(i).cardName + " ");
	    }

	    
		//activate action listener for transfering text from the textual command to the display panel
		textualcommand.button.addActionListener(new SendMessageButtonListener(board, display, textualcommand, boardstructure, murderenvelope, notes));

		textualcommand.textfield.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					textualcommand.button.doClick();
				}
			}
		});	
		
	    
	    displayACards();
	    
	    displayXCards();
	}
	
	public void dealCards(ArrayList<Card> cardAL) {
		
		Random p = new Random();
		Random w = new Random();
		Random r = new Random();
		
		int suspect = p.nextInt(5);
		int weapon = w.nextInt(11-6) + 6;
		int room = r.nextInt(20-12) + 12;
		
		murderenvelope = new MurderEnvelope(cardAL.get(suspect), cardAL.get(weapon), cardAL.get(room));
		
		envelopeCardAL.add(cardAL.get(suspect));
		envelopeCardAL.add(cardAL.get(weapon));		
		envelopeCardAL.add(cardAL.get(room));
		
		cardAL.remove(suspect);
		cardAL.remove(weapon-1);
		cardAL.remove(room-2);
		
		
		Random random = new Random();
		int noOfCardsLeft = noOfCards-3;
		random.nextInt();
		for (int i=0; i<noOfCardsLeft; i++) {
		    int change = i + random.nextInt(noOfCardsLeft - i);
		    
		    Card tmp = cardAL.get(i);
		    cardAL.set(i, cardAL.get(change));
		    cardAL.set(change, tmp);
		}
		
		int k=0;
		int t=0;
		for(int i=0; i<noOfCardsLeft; i++) {
			board.tokenAL.get(k++).myCardsAL.add(cardAL.get(i));
			if(k == Main.numPlayers) {
				k=0;
				t++;
			}
			if(t == noOfCardsLeft/Main.numPlayers) {
				for(int j=i+1; j<noOfCardsLeft; j++) {
					visibleCardAL.add(cardAL.get(j));
				}
				i=noOfCardsLeft;
			}
		}
    }
	
	
	
	public void displayACards() {

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
				board.tokenAL.get(k).notes.notesArray[b][a] = new JLabel(board.tokenAL.get(k).notes.AImageIcon);
				board.tokenAL.get(k).notes.layeredPane.add(board.tokenAL.get(k).notes.notesArray[b][a], JLayeredPane.PALETTE_LAYER);
			    int i = (int) board.tokenAL.get(k).notes.notesPositionXArray[b][a];
			    int j = (int) board.tokenAL.get(k).notes.notesPositionYArray[b][a];
				board.tokenAL.get(k).notes.notesArray[b][a].setBounds(i, j, board.tokenAL.get(k).notes.XImageIcon.getIconWidth()-1, board.tokenAL.get(k).notes.XImageIcon.getIconHeight()-1); 
			}
		}
	}
	
	public void displayXCards() {
		
		for(int k=0; k<Main.numPlayers; k++) {
			
			for(int i=0; i<board.tokenAL.get(k).myCardsAL.size(); i++) {
				int b=0;
				
				if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("green")) {
					b=0; 
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("mustard")) {					
					b=1;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("peacock")) {					
					b=2;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("plum")) {					
					b=3;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("scarlett")) {					
					b=4;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("white")) {					
					b=5;
					fillRowWithX(b, k);
				}
				

				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("candlestick")) {					
					b=6;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("knife")) {					
					b=7;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("lead pipe")) {					
					b=8;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("revolver")) {					
					b=9;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("rope")) {					
					b=10;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("wrench")) {					
					b=11;
					fillRowWithX(b, k);
				}
				

				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("ball room")) {					
					b=12;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("billiard room")) {					
					b=13;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("conservatory")) {					
					b=14;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("dining room")) {					
					b=15;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("hall")) {					
					b=16;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("kitchen")) {					
					b=17;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("library")) {					
					b=18;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("lounge")) {
					b=19;
					fillRowWithX(b, k);
				}
				else if(board.tokenAL.get(k).myCardsAL.get(i).cardName.equals("study")) {
					b=20;
					fillRowWithX(b, k);
				}	
				
			}
			
		}
		
	}
	
	public void fillRowWithX(int b, int k) {
	
		for(int a=0; a<Main.numPlayers; a++) {
			if(a==k) {
				board.tokenAL.get(k).notes.notesArray[b][a] = new JLabel(board.tokenAL.get(k).notes.tickImageIcon);
			} else {
				board.tokenAL.get(k).notes.notesArray[b][a] = new JLabel(board.tokenAL.get(k).notes.XImageIcon);
			}
			board.tokenAL.get(k).notes.layeredPane.add(board.tokenAL.get(k).notes.notesArray[b][a], JLayeredPane.PALETTE_LAYER);
		    int i = (int) board.tokenAL.get(k).notes.notesPositionXArray[b][a];
		    int j = (int) board.tokenAL.get(k).notes.notesPositionYArray[b][a];
			board.tokenAL.get(k).notes.notesArray[b][a].setBounds(i, j, board.tokenAL.get(k).notes.XImageIcon.getIconWidth()-1, board.tokenAL.get(k).notes.XImageIcon.getIconHeight()-1); 
		}
		
	}
	
}
