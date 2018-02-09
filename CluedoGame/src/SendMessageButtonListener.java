import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageButtonListener implements ActionListener {
	
	//usernames
	static String  username1 = "jem";
	static String  username2 = "gav";
	static String  username3 = "euan";
	int i=0;
	
	
	public void actionPerformed(ActionEvent event) {
		buttonfunction(Main.display, Main.textualcommand);
	}
	
	
	public void buttonfunction(Display display, TextualCommand textualcommand) {

		//according to the text entered in the textual command, move each character one box up, down, left or right
		
		if(textualcommand.textfield.getText().equals("move green up")) {
			Board.greenLabel.setBounds(Board.greenx, Board.greeny-=23, Board.greenImage.getIconWidth(), Board.greenImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move green down")) {
			Board.greenLabel.setBounds(Board.greenx, Board.greeny+=23, Board.greenImage.getIconWidth(), Board.greenImage.getIconHeight());
			whoseTurn(display, textualcommand);			
		} else if(textualcommand.textfield.getText().equals("move green left")) {
			Board.greenLabel.setBounds(Board.greenx-=23, Board.greeny, Board.greenImage.getIconWidth(), Board.greenImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move green right")) {
			Board.greenLabel.setBounds(Board.greenx+=23, Board.greeny, Board.greenImage.getIconWidth(), Board.greenImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move mustard up")) {
			Board.mustardLabel.setBounds(Board.mustardx, Board.mustardy-=23, Board.mustardImage.getIconWidth(), Board.mustardImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move mustard down")) {
			Board.mustardLabel.setBounds(Board.mustardx, Board.mustardy+=23, Board.mustardImage.getIconWidth(), Board.mustardImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move mustard left")) {
			Board.mustardLabel.setBounds(Board.mustardx-=23, Board.mustardy, Board.mustardImage.getIconWidth(), Board.mustardImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move mustard right")) {
			Board.mustardLabel.setBounds(Board.mustardx+=23, Board.mustardy, Board.mustardImage.getIconWidth(), Board.mustardImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 

		else if(textualcommand.textfield.getText().equals("move peacock up")) {
			Board.peacockLabel.setBounds(Board.peacockx, Board.peacocky-=23, Board.peacockImage.getIconWidth(), Board.peacockImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move peacock down")) {
			Board.peacockLabel.setBounds(Board.peacockx, Board.peacocky+=23, Board.peacockImage.getIconWidth(), Board.peacockImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move peacock left")) {
			Board.peacockLabel.setBounds(Board.peacockx-=23, Board.peacocky, Board.peacockImage.getIconWidth(), Board.peacockImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move peacock right")) {
			Board.peacockLabel.setBounds(Board.peacockx+=23, Board.peacocky, Board.peacockImage.getIconWidth(), Board.peacockImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move plum up")) {
			Board.plumLabel.setBounds(Board.plumx, Board.plumy-=23, Board.plumImage.getIconWidth(), Board.plumImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move plum down")) {
			Board.plumLabel.setBounds(Board.plumx, Board.plumy+=23, Board.plumImage.getIconWidth(), Board.plumImage.getIconHeight());
			whoseTurn(display, textualcommand);			
		} else if(textualcommand.textfield.getText().equals("move plum left")) {
			Board.plumLabel.setBounds(Board.plumx-=23, Board.plumy, Board.plumImage.getIconWidth(), Board.plumImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move plum right")) {
			Board.plumLabel.setBounds(Board.plumx+=23, Board.plumy, Board.plumImage.getIconWidth(), Board.plumImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 

		else if(textualcommand.textfield.getText().equals("move scarlett up")) {
			Board.scarlettLabel.setBounds(Board.scarlettx, Board.scarletty-=23, Board.scarlettImage.getIconWidth(), Board.scarlettImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move scarlett down")) {
			Board.scarlettLabel.setBounds(Board.scarlettx, Board.scarletty+=23, Board.scarlettImage.getIconWidth(), Board.scarlettImage.getIconHeight());
			whoseTurn(display, textualcommand);			
		} else if(textualcommand.textfield.getText().equals("move scarlett left")) {
			Board.scarlettLabel.setBounds(Board.scarlettx-=23, Board.scarletty, Board.scarlettImage.getIconWidth(), Board.scarlettImage.getIconHeight());
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move scarlett right")) {
			Board.scarlettLabel.setBounds(Board.scarlettx+=23, Board.scarletty, Board.scarlettImage.getIconWidth(), Board.scarlettImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 

		else if(textualcommand.textfield.getText().equals("move white up")) {
			Board.whiteLabel.setBounds(Board.whitex, Board.whitey-=23, Board.whiteImage.getIconWidth(), Board.whiteImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move white down")) {
			Board.whiteLabel.setBounds(Board.whitex, Board.whitey+=23, Board.whiteImage.getIconWidth(), Board.whiteImage.getIconHeight());
			whoseTurn(display, textualcommand);			
		} else if(textualcommand.textfield.getText().equals("move white left")) {
			Board.whiteLabel.setBounds(Board.whitex-=23, Board.whitey, Board.whiteImage.getIconWidth(), Board.whiteImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move white right")) {
			Board.whiteLabel.setBounds(Board.whitex+=23, Board.whitey, Board.whiteImage.getIconWidth(), Board.whiteImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 
		
		//according to the text entered in the textual command, move each weapon to a given room
		
		else if(textualcommand.textfield.getText().equals("move candlestick to kitchen")) {
			moveRoom(Board.candlestickLabel, "kitchen");
			Board.candlestickLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to ball room")) {
			moveRoom(Board.candlestickLabel, "ball room");
			Board.candlestickLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move candlestick to conservatory")) {
			moveRoom(Board.candlestickLabel, "conservatory");
			Board.candlestickLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move candlestick to billiard room")) {
			moveRoom(Board.candlestickLabel, "billiard room");
			Board.candlestickLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to library")) {
			moveRoom(Board.candlestickLabel, "library");
			Board.candlestickLabel.setBounds(Board.libraryx, Board.libraryy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to study")) {
			moveRoom(Board.candlestickLabel, "study");
			Board.candlestickLabel.setBounds(Board.studyx, Board.studyy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to hall")) {
			moveRoom(Board.candlestickLabel, "hall");
			Board.candlestickLabel.setBounds(Board.hallx, Board.hally, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to lounge")) {
			moveRoom(Board.candlestickLabel, "lounge");
			Board.candlestickLabel.setBounds(Board.loungex, Board.loungey, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to dining room")) {
			moveRoom(Board.candlestickLabel, "dining room");
			Board.candlestickLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move knife to kitchen")) {
			moveRoom(Board.knifeLabel, "kitchen");
			Board.knifeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to ball room")) {
			moveRoom(Board.knifeLabel, "ball room");
			Board.knifeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move knife to conservatory")) {
			moveRoom(Board.knifeLabel, "conservatory");
			Board.knifeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move knife to billiard room")) {
			moveRoom(Board.knifeLabel, "billiard room");
			Board.knifeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to library")) {
			moveRoom(Board.knifeLabel, "library");
			Board.knifeLabel.setBounds(Board.libraryx, Board.libraryy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to study")) {
			moveRoom(Board.knifeLabel, "study");
			Board.knifeLabel.setBounds(Board.studyx, Board.studyy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to hall")) {
			moveRoom(Board.knifeLabel, "hall");
			Board.knifeLabel.setBounds(Board.hallx, Board.hally, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to lounge")) {
			moveRoom(Board.knifeLabel, "lounge");
			Board.knifeLabel.setBounds(Board.loungex, Board.loungey, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to dining room")) {
			moveRoom(Board.knifeLabel, "dining room");
			Board.knifeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move lead pipe to kitchen")) {
			moveRoom(Board.leadpipeLabel, "kitchen");
			Board.leadpipeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to ball room")) {
			moveRoom(Board.leadpipeLabel, "ball room");
			Board.leadpipeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move lead pipe to conservatory")) {
			moveRoom(Board.leadpipeLabel, "conservatory");
			Board.leadpipeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move lead pipe to billiard room")) {
			moveRoom(Board.leadpipeLabel, "billiard room");
			Board.leadpipeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to library")) {
			moveRoom(Board.leadpipeLabel, "library");
			Board.leadpipeLabel.setBounds(Board.libraryx, Board.libraryy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to study")) {
			moveRoom(Board.leadpipeLabel, "study");
			Board.leadpipeLabel.setBounds(Board.studyx, Board.studyy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to hall")) {
			moveRoom(Board.leadpipeLabel, "hall");
			Board.leadpipeLabel.setBounds(Board.hallx, Board.hally, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to lounge")) {
			moveRoom(Board.leadpipeLabel, "lounge");
			Board.leadpipeLabel.setBounds(Board.loungex, Board.loungey, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to dining room")) {
			moveRoom(Board.leadpipeLabel, "dining room");
			Board.leadpipeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move revolver to kitchen")) {
			moveRoom(Board.revolverLabel, "kitchen");
			Board.revolverLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to ball room")) {
			moveRoom(Board.revolverLabel, "ball room");
			Board.revolverLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move revolver to conservatory")) {
			moveRoom(Board.revolverLabel, "conservatory");
			Board.revolverLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move revolver to billiard room")) {
			moveRoom(Board.revolverLabel, "billiard room");
			Board.revolverLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to library")) {
			moveRoom(Board.revolverLabel, "library");
			Board.revolverLabel.setBounds(Board.libraryx, Board.libraryy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to study")) {
			moveRoom(Board.revolverLabel, "study");
			Board.revolverLabel.setBounds(Board.studyx, Board.studyy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to hall")) {
			moveRoom(Board.revolverLabel, "hall");
			Board.revolverLabel.setBounds(Board.hallx, Board.hally, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to lounge")) {
			moveRoom(Board.revolverLabel, "lounge");
			Board.revolverLabel.setBounds(Board.loungex, Board.loungey, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to dining room")) {
			moveRoom(Board.revolverLabel, "dining room");
			Board.revolverLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 

		else if(textualcommand.textfield.getText().equals("move rope to kitchen")) {
			moveRoom(Board.ropeLabel, "kitchen");
			Board.ropeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to ball room")) {
			moveRoom(Board.ropeLabel, "ball room");
			Board.ropeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move rope to conservatory")) {
			moveRoom(Board.ropeLabel, "conservatory");
			Board.ropeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move rope to billiard room")) {
			moveRoom(Board.ropeLabel, "billiard room");
			Board.ropeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to library")) {
			moveRoom(Board.ropeLabel, "library");
			Board.ropeLabel.setBounds(Board.libraryx, Board.libraryy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to study")) {
			moveRoom(Board.ropeLabel, "study");
			Board.ropeLabel.setBounds(Board.studyx, Board.studyy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to hall")) {
			moveRoom(Board.ropeLabel, "hall");
			Board.ropeLabel.setBounds(Board.hallx, Board.hally, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to lounge")) {
			moveRoom(Board.ropeLabel, "lounge");
			Board.ropeLabel.setBounds(Board.loungex, Board.loungey, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to dining room")) {
			moveRoom(Board.ropeLabel, "dining room");
			Board.ropeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move wrench to kitchen")) {
			moveRoom(Board.wrenchLabel, "kitchen");
			Board.wrenchLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to ball room")) {
			moveRoom(Board.wrenchLabel, "ball room");
			Board.wrenchLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move wrench to conservatory")) {
			moveRoom(Board.wrenchLabel, "conservatory");
			Board.wrenchLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move wrench to billiard room")) {
			moveRoom(Board.wrenchLabel, "billiard room");
			Board.wrenchLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to library")) {
			moveRoom(Board.wrenchLabel, "library");
			Board.wrenchLabel.setBounds(Board.libraryx, Board.libraryy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to study")) {
			moveRoom(Board.wrenchLabel, "study");
			Board.wrenchLabel.setBounds(Board.studyx, Board.studyy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to hall")) {
			moveRoom(Board.wrenchLabel, "hall");
			Board.wrenchLabel.setBounds(Board.hallx, Board.hally, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to lounge")) {
			moveRoom(Board.wrenchLabel, "lounge");
			Board.wrenchLabel.setBounds(Board.loungex, Board.loungey, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to dining room")) {
			moveRoom(Board.wrenchLabel, "dining room");
			Board.wrenchLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}
		
		
		else if(textualcommand.textfield.getText().length() > 0) {
			whoseTurn(display, textualcommand);
		}
		
	}
	
	//alternates whose turn it is to make a move
	public void whoseTurn(Display display, TextualCommand textualcommand) {
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
	
	public void moveRoom(JLabel weapon, String room) {
		if (room.equals("kitchen")){
			whichRoom(weapon, Board.kitchenx, Board.kitcheny);
		} 
		else if (room.equals("ball room")){
			whichRoom(weapon, Board.ballroomx, Board.ballroomy);			
		}
		else if (room.equals("conservatory")){
			whichRoom(weapon, Board.conservatoryx, Board.conservatoryy);
		}
		else if (room.equals("billiard room")){
			whichRoom(weapon, Board.billiardroomx, Board.billiardroomy);
		}
		else if (room.equals("library")){
			whichRoom(weapon, Board.libraryx, Board.libraryy);
		}
		else if (room.equals("study")){
			whichRoom(weapon, Board.studyx, Board.studyy);
		}
		else if (room.equals("hall")){
			whichRoom(weapon, Board.hallx, Board.hally);
		}
		else if (room.equals("lounge")){
			whichRoom(weapon, Board.loungex, Board.loungey);
		}
		else if (room.equals("dining room")){
			whichRoom(weapon, Board.diningroomx, Board.diningroomy);
		}
	}
	
	
	public void whichRoom(JLabel weapon, int x, int y) {
		if(Board.candlestickLabel.getX() == x && Board.candlestickLabel.getY() == y) {
			Board.candlestickLabel.setBounds(weapon.getX(), weapon.getY(), Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
		} 
		else if(Board.knifeLabel.getX() == x && Board.knifeLabel.getY() == y) {
			Board.knifeLabel.setBounds(weapon.getX(), weapon.getY(), Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
		} 
		else if(Board.leadpipeLabel.getX() == x && Board.leadpipeLabel.getY() == y) {
			Board.leadpipeLabel.setBounds(weapon.getX(), weapon.getY(), Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
		}
		else if(Board.revolverLabel.getX() == x && Board.revolverLabel.getY() == y) {
			Board.revolverLabel.setBounds(weapon.getX(), weapon.getY(), Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
		}
		else if(Board.ropeLabel.getX() == x && Board.ropeLabel.getY() == y) {
			Board.ropeLabel.setBounds(weapon.getX(), weapon.getY(), Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());				
		}
		else if(Board.wrenchLabel.getX() == x && Board.wrenchLabel.getY() == y) {
			Board.wrenchLabel.setBounds(weapon.getX(), weapon.getY(), Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());	
		}
	}
	
	
	
}