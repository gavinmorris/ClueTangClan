# ClueTangClan

![alt text](https://cdn-img.easyicon.net/png/5488/548871.gif)

# Members
* Euan Hayden (Supreme Leader)
* Jem Cairns (Chief Programmer)
* Gavin Morris (Morale Booster)

# Sprint 1

We have displayed 3 Panels currently.

**1. Text input panel for commands.**

This is  a JTextField which is added to a JPanel. This allows the user to input textual commands and make their move by clicking a JButton which passes the text in a sendMessageListener. 

**2. Display panel to echo text.**

This is an uneditable JTextArea which echos the text in the input field onto this display panel. Username tags are displayed beside the message to  indicate who made their turn.
 
**3. Board panel which display the board and text panels.**

This JPanel uses JLayeredPane to display the board image as a default layer and then adding JLabels (tokens) over that default layer and display them as Pallet Layers. The 6 player tokens and weapon layers are displayed as JLabels and each have unique coordinate in relation to the board.

**Functionality.**

When the “Complete Move” JButton is clicked the input from the input text field is passed into an ActionListener method where it is compared to numerous if else statements.

**Weapon commands work like this.**

* move **"weapon name"** to **"room name"**
* move **candlestick** to **library**

**Player commands work like this:**

* move **"player name"** to **"left/right/up/down"**
* move **plum left**

Each player label is moved either EAST, WEST, NORTH, SOUTH by 23 pixels, depending on what direction picked.

![alt text](https://i.imgur.com/6uqJcCX.png)


# Game Features for Sprint 2
- [ ]  input for names of players
- [x]  take turns
- [ ]  dice feature
- [ ]  choose exit for each room
- [ ]  move between rooms using decret passage
- [x]  done command
- [ ] quit command
- [ ] not case sensitive white space thing 
- [ ] invalid move messages

![alt text](https://i.imgur.com/p7piPoB.png)
