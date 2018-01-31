
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainGUI extends JFrame{

	//window title thang
    String appName = "Cluedo";

    
    //create Cleudo JFrame
    JFrame newFrame = new JFrame(appName);
    
    //The button to send shit
    JButton sendMessage;
    
    //Text Input field
    JTextField messageBox;
    
    //display text
    JTextArea chatBox;
    
    //displayBoard
    JLabel board;

    //main
    public static void main(String[] args) {
       new MainGUI();
        
    }

    //build method
    public MainGUI() {
    	
        //chat box commentary
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        //textfield
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLUE);
        inputPanel.setLayout(new GridBagLayout());

        //board window
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
       
        
/*---------------- InputPanel------------------------*/
        
        //input field settings
        messageBox = new JTextField(30);
        
        //requests input from message component
        messageBox.requestFocusInWindow();

        //button settings
        sendMessage = new JButton("Send Message");
        
        //requests input from messageBox field
        sendMessage.addActionListener(new sendMessageButtonListener());



        //add message box to left of inputPanel
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        //add send button to right of inputPanel
        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        
        //actually adding to inputPanel
        inputPanel.add(messageBox, left);
        inputPanel.add(sendMessage, right);
       
/*--------------------DisplayPanel----------------------------*/
        
        //blank JtextArea
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);

        //add to the jPanel
        displayPanel.add(new JScrollPane(chatBox), BorderLayout.EAST);

/*-------------------------BoardPanel---------------------------------*/
        
        //import board image as an ImageIcon object
        board = new JLabel(new ImageIcon("board.jpg"));
        boardPanel.add(board);
/*-------------------------Adding to JFRAMe---------------------------------*/

        //set input panel to south of display panel, addd to displayPanel
        displayPanel.add(inputPanel, BorderLayout.SOUTH );
        displayPanel.add(boardPanel, BorderLayout.WEST);
        newFrame.add(displayPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setSize(800, 800);
        
        
        
  
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                chatBox.append("<" + username + ">:  " + messageBox.getText()
                        + "\n");
                messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    }

    String  username = "GAv";


}