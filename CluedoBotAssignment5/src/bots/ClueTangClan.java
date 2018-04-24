package bots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import gameengine.*;

public class ClueTangClan implements BotAPI {
	private int strategy = 0;
	private int numPlayers;
	private final int numCards = 21;
	private final int numSuspects = 6;
	private final int numWeapons = 6;
	private final int numRooms = 9;
	private ArrayList<ArrayList<ArrayList<Character>>> notes = new ArrayList<ArrayList<ArrayList<Character>>>();
	private String[] playerNames;
	private int notesCounter = 1;
	private int previousLogLength = 0;
	public int[] shownCards = new int[numCards];
	public boolean gameStart = true;

	public String[] characterNames = { "Green", "Mustard", "Peacock", "Plum", "Scarlett", "White" };
	public String[] weaponNames = { "Candlestick", "Dagger", "Leadpipe", "Pistol", "Rope", "Wrench" };
	public String[] roomNames = { "Ballroom", "Billiard Room", "Conservatory", "Dining Room", "Hall", "Kitchen",
			"Library", "Lounge", "Study" };

	public double[][] characterMatrix = new double[4][6];
	public double[][] weaponMatrix = new double[4][6];
	public double[][] roomMatrix = new double[4][9];

	int knownCharactersCount = 0;
	int knownWeaponsCount = 0;
	int knownRoomsCount = 0;

	private boolean weKnowTheSuspect = false;
	private boolean weKnowTheWeapon = false;
	private boolean weKnowTheRoom = false;
	private String suspect = "";
	private String weapon = "";
	private String room = "";
	private int weNeedThis = 0;
	private int movesTaken = 0;
	private int size = 0;
	private boolean roll = true;
	private int exitNumIterator = 0;
	private boolean inRoom = false;

	// hardcoded for test it should be set to pickARoute
	private String route = "loungeDiningBallBillLib";
	private String exitNum[] = null;

	private String pickARoute() {
		return "";
	}
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

	public ClueTangClan(Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
		this.player = player;
		this.playersInfo = playersInfo;
		this.map = map;
		this.dice = dice;
		this.log = log;
		this.deck = deck;

		if (player.getToken().getName() == "Green") {
			size = twoToBallRoom.length;
		} else if (player.getToken().getName() == "Peacock") {
			size = threeToConvservatory.length;
		} else if (player.getToken().getName() == "Plum") {
			size = fourToStudy.length;
		} else if (player.getToken().getName() == "Scarlett") {
			size = fiveToLounge.length;
		} else if (player.getToken().getName() == "Mustard") {
			size = sixToDiningRoom.length;
		} else if (player.getToken().getName() == "White") {
			size = oneToBallRoom.length;
		}
	}

	public String getName() {
		return "ClueTangClan"; // must match the class name
	}

	public String getCommand() {
		if (gameStart) {
			// Calling setup functions since numPlayers wont be initialised properly since
			numPlayers = playersInfo.numPlayers();

			playerNames = playersInfo.getPlayersNames();

			for (int i = 0; i < numPlayers; i++) {
				notes.add(new ArrayList<ArrayList<Character>>());
			}

			for (int i = 0; i < numCards; i++) {
				for (int j = 0; j < numPlayers; j++) {
					notes.get(j).add(new ArrayList<Character>());
					notes.get(j).get(i).add('0');
				}
			}

			markOffMyCardsOnNotes();

			for (int i = 0; i < numCards; i++) {
				shownCards[i] = 0;
			}

			EnvelopeCalc();
			setMatrix();
			PrintMatrix();
			gameStart = false;
		}

		checkGeneralLogAndUpdateNotes();

		checkIfWeKnowTheSuspect();
		checkIfWeKnowTheWeapon();
		checkIfWeKnowTheRoom();

		int guessingTime = 0;
		if (weKnowTheSuspect)
			guessingTime++;
		if (weKnowTheWeapon)
			guessingTime++;
		if (weKnowTheRoom)
			guessingTime++;

		if (guessingTime >= 2) {
			// root one to basement
		}

		setSize();

		if (roll) {
			roll = false;

			boolean loungecons = route.toLowerCase().indexOf("loungecons") != -1 ? true : false;
			boolean conslounge = route.toLowerCase().indexOf("conslounge") != -1 ? true : false;
			boolean kitstudy = route.toLowerCase().indexOf("kitstudy") != -1 ? true : false;
			boolean studykit = route.toLowerCase().indexOf("studykit") != -1 ? true : false;

			if (player.getToken().isInRoom()) {
				if (loungecons && player.getToken().getRoom().toString().equalsIgnoreCase("lounge")) {
					return "passage";
				}

				else if (conslounge && player.getToken().getRoom().toString().equalsIgnoreCase("conservatory")) {
					return "passage";
				}

				else if (kitstudy && player.getToken().getRoom().toString().equalsIgnoreCase("kitchen")) {
					return "passage";
				}

				else if (studykit && player.getToken().getRoom().toString().equalsIgnoreCase("study")) {
					return "passage";
				} else {

					if (player.getToken().isInRoom()) {
						movesTaken = 0;
					}

					return "roll";
				}
			} else

			if (player.getToken().isInRoom()) {
				movesTaken = 0;
			}

			return "roll";
		} 
		else if(player.getToken().isInRoom()) {
			return "question";
		}
		else {
			roll = true;

			return "done";
		}
	}

	public String getMove() {
		String move = null;

		if (inRoom) {
			if (player.getToken().getRoom().toString().equalsIgnoreCase("Lounge")) {
				if (route.toLowerCase().endsWith("lounge")) {
					move = loungeToHall[movesTaken];
					movesTaken++;
				} else {
					move = loungeToDiningRoom[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Dining Room")) {
				if (exitNum[exitNumIterator].equals("1")) {
					if (route.endsWith("Dining")) {
						move = diningRoomToHall[movesTaken];
						movesTaken++;
					} else {
						move = diningRoomToLounge[movesTaken];
						movesTaken++;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					boolean diningKitchen = route.toLowerCase().indexOf("diningkit") != -1 ? true : false;
					if (diningKitchen) {
						move = diningRoomToKitchen[movesTaken];
						movesTaken++;
					} else {
						move = diningRoomToBallRoom[movesTaken];
						movesTaken++;
					}
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Kitchen")) {

				boolean kitchenDining = route.toLowerCase().indexOf("kitdining") != -1 ? true : false;

				if (kitchenDining) {
					move = kitchenToDiningRoom[movesTaken];
					movesTaken++;
				} else {
					move = kitchenToBallRoom[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Ballroom")) {

				if (exitNum[exitNumIterator].equals("1")) {
					move = ballRoomToKitchen[movesTaken];
					movesTaken++;
				} else if (exitNum[exitNumIterator].equals("2")) {
					move = ballRoomToDiningRoom[movesTaken];
					movesTaken++;
				}

				else if (exitNum[exitNumIterator].equals("3")) {
					move = ballRoomToBilliardRoom[movesTaken];
					movesTaken++;
				} else if (exitNum[exitNumIterator].equals("4")) {
					move = ballRoomToConservatory[movesTaken];
					movesTaken++;
				}

			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Conservatory")) {

				boolean consBill = route.toLowerCase().indexOf("consbill") != -1 ? true : false;

				if (consBill) {
					move = conservatoryToBilliardRoom[movesTaken];
					movesTaken++;
				} else {
					move = conservatoryToBallRoom[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Billiard Room")) {
				if (exitNum[exitNumIterator].equals("1")) {
					boolean billBall = route.toLowerCase().indexOf("billBall") != -1 ? true : false;
					if (billBall) {
						move = billiardRoomToBallRoom[movesTaken];
						movesTaken++;
					} else {
						move = billiardRoomToConservatory[movesTaken];
						movesTaken++;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					move = billiardRoomToLibrary[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Library")) {
				if (exitNum[exitNumIterator].equals("1")) {
					boolean libStudy = route.toLowerCase().indexOf("libstudy") != -1 ? true : false;
					if (libStudy) {
						move = libraryToStudy[movesTaken];
						movesTaken++;
					} else {
						move = libraryToHall[movesTaken];
						movesTaken++;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					move = libraryToBilliardRoom[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Study")) {
				boolean studylib = route.toLowerCase().indexOf("studylib") != -1 ? true : false;

				if (studylib) {
					move = studyToLib[movesTaken];
					movesTaken++;
				} else {
					move = studyToHall[movesTaken];
					movesTaken++;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Hall")) {
				if (exitNum[exitNumIterator].equals("1")) {
					move = hallToBasement[movesTaken];
					movesTaken++;
				}

				else if (exitNum[exitNumIterator].equals("2")) {
					move = hallToStudy[movesTaken];
					movesTaken++;
				}
			}
		} else {
			if (player.getToken().getName() == "Green") {
				move = twoToBallRoom[movesTaken];
				movesTaken++;
			} else if (player.getToken().getName() == "Peacock") {
				move = threeToConvservatory[movesTaken];
				movesTaken++;
			} else if (player.getToken().getName() == "Plum") {
				move = fourToStudy[movesTaken];
				movesTaken++;
			} else if (player.getToken().getName() == "Scarlett") {
				move = fiveToLounge[movesTaken];
				movesTaken++;
			} else if (player.getToken().getName() == "Mustard") {
				move = sixToDiningRoom[movesTaken];
				movesTaken++;
			} else if (player.getToken().getName() == "White") {
				move = oneToBallRoom[movesTaken];
				movesTaken++;
			}
		}

		return move;
	}

	public String getSuspect() {
		//if we are in basement return suspect
		if(player.getToken().getRoom().toString().equalsIgnoreCase("Cellar")) {
			return suspect;
		}
		//else....
		else {
			return "Green";
		}
	}

	public String getWeapon() {
		//if we are in basement return suspect
		if(player.getToken().getRoom().toString().equalsIgnoreCase("Cellar")) {
			return weapon;
		}
		//else....
		else {
			return "Dagger";
		}
	}

	public String getRoom() {
		//if we are in basement return suspect
		if(player.getToken().getRoom().toString().equalsIgnoreCase("Cellar")) {
			return room;
		}
		//else....
		else {
			return player.getToken().getRoom().toString();
		}
	}

	public String getDoor() {
		if (weNeedThis > 0) {
			exitNumIterator++;
		}
		movesTaken = 0;
		weNeedThis++;
		return exitNum[exitNumIterator];
	}

	public String getCard(Cards matchingCards) {
		Iterator<Card> iterator = matchingCards.iterator();
		ArrayList<Card> cards = new ArrayList<Card>();
		int noOfCards = 0;
		while (iterator.hasNext()) {
			noOfCards++;
			Card temp = matchingCards.next();
			cards.add(temp);
		}

		String returnedCard = "";
		if (noOfCards < 3) {
			if (isOurTokenCard(cards.get(0).toString(), cards.get(1).toString())) {
				returnedCard = player.getToken().getName();
			} else if (cardHasBeenShown(cards.get(0).toString(), cards.get(1).toString())) {
				returnedCard = shownCard(cards.get(0).toString(), cards.get(1).toString());
			} else if (cardIsWeapon(cards.get(0).toString(), cards.get(1).toString())) {
				returnedCard = weaponCard(cards.get(0).toString(), cards.get(1).toString());
			} else if (cardIsSuspect(cards.get(0).toString(), cards.get(1).toString())) {
				returnedCard = suspectCard(cards.get(0).toString(), cards.get(1).toString());
			} else {
				returnedCard = roomCard(cards.get(0).toString(), cards.get(1).toString());
			}
		} else {
			if (isOurTokenCard(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString())) {
				returnedCard = player.getToken().getName();
			} else if (cardHasBeenShown(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString())) {
				returnedCard = shownCard(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString());
			} else if (cardIsWeapon(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString())) {
				returnedCard = weaponCard(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString());
			} else if (cardIsSuspect(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString())) {
				returnedCard = suspectCard(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString());
			} else {
				returnedCard = roomCard(cards.get(0).toString(), cards.get(1).toString(), cards.get(2).toString());
			}
		}
		shownCards[getCardNum(returnedCard)] = 1;
		return returnedCard;
	}

	public void notifyResponse(Log response) {
		Iterator<String> iterator = response.iterator();
		ArrayList<String> messages = new ArrayList<String>();
		int logLength = 0;
		while (iterator.hasNext()) {
			logLength++;
			String temp = response.next();
			messages.add(temp);
		}

		String[] logQuestion = messages.get(logLength - 2).toString().split(" ");
		String[] logAnswer = messages.get(logLength - 1).toString().split(" ");
		int logQuestionLength = logQuestion.length;
		int logAnswerLength = logAnswer.length;

		String queriedPlayerName = "";
		int queriedPlayerNum;

		String suspectName = "";
		String weaponName = "";
		String roomName = "";
		int suspectNum;
		int weaponNum;
		int roomNum;

		String cardName = "";
		int cardNum = 0;

		boolean hasCard = false;

		// get card name name and num if there is one
		// also get queried player name and num
		if (logAnswer[logAnswerLength - 2].equalsIgnoreCase("card:")) {
			cardNum = logLength - 1;
			cardName = removeFullStop(logAnswer[cardNum]);
			queriedPlayerNum = logAnswerLength - 5;
		} else {
			queriedPlayerNum = logAnswerLength - 6;
		}
		queriedPlayerName = logAnswer[queriedPlayerNum];

		// get room name and num
		if (logQuestion[logQuestionLength - 2].equalsIgnoreCase("Dining")) {
			roomNum = logQuestionLength - 2;
			roomName = "Dining Room";
		} else if (logQuestion[logQuestionLength - 2].equalsIgnoreCase("Billiard")) {
			roomNum = logQuestionLength - 2;
			roomName = "Billiard Room";
		} else {
			roomNum = logQuestionLength - 1;
			roomName = removeFullStop(logQuestion[roomNum]);
		}

		// get weapon name and num
		if (logQuestion[roomNum - 4].equalsIgnoreCase("Lead")) {
			weaponNum = roomNum - 4;
			weaponName = "Lead Pipe";
		} else {
			weaponNum = roomNum - 3;
			weaponName = logQuestion[weaponNum];
		}

		// get suspect name and num
		suspectNum = weaponNum - 3;
		suspectName = logQuestion[suspectNum];

		if (cardName != "") {
			hasCard = true;
		}

		if (hasCard) {
			markOffCardOnNotesForAllPlayers(getCardNum(cardName), queriedPlayerName);
		} else {
			markOffCardOnNotesForOnePlayer(getCardNum(suspectName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(weaponName), queriedPlayerName);
			markOffCardOnNotesForOnePlayer(getCardNum(roomName), queriedPlayerName);
		}
	}
	// our functions
	// notes functions

	public void checkGeneralLogAndUpdateNotes() {

		Iterator<String> iterator = log.iterator();
		ArrayList<String> messages = new ArrayList<String>();
		int logLength = 0;
		while (iterator.hasNext()) {
			logLength++;
			String temp = log.next();
			messages.add(temp);
		}
		int currentLogLength = logLength;

		while (logLength > 2) {
			String[] logQuestion = messages.get(logLength - 2).toString().split(" ");
			String[] logAnswer = messages.get(logLength - 1).toString().split(" ");
			int logQuestionLength = logQuestion.length;
			int logAnswerLength = logAnswer.length;

			String queriedPlayerName = "";
			String queryingPlayerName = "";
			int queriedPlayerNum;
			int queryingPlayerNum;

			String suspectName = "";
			String weaponName = "";
			String roomName = "";
			int suspectNum;
			int weaponNum;
			int roomNum;

			boolean hasCard = false;

			// get queried player name and num
			if (logAnswer[logAnswerLength - 1].equalsIgnoreCase("cards.")) {
				queriedPlayerNum = logAnswerLength - 6;
			} else {
				queriedPlayerNum = logAnswerLength - 4;
			}
			queriedPlayerName = logAnswer[queriedPlayerNum];

			if (!queriedPlayerName.equalsIgnoreCase(getName())) {

				// get room name and num
				if (logQuestion[logQuestionLength - 2].equalsIgnoreCase("Dining")) {
					roomNum = logQuestionLength - 2;
					roomName = "Dining Room";
				} else if (logQuestion[logQuestionLength - 2].equalsIgnoreCase("Billiard")) {
					roomNum = logQuestionLength - 2;
					roomName = "Billiard Room";
				} else {
					roomNum = logQuestionLength - 1;
					roomName = removeFullStop(logQuestion[roomNum]);
				}

				// get weapon name and num
				if (logQuestion[roomNum - 4].equalsIgnoreCase("Lead")) {
					weaponNum = roomNum - 4;
					weaponName = "Lead Pipe";
				} else {
					weaponNum = roomNum - 3;
					weaponName = logQuestion[weaponNum];
				}

				// get suspect name and num
				suspectNum = weaponNum - 3;
				suspectName = logQuestion[suspectNum];

				// get querying player name and num
				queryingPlayerNum = suspectNum - 4;
				queryingPlayerName = logQuestion[queryingPlayerNum];
				if (queryingPlayerName.equalsIgnoreCase(getName())) {
					break;
				}

				if (logAnswer[queriedPlayerNum + 1].equalsIgnoreCase("showed")) {
					hasCard = true;
				}

				if (hasCard) {
					markOffPotentialCardsOnNotesForOnePlayer(getCardNum(suspectName), getCardNum(weaponName),
							getCardNum(roomName), queriedPlayerName);
				} else {
					markOffCardOnNotesForOnePlayer(getCardNum(suspectName), queriedPlayerName);
					markOffCardOnNotesForOnePlayer(getCardNum(weaponName), queriedPlayerName);
					markOffCardOnNotesForOnePlayer(getCardNum(roomName), queriedPlayerName);
				}
				markOffSingleNumsOnNotesForOnePlayer();
			}

			logLength -= 2;
			if (logLength == previousLogLength) {
				break;
			}
		}
		previousLogLength = currentLogLength;
	}

	public void markOffCardOnNotesForAllPlayers(int cardNum, String playerName) {
		for (int j = 0; j < numPlayers; j++) {
			for (int k = 0; k < notes.get(j).get(cardNum).size();) {
				notes.get(j).get(cardNum).remove(k);
			}
			if (playerNames[j].equals(playerName)) {
				notes.get(j).get(cardNum).add('y');
				// TODO 0s and 1s
			} else {
				notes.get(j).get(cardNum).add('x');
			}
		}
	}

	public void markOffCardOnNotesForOnePlayer(int cardNum, String playerName) {
		for (int j = 0; j < numPlayers; j++) {
			if (playerNames[j].equals(playerName)) {
				for (int k = 0; k < notes.get(j).get(cardNum).size(); k++) {
					notes.get(j).get(cardNum).remove(k);
				}
				notes.get(j).get(cardNum).add('x');
			}
		}
	}

	public void markOffPotentialCardsOnNotesForOnePlayer(int suspectNum, int weaponNum, int roomNum,
			String playerName) {
		for (int j = 0; j < numPlayers; j++) {
			if (playerNames[j].equals(playerName)) {
				if (notes.get(j).get(suspectNum).get(0) == '0') {
					notes.get(j).get(suspectNum).remove(0);
					notes.get(j).get(suspectNum).add((char) (notesCounter + '0'));
				} else if (notes.get(j).get(suspectNum).get(0) == 'x' || notes.get(j).get(suspectNum).get(0) == 'y') {
					// -----------add in propability code--------------
				} else {
					notes.get(j).get(suspectNum).add((char) (notesCounter + '0'));
				}
				if (notes.get(j).get(weaponNum).get(0) == '0') {
					notes.get(j).get(weaponNum).remove(0);
					notes.get(j).get(weaponNum).add((char) (notesCounter + '0'));
				} else if (notes.get(j).get(weaponNum).get(0) == 'x' || notes.get(j).get(weaponNum).get(0) == 'y') {
					// -----------add in propability code--------------
				} else {
					notes.get(j).get(weaponNum).add((char) (notesCounter + '0'));
				}
				if (notes.get(j).get(roomNum).get(0) == '0') {
					notes.get(j).get(roomNum).remove(0);
					notes.get(j).get(roomNum).add((char) (notesCounter + '0'));
				} else if (notes.get(j).get(roomNum).get(0) == 'x' || notes.get(j).get(roomNum).get(0) == 'y') {
					// -----------add in propability code--------------
				} else {
					notes.get(j).get(roomNum).add((char) (notesCounter + '0'));
				}
			}
		}
		notesCounter++;
	}

	public void markOffSingleNumsOnNotesForOnePlayer() {
		for (int j = 0; j < numPlayers; j++) {
			for (int k = 1; k < notesCounter; k++) {
				int counter = 0;
				int cardNum = 0;
				for (int i = 0; i < numCards; i++) {
					for (int a = 0; a < notes.get(j).get(i).size(); a++) {
						if (notes.get(j).get(i).get(a) == (char) k + '0') {
							counter++;
							cardNum = i;
						}
					}
				}
				if (counter == 1) {
					markOffCardOnNotesForAllPlayers(cardNum, playerNames[j]);
				}
			}
		}
	}

	public void markOffMyCardsOnNotes() {
		for (int i = 0; i < 6; i++) {
			if (player.hasCard(characterNames[i])) {
				markOffCardOnNotesForAllPlayers(i, getName());
				knownCharactersCount++;
				characterMatrix[1][i] = 1;
			} else {
				markOffCardOnNotesForOnePlayer(i, getName());
				characterMatrix[1][i] = 0;
			}
		}

		for (int i = 0; i < 6; i++) {
			if (player.hasCard(weaponNames[i])) {
				markOffCardOnNotesForAllPlayers(i, getName());
				knownWeaponsCount++;
				weaponMatrix[1][i] = 1;
			} else {
				markOffCardOnNotesForOnePlayer(i, getName());
				weaponMatrix[1][i] = 0;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (player.hasCard(roomNames[i])) {
				markOffCardOnNotesForAllPlayers(i, getName());
				knownRoomsCount++;
				roomMatrix[1][i] = 1;
			} else {
				markOffCardOnNotesForOnePlayer(i, getName());
				roomMatrix[1][i] = 0;
			}
		}
	}

	public void checkIfWeKnowTheSuspect() {
		if (!weKnowTheSuspect) {
			for (int i = 0; i < numSuspects; i++) {
				int counter = 0;
				for (int j = 0; j < numPlayers; j++) {
					if (notes.get(j).get(i).get(0) == 'x')
						counter++;
				}
				if (counter == numPlayers) {
					suspect = getCardName(i);
					weKnowTheSuspect = true;
				}
			}
		}
	}

	public void checkIfWeKnowTheWeapon() {
		if (!weKnowTheWeapon) {
			for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
				int counter = 0;
				for (int j = 0; j < numPlayers; j++) {
					if (notes.get(j).get(i).get(0) == 'x')
						counter++;
				}
				if (counter == numPlayers) {
					weapon = getCardName(i);
					weKnowTheWeapon = true;
				}
			}
		}
	}

	public void checkIfWeKnowTheRoom() {
		if (!weKnowTheRoom) {
			for (int i = 0; i < numSuspects + numWeapons + numRooms; i++) {
				int counter = 0;
				for (int j = 0; j < numPlayers; j++) {
					if (notes.get(j).get(i).get(0) == 'x')
						counter++;
				}
				if (counter == numPlayers) {
					room = getCardName(i);
					weKnowTheRoom = true;
				}
			}
		}
	}

	// returning a card when we are queried functions

	public boolean isOurTokenCard(String a, String b) {
		return isOurTokenCard(a, b, "");
	}

	public boolean isOurTokenCard(String a, String b, String c) {
		if (a.equalsIgnoreCase(player.getToken().getName()) || b.equalsIgnoreCase(player.getToken().getName())
				|| c.equalsIgnoreCase(player.getToken().getName()))
			return true;
		else
			return false;
	}

	public boolean cardHasBeenShown(String a, String b) {
		return cardHasBeenShown(a, b, "");
	}

	public boolean cardHasBeenShown(String a, String b, String c) {
		boolean result = false;
		for (int i = 0; i < numCards; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = 0; i < numCards; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = 0; i < numCards; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		return result;
	}

	public String shownCard(String a, String b) {
		return shownCard(a, b, "");
	}

	public String shownCard(String a, String b, String c) {
		String result = "";
		for (int i = 0; i < numCards; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = 0; i < numCards; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = 0; i < numCards; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		return result;
	}

	public boolean cardIsWeapon(String a, String b) {
		return cardIsWeapon(a, b, "");
	}

	public boolean cardIsWeapon(String a, String b, String c) {
		boolean result = false;
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		return result;
	}

	public String weaponCard(String a, String b) {
		return weaponCard(a, b, "");
	}

	public String weaponCard(String a, String b, String c) {
		String result = "";
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = numSuspects; i < numSuspects + numWeapons; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		return result;
	}

	public boolean cardIsSuspect(String a, String b) {
		return cardIsSuspect(a, b, "");
	}

	public boolean cardIsSuspect(String a, String b, String c) {
		boolean result = false;
		for (int i = 0; i < numSuspects; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = 0; i < numSuspects; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = 0; i < numSuspects; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		return result;
	}

	public String suspectCard(String a, String b) {
		return suspectCard(a, b, "");
	}

	public String suspectCard(String a, String b, String c) {
		String result = "";
		for (int i = 0; i < numSuspects; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = 0; i < numSuspects; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = 0; i < numSuspects; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		return result;
	}

	public boolean cardIsRoom(String a, String b) {
		return cardIsRoom(a, b, "");
	}

	public boolean cardIsRoom(String a, String b, String c) {
		boolean result = false;
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = true;
			}
		}
		return result;
	}

	public String roomCard(String a, String b) {
		return roomCard(a, b, "");
	}

	public String roomCard(String a, String b, String c) {
		String result = "";
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (c.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (a.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		for (int i = numSuspects + numWeapons; i < numSuspects + numWeapons + numRooms; i++) {
			if (b.equalsIgnoreCase(getCardName(i)) && shownCards[i] == 1) {
				result = getCardName(shownCards[i]);
			}
		}
		return result;
	}

	public int getCardNum(String cardName) {
		int num = 0;
		if (cardName.equalsIgnoreCase("green"))
			num = 0;
		else if (cardName.equalsIgnoreCase("mustard"))
			num = 1;
		else if (cardName.equalsIgnoreCase("peacock"))
			num = 2;
		else if (cardName.equalsIgnoreCase("plum"))
			num = 3;
		else if (cardName.equalsIgnoreCase("scarlett"))
			num = 4;
		else if (cardName.equalsIgnoreCase("white"))
			num = 5;

		else if (cardName.equalsIgnoreCase("candlestick"))
			num = 6;
		else if (cardName.equalsIgnoreCase("dagger"))
			num = 7;
		else if (cardName.equalsIgnoreCase("lead pipe"))
			num = 8;
		else if (cardName.equalsIgnoreCase("pistol"))
			num = 9;
		else if (cardName.equalsIgnoreCase("rope"))
			num = 10;
		else if (cardName.equalsIgnoreCase("wrench"))
			num = 11;

		else if (cardName.equalsIgnoreCase("ballroom"))
			num = 12;
		else if (cardName.equalsIgnoreCase("billiard room"))
			num = 13;
		else if (cardName.equalsIgnoreCase("conservatory"))
			num = 14;
		else if (cardName.equalsIgnoreCase("dining room"))
			num = 15;
		else if (cardName.equalsIgnoreCase("hall"))
			num = 16;
		else if (cardName.equalsIgnoreCase("kitchen"))
			num = 17;
		else if (cardName.equalsIgnoreCase("library"))
			num = 18;
		else if (cardName.equalsIgnoreCase("lounge"))
			num = 19;
		else if (cardName.equalsIgnoreCase("study"))
			num = 20;

		return num;
	}

	public String getCardName(int cardNum) {
		String name = "";
		if (cardNum == 0)
			name = "green";
		else if (cardNum == 1)
			name = "mustard";
		else if (cardNum == 2)
			name = "peacock";
		else if (cardNum == 3)
			name = "plum";
		else if (cardNum == 4)
			name = "scarlett";
		else if (cardNum == 5)
			name = "white";

		else if (cardNum == 6)
			name = "candlestick";
		else if (cardNum == 7)
			name = "dagger";
		else if (cardNum == 8)
			name = "lead pipe";
		else if (cardNum == 9)
			name = "pistol";
		else if (cardNum == 10)
			name = "rope";
		else if (cardNum == 11)
			name = "wrench";

		else if (cardNum == 12)
			name = "ballroom";
		else if (cardNum == 13)
			name = "billiard room";
		else if (cardNum == 14)
			name = "conservatory";
		else if (cardNum == 15)
			name = "dining room";
		else if (cardNum == 16)
			name = "hall";
		else if (cardNum == 17)
			name = "kitchen";
		else if (cardNum == 18)
			name = "library";
		else if (cardNum == 19)
			name = "lounge";
		else if (cardNum == 20)
			name = "study";

		return name;
	}

	public String removeFullStop(String room) {
		char[] roomArr = room.toCharArray();
		char[] subArr = new char[roomArr.length - 1];

		for (int i = 0; i < subArr.length; i++) {
			subArr[i] = roomArr[i];
		}
		String returnString = subArr.toString();

		return returnString;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyPlayerName(String playerName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTurnOver(String playerName, String position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyQuery(String playerName, String query) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyReply(String playerName, boolean cardShown) {
		// TODO Auto-generated method stub
	}

	private void setSize() {
		if (movesTaken == size) {
			movesTaken = 0;
		}
		exitNum = setExitNum();

		if (player.getToken().isInRoom()) {
			inRoom = true;
			if (player.getToken().getRoom().toString().equalsIgnoreCase("Lounge")) {
				if (route.toLowerCase().endsWith("lounge")) {
					size = loungeToHall.length;
				} else {
					size = loungeToDiningRoom.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Dining Room")) {
				if (exitNum[exitNumIterator].equals("1")) {
					if (route.endsWith("Dining")) {
						size = diningRoomToHall.length;
					} else {
						size = diningRoomToLounge.length;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					boolean diningKitchen = route.toLowerCase().indexOf("diningkit") != -1 ? true : false;
					if (diningKitchen) {
						size = diningRoomToKitchen.length;
					} else {
						size = diningRoomToBallRoom.length;
					}
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Kitchen")) {
				boolean kitchenDining = route.toLowerCase().indexOf("kitdining") != -1 ? true : false;

				if (kitchenDining) {
					size = kitchenToDiningRoom.length;
				} else {
					size = kitchenToBallRoom.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Ballroom")) {

				if (exitNum[exitNumIterator].equals("1")) {
					size = ballRoomToKitchen.length;
				} else if (exitNum[exitNumIterator].equals("2")) {
					size = ballRoomToDiningRoom.length;
				}

				else if (exitNum[exitNumIterator].equals("3")) {
					size = ballRoomToBilliardRoom.length;
				} else if (exitNum[exitNumIterator].equals("4")) {
					size = ballRoomToConservatory.length;
				}

			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Conservatory")) {

				boolean consBill = route.toLowerCase().indexOf("consbill") != -1 ? true : false;

				if (consBill) {
					size = conservatoryToBilliardRoom.length;
				} else {
					size = conservatoryToBallRoom.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Billiard Room")) {
				if (exitNum[exitNumIterator].equals("1")) {
					boolean billBall = route.toLowerCase().indexOf("billBall") != -1 ? true : false;
					if (billBall) {
						size = billiardRoomToBallRoom.length;
					} else {
						size = billiardRoomToConservatory.length;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					size = billiardRoomToLibrary.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Library")) {
				if (exitNum[exitNumIterator].equals("1")) {
					boolean libStudy = route.toLowerCase().indexOf("libstudy") != -1 ? true : false;
					if (libStudy) {
						size = libraryToStudy.length;
					} else {
						size = libraryToHall.length;
					}
				} else if (exitNum[exitNumIterator].equals("2")) {
					size = libraryToBilliardRoom.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Study")) {

				boolean studylib = route.toLowerCase().indexOf("studylib") != -1 ? true : false;

				if (studylib) {
					size = studyToLib.length;
				} else {
					size = studyToHall.length;
				}
			} else if (player.getToken().getRoom().toString().equalsIgnoreCase("Hall")) {
				if (exitNum[exitNumIterator].equals("1")) {
					size = hallToBasement.length;
				}

				else if (exitNum[exitNumIterator].equals("2")) {
					size = hallToStudy.length;
				}
			}
		}
	}

	String kitchenToDiningRoom[] = { "d", "d", "r", "r", "r", "r", "d", "d", "d", "d", "l" };
	String kitchenToBallRoom[] = { "d", "r", "r", "r", "u", "u", "r" };

	// exit 1
	String ballRoomToKitchen[] = { "l", "d", "d", "l", "l", "l", "u" };

	// exit 2
	String ballRoomToDiningRoom[] = { "d", "d", "d", "d", "d", "l", "l" };

	// exit 3
	String ballRoomToBilliardRoom[] = { "d", "d", "r", "r", "r", "r" };

	// exit 4
	String ballRoomToConservatory[] = { "r", "r", "r", "u" };

	String conservatoryToBallRoom[] = { "d", "l", "l", "l" };
	String conservatoryToBilliardRoom[] = { "d", "d", "d", "l", "d", "d", "r" };

	// exit 1
	String billiardRoomToBallRoom[] = { "l", "l", "l", "l", "u", "u" };
	String billiardRoomToConservatory[] = { "l", "u", "u", "r", "u", "u", "u" };

	// exit 2
	String billiardRoomToLibrary[] = { "d", "l", "l", "d" };

	// exit 1
	String libraryToBilliardRoom[] = { "u", "r", "r", "u" };

	// exit 2
	String libraryToHall[] = { "l", "l", "d", "l", "l", "l", "d" };
	String libraryToStudy[] = { "l", "d", "d", "r", "d", "d", "d" };

	String studyToLibrary[] = { "u", "u", "u", "l", "u", "u", "r" };
	String studyToHall[] = { "u", "l", "l", "l" };
	String studyToLib[] = { "u", "u", "u", "l", "u", "u", "r" };

	// exit 1
	String hallToLibrary[] = { "u", "r", "r", "r", "u", "r", "r" };
	String hallToDiningRoom[] = { "u", "l", "l", "l", "l", "l", "u", "u" };
	String hallToLounge[] = { "u", "l", "l", "l", "l", "l", "d", "d" };
	String hallToBasement[] = { "u", "u" };

	// exit 2
	String hallToStudy[] = { "r", "r", "r", "d" };

	String loungeToHall[] = { "u", "u", "r", "r", "r", "r", "r", "d" };
	String loungeToDiningRoom[] = { "u", "u", "u", "u" };

	// exit 1
	String diningRoomToKitchen[] = { "r", "u", "u", "u", "u", "l", "l", "l", "l", "u", "u" };
	String diningRoomToBallRoom[] = { "r", "r", "u", "u", "u", "u", "u" };
	String diningRoomToHall[] = { "d", "d", "r", "r", "r", "r", "r", "d" };

	// exit2
	String diningRoomToLounge[] = { "d", "d", "d", "d" };

	// 1
	String oneToBallRoom[] = { "d", "l", "l", "d", "d", "d", "d", "r" };

	// 2
	String twoToBallRoom[] = { "d", "d", "r", "r", "d", "d", "d", "d", "l" };

	// 3
	String threeToConvservatory[] = { "l", "l", "l", "l", "l", "u", "u" };

	// 4
	String fourToStudy[] = { "l", "l", "l", "l", "l", "l", "d", "d" };

	// 5
	String fiveToLounge[] = { "u", "u", "u", "u", "u", "u", "l", "d" };

	// 6
	String sixToLounge[] = { "r", "r", "r", "r", "r", "d", "d" };
	String sixToDiningRoom[] = { "r", "r", "r", "r", "r", "r", "d", "d" };

	// ------Exit patterns for getDoor()--------

	// Dining
	String diningBallBillLib[] = { "2", "3", "2", "1", "1" };
	String diningBallConsLounge[] = { "2", "4", "1" };// passage
	String diningBallKitchenStudy[] = { "2", "1" };// passage
	String diningBallBillLibStudy[] = { "2", "3", "2", "1", "1" };
	String diningLounge[] = { "2", "1" };
	String diningHall[] = { "1", "1" };
	String diningBallBillConsLounge[] = { "2", "3", "2", "1", "1", "1" };// passage
	String diningBallKitStudyLib[] = { "2", "1", "1", "1" };// passage
	String diningKitStudy[] = { "2", "1" };// passage
	String diningLoungeConsBillLib[] = { "1", "2", "1", "1" };// passage

	// kitchen
	String kitBallBillLib[] = { "3", "2", "1", "1" };
	String kitBallDiningLounge[] = { "2", "1", "1" };
	String kitBallConsLounge[] = { "4", "1" };// passage
	String kitStudyLib[] = { "1" };// passage
	String kitBallDining[] = { "2", "1", "1" };
	String kitStudy[] = { "1" };// passage
	String kitDining[] = { "1", "1" };
	String kitDiningLounge[] = { "1", "1" };

	// ballroom
	String ballConsBillLibStudy[] = { "4", "2", "1", "1" };
	String ballBillLibStudy[] = { "3", "2", "1", "1" };
	String ballConsLoungeDining[] = { "4", "1", "1" };// passage
	String ballKitStudyLib[] = { "1", "1", "1" };// passage
	String ballKitStudy[] = { "1", "1" };// passage
	String ballConsLounge[] = { "4", "1" };// passage
	String ballDiningLounge[] = { "2", "1", "1" };
	String ballBillLib[] = { "3", "2", "1", "1" };
	String ballDining[] = { "2", "1", "1" };

	// conservatory
	String consBallBillLibStudy[] = { "3", "2", "1", "1" };
	String consBallKitStudyLib[] = { "1", "1", "1" };
	String consBallDiningLounge[] = { "2", "1", "1" };
	String consBillLibStudy[] = { "2", "1", "1" };
	String consBallBillLib[] = { "3", "2", "1" };
	String consBallKitStudy[] = { "1", "1" }; // passage
	String consBillLib[] = { "2", "1", "1" };
	String consLoungeDining[] = { "1", "1" };// passage
	String consBallDining[] = { "2", "1" };
	String consLounge[] = { "1" }; // passage

	// billiard Room
	String billBallConsLoungeDining[] = { "1", "4", "1", "1" }; // passage
	String billBallKitStudyLib[] = { "1", "1", "1", "1" }; // passage
	String billConsLoungeDining[] = { "1", "1", "1" }; // passage
	String billBallKitStudy[] = { "1", "1", "1" }; // passage
	String billBallDiningLounge[] = { "1", "2", "1", "1" };
	String billLibStudy[] = { "2", "1", "1" };
	String billBallDining[] = { "1", "2", "1", "1" };
	String billConsLounge[] = { "1", "1" }; // passage
	String billLib[] = { "2", "1", "1" };

	// library
	String libBillBallDiningLounge[] = { "2", "1", "2", "1", "1" };
	String libStudyKitBallDining[] = { "1", "2", "1", "1" };// passage
	String libBillConsLoungeDining[] = { "2", "1", "1", "1" };// passage
	String libBillBallDining[] = { "2", "1", "2", "1" };
	String libBillConsLounge[] = { "2", "1", "1" };// passage
	String libStudy[] = { "1", "1" };
	String lib[] = { "1" };

	// study
	String studyLibBillConsLounge[] = { "2", "1", "1" }; // passage
	String studyLibBillBallDining[] = { "2", "1", "2", "1", "1" };
	String studyKitBallDiningLounge[] = { "2", "1", "1" }; // passage
	String studyKitBallBillLib[] = { "3", "2", "1", "1" }; // passage
	String studyKitBallConsLounge[] = { "4", "1" }; // passage
	String studyKitBallDining[] = { "2", "1", "1" }; // passage
	String studyKitDining[] = { "1", "1" }; // passage
	String studyLib[] = { "1", "1" };

	// Lounge
	String loungeDiningBallBillLib[] = { "2", "3", "2", "1", "1" };
	String loungeConsBillBallDining[] = { "1", "2", "1", "1" };// passage
	String loungeConsBallDining[] = { "2", "1", "1" };// passage
	String loungeDining[] = { "1", "1" };
	String loungeConsBallKitStudy[] = { "1", "1" }; // passage
	String loungeDiningKitStudy[] = { "2", "1" }; // passage
	String lounge[] = { "1" };
	String loungeConsBillLib[] = { "2", "1", "1" };

	private String[] setExitNum() {
		String exitNumArray[] = null;
		if (route.equalsIgnoreCase("diningBallConsLounge"))
			exitNumArray = Arrays.copyOf(diningBallConsLounge, diningBallConsLounge.length);
		else if (route.equalsIgnoreCase("diningBallBillLib"))
			exitNumArray = Arrays.copyOf(diningBallBillLib, diningBallBillLib.length);
		else if (route.equalsIgnoreCase("diningBallKitchenStudy"))
			exitNumArray = Arrays.copyOf(diningBallKitchenStudy, diningBallKitchenStudy.length);
		else if (route.equalsIgnoreCase("diningBallBillLibStudy"))
			exitNumArray = Arrays.copyOf(diningBallBillLibStudy, diningBallBillLibStudy.length);
		else if (route.equalsIgnoreCase("diningLounge"))
			exitNumArray = Arrays.copyOf(diningLounge, diningLounge.length);
		else if (route.equalsIgnoreCase("diningHall"))
			exitNumArray = Arrays.copyOf(diningHall, diningHall.length);
		else if (route.equalsIgnoreCase("diningBallBillConsLounge"))
			exitNumArray = Arrays.copyOf(diningBallBillConsLounge, diningBallBillConsLounge.length);
		else if (route.equalsIgnoreCase("diningBallKitStudyLib"))
			exitNumArray = Arrays.copyOf(diningBallKitStudyLib, diningBallKitStudyLib.length);
		else if (route.equalsIgnoreCase("diningKitStudy"))
			exitNumArray = Arrays.copyOf(diningKitStudy, diningKitStudy.length);
		else if (route.equalsIgnoreCase("diningLoungeConsBillLib"))
			exitNumArray = Arrays.copyOf(diningLoungeConsBillLib, diningLoungeConsBillLib.length);
		else if (route.equalsIgnoreCase("kitBallBillLib"))
			exitNumArray = Arrays.copyOf(kitBallBillLib, kitBallBillLib.length);
		else if (route.equalsIgnoreCase("kitBallDiningLounge"))
			exitNumArray = Arrays.copyOf(kitBallDiningLounge, kitBallDiningLounge.length);
		else if (route.equalsIgnoreCase("kitBallConsLounge"))
			exitNumArray = Arrays.copyOf(kitBallConsLounge, kitBallConsLounge.length);
		else if (route.equalsIgnoreCase("kitStudyLib"))
			exitNumArray = Arrays.copyOf(kitStudyLib, kitStudyLib.length);
		else if (route.equalsIgnoreCase("kitBallDining"))
			exitNumArray = Arrays.copyOf(kitBallDining, kitBallDining.length);
		else if (route.equalsIgnoreCase("kitStudy"))
			exitNumArray = Arrays.copyOf(kitStudy, kitStudy.length);
		else if (route.equalsIgnoreCase("kitDining"))
			exitNumArray = Arrays.copyOf(kitDining, kitDining.length);
		else if (route.equalsIgnoreCase("kitDiningLounge"))
			exitNumArray = Arrays.copyOf(kitDiningLounge, kitDiningLounge.length);
		else if (route.equalsIgnoreCase("ballBillLibStudy"))
			exitNumArray = Arrays.copyOf(ballBillLibStudy, ballBillLibStudy.length);
		else if (route.equalsIgnoreCase("ballConsBillLibStudy"))
			exitNumArray = Arrays.copyOf(ballConsBillLibStudy, ballConsBillLibStudy.length);
		else if (route.equalsIgnoreCase("ballConsLoungeDining"))
			exitNumArray = Arrays.copyOf(ballConsLoungeDining, ballConsLoungeDining.length);
		else if (route.equalsIgnoreCase("ballKitStudyLib"))
			exitNumArray = Arrays.copyOf(ballKitStudyLib, ballKitStudyLib.length);
		else if (route.equalsIgnoreCase("ballKitStudy"))
			exitNumArray = Arrays.copyOf(ballKitStudy, ballKitStudy.length);
		else if (route.equalsIgnoreCase("ballConsLounge"))
			exitNumArray = Arrays.copyOf(ballConsLounge, ballConsLounge.length);
		else if (route.equalsIgnoreCase("ballDiningLounge"))
			exitNumArray = Arrays.copyOf(ballDiningLounge, ballDiningLounge.length);
		else if (route.equalsIgnoreCase("ballBillLib"))
			exitNumArray = Arrays.copyOf(ballBillLib, ballBillLib.length);
		else if (route.equalsIgnoreCase("ballDining"))
			exitNumArray = Arrays.copyOf(ballDining, ballDining.length);
		else if (route.equalsIgnoreCase("consBallBillLibStudy"))
			exitNumArray = Arrays.copyOf(consBallBillLibStudy, consBallBillLibStudy.length);
		else if (route.equalsIgnoreCase("consBallKitStudyLib"))
			exitNumArray = Arrays.copyOf(consBallKitStudyLib, consBallKitStudyLib.length);
		else if (route.equalsIgnoreCase("consBallDiningLounge"))
			exitNumArray = Arrays.copyOf(consBallDiningLounge, consBallDiningLounge.length);
		else if (route.equalsIgnoreCase("consBillLibStudy"))
			exitNumArray = Arrays.copyOf(consBillLibStudy, consBillLibStudy.length);
		else if (route.equalsIgnoreCase("consBallBillLib"))
			exitNumArray = Arrays.copyOf(consBallBillLib, consBallBillLib.length);
		else if (route.equalsIgnoreCase("consBallKitStudy"))
			exitNumArray = Arrays.copyOf(consBallKitStudy, consBallKitStudy.length);
		else if (route.equalsIgnoreCase("consBillLib"))
			exitNumArray = Arrays.copyOf(consBillLib, consBillLib.length);
		else if (route.equalsIgnoreCase("consLoungeDining"))
			exitNumArray = Arrays.copyOf(consLoungeDining, consLoungeDining.length);
		else if (route.equalsIgnoreCase("consBallDining"))
			exitNumArray = Arrays.copyOf(consBallDining, consBallDining.length);
		else if (route.equalsIgnoreCase("billBallConsLoungeDining"))
			exitNumArray = Arrays.copyOf(billBallConsLoungeDining, billBallConsLoungeDining.length);
		else if (route.equalsIgnoreCase("billBallKitStudyLib"))
			exitNumArray = Arrays.copyOf(billBallKitStudyLib, billBallKitStudyLib.length);
		else if (route.equalsIgnoreCase("billConsLoungeDining"))
			exitNumArray = Arrays.copyOf(billConsLoungeDining, billConsLoungeDining.length);
		else if (route.equalsIgnoreCase("billBallKitStudy"))
			exitNumArray = Arrays.copyOf(billBallKitStudy, billBallKitStudy.length);
		else if (route.equalsIgnoreCase("billBallDiningLounge"))
			exitNumArray = Arrays.copyOf(billBallDiningLounge, billBallDiningLounge.length);
		else if (route.equalsIgnoreCase("billLibStudy"))
			exitNumArray = Arrays.copyOf(billLibStudy, billLibStudy.length);
		else if (route.equalsIgnoreCase("billBallDining"))
			exitNumArray = Arrays.copyOf(billBallDining, billBallDining.length);
		else if (route.equalsIgnoreCase("billConsLounge"))
			exitNumArray = Arrays.copyOf(billConsLounge, billConsLounge.length);
		else if (route.equalsIgnoreCase("billLib"))
			exitNumArray = Arrays.copyOf(billLib, billLib.length);
		else if (route.equalsIgnoreCase("libBillBallDiningLounge"))
			exitNumArray = Arrays.copyOf(libBillBallDiningLounge, libBillBallDiningLounge.length);
		else if (route.equalsIgnoreCase("libStudyKitBallDining"))
			exitNumArray = Arrays.copyOf(libStudyKitBallDining, libStudyKitBallDining.length);
		else if (route.equalsIgnoreCase("libBillConsLoungeDining"))
			exitNumArray = Arrays.copyOf(libBillConsLoungeDining, libBillConsLoungeDining.length);
		else if (route.equalsIgnoreCase("libBillBallDining"))
			exitNumArray = Arrays.copyOf(libBillBallDining, libBillBallDining.length);
		else if (route.equalsIgnoreCase("libBillConsLounge"))
			exitNumArray = Arrays.copyOf(libBillConsLounge, libBillConsLounge.length);
		else if (route.equalsIgnoreCase("libStudy"))
			exitNumArray = Arrays.copyOf(libStudy, libStudy.length);
		else if (route.equalsIgnoreCase("studyLibBillConsLounge"))
			exitNumArray = Arrays.copyOf(studyLibBillConsLounge, studyLibBillConsLounge.length);
		else if (route.equalsIgnoreCase("studyLibBillBallDining"))
			exitNumArray = Arrays.copyOf(studyLibBillBallDining, studyLibBillBallDining.length);
		else if (route.equalsIgnoreCase("studyKitBallDiningLounge"))
			exitNumArray = Arrays.copyOf(studyKitBallDiningLounge, studyKitBallDiningLounge.length);
		else if (route.equalsIgnoreCase("studyKitBallBillLib"))
			exitNumArray = Arrays.copyOf(studyKitBallBillLib, studyKitBallBillLib.length);
		else if (route.equalsIgnoreCase("studyKitBallConsLounge"))
			exitNumArray = Arrays.copyOf(studyKitBallConsLounge, studyKitBallConsLounge.length);
		else if (route.equalsIgnoreCase("studyKitBallDining"))
			exitNumArray = Arrays.copyOf(studyKitBallDining, studyKitBallDining.length);
		else if (route.equalsIgnoreCase("studyKitDining"))
			exitNumArray = Arrays.copyOf(studyKitDining, studyKitDining.length);
		else if (route.equalsIgnoreCase("studyLib"))
			exitNumArray = Arrays.copyOf(studyLib, studyLib.length);
		else if (route.equalsIgnoreCase("loungeDiningBallBillLib"))
			exitNumArray = Arrays.copyOf(loungeDiningBallBillLib, loungeDiningBallBillLib.length);
		else if (route.equalsIgnoreCase("loungeConsBillBallDining"))
			exitNumArray = Arrays.copyOf(loungeConsBillBallDining, loungeConsBillBallDining.length);
		else if (route.equalsIgnoreCase("loungeConsBallDining"))
			exitNumArray = Arrays.copyOf(loungeConsBallDining, loungeConsBallDining.length);
		else if (route.equalsIgnoreCase("loungeDining"))
			exitNumArray = Arrays.copyOf(loungeDining, loungeDining.length);
		else if (route.equalsIgnoreCase("loungeConsBallKitStudy"))
			exitNumArray = Arrays.copyOf(loungeConsBallKitStudy, loungeConsBallKitStudy.length);
		else if (route.equalsIgnoreCase("loungeDiningKitStudy"))
			exitNumArray = Arrays.copyOf(loungeDiningKitStudy, loungeDiningKitStudy.length);
		else if (route.equalsIgnoreCase("consLounge"))
			exitNumArray = Arrays.copyOf(consLounge, consLounge.length);
		else if (route.equalsIgnoreCase("lounge"))
			exitNumArray = Arrays.copyOf(lounge, lounge.length);
		else if (route.equalsIgnoreCase("lib"))
			exitNumArray = Arrays.copyOf(lib, lib.length);
		else if (route.equalsIgnoreCase("loungeConsBillLib"))
			exitNumArray = Arrays.copyOf(loungeConsBillLib, loungeConsBillLib.length);

		return exitNumArray;
	}

	public double PossCalc() {
		return EnvelopeCalc();
	}

	public double EnvelopeCalc() {
		int charCalc;
		int weaponCalc;
		int roomCalc;

		if (weKnowTheSuspect) {
			charCalc = 1;
		} else {
			charCalc = (6 - knownCharactersCount);
		}

		if (weKnowTheWeapon) {
			weaponCalc = 1;
		} else {
			weaponCalc = (6 - knownWeaponsCount);
		}

		if (weKnowTheRoom) {
			roomCalc = 1;
		} else {
			roomCalc = (9 - knownRoomsCount);
		}

		return charCalc * weaponCalc * roomCalc;
	}

	public double PlayerCalc(int i) {
		// TODO
		return 0;// factorial((21-knownWeaponsCount-knownCharactersCount-knownRoomsCount) -
					// player[i].knownNotHave)/(numPlayers-1);
	}

	public void MatrixOfProbability(int scenario) {

	}

	// TODO
	// Add probability code to jems notes code
	// Update weapon/character/room known cards every time slot is set to y

	public void setMatrix() {
		System.out.println("Char: " + knownCharactersCount);
		System.out.println("Weapon: " + knownWeaponsCount);
		System.out.println("Room: " + knownRoomsCount);

		for (int j = 0; j < 6; j++) {
			if(characterMatrix[1][j] == 0) {
				characterMatrix[0][j] = (double) 1 / (6 - knownCharactersCount);
				setCharacters(j);
			}
			if(weaponMatrix[1][j] == 0) {
				weaponMatrix[0][j] = (double) 1 / (6 - knownWeaponsCount);
				setWeapons(j);
			}
		}

		for (int j = 0; j < 9; j++) {
			if(roomMatrix[1][j] == 0) {
				roomMatrix[0][j] = (double) 1 / (9 - knownRoomsCount);
				setRooms(j);
			}
		}
	}

	public void setCharacters(int j) {
		for (int i = 2; i < numPlayers + 1; i++) {
			characterMatrix[i][j] = (double) (6 - knownCharactersCount - 1) / ((6 - knownCharactersCount) * (numPlayers - 1));
		}
	}

	public void setWeapons(int j) {
		for (int i = 2; i < numPlayers + 1; i++) {
			weaponMatrix[i][j] = (double) (6 - knownWeaponsCount - 1) / ((6 - knownWeaponsCount) * (numPlayers - 1));
		}
	}

	public void setRooms(int j) {
		for (int i = 2; i < numPlayers + 1; i++) {
			roomMatrix[i][j] = (double) (9 - knownRoomsCount - 1) / ((9 - knownRoomsCount) * (numPlayers - 1));
		}
	}

	public void PrintMatrix() {
		System.out.println("Characters");

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < numPlayers + 1; j++) {
				System.out.print(characterMatrix[j][i] + ", ");
			}
			System.out.println();
		}

		System.out.println("Weapons");

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < numPlayers + 1; j++) {
				System.out.print(weaponMatrix[j][i] + ", ");
			}
			System.out.println();
		}

		System.out.println("Rooms");

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < numPlayers + 1; j++) {
				System.out.print(roomMatrix[j][i] + ", ");
			}
			System.out.println();
		}
	}
}
