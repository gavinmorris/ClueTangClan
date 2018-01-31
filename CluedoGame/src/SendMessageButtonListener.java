import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SendMessageButtonListener implements ActionListener {
		
	String  username1 = "jem";
	String  username2 = "gav";
	String  username3 = "euan";
	int i=0;
	
	public void actionPerformed(ActionEvent event) {
		buttonfunction(Main.display, Main.textualcommand);
	}
	
	public void buttonfunction(Display display, TextualCommand textualcommand) {
		if (textualcommand.textfield.getText().length() < 1) {
			// do nothing
		} else {
			i++;
			if(i%3 == 0) {
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username1 +  ">");				
			} else if(i%3 == 1) {
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username2 +  ">");				
			} else {
				display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username3 +  ">");				
			}
			textualcommand.textfield.setText("");
		}
	}
	
}