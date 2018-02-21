import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    
    public Token green;
    public Token mustard;
    public Token peacock;
    public Token plum;
    public Token scarlett;
    public Token white;
	
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
		
		green = new Token("green");
	    mustard = new Token("mustard");
	    peacock = new Token("peacock");
	    plum = new Token("plum");
	    scarlett = new Token("scarlett");
	    white = new Token("white");
		
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
		
		layeredPane.add(green, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(mustard, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(peacock, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(plum, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(scarlett, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(white, JLayeredPane.PALETTE_LAYER);
		
		//movePiece("green", greenStartingx, greenStartingy);
		
		green.setBounds(greenStartingx, greenStartingy, green.imageIcon.getIconWidth(), green.imageIcon.getIconHeight());
		mustard.setBounds(mustardStartingx, mustardStartingy, mustard.imageIcon.getIconWidth(), mustard.imageIcon.getIconHeight());
		peacock.setBounds(peacockStartingx, peacockStartingy, peacock.imageIcon.getIconWidth(), peacock.imageIcon.getIconHeight());
		plum.setBounds(plumStartingx, plumStartingy, plum.imageIcon.getIconWidth(), plum.imageIcon.getIconHeight());
		scarlett.setBounds(scarlettStartingx, scarlettStartingy, scarlett.imageIcon.getIconWidth(), scarlett.imageIcon.getIconHeight());
		white.setBounds(whiteStartingx, whiteStartingy, white.imageIcon.getIconWidth(), white.imageIcon.getIconHeight());
		
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




