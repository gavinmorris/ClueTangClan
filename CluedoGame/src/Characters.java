import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Characters{
	//ID for each character
	int ID;
	//Character's name
	String Name;
	//Character's color
	String Color;
	//Current position on the board
	//Will map to gridbaglayout beneath the board
	private int X;
	private int Y;
	
//Accessors
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}

//Mutators
	public void setX(int newX) {
		this.X = newX;
	}
	
	public void setY(int newY) {
		this.Y = newY;
	}
}