
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//declare buffered images, image icons and jlabels
    public BufferedImage boardBufferedImage;
	public ImageIcon boardImage;
    public JLabel cluedoboard;
	
    //declare starting positions for each character piece
	public static int greenStartingx = 250; public static int greenStartingy = 24;
	public static int mustardStartingx = 43; public static int mustardStartingy = 415;
	public static int peacockStartingx = 572; public static int peacockStartingy = 162;
	public static int plumStartingx = 572; public static int plumStartingy = 461;
	public static int scarlettStartingx = 204; public static int scarlettStartingy = 576;
	public static int whiteStartingx = 365; public static int whiteStartingy = 24;
	
	//declare location within rooms for weapons to go to
	public static int kitchenx = 65; public static int kitcheny = 44;
	public static int ballroomx = 300; public static int ballroomy = 44;
	public static int conservatoryx = 555; public static int conservatoryy = 44;
	public static int billiardroomx = 555; public static int billiardroomy = 260;
	public static int libraryx = 555; public static int libraryy = 374;
	public static int studyx = 445; public static int studyy = 511;
	public static int hallx = 300; public static int hally = 540;
	public static int loungex = 155; public static int loungey = 500;
	public static int diningroomx = 170; public static int diningroomy = 285;
    
	//allows us to iterate through an ArrayList to access players instead of using discrete names in the code
    ArrayList<Token> tokenAL = new ArrayList<Token>();
	
    public Weapon candlestick;
    public Weapon knife;
    public Weapon leadpipe;
    public Weapon revolver;
    public Weapon rope;
    public Weapon wrench;
    
    
	
    
    public JLayeredPane layeredPane;

	public Board() {
		
		try {
			boardBufferedImage = ImageIO.read(this.getClass().getResource("Board.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		boardImage = new ImageIcon(boardBufferedImage);
		cluedoboard = new JLabel(boardImage);
		
		//creating new token objects
	    tokenAL.add(new Token("scarlett"));
		tokenAL.add(new Token("mustard"));
		tokenAL.add(new Token("green"));
	    tokenAL.add(new Token("white"));
		tokenAL.add(new Token("peacock"));
	    tokenAL.add(new Token("plum"));
		
		
		
	    candlestick = new Weapon("candlestick", "kitchen");
	    knife = new Weapon("knife", "ball room");
	    leadpipe = new Weapon("leadpipe", "dining room");
	    revolver = new Weapon("revolver", "lounge");
	    rope = new Weapon("rope", "hall");
	    wrench = new Weapon("wrench", "study");
		
		
		
		//declare new jlayeredpane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
		
		//set jlayeredpane background to cluedo board and set the pieces and weapons to sit on top of the board
		layeredPane.add(cluedoboard, JLayeredPane.DEFAULT_LAYER);
		cluedoboard.setBounds(0, 0, boardImage.getIconWidth(), boardImage.getIconHeight()); 
		
		layeredPane.add(tokenAL.get(0), JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tokenAL.get(1), JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tokenAL.get(2), JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tokenAL.get(3), JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tokenAL.get(4), JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tokenAL.get(5), JLayeredPane.PALETTE_LAYER);
		
		//movePiece("green", greenStartingx, greenStartingy);
		tokenAL.get(0).setBounds(scarlettStartingx, scarlettStartingy, tokenAL.get(4).imageIcon.getIconWidth(), tokenAL.get(4).imageIcon.getIconHeight());
		tokenAL.get(1).setBounds(mustardStartingx, mustardStartingy, tokenAL.get(1).imageIcon.getIconWidth(), tokenAL.get(1).imageIcon.getIconHeight());
		tokenAL.get(2).setBounds(whiteStartingx, whiteStartingy, tokenAL.get(5).imageIcon.getIconWidth(), tokenAL.get(5).imageIcon.getIconHeight());
		tokenAL.get(3).setBounds(greenStartingx, greenStartingy, tokenAL.get(0).imageIcon.getIconWidth(), tokenAL.get(0).imageIcon.getIconHeight());
		tokenAL.get(4).setBounds(peacockStartingx, peacockStartingy, tokenAL.get(2).imageIcon.getIconWidth(), tokenAL.get(2).imageIcon.getIconHeight());
		tokenAL.get(5).setBounds(plumStartingx, plumStartingy, tokenAL.get(3).imageIcon.getIconWidth(), tokenAL.get(3).imageIcon.getIconHeight());
		
		layeredPane.add(candlestick, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(knife, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(leadpipe, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(revolver, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(rope, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(wrench, JLayeredPane.PALETTE_LAYER);

		candlestick.setBounds(candlestick.getX(), candlestick.getY(), candlestick.imageIcon.getIconWidth(), candlestick.imageIcon.getIconHeight());
		knife.setBounds(knife.getX(), knife.getY(), knife.imageIcon.getIconWidth(), knife.imageIcon.getIconHeight());
		leadpipe.setBounds(leadpipe.getX(), leadpipe.getY(), leadpipe.imageIcon.getIconWidth(), leadpipe.imageIcon.getIconHeight());
		revolver.setBounds(revolver.getX(), revolver.getY(), revolver.imageIcon.getIconWidth(), revolver.imageIcon.getIconHeight());
		rope.setBounds(rope.getX(), rope.getY(), rope.imageIcon.getIconWidth(), rope.imageIcon.getIconHeight());
		wrench.setBounds(wrench.getX(), wrench.getY(), wrench.imageIcon.getIconWidth(), wrench.imageIcon.getIconHeight());
		
		this.add(layeredPane);
		
		this.setBorder(new EmptyBorder(4, 4, 0, 4));
		this.setBackground(Color.DARK_GRAY);
	}
	
	

		
}




