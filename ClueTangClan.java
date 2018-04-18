package bots;

import java.util.ArrayList;
import java.util.Iterator;

import gameengine.*;

public class ClueTangClan implements BotAPI {

	private int strategy=0;
	private int numPlayers;
	private final int numCards = 21;
	private char[][] notes;
	private String[] playerNames;
	
    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the board or the player objects
    // It may only inspect the state of the board and the player objects

    private Player player;
    private PlayersInfo playersInfo;
    private Map map;
    private Dice dice;
    private Log log;
    private Deck deck;

    public ClueTangClan (Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
        this.player = player;
        this.playersInfo = playersInfo;
        this.map = map;
        this.dice = dice;
        this.log = log;
        this.deck = deck;
        
        numPlayers = playersInfo.numPlayers();
        playerNames = playersInfo.getPlayersNames();
        
        notes = new char[21][numPlayers];
        for(int i=0; i<numCards; i++) {
        	for(int j=0; j<numPlayers; j++) {
        		notes[i][j] = '0';
        	}
        }
        
        markOffMyCardsOnNotes();
        
    	if(player.getToken().getName().equalsIgnoreCase("green")) {
    		strategy=1;
    	}
    	else if(player.getToken().getName().equalsIgnoreCase("mustard")) {
    		strategy=2;
    	}
    	else if(player.getToken().getName().equalsIgnoreCase("peacock")) {
    		strategy=3;
    	}
    	else if(player.getToken().getName().equalsIgnoreCase("plum")) {
    		strategy=4;
    	}
    	else if(player.getToken().getName().equalsIgnoreCase("scarlett")) {
    		strategy=5;
    	}
    	else if(player.getToken().getName().equalsIgnoreCase("white")) {
    		strategy=6;
    	}
    }

    public String getName() {
        return "ClueTangClan"; // must match the class name
    }

    public String getCommand() {
        // Add your code here
    	String move = "";
    	if(move == "roll") {
    		return "roll";
    	}
    	else if(move == "question") {
    		return "question";
    	}
    	else if(move == "passage") {
    		return "passage";
    	}
    	else if(move == "accuse") {
    		return "accuse";
    	}
    	else {
            return "done";
    	}
    }

    public String getMove() {
        // Add your code here
        return "r";
    }

    public String getSuspect() {
        // Add your code here
        return Names.SUSPECT_NAMES[0];
    }

    public String getWeapon() {
        // Add your code here
        return Names.WEAPON_NAMES[0];
    }

    public String getRoom() {
        // Add your code here
        return Names.ROOM_NAMES[0];
    }

    public String getDoor() {
        // Add your code here
        return "1";
    }

    public String getCard(Cards matchingCards) {
        // Add your code here
        return matchingCards.get().toString();
    }

    
    
    
    
    
    
    

    
    //our functions
    
    public void notifyResponse(Log response) {
		String queriedPlayerName="";
		String shownCardName="";
		int queriedPlayerNum=11;
		int shownCardNum=15;
		
		String suspectName="";
		String weaponName="";
		String roomName="";
		int suspectNum=4;
		int weaponNum=7;
		int roomNum=10;

		boolean hasCard=false;

		ArrayList<String> messages = new ArrayList<String>();
		int length=0;
		while(response.hasNext()) {
			messages.add(response.next());
			length++;
		}
		
		suspectName = messages.get(suspectNum).toString();
		
		if(messages.get(weaponNum).toString().equalsIgnoreCase("Lead")) {
			queriedPlayerNum++;
			shownCardNum++;
			roomNum++;
			weaponName = "Lead Pipe";
		}
		else {
			weaponName = messages.get(weaponNum).toString();
		}
		
		if(messages.get(roomNum).toString().equalsIgnoreCase("Dining")) {
			queriedPlayerNum++;
			shownCardNum++;
			roomName = "Dining Room";
		}
		else if(messages.get(roomNum).toString().equalsIgnoreCase("Billiard")) {
			queriedPlayerNum++;
			shownCardNum++;
			roomName = "Billiard Room";
		}
		else {
			roomName = removeFullStop(messages.get(roomNum).toString());
		}
		
		queriedPlayerName = messages.get(queriedPlayerNum).toString();
		
		if(messages.get(queriedPlayerNum+1).toString().equalsIgnoreCase("showed")) {
			hasCard=true;
			shownCardName = messages.get(queriedPlayerNum+4).toString();
		}

		if(hasCard) {
			markOffCardOnNotesForAllPlayers(getCardNum(shownCardName), queriedPlayerName);
		}
		else {
			markOffCardOnNotesForOnePlayer(getCardNum(suspectName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(weaponName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(roomName), queriedPlayerName);
		}
    }
    
    
    
    
    public void markOffMyCardsOnNotes(){
        if(player.hasCard("Green")) {
        	markOffCardOnNotesForAllPlayers(0, "ClueTangClan");
        }
        else if(player.hasCard("Mustard")) {
        	markOffCardOnNotesForAllPlayers(1, "ClueTangClan");
        }
        else if(player.hasCard("Peacock")) {
        	markOffCardOnNotesForAllPlayers(2, "ClueTangClan");
        }
        else if(player.hasCard("Plum")) {
        	markOffCardOnNotesForAllPlayers(3, "ClueTangClan");
        }
        else if(player.hasCard("Scarlett")) {
        	markOffCardOnNotesForAllPlayers(4, "ClueTangClan");
        }
        else if(player.hasCard("White")) {
        	markOffCardOnNotesForAllPlayers(5, "ClueTangClan");
        }

        else if(player.hasCard("Candlestick")) {
        	markOffCardOnNotesForAllPlayers(6, "ClueTangClan");
        }
        else if(player.hasCard("Dagger")) {
        	markOffCardOnNotesForAllPlayers(7, "ClueTangClan");
        }
        else if(player.hasCard("Leadpipe")) {
        	markOffCardOnNotesForAllPlayers(8, "ClueTangClan");
        }
        else if(player.hasCard("Pistol")) {
        	markOffCardOnNotesForAllPlayers(9, "ClueTangClan");
        }
        else if(player.hasCard("Rope")) {
        	markOffCardOnNotesForAllPlayers(10, "ClueTangClan");
        }
        else if(player.hasCard("Wrench")) {
        	markOffCardOnNotesForAllPlayers(11, "ClueTangClan");
        }

        else if(player.hasCard("Ballroom")) {
        	markOffCardOnNotesForAllPlayers(12, "ClueTangClan");
        }
        else if(player.hasCard("Billiard Room")) {
        	markOffCardOnNotesForAllPlayers(13, "ClueTangClan");
        }
        else if(player.hasCard("Conservatory")) {
        	markOffCardOnNotesForAllPlayers(14, "ClueTangClan");
        }
        else if(player.hasCard("Dining Room")) {
        	markOffCardOnNotesForAllPlayers(15, "ClueTangClan");
        }
        else if(player.hasCard("Hall")) {
        	markOffCardOnNotesForAllPlayers(16, "ClueTangClan");
        }
        else if(player.hasCard("Kitchen")) {
        	markOffCardOnNotesForAllPlayers(17, "ClueTangClan");
        }
        else if(player.hasCard("Library")) {
        	markOffCardOnNotesForAllPlayers(18, "ClueTangClan");
        }
        else if(player.hasCard("Lounge")) {
        	markOffCardOnNotesForAllPlayers(19, "ClueTangClan");
        }
        else if(player.hasCard("Study")) {
        	markOffCardOnNotesForAllPlayers(20, "ClueTangClan");
        }
    }
    public void markOffCardOnNotesForAllPlayers(int cardNum, String playerName) {
    	for(int j=0; j<numPlayers; j++) {
    		if(playerNames[j].equals(playerName)) {
    			notes[cardNum][j] = 'y';
    		}
    		else {
    			notes[cardNum][j] = 'x';
    		}
    	}
    }
    public void markOffCardOnNotesForOnePlayer(int cardNum, String playerName) {
    	for(int j=0; j<numPlayers; j++) {
    		if(playerNames[j].equals(playerName)) {
    			notes[cardNum][j] = 'x';
    		}
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getCardNum(String cardName) {
    	int num=0;
    	if(cardName.equalsIgnoreCase("green")) num=0;
    	else if(cardName.equalsIgnoreCase("mustard")) num=1;
    	else if(cardName.equalsIgnoreCase("peacock")) num=2;
    	else if(cardName.equalsIgnoreCase("plum")) num=3;
    	else if(cardName.equalsIgnoreCase("scarlett")) num=4;
    	else if(cardName.equalsIgnoreCase("white")) num=5;

    	else if(cardName.equalsIgnoreCase("candlestick")) num=6;
    	else if(cardName.equalsIgnoreCase("dagger")) num=7;
    	else if(cardName.equalsIgnoreCase("lead pipe")) num=8;
    	else if(cardName.equalsIgnoreCase("pistol")) num=9;
    	else if(cardName.equalsIgnoreCase("rope")) num=10;
    	else if(cardName.equalsIgnoreCase("wrench")) num=11;

    	else if(cardName.equalsIgnoreCase("ballroom")) num=12;
    	else if(cardName.equalsIgnoreCase("billiard room")) num=13;
    	else if(cardName.equalsIgnoreCase("conservatory")) num=14;
    	else if(cardName.equalsIgnoreCase("dining room")) num=15;
    	else if(cardName.equalsIgnoreCase("hall")) num=16;
    	else if(cardName.equalsIgnoreCase("kitchen")) num=17;
    	else if(cardName.equalsIgnoreCase("library")) num=18;
    	else if(cardName.equalsIgnoreCase("lounge")) num=19;
    	else if(cardName.equalsIgnoreCase("study")) num=20;
    	
    	return num;
    }
    public String getCardName(int cardNum) {
    	String name="";
    	if(cardNum == 0) name="green";
    	else if(cardNum == 1) name="mustard";
    	else if(cardNum == 2) name="peacock";
    	else if(cardNum == 3) name="plum";
    	else if(cardNum == 4) name="scarlett";
    	else if(cardNum == 5) name="white";

    	else if(cardNum == 6) name="candlestick";
    	else if(cardNum == 7) name="dagger";
    	else if(cardNum == 8) name="lead pipe";
    	else if(cardNum == 9) name="pistol";
    	else if(cardNum == 10) name="rope";
    	else if(cardNum == 11) name="wrench";

    	else if(cardNum == 12) name="ballroom";
    	else if(cardNum == 13) name="billiard room";
    	else if(cardNum == 14) name="conservatory";
    	else if(cardNum == 15) name="dining room";
    	else if(cardNum == 16) name="hall";
    	else if(cardNum == 17) name="kitchen";
    	else if(cardNum == 18) name="library";
    	else if(cardNum == 19) name="lounge";
    	else if(cardNum == 20) name="study";
    	
    	return name;
    }
    
    public String removeFullStop(String room) {
    	char[] roomArr = room.toCharArray();
    	char[] subArr = new char[roomArr.length-1];
    	
    	for(int i=0; i<subArr.length; i++) {
    		subArr[i] = roomArr[i];
    	}
    	String returnString = subArr.toString();
    	
    	
    	
    	return returnString;
    }
    
    

}
