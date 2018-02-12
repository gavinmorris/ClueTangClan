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
    public static BufferedImage boardBufferedImage;
	public static ImageIcon boardImage;
    public static JLabel cluedoboard;
    
    public static BufferedImage greenBufferedImage, mustardBufferedImage, peacockBufferedImage, plumBufferedImage, scarlettBufferedImage, whiteBufferedImage;
	public static ImageIcon greenImage, mustardImage, peacockImage, plumImage, scarlettImage, whiteImage;
    public static JLabel greenLabel, mustardLabel, peacockLabel, plumLabel, scarlettLabel, whiteLabel;
    
	public static BufferedImage candlestickBufferedImage, knifeBufferedImage, leadpipeBufferedImage, revolverBufferedImage, ropeBufferedImage, wrenchBufferedImage;
	public static ImageIcon candlestickImage, knifeImage, leadpipeImage, revolverImage, ropeImage, wrenchImage;
    public static JLabel candlestickLabel, knifeLabel, leadpipeLabel, revolverLabel, ropeLabel, wrenchLabel;
	
    //declare starting positions for each character piece
	static int greenx = 250; static int greeny = 25;
	static int mustardx = 44; static int mustardy = 415;
	static int peacockx = 572; static int peacocky = 161;
	static int plumx = 572; static int plumy = 461;
	static int scarlettx = 204; static int scarletty = 576;
	static int whitex = 365; static int whitey = 25;
	
	//declare location within rooms for weapons to go to
	static int kitchenx = 65; static int kitcheny = 44;
	static int ballroomx = 300; static int ballroomy = 44;
	static int conservatoryx = 555; static int conservatoryy = 44;
	static int billiardroomx = 555; static int billiardroomy = 260;
	static int libraryx = 555; static int libraryy = 374;
	static int studyx = 445; static int studyy = 511;
	static int hallx = 300; static int hally = 540;
	static int loungex = 155; static int loungey = 500;
	static int diningroomx = 170; static int diningroomy = 285;
    
    public JLayeredPane layeredPane;

	public Board() {

		
		
		//assign each image icon its corresponding buffered image and assign each jlabel its corresponding image icon
		
		try {
			boardBufferedImage = ImageIO.read(this.getClass().getResource("Board.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		
		boardImage = new ImageIcon(boardBufferedImage);
		cluedoboard = new JLabel(boardImage);
		

		try {
			greenBufferedImage = ImageIO.read(this.getClass().getResource("Green.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			mustardBufferedImage = ImageIO.read(this.getClass().getResource("Mustard.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			peacockBufferedImage = ImageIO.read(this.getClass().getResource("Peacock.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			plumBufferedImage = ImageIO.read(this.getClass().getResource("Plum.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			scarlettBufferedImage = ImageIO.read(this.getClass().getResource("Scarlett.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			whiteBufferedImage = ImageIO.read(this.getClass().getResource("White.png"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}

		greenImage = new ImageIcon(greenBufferedImage);
		mustardImage = new ImageIcon(mustardBufferedImage);
		peacockImage = new ImageIcon(peacockBufferedImage);
		plumImage = new ImageIcon(plumBufferedImage);
		scarlettImage = new ImageIcon(scarlettBufferedImage);
		whiteImage = new ImageIcon(whiteBufferedImage);

		greenLabel = new JLabel(greenImage);
		mustardLabel = new JLabel(mustardImage);
		peacockLabel = new JLabel(peacockImage);
		plumLabel = new JLabel(plumImage);
		scarlettLabel = new JLabel(scarlettImage);
		whiteLabel = new JLabel(whiteImage);
		
		
		try {
			candlestickBufferedImage = ImageIO.read(this.getClass().getResource("Candlestick.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			knifeBufferedImage = ImageIO.read(this.getClass().getResource("Knife.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			leadpipeBufferedImage = ImageIO.read(this.getClass().getResource("LeadPipe.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			revolverBufferedImage = ImageIO.read(this.getClass().getResource("Revolver.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			ropeBufferedImage = ImageIO.read(this.getClass().getResource("Rope.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		try {
			wrenchBufferedImage = ImageIO.read(this.getClass().getResource("Wrench.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}

		candlestickImage = new ImageIcon(candlestickBufferedImage);
		knifeImage = new ImageIcon(knifeBufferedImage);
		leadpipeImage = new ImageIcon(leadpipeBufferedImage);
		revolverImage = new ImageIcon(revolverBufferedImage);
		ropeImage = new ImageIcon(ropeBufferedImage);
		wrenchImage = new ImageIcon(wrenchBufferedImage);
		
		candlestickLabel = new JLabel(candlestickImage);
		knifeLabel = new JLabel(knifeImage);
		leadpipeLabel = new JLabel(leadpipeImage);
		revolverLabel = new JLabel(revolverImage);
		ropeLabel = new JLabel(ropeImage);
		wrenchLabel = new JLabel(wrenchImage);
		
		//declare new jlayeredpane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
		
		//set jlayeredpane background to cluedo board and set the pieces and weapons to sit on top of the board
		layeredPane.add(cluedoboard, JLayeredPane.DEFAULT_LAYER);
		cluedoboard.setBounds(0, 0, boardImage.getIconWidth(), boardImage.getIconHeight()); 
		
		layeredPane.add(greenLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(mustardLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(peacockLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(plumLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(scarlettLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(whiteLabel, JLayeredPane.PALETTE_LAYER);
		
		greenLabel.setBounds(greenx, greeny, greenImage.getIconWidth(), greenImage.getIconHeight());
		mustardLabel.setBounds(mustardx, mustardy, mustardImage.getIconWidth(), mustardImage.getIconHeight());
		peacockLabel.setBounds(peacockx, peacocky, peacockImage.getIconWidth(), peacockImage.getIconHeight());
		plumLabel.setBounds(plumx, plumy, plumImage.getIconWidth(), plumImage.getIconHeight());
		scarlettLabel.setBounds(scarlettx, scarletty, scarlettImage.getIconWidth(), scarlettImage.getIconHeight());
		whiteLabel.setBounds(whitex, whitey, whiteImage.getIconWidth(), whiteImage.getIconHeight());
		
		layeredPane.add(candlestickLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(knifeLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(leadpipeLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(revolverLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ropeLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(wrenchLabel, JLayeredPane.PALETTE_LAYER);

		candlestickLabel.setBounds(kitchenx, kitcheny, candlestickImage.getIconWidth(), candlestickImage.getIconHeight());
		knifeLabel.setBounds(ballroomx, ballroomy, knifeImage.getIconWidth(), knifeImage.getIconHeight());
		leadpipeLabel.setBounds(diningroomx, diningroomy, leadpipeImage.getIconWidth(), leadpipeImage.getIconHeight());
		revolverLabel.setBounds(hallx, hally, revolverImage.getIconWidth(), revolverImage.getIconHeight());
		ropeLabel.setBounds(loungex, loungey, ropeImage.getIconWidth(), ropeImage.getIconHeight());
		wrenchLabel.setBounds(studyx, studyy, wrenchImage.getIconWidth(), wrenchImage.getIconHeight());
		
		this.add(layeredPane);
		
		this.setBorder(new EmptyBorder(4, 4, 0, 4));
		this.setBackground(Color.DARK_GRAY);
	}
	
//	public void paintComponent(Graphics g) {
//		 super.paintComponent(g);
//		 Graphics2D g2 = (Graphics2D) g;
//
//		 g2.drawImage(boardImage, 0, 0, boardImage.getWidth(), boardImage.getHeight(), this);
//		 
//	}
		
}




