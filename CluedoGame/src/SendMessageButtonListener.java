import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
	ActionListener to echo text onto display JTextArea
 */

class SendMessageButtonListener implements ActionListener {
	
	//SMH!
	String  userTag = "jembaba58";
	
	//connects to display class and textualCommand class
	public void actionPerformed(ActionEvent event) {
		buttonfunction(Main.display, Main.textualcommand);
	}
	
	
	//Takes from textualCommand gives to display
	public void buttonfunction(Display display, TextualCommand textualcommand) {
		if (textualcommand.textfield.getText().length() < 1) {
			// do nothing
		} else {
			display.textarea.append("<" + userTag+">: " +  textualcommand.textfield.getText() + "\n");
			textualcommand.textfield.setText("");
		}
	}
	
}