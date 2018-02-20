import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Weapon extends JLabel{

	public String name;
	public int xcoordinate, ycoordinate;
	
	public ImageIcon imageIcon;
	
	public Weapon(String name, String room) {
	    this.name = name;
	    imageIcon = setLabelIcon(name);
	    this.setIcon(imageIcon);
		moveTo(room);
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

	
	public void moveTo(String room) {
		if(room.equals("kitchen")) {
			this.xcoordinate = Board.kitchenx;
			this.ycoordinate = Board.kitcheny;
		}
		else if(room.equals("ball room")) {
			this.xcoordinate = Board.ballroomx;
			this.ycoordinate = Board.ballroomy;			
		}
		else if(room.equals("conservatory")) {
			this.xcoordinate = Board.conservatoryx;
			this.ycoordinate = Board.conservatoryy;
		}
		else if(room.equals("billiard room")) {
			this.xcoordinate = Board.billiardroomx;
			this.ycoordinate = Board.billiardroomy;
		}
		else if(room.equals("library")) {
			this.xcoordinate = Board.libraryx;
			this.ycoordinate = Board.libraryy;
		}
		else if(room.equals("study")) {
			this.xcoordinate = Board.studyx;
			this.ycoordinate = Board.studyy;
		}
		else if(room.equals("hall")) {
			this.xcoordinate = Board.hallx;
			this.ycoordinate = Board.hally;
		}
		else if(room.equals("lounge")) {
			this.xcoordinate = Board.loungex;
			this.ycoordinate = Board.loungey;
		}
		else if(room.equals("dining room")) {
			this.xcoordinate = Board.diningroomx;
			this.ycoordinate = Board.diningroomy;
		}
	}
	
	public ImageIcon setLabelIcon(String name) {
		String imageName = null;
		ImageIcon icon = null;
		if(name.equals("candlestick")) {
			imageName = "candlestick.jpg";
		}
		else if(name.equals("knife")) {
			imageName = "knife.jpg";
		}
		else if(name.equals("leadpipe")) {
			imageName = "leadpipe.jpg";
		}
		else if(name.equals("revolver")) {
			imageName = "revolver.jpg";
		}
		else if(name.equals("rope")) {
			imageName = "rope.jpg";
		}
		else if(name.equals("wrench")) {
			imageName = "wrench.jpg";
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
