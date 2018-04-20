package bots;

import java.util.ArrayList;
import java.util.Iterator;

import gameengine.*;

public class ClueTangClan implements BotAPI {

	private int strategy=0;
	private int numPlayers;
	private final int numCards = 21;
	private final int numSuspects = 6;
	private final int numWeapons = 6;
	private final int numRooms = 9;
	private ArrayList<ArrayList<ArrayList<Character>>> notes = new ArrayList<ArrayList<ArrayList<Character>>>();
	private String[] playerNames;
	private int notesCounter=1;
	private int previousLogLength=0;
	
	private boolean weKnowTheSuspect=false;
	private boolean weKnowTheWeapon=false;
	private boolean weKnowTheRoom=false;
	private String suspect="";
	private String weapon="";
	private String room="";
	
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
        
        for(int i=0; i<numPlayers; i++) {
        	notes.add(new ArrayList<ArrayList<Character>>());
        }
        for(int i=0; i<numCards; i++) {
        	for(int j=0; j<numPlayers; j++) {
        		notes.get(j).add(new ArrayList<Character>());
        		notes.get(j).get(i).add('0');
        	}
        }
        
        markOffMyCardsOnNotes();
    }

    public String getName() {
        return "ClueTangClan"; // must match the class name
    }

    public String getCommand() {
    	
    	checkGeneralLogAndUpdateNotes();
    	markOffSingleNumsOnNotesForOnePlayer();
    	
    	checkIfWeKnowTheSuspect();
    	checkIfWeKnowTheWeapon();
    	checkIfWeKnowTheRoom();
    	
    	int guessingTime=0;
    	if(weKnowTheSuspect) guessingTime++;
    	if(weKnowTheWeapon) guessingTime++;
    	if(weKnowTheRoom) guessingTime++;
    	
    	if(guessingTime>=2) {
    		//root one to basement
    	}
    	
    	
        return "done";
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
    
    public void notifyResponse(Log response) {
    	Iterator<String> iterator = response.iterator();
		ArrayList<String> messages = new ArrayList<String>();
		int logLength = 0;
		while(iterator.hasNext()) {
			logLength++;
			String temp = response.next();
			messages.add(temp);
			System.out.println(temp);
		}

		System.out.println(logLength);
		String[] logQuestion = messages.get(logLength-2).toString().split(" ");
		String[] logAnswer = messages.get(logLength-1).toString().split(" ");
		int logQuestionLength = logQuestion.length;
		int logAnswerLength = logAnswer.length;

		String queriedPlayerName="";
		int queriedPlayerNum;
		
		String suspectName="";
		String weaponName="";
		String roomName="";
		int suspectNum;
		int weaponNum;
		int roomNum;
		
		String cardName="";
		int cardNum=0;

		boolean hasCard=false;
		
		//get card name name and num if there is one
		//also get queried player name and num
		if(logAnswer[logAnswerLength-2].equalsIgnoreCase("card:")) {
			cardNum = logLength-1;
			cardName = removeFullStop(logAnswer[cardNum]);
			queriedPlayerNum = logAnswerLength-5;
		}
		else {
			queriedPlayerNum = logAnswerLength-6;
		}
		queriedPlayerName = logAnswer[queriedPlayerNum];
		
		//get room name and num
		if(logQuestion[logQuestionLength-2].equalsIgnoreCase("Dining")) {
			roomNum = logQuestionLength-2;
			roomName = "Dining Room";
		}
		else if(logQuestion[logQuestionLength-2].equalsIgnoreCase("Billiard")) {
			roomNum = logQuestionLength-2;
			roomName = "Billiard Room";
		}
		else {
			roomNum = logQuestionLength-1;
			roomName = removeFullStop(logQuestion[roomNum]);
		}
		
		//get weapon name and num
		if(logQuestion[roomNum-4].equalsIgnoreCase("Lead")) {
			weaponNum = roomNum-4;
			weaponName = "Lead Pipe";
		}
		else {
			weaponNum = roomNum-3;
			weaponName = logQuestion[weaponNum];
		}
		
		//get suspect name and num
		suspectNum = weaponNum-3;
		suspectName = logQuestion[suspectNum];
		
		if(cardName!="") {
			hasCard=true;
		}

		if(hasCard) {
			markOffCardOnNotesForAllPlayers(getCardNum(cardName), queriedPlayerName);
		}
		else {
			markOffCardOnNotesForOnePlayer(getCardNum(suspectName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(weaponName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(roomName), queriedPlayerName);
		}
    }

    
    
    
    
    
    
    

    
    //our functions
    
    
    
    
    //notes functions 
   
	 public void checkGeneralLogAndUpdateNotes() {

		Iterator<String> iterator = log.iterator();
		ArrayList<String> messages = new ArrayList<String>();
		int logLength = 0;
		while(iterator.hasNext()) {
			logLength++;
			String temp = log.next();
			messages.add(temp);
			System.out.println(temp);
		}
		int currentLogLength = logLength;
		
		while(logLength>2) {
			System.out.println(logLength);
			String[] logQuestion = messages.get(logLength-2).toString().split(" ");
			String[] logAnswer = messages.get(logLength-1).toString().split(" ");
			int logQuestionLength = logQuestion.length;
			int logAnswerLength = logAnswer.length;
			System.out.println(logQuestionLength);
			System.out.println(logAnswerLength);

			String queriedPlayerName="";
			int queriedPlayerNum;
			
			String suspectName="";
			String weaponName="";
			String roomName="";
			int suspectNum;
			int weaponNum;
			int roomNum;

			boolean hasCard=false;
			
			//get queried player name and num
			if(logAnswer[logAnswerLength-1].equalsIgnoreCase("cards.")) {
				queriedPlayerNum = logAnswerLength-6;
			}
			else {
				queriedPlayerNum = logAnswerLength-4;
			}
			queriedPlayerName = logAnswer[queriedPlayerNum];
			
			//get room name and num
			if(logQuestion[logQuestionLength-2].equalsIgnoreCase("Dining")) {
				roomNum = logQuestionLength-2;
				roomName = "Dining Room";
			}
			else if(logQuestion[logQuestionLength-2].equalsIgnoreCase("Billiard")) {
				roomNum = logQuestionLength-2;
				roomName = "Billiard Room";
			}
			else {
				roomNum = logQuestionLength-1;
				roomName = removeFullStop(logQuestion[roomNum]);
			}
			
			//get weapon name and num
			if(logQuestion[roomNum-4].equalsIgnoreCase("Lead")) {
				weaponNum = roomNum-4;
				weaponName = "Lead Pipe";
			}
			else {
				weaponNum = roomNum-3;
				weaponName = logQuestion[weaponNum];
			}
			
			//get suspect name and num
			suspectNum = weaponNum-3;
			suspectName = logQuestion[suspectNum];
			
			if(logAnswer[queriedPlayerNum+1].equalsIgnoreCase("showed")) {
				hasCard=true;
			}

			if(hasCard) {
				markOffPotentialCardsOnNotesForOnePlayer(getCardNum(suspectName), getCardNum(weaponName), getCardNum(roomName), queriedPlayerName);
			}
			else {
				markOffCardOnNotesForOnePlayer(getCardNum(suspectName), queriedPlayerName);
				markOffCardOnNotesForOnePlayer(getCardNum(weaponName), queriedPlayerName);
				markOffCardOnNotesForOnePlayer(getCardNum(roomName), queriedPlayerName);
			}
			logLength-=2;
			if(logLength == previousLogLength) {
				break;
			}
			
		}
		previousLogLength = currentLogLength;
		
		
    }

    
    public void markOffMyCardsOnNotes(){
    	if(player.hasCard("Green")) markOffCardOnNotesForAllPlayers(0, getName());
    	else markOffCardOnNotesForOnePlayer(0, getName());
    	
        if(player.hasCard("Mustard")) markOffCardOnNotesForAllPlayers(1, getName());
    	else markOffCardOnNotesForOnePlayer(1, getName());
    	
        if(player.hasCard("Peacock")) markOffCardOnNotesForAllPlayers(2, getName());
    	else markOffCardOnNotesForOnePlayer(2, getName());
    	
        if(player.hasCard("Plum")) markOffCardOnNotesForAllPlayers(3, getName());
    	else markOffCardOnNotesForOnePlayer(3, getName());
    	
        if(player.hasCard("Scarlett")) markOffCardOnNotesForAllPlayers(4, getName());
    	else markOffCardOnNotesForOnePlayer(4, getName());
    	
        if(player.hasCard("White")) markOffCardOnNotesForAllPlayers(5, getName());
    	else markOffCardOnNotesForOnePlayer(5, getName());
    	

        if(player.hasCard("Candlestick")) markOffCardOnNotesForAllPlayers(6, getName());
    	else markOffCardOnNotesForOnePlayer(6, getName());
    	
        if(player.hasCard("Dagger")) markOffCardOnNotesForAllPlayers(7, getName());
    	else markOffCardOnNotesForOnePlayer(7, getName());
    	
        if(player.hasCard("Leadpipe")) markOffCardOnNotesForAllPlayers(8, getName());
    	else markOffCardOnNotesForOnePlayer(8, getName());
    	
        if(player.hasCard("Pistol")) markOffCardOnNotesForAllPlayers(9, getName());
    	else markOffCardOnNotesForOnePlayer(9, getName());
    	
        if(player.hasCard("Rope")) markOffCardOnNotesForAllPlayers(10, getName());
    	else markOffCardOnNotesForOnePlayer(10, getName());
    	
        if(player.hasCard("Wrench")) markOffCardOnNotesForAllPlayers(11, getName());
    	else markOffCardOnNotesForOnePlayer(11, getName());
    	

        if(player.hasCard("Ballroom")) markOffCardOnNotesForAllPlayers(12, getName());
    	else markOffCardOnNotesForOnePlayer(12, getName());
    	
        if(player.hasCard("Billiard Room")) markOffCardOnNotesForAllPlayers(13, getName());
    	else markOffCardOnNotesForOnePlayer(13, getName());
    	
        if(player.hasCard("Conservatory")) markOffCardOnNotesForAllPlayers(14, getName());
    	else markOffCardOnNotesForOnePlayer(14, getName());
    	
        if(player.hasCard("Dining Room")) markOffCardOnNotesForAllPlayers(15, getName());
    	else markOffCardOnNotesForOnePlayer(15, getName());
    	
        if(player.hasCard("Hall")) markOffCardOnNotesForAllPlayers(16, getName());
    	else markOffCardOnNotesForOnePlayer(16, getName());
    	
        if(player.hasCard("Kitchen")) markOffCardOnNotesForAllPlayers(17, getName());
    	else markOffCardOnNotesForOnePlayer(17, getName());
    	
        if(player.hasCard("Library")) markOffCardOnNotesForAllPlayers(18, getName());
    	else markOffCardOnNotesForOnePlayer(18, getName());
    	
        if(player.hasCard("Lounge")) markOffCardOnNotesForAllPlayers(19, getName());
    	else markOffCardOnNotesForOnePlayer(19, getName());
    	
        if(player.hasCard("Study")) markOffCardOnNotesForAllPlayers(20, getName());
    	else markOffCardOnNotesForOnePlayer(20, getName());
    	
    }
    public void markOffCardOnNotesForAllPlayers(int cardNum, String playerName) {
    	for(int j=0; j<numPlayers; j++) {
			for(int k=0; k<notes.get(j).get(cardNum).size(); k++) {
				notes.get(j).get(cardNum).remove(k);
			}
    		if(playerNames[j].equals(playerName)) {
    			notes.get(j).get(cardNum).add('y');
    		}
    		else {
    			notes.get(j).get(cardNum).add('x');
    		}
    	}
    }
    public void markOffCardOnNotesForOnePlayer(int cardNum, String playerName) {
    	for(int j=0; j<numPlayers; j++) {
    		if(playerNames[j].equals(playerName)) {
        		for(int k=0; k<notes.get(j).get(cardNum).size(); k++) {
    				notes.get(j).get(cardNum).remove(k);
    			}
    			notes.get(j).get(cardNum).add('x');
    		}
    	}
    }
    public void markOffPotentialCardsOnNotesForOnePlayer(int suspectNum, int weaponNum, int roomNum, String playerName) {
    	for(int j=0; j<numPlayers; j++) {
    		if(playerNames[j].equals(playerName)) {
    			if(notes.get(j).get(suspectNum).get(0) == '0') {
    				notes.get(j).get(suspectNum).remove(0);
        			notes.get(j).get(suspectNum).add((char) (notesCounter+'0'));
    			}
    			else if(notes.get(j).get(suspectNum).get(0) == 'x' || notes.get(j).get(suspectNum).get(0) == 'y') {
    				//-----------add in propability code--------------
    			}
    			else {
        			notes.get(j).get(suspectNum).add((char) (notesCounter+'0'));
    			}
    			if(notes.get(j).get(weaponNum).get(0) == '0') {
    				notes.get(j).get(weaponNum).remove(0);
        			notes.get(j).get(weaponNum).add((char) (notesCounter+'0'));
    			}
    			else if(notes.get(j).get(weaponNum).get(0) == 'x' || notes.get(j).get(weaponNum).get(0) == 'y') {
    				//-----------add in propability code--------------
    			}
    			else {
        			notes.get(j).get(weaponNum).add((char) (notesCounter+'0'));
    			}
    			if(notes.get(j).get(roomNum).get(0) == '0') {
    				notes.get(j).get(roomNum).remove(0);
        			notes.get(j).get(roomNum).add((char) (notesCounter+'0'));
    			}
    			else if(notes.get(j).get(roomNum).get(0) == 'x' || notes.get(j).get(roomNum).get(0) == 'y') {
    				//-----------add in propability code--------------
    			}
    			else {
        			notes.get(j).get(roomNum).add((char) (notesCounter+'0'));
    			}
    		}
    	}
    	notesCounter++;
    }
    public void markOffSingleNumsOnNotesForOnePlayer() {
    	for(int j=0; j<numPlayers; j++) {
    		for(int k=1; k==notesCounter; k++) {
    			int counter=0;
    			int cardNum=0;
    			for(int i=0; i<numCards; i++) {
    				for(int a=0; a<notes.get(j).get(i).size(); a++) {
        	    		if(notes.get(j).get(i).get(a) == (char) k) {
        	    			counter++;
        	    			cardNum=i;
        	    		}
    				}
    	    	}
    			if(counter==1) {
    				markOffCardOnNotesForAllPlayers(cardNum, playerNames[j]);
    			}
    		}
		}
    }
     
    public void checkIfWeKnowTheSuspect() {
    	if(!weKnowTheSuspect) {
    		for(int i=0; i<numSuspects; i++) {
    			int counter=0;
    			for(int j=0; j<numPlayers; j++) {
    				if(notes.get(j).get(i).get(0) == 'x') counter++;
    			}
        		if(counter==numPlayers) {
        			suspect = getCardName(i);
        			weKnowTheSuspect=true;
        		}
    		}
    	}
    }
    public void checkIfWeKnowTheWeapon() {
    	if(!weKnowTheWeapon) {
    		for(int i=numSuspects; i<numSuspects+numWeapons; i++) {
    			int counter=0;
    			for(int j=0; j<numPlayers; j++) {
    				if(notes.get(j).get(i).get(0) == 'x') counter++;
    			}
        		if(counter==numPlayers) {
        			weapon = getCardName(i);
        			weKnowTheWeapon=true;
        		}
    		}
    	}
    }
    public void checkIfWeKnowTheRoom() {
    	if(!weKnowTheRoom) {
    		for(int i=0; i<numSuspects+numWeapons+numRooms; i++) {
    			int counter=0;
    			for(int j=0; j<numPlayers; j++) {
    				if(notes.get(j).get(i).get(0) == 'x') counter++;
    			}
        		if(counter==numPlayers) {
        			room = getCardName(i);
        			weKnowTheRoom=true;
        		}
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
