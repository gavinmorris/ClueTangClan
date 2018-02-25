import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//declare textarea and scrollpane
    public JTextArea textarea = new JTextArea(20, 30);
    public JScrollPane scrollpane = new JScrollPane(textarea);
	TextualCommand textualcommand = new TextualCommand();
    
    public String introText = "Display Panel";
	
	public Display() {
		String introText = "Display Panel";
		textarea.setEditable(false);	//so that the display panel cannot be changed without using the textual command
		textarea.setLineWrap(true);
		textarea.setText("\n --------------- "  + introText + " ---------------\n"+"Type start to begin.\n");
		
		String result = "";
		String tokenNames[] = {"Miss. Scarlett (red token)", "Col. Mustard (yellow token)", "Rev. Green (green token)", "Ms. White (white token)", "Mrs. Peacock (blue token)", "Prof. Plum (purple token)", "Ms. Scarlett (red token)"};
		for(int i=0;i<Main.numPlayers;i++) {
			result = result +Main.playerNames[i]+" -> "+tokenNames[i]+"\n";
		}
		String help = "\n Type help to see instructions.";
		textarea.setText(result+help+"\n --------------- "  + introText + " ---------------\n"+"Type start to begin.\n");
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(scrollpane);
        this.setBorder(new EmptyBorder(10, 10, 0, 10));
	}

}
