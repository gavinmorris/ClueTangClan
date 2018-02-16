import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Token extends JLabel{

	public String name;
	public int xcoordinate, ycoordinate;
	
	public ImageIcon imageIcon;
	
	public Token(String name) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    this.setIcon(imageIcon);
		setStartingPosition(name);
	}
	
	public String getName() {
	    return name;
	}
	public int getX() {
	    return xcoordinate;
	}
	public int getY() {
	    return ycoordinate;
	}

	
	public void setPosition(int xcoordinate, int ycoordinate) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.setBounds(xcoordinate, ycoordinate, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	}

	
	public void setStartingPosition(String name) {
		if(name.equals("green")) {
			this.xcoordinate = Board.greenStartingx;
			this.ycoordinate = Board.greenStartingy;
		}
		else if(name.equals("mustard")) {
			this.xcoordinate = Board.mustardStartingx;
			this.ycoordinate = Board.mustardStartingy;			
		}
		else if(name.equals("peacock")) {
			this.xcoordinate = Board.peacockStartingx;
			this.ycoordinate = Board.peacockStartingy;
		}
		else if(name.equals("plum")) {
			this.xcoordinate = Board.plumStartingx;
			this.ycoordinate = Board.plumStartingy;
		}
		else if(name.equals("scarlett")) {
			this.xcoordinate = Board.scarlettStartingx;
			this.ycoordinate = Board.scarlettStartingy;
		}
		else if(name.equals("white")) {
			this.xcoordinate = Board.whiteStartingx;
			this.ycoordinate = Board.whiteStartingy;
		}
	}
	
	public ImageIcon setLabelIcon(String name) {
		String imageName = null;
		ImageIcon icon = null;
		if(name.equals("green")) {
			imageName = "green.png";
		}
		else if(name.equals("mustard")) {
			imageName = "mustard.png";
		}
		else if(name.equals("peacock")) {
			imageName = "peacock.png";
		}
		else if(name.equals("plum")) {
			imageName = "plum.png";
		}
		else if(name.equals("scarlett")) {
			imageName = "scarlett.png";
		}
		else if(name.equals("white")) {
			imageName = "white.png";
		}

		BufferedImage imageBuffered = null;
		try {
			imageBuffered = ImageIO.read(this.getClass().getResource(imageName));
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}

		icon = new ImageIcon(imageBuffered);
		return icon;
	}
	
	
	
}
