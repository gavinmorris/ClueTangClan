import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Tokens extends JLabel{

	public String name;
	public int xcoordinate, ycoordinate;
	
	public ImageIcon imageIcon;
	
	public Tokens(String name) {
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
	}

	
	public void setStartingPosition(String name) {
		if(name.equals("green")) {
			this.xcoordinate = Board.greenx;
			this.ycoordinate = Board.greeny;
		}
		else if(name.equals("mustard")) {
			this.xcoordinate = Board.mustardx;
			this.ycoordinate = Board.mustardy;			
		}
		else if(name.equals("peacock")) {
			this.xcoordinate = Board.peacockx;
			this.ycoordinate = Board.peacocky;
		}
		else if(name.equals("plum")) {
			this.xcoordinate = Board.plumx;
			this.ycoordinate = Board.plumy;
		}
		else if(name.equals("scarlett")) {
			this.xcoordinate = Board.scarlettx;
			this.ycoordinate = Board.scarletty;
		}
		else if(name.equals("white")) {
			this.xcoordinate = Board.whitex;
			this.ycoordinate = Board.whitey;
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
