import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//declare textarea and scrollpane
    public JTextArea textarea = new JTextArea(20, 20);
    public JScrollPane scrollpane = new JScrollPane(textarea);
    
    public String introText = "Display Panel";
	
	public Display() {
		textarea.setEditable(false);	//so that the display panel cannot be changed without using the textual command
		textarea.setLineWrap(true);
		String result = "";
		String tokenNames[] = {"Mr. Green (green token)","Col. Mustard (yellow token)", "Ms. Peacock (blue token)", "Prof. Plum (purple token)", "Ms. Scarlett (red token)", "Ms. White (white token)"};
		for(int i=0;i<Main.numPlayers;i++) {
			result = result +Main.playerNames[i]+" -> "+tokenNames[i]+"\n";
		}
		textarea.setText(result+"\n --------------- "  + introText + " ---------------\n"+"Type start to begin.\n");
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(scrollpane);
        this.setBorder(new EmptyBorder(10, 10, 0, 10));	
	}

}
