import javax.swing.*;

/**
 * This class creates the main frame for the game with title and
 * other essential features.
 */
public class CallGame extends JFrame{

    /**
     * Our constructor
     */
    public CallGame() {

        // Call the game class
        Game panel = new Game();
        // Set the title
        this.setTitle("SNAKE");
        // Close the frame by clicking on Exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        /** After a long and ardous journey we tried to
         * make a  menu swaping frames, but alas-
         * our journey came to a screeching halt
         * as the menu could not be constructed
         * :(
         *
         */
        //JPanel outerBox = new JPanel();
        //// Make a Box Layout Manager
        //BoxLayout boxLayout = new BoxLayout(outerBox, BoxLayout.Y_AXIS);
        //// Set the boxLayout as the Layout Manager for outerBox
        //outerBox.setLayout(boxLayout);
        //String imageFile = "background.jpg";
        //// So, the outerBox will contain two components vertically
        //// They are both Box objects
        ////
        //// The top component
        //Box topBox = Box.createHorizontalBox();
        //// The bottom component
        //Box bottomBox = Box.createHorizontalBox();
        ////The buttomBox
        //Box bottom2box = Box.createHorizontalBox();

        //// Add the two components to outerBox
        //outerBox.add(topBox);
        //outerBox.add(bottomBox);
        //outerBox.add(bottom2box);

        //// Add the outerBox to the frame
        //panel.add(outerBox);

        ////
        //// Create visible components
        ////

        //// For the topBox
        //// The image object
        //ImageIcon image = new ImageIcon(imageFile);
        //JLabel imageLabel = new JLabel(image);
        //// The fortune object

        //Font font4 = new Font("Helvetica", Font.BOLD, 26);
        //String authors = "Furkan Karabulut, Yazan Alshoroogi, Vijayvenkat Madapakula";
        //JLabel label2 = new JLabel(authors);
        //label2.setFont(font4);

        //JButton button = new JButton("Play");

        //topBox.add(imageLabel);
        //bottomBox.add(label2);
        //bottom2box.add(button);

        // add the component to the container
        this.add(panel);
        // process on the container
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
