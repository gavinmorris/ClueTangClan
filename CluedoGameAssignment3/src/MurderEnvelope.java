import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class MurderEnvelope extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Card suspect;
	public Card weapon;
	public Card room;
	//aidan is gerry adams :)
	public int envelopeWidth;
	public int envelopeHeight;
    
    public JLayeredPane layeredPane;
	
	public MurderEnvelope(Card suspect, Card weapon, Card room) {
		this.suspect = suspect;
		this.weapon = weapon;
		this.room = room;
		envelopeWidth = suspect.imageIcon.getIconWidth()+weapon.imageIcon.getIconWidth()+room.imageIcon.getIconWidth()+40;
		envelopeHeight = room.imageIcon.getIconHeight() + 30;

		
		//declare new jlayeredpane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(envelopeWidth, envelopeHeight));
		
		//set jlayeredpane background to envelope and add the cards
		layeredPane.add(suspect, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(weapon, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(room, JLayeredPane.DEFAULT_LAYER);
		suspect.setBounds(10, 10, suspect.imageIcon.getIconWidth(), suspect.imageIcon.getIconHeight());
		weapon.setBounds(suspect.imageIcon.getIconWidth()+20, 10, weapon.imageIcon.getIconWidth(), weapon.imageIcon.getIconHeight());
		room.setBounds(suspect.imageIcon.getIconWidth()+weapon.imageIcon.getIconWidth()+30, 10, room.imageIcon.getIconWidth(), room.imageIcon.getIconHeight()); 
		
		this.add(layeredPane);
		
//		this.setBorder(new EmptyBorder(4, 4, 0, 4));
		this.setBackground(Color.DARK_GRAY);
		
	}
	
	public int getWidth() {
		return envelopeWidth;
	}
	public int getHeight() {
		return envelopeHeight;
	}
}
