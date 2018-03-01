import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Notes extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage notesImageBuffered;
	public ImageIcon notesImageIcon;
	public JLabel notesLabel;
	
	public BufferedImage XImageBuffered, AImageBuffered, tickImageBuffered;
	public ImageIcon XImageIcon, AImageIcon, tickImageIcon;
    
    public JLayeredPane layeredPane;
	
	public JLabel[][] notesArray = new JLabel[21][6];
	public double[][] notesPositionXArray = new double[21][6];
	public double[][] notesPositionYArray = new double[21][6];
	
	public Notes() {
		
		readInImages();
		notesImageIcon = new ImageIcon(notesImageBuffered);
		notesLabel = new JLabel(notesImageIcon);
		
		XImageIcon = new ImageIcon(XImageBuffered);
		AImageIcon = new ImageIcon(AImageBuffered);
		tickImageIcon = new ImageIcon(tickImageBuffered);
		
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(notesImageIcon.getIconWidth(), notesImageIcon.getIconHeight()));
		
		//set jlayeredpane background to cluedo board and set the pieces and weapons to sit on top of the board
		layeredPane.add(notesLabel, JLayeredPane.DEFAULT_LAYER);
		notesLabel.setBounds(0, 0, notesImageIcon.getIconWidth(), notesImageIcon.getIconHeight()); 
		
		this.add(layeredPane);
		this.setBackground(Color.DARK_GRAY);
		
		setPositionArray();
		
		//printNotesPositionArray();
		
		getRidOfAbsentPlayers();
	}
	
	
	public void setPositionArray() {
		double k = 26.8;
		for(int i=0; i<6; i++) {
			k += 64.3	;
			for(int j=0; j<21; j++) {
				notesPositionXArray[j][i] = k;
			}
		}
		
		double t = 49.35;
		for(int i=0; i<21; i++) {
			if(i==6 || i==12) { t += 20; }
			t += 20.238;			
			for(int j=0; j<6; j++) {
				notesPositionYArray[i][j] = t;
			}
		}
	}
	
	public void printNotesPositionArray() {
		for(int i=0; i<21; i++) {
			System.out.println("");
			for(int j=0; j<6; j++) {
				System.out.print(" (" + notesPositionXArray[i][j] + ", " + notesPositionYArray[i][j] + ") \t");
			}
		}
	}
	
	public void getRidOfAbsentPlayers() {
		
		if(Main.numPlayers < 6) {
			for(int i=0; i<21; i++) {
				int j=5;
			    notesArray[i][j] = new JLabel(XImageIcon);
			    layeredPane.add(notesArray[i][j], JLayeredPane.PALETTE_LAYER);
			    int a = (int) notesPositionXArray[i][j];
			    int b = (int) notesPositionYArray[i][j];
			    notesArray[i][j].setBounds(a, b, XImageIcon.getIconWidth()-1, XImageIcon.getIconHeight()-1); 
			}
			
			if(Main.numPlayers < 5) {
				for(int i=0; i<21; i++) {
					int j=4;
				    notesArray[i][j] = new JLabel(XImageIcon);
				    layeredPane.add(notesArray[i][j], JLayeredPane.PALETTE_LAYER);
				    int a = (int) notesPositionXArray[i][j];
				    int b = (int) notesPositionYArray[i][j];
				    notesArray[i][j].setBounds(a, b, XImageIcon.getIconWidth()-1, XImageIcon.getIconHeight()-1); 
				}
				
				if(Main.numPlayers < 4) {
					for(int i=0; i<21; i++) {
						int j=3;
					    notesArray[i][j] = new JLabel(XImageIcon);
					    layeredPane.add(notesArray[i][j], JLayeredPane.PALETTE_LAYER);
					    int a = (int) notesPositionXArray[i][j];
					    int b = (int) notesPositionYArray[i][j];
					    notesArray[i][j].setBounds(a, b, XImageIcon.getIconWidth()-1, XImageIcon.getIconHeight()-1); 
					}
					
					if(Main.numPlayers < 3) {
						for(int i=0; i<21; i++) {
							int j=2;
						    notesArray[i][j] = new JLabel(XImageIcon);
						    layeredPane.add(notesArray[i][j], JLayeredPane.PALETTE_LAYER);
						    int a = (int) notesPositionXArray[i][j];
						    int b = (int) notesPositionYArray[i][j];
						    notesArray[i][j].setBounds(a, b, XImageIcon.getIconWidth()-1, XImageIcon.getIconHeight()-1); 
						}
					}
				}
			}
		}
	}
	
	
	public void readInImages() {
		try {
			notesImageBuffered = ImageIO.read(this.getClass().getResource("Notes.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		
		try {
			XImageBuffered = ImageIO.read(this.getClass().getResource("X.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		
		try {
			AImageBuffered = ImageIO.read(this.getClass().getResource("A.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		
		try {
			tickImageBuffered = ImageIO.read(this.getClass().getResource("Tick.jpg"));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
	}
	
	
	
}
