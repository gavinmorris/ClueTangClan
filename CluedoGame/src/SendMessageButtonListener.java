import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SendMessageButtonListener implements ActionListener {
		
	static String  username1 = "jem";
	static String  username2 = "gav";
	static String  username3 = "euan";
	int i=0;
	
	public void actionPerformed(ActionEvent event) {
		buttonfunction(Main.display, Main.textualcommand);
	}
	
	public void buttonfunction(Display display, TextualCommand textualcommand) {

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
		
		
		else if(textualcommand.textfield.getText().equals("move candlestick to kitchen")) {
			Board.candlestickLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to ball room")) {
			Board.candlestickLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move candlestick to conservatory")) {
			Board.candlestickLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move candlestick to billiard room")) {
			Board.candlestickLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to library")) {
			Board.candlestickLabel.setBounds(Board.libraryx, Board.libraryy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to study")) {
			Board.candlestickLabel.setBounds(Board.studyx, Board.studyy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to hall")) {
			Board.candlestickLabel.setBounds(Board.hallx, Board.hally, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to lounge")) {
			Board.candlestickLabel.setBounds(Board.loungex, Board.loungey, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move candlestick to dining room")) {
			Board.candlestickLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.candlestickImage.getIconWidth(), Board.candlestickImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move knife to kitchen")) {
			Board.knifeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to ball room")) {
			Board.knifeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move knife to conservatory")) {
			Board.knifeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move knife to billiard room")) {
			Board.knifeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to library")) {
			Board.knifeLabel.setBounds(Board.libraryx, Board.libraryy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to study")) {
			Board.knifeLabel.setBounds(Board.studyx, Board.studyy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to hall")) {
			Board.knifeLabel.setBounds(Board.hallx, Board.hally, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to lounge")) {
			Board.knifeLabel.setBounds(Board.loungex, Board.loungey, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move knife to dining room")) {
			Board.knifeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.knifeImage.getIconWidth(), Board.knifeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move lead pipe to kitchen")) {
			Board.leadpipeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to ball room")) {
			Board.leadpipeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move lead pipe to conservatory")) {
			Board.leadpipeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move lead pipe to billiard room")) {
			Board.leadpipeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to library")) {
			Board.leadpipeLabel.setBounds(Board.libraryx, Board.libraryy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to study")) {
			Board.leadpipeLabel.setBounds(Board.studyx, Board.studyy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to hall")) {
			Board.leadpipeLabel.setBounds(Board.hallx, Board.hally, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to lounge")) {
			Board.leadpipeLabel.setBounds(Board.loungex, Board.loungey, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move lead pipe to dining room")) {
			Board.leadpipeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.leadpipeImage.getIconWidth(), Board.leadpipeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move revolver to kitchen")) {
			Board.revolverLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to ball room")) {
			Board.revolverLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move revolver to conservatory")) {
			Board.revolverLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move revolver to billiard room")) {
			Board.revolverLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to library")) {
			Board.revolverLabel.setBounds(Board.libraryx, Board.libraryy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to study")) {
			Board.revolverLabel.setBounds(Board.studyx, Board.studyy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to hall")) {
			Board.revolverLabel.setBounds(Board.hallx, Board.hally, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to lounge")) {
			Board.revolverLabel.setBounds(Board.loungex, Board.loungey, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move revolver to dining room")) {
			Board.revolverLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.revolverImage.getIconWidth(), Board.revolverImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} 

		else if(textualcommand.textfield.getText().equals("move rope to kitchen")) {
			Board.ropeLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to ball room")) {
			Board.ropeLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move rope to conservatory")) {
			Board.ropeLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move rope to billiard room")) {
			Board.ropeLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to library")) {
			Board.ropeLabel.setBounds(Board.libraryx, Board.libraryy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to study")) {
			Board.ropeLabel.setBounds(Board.studyx, Board.studyy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to hall")) {
			Board.ropeLabel.setBounds(Board.hallx, Board.hally, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to lounge")) {
			Board.ropeLabel.setBounds(Board.loungex, Board.loungey, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move rope to dining room")) {
			Board.ropeLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.ropeImage.getIconWidth(), Board.ropeImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}

		else if(textualcommand.textfield.getText().equals("move wrench to kitchen")) {
			Board.wrenchLabel.setBounds(Board.kitchenx, Board.kitcheny, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to ball room")) {
			Board.wrenchLabel.setBounds(Board.ballroomx, Board.ballroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());	
			whoseTurn(display, textualcommand);		
		} else if(textualcommand.textfield.getText().equals("move wrench to conservatory")) {
			Board.wrenchLabel.setBounds(Board.conservatoryx, Board.conservatoryy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());	
			whoseTurn(display, textualcommand);	
		} else if(textualcommand.textfield.getText().equals("move wrench to billiard room")) {
			Board.wrenchLabel.setBounds(Board.billiardroomx, Board.billiardroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to library")) {
			Board.wrenchLabel.setBounds(Board.libraryx, Board.libraryy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to study")) {
			Board.wrenchLabel.setBounds(Board.studyx, Board.studyy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to hall")) {
			Board.wrenchLabel.setBounds(Board.hallx, Board.hally, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to lounge")) {
			Board.wrenchLabel.setBounds(Board.loungex, Board.loungey, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		} else if(textualcommand.textfield.getText().equals("move wrench to dining room")) {
			Board.wrenchLabel.setBounds(Board.diningroomx, Board.diningroomy, Board.wrenchImage.getIconWidth(), Board.wrenchImage.getIconHeight());
			whoseTurn(display, textualcommand);
		}
		
		
		else if(textualcommand.textfield.getText().length() > 0) {
			whoseTurn(display, textualcommand);
		}
		
	}
	
	public void whoseTurn(Display display, TextualCommand textualcommand) {
		if(i%3 == 0) {
			display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username1 +  ">");				
		} else if(i%3 == 1) {
			display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username2 +  ">");				
		} else {
			display.textarea.append(textualcommand.textfield.getText() + "\n" + "<"  + username3 +  ">");				
		}
		
		textualcommand.textfield.setText("");
		i++;
	}
	
	
}