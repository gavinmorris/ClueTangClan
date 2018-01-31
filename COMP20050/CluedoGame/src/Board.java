import javax.swing.*;
//HIHIODHSFLSKHFKJH
public class Board extends JFrame {
	
	public Board() {
		ImageIcon image = new ImageIcon("cluedoboard.jpg");
        JLabel label = new JLabel(image);
		JFrame frame = new JFrame();
		frame.setTitle("Cluedo");
        frame.add(label);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}

// 
//
//
//  //Displaying the Cluedo frame
// 
//class Board extends JFrame
//    {
//   
//    
//      //Constructing the frame Image
//     
//       public Board()
//       {
//         
//         //Display on the window
//         setTitle("Cluedo");
//         
//         //import frame image as an ImageIcon object
//         ImageIcon image = new ImageIcon("cluedoboard.jpg");
//         
//         //Allow ImageIcon object to be displayed as a JLabel
//         JLabel label = new JLabel(image);
//         
//         //You already know wtf it is
//         JFrame frame = new JFrame();
//         frame.add(label);
//         frame.setVisible(true);
//         frame.setSize(700, 700);
//         
//         //Program will terminate when X'ed out of
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 
//      }
// 
//    
//}


