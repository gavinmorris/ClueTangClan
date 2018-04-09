![alt text](https://i.imgur.com/bK5ti4Z.png)

# Members
* Euan Hayden (16495896)
* Jem Cairns (16439124)
* Gavin Morris (16360966)

# Sprint 4

We have displayed 3 Panels.

**1. Player Options.**

Right off the bat when you launch the game, you are a prompted by a JOptionPane which asks you to input the number of players you wish to play with, input must be an integer between (2-6), we handle errors for integrs out of range. Then a sperate JOptionPane will prompt you to enter the username of each of the players 1 -> 6.

**2. Start the Game.**

To begin the game you must enter "start", this rolls the dice and allows Player 1 to move, we count the moves, we do not count placement of weapons between rooms as actual player moves.
 
**3. Board Boundaries.**

Using a multidimesional array, a player's moves over the board is tracked, if they try and move into a wall or out of the bounds of the board they will get an error message. No moves will be deducted for an invalid move. If you try to move onto a tile which is occupied by another player, you will also be greeted with an error message.
 
**4. Player turns.**

When a player has made all their moves, it does not automatically switch to the next players turns, we feel that this disrupts the pace of the game. Instead once a player has no moves left, they are prompted to enter "done" to switch to the next player. This gives the player time to move weapons around as they wish, if they try and move with 0 moves left they will be greeted with "No moves left, type done." message.

**5. Entering/ Exiting Rooms.**

A player may enter any room through the identified entry point, once the token reaches the threshold of the room it automatically switches into a slot in that room. When exiting the room doorways are labeled with numbers, enter that number to exit the room. Secret passages have also been implememted to fast travel across the board.

**6. Asking a question.**

When a player enters a room they have two options they can either ask a question or finish their turn. To ask a question they simply type 'question' and to finsh their turn they type 'done'. WHen asking a question you must specify the player name of who you are accusing and the weapon of choice. EVery player will be asked this question and they will show whether they have the card or not.

**Functionality.**

Hit enter or the JButton to complete a move.

**Weapon commands work like this.**

* move **"weapon name"** to **"room name"**
* move **candlestick** to **library**

**Player commands work like this:**
* **Not case sensitive!**
* move up = **u**
* move down = **d**
* move right = **r**
* move left = **l**

**Other Commands.**

* help - displays panel with command information.
* quit - termiantes game.

![alt text](https://i.imgur.com/wkMv3tW.png)

