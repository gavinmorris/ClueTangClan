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
			imageName = "CardImages/GreenCard.jpg";
		}
		else if(cardName.equals("mustard")) {
			imageName = "CardImages/MustardCard.jpg";
		}
		else if(cardName.equals("peacock")) {
			imageName = "CardImages/PeacockCard.jpg";
		}
		else if(cardName.equals("plum")) {
			imageName = "CardImages/PlumCard.jpg";
		}
		else if(cardName.equals("scarlett")) {
			imageName = "CardImages/ScarlettCard.jpg";
		}
		else if(cardName.equals("white")) {
			imageName = "CardImages/WhiteCard.jpg";
		}
		else if(cardName.equals("candlestick")) {
			imageName = "CardImages/CandlestickCard.jpg";
		}
		else if(cardName.equals("knife")) {
			imageName = "CardImages/KnifeCard.jpg";
		}
		else if(cardName.equals("lead pipe")) {
			imageName = "CardImages/LeadPipeCard.jpg";
		}
		else if(cardName.equals("revolver")) {
			imageName = "CardImages/RevolverCard.jpg";
		}
		else if(cardName.equals("rope")) {
			imageName = "CardImages/RopeCard.jpg";
		}
		else if(cardName.equals("wrench")) {
			imageName = "CardImages/WrenchCard.jpg";
		}
		else if(cardName.equals("ball room")) {
			imageName = "CardImages/BallRoomCard.jpg";
		}
		else if(cardName.equals("billiard room")) {
			imageName = "CardImages/BilliardRoomCard.jpg";
		}
		else if(cardName.equals("conservatory")) {
			imageName = "CardImages/ConservatoryCard.jpg";
		}
		else if(cardName.equals("dining room")) {
			imageName = "CardImages/DiningRoomCard.jpg";
		}
		else if(cardName.equals("hall")) {
			imageName = "CardImages/HallCard.jpg";
		}
		else if(cardName.equals("kitchen")) {
			imageName = "CardImages/KitchenCard.jpg";
		}
		else if(cardName.equals("library")) {
			imageName = "CardImages/LibraryCard.jpg";
		}
		else if(cardName.equals("lounge")) {
			imageName = "CardImages/LoungeCard.jpg";
		}
		else if(cardName.equals("study")) {
			imageName = "CardImages/StudyCard.jpg";
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
