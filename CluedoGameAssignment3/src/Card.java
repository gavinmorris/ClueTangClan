import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Card extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public String cardName;
	public ImageIcon imageIcon;
	
	public Card(String cardName) {
		this.cardName = cardName;
	    imageIcon = setLabelIcon(cardName);
	    this.setIcon(imageIcon);
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public ImageIcon setLabelIcon(String cardName) {
		String imageName = null;
		ImageIcon icon = null;
		if(cardName.equals("green")) {
			imageName = "GreenCard.jpg";
		}
		else if(cardName.equals("mustard")) {
			imageName = "MustardCard.jpg";
		}
		else if(cardName.equals("peacock")) {
			imageName = "PeacockCard.jpg";
		}
		else if(cardName.equals("plum")) {
			imageName = "PlumCard.jpg";
		}
		else if(cardName.equals("scarlett")) {
			imageName = "ScarlettCard.jpg";
		}
		else if(cardName.equals("white")) {
			imageName = "WhiteCard.jpg";
		}
		else if(cardName.equals("candlestick")) {
			imageName = "CandlestickCard.jpg";
		}
		else if(cardName.equals("knife")) {
			imageName = "KnifeCard.jpg";
		}
		else if(cardName.equals("lead pipe")) {
			imageName = "LeadPipeCard.jpg";
		}
		else if(cardName.equals("revolver")) {
			imageName = "RevolverCard.jpg";
		}
		else if(cardName.equals("rope")) {
			imageName = "RopeCard.jpg";
		}
		else if(cardName.equals("wrench")) {
			imageName = "WrenchCard.jpg";
		}
		else if(cardName.equals("ball room")) {
			imageName = "BallRoomCard.jpg";
		}
		else if(cardName.equals("billiard room")) {
			imageName = "BilliardRoomCard.jpg";
		}
		else if(cardName.equals("conservatory")) {
			imageName = "ConservatoryCard.jpg";
		}
		else if(cardName.equals("dining room")) {
			imageName = "DiningRoomCard.jpg";
		}
		else if(cardName.equals("hall")) {
			imageName = "HallCard.jpg";
		}
		else if(cardName.equals("kitchen")) {
			imageName = "KitchenCard.jpg";
		}
		else if(cardName.equals("library")) {
			imageName = "LibraryCard.jpg";
		}
		else if(cardName.equals("lounge")) {
			imageName = "LoungeCard.jpg";
		}
		else if(cardName.equals("study")) {
			imageName = "StudyCard.jpg";
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
