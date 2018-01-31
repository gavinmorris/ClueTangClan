import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SendMessageButtonListener implements ActionListener {
		
	String  username = "jembaba58";
	
	public void actionPerformed(ActionEvent event) {
		buttonfunction(Main.display, Main.textualcommand);
	}
	
	public void buttonfunction(Display display, TextualCommand textualcommand) {
		if (textualcommand.textfield.getText().length() < 1) {
			// do nothing
		} else {
			display.textarea.append("<"  + username +  ">: " +  textualcommand.textfield.getText() + "\n");
			textualcommand.textfield.setText("");
		}
	}
	
}