import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

public class Board extends JPanel {

	//declare image icons and jlabels
	public ImageIcon image;
    public JLabel cluedoboard;
    
	public static ImageIcon greenImage, mustardImage, peacockImage, plumImage, scarlettImage, whiteImage;
    public static JLabel greenLabel, mustardLabel, peacockLabel, plumLabel, scarlettLabel, whiteLabel;
    
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
		
		//assign each image icon its corresponding image and assign each jlabel its corresponding image icon
		image = new ImageIcon("Images/Board/Board.jpg");
		cluedoboard = new JLabel(image);

		greenImage = new ImageIcon("Images/Characters/Green.png");
		mustardImage = new ImageIcon("Images/Characters/Mustard.png");
		peacockImage = new ImageIcon("Images/Characters/Peacock.png");
		plumImage = new ImageIcon("Images/Characters/Plum.png");
		scarlettImage = new ImageIcon("Images/Characters/Scarlett.png");
		whiteImage = new ImageIcon("Images/Characters/White.png");

		greenLabel = new JLabel(greenImage);
		mustardLabel = new JLabel(mustardImage);
		peacockLabel = new JLabel(peacockImage);
		plumLabel = new JLabel(plumImage);
		scarlettLabel = new JLabel(scarlettImage);
		whiteLabel = new JLabel(whiteImage);

		candlestickImage = new ImageIcon("Images/Weapons/Candlestick.jpg");
		knifeImage = new ImageIcon("Images/Weapons/Knife.jpg");
		leadpipeImage = new ImageIcon("Images/Weapons/Leadpipe.jpg");
		revolverImage = new ImageIcon("Images/Weapons/Revolver.jpg");
		ropeImage = new ImageIcon("Images/Weapons/Rope.jpg");
		wrenchImage = new ImageIcon("Images/Weapons/Wrench.jpg");
		
		candlestickLabel = new JLabel(candlestickImage);
		knifeLabel = new JLabel(knifeImage);
		leadpipeLabel = new JLabel(leadpipeImage);
		revolverLabel = new JLabel(revolverImage);
		ropeLabel = new JLabel(ropeImage);
		wrenchLabel = new JLabel(wrenchImage);
		
		//declare new jlayeredpane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		
		//set jlayeredpane background to cluedo board and set the pieces and weapons to sit on top of the board
		layeredPane.add(cluedoboard, JLayeredPane.DEFAULT_LAYER);
		cluedoboard.setBounds(0, 0, image.getIconWidth(), image.getIconHeight()); 
		
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
		
}




