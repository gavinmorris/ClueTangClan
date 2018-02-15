import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Board extends JPanel {


	//declare buffered images, image icons and jlabels
    public BufferedImage boardBufferedImage;
	public ImageIcon boardImage;
    public JLabel cluedoboard;
	
    //declare starting positions for each character piece
	public static int greenx = 250; static int greeny = 25;
	public static int mustardx = 44; static int mustardy = 415;
	public static int peacockx = 572; static int peacocky = 161;
	public static int plumx = 572; static int plumy = 461;
	public static int scarlettx = 204; static int scarletty = 576;
	public static int whitex = 365; static int whitey = 25;
	
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
    
    public JLayeredPane layeredPane;
    
    public Weapons candlestick;
    public Weapons knife;
    public Weapons leadpipe;
    public Weapons revolver;
    public Weapons rope;
    public Weapons wrench;
    
    public Tokens green;
    public Tokens mustard;
    public Tokens peacock;
    public Tokens plum;
    public Tokens scarlett;
    public Tokens white;

	public Board() {
		
		try {
			boardBufferedImage = ImageIO.read(this.getClass().getResource("Board.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		boardImage = new ImageIcon(boardBufferedImage);
		cluedoboard = new JLabel(boardImage);

		candlestick = new Weapons("candlestick", "kitchen");
		knife = new Weapons("knife", "ball room");
		leadpipe = new Weapons("leadpipe", "dining room");
		revolver = new Weapons("revolver", "lounge");
		rope = new Weapons("rope", "hall");
		wrench = new Weapons("wrench", "study");
		
		green = new Tokens("green");
		mustard = new Tokens("mustard");
		peacock = new Tokens("peacock");
		plum = new Tokens("plum");
		scarlett = new Tokens("scarlett");
		white = new Tokens("white");
		
		
		
		
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
		
		green.setBounds(greenx, greeny, green.imageIcon.getIconWidth(), green.imageIcon.getIconHeight());
		mustard.setBounds(mustardx, mustardy, mustard.imageIcon.getIconWidth(), mustard.imageIcon.getIconHeight());
		peacock.setBounds(peacockx, peacocky, peacock.imageIcon.getIconWidth(), peacock.imageIcon.getIconHeight());
		plum.setBounds(plumx, plumy, plum.imageIcon.getIconWidth(), plum.imageIcon.getIconHeight());
		scarlett.setBounds(scarlettx, scarletty, scarlett.imageIcon.getIconWidth(), scarlett.imageIcon.getIconHeight());
		white.setBounds(whitex, whitey, white.imageIcon.getIconWidth(), white.imageIcon.getIconHeight());
		
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




