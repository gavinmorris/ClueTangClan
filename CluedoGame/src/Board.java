import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class Board extends JPanel {

	public ImageIcon image;
    public JLabel cluedoboard;
    
	public static ImageIcon greenImage, mustardImage, peacockImage, plumImage, scarlettImage, whiteImage;
    public static JLabel greenLabel, mustardLabel, peacockLabel, plumLabel, scarlettLabel, whiteLabel;
    
	public static ImageIcon candlestickImage, knifeImage, leadpipeImage, revolverImage, ropeImage, wrenchImage;
    public static JLabel candlestickLabel, knifeLabel, leadpipeLabel, revolverLabel, ropeLabel, wrenchLabel;
	
	static int greenx = 250; static int greeny = 25;
	static int mustardx = 45; static int mustardy = 415;
	static int peacockx = 572; static int peacocky = 161;
	static int plumx = 572; static int plumy = 461;
	static int scarlettx = 204; static int scarletty = 576;
	static int whitex = 365; static int whitey = 25;
	
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
		
		image = new ImageIcon("cluedoboard.jpg");
		cluedoboard = new JLabel(image);

		greenImage = new ImageIcon("green.jpg");
		mustardImage = new ImageIcon("mustard.jpg");
		peacockImage = new ImageIcon("peacock.jpg");
		plumImage = new ImageIcon("plum.jpg");
		scarlettImage = new ImageIcon("scarlett.jpg");
		whiteImage = new ImageIcon("white.jpg");
		
		greenLabel = new JLabel(greenImage);
		mustardLabel = new JLabel(mustardImage);
		peacockLabel = new JLabel(peacockImage);
		plumLabel = new JLabel(plumImage);
		scarlettLabel = new JLabel(scarlettImage);
		whiteLabel = new JLabel(whiteImage);
		
		candlestickImage = new ImageIcon("candlestick.jpg");
		knifeImage = new ImageIcon("knife.jpg");
		leadpipeImage = new ImageIcon("leadpipe.jpg");
		revolverImage = new ImageIcon("revolver.jpg");
		ropeImage = new ImageIcon("rope.jpg");
		wrenchImage = new ImageIcon("wrench.jpg");
		
		candlestickLabel = new JLabel(candlestickImage);
		knifeLabel = new JLabel(knifeImage);
		leadpipeLabel = new JLabel(leadpipeImage);
		revolverLabel = new JLabel(revolverImage);
		ropeLabel = new JLabel(ropeImage);
		wrenchLabel = new JLabel(wrenchImage);
		
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		
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




