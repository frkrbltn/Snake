import javax.swing.*;

/**
 * This program created a game that is calles Snake
 * opens with menu then opens the game
 *
 */
public class Snake {

    /**
     * That is our main method
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create the try and catch for exceptions
        try {
            // First message
            String welcomeUser = JOptionPane.showInputDialog("Are you ready for the game? \n if you are type Y");

            // Based on the user's input
            if (welcomeUser.charAt(0) == 'y' || welcomeUser.charAt(0) == 'Y') {
                JOptionPane.showMessageDialog(null, "This game created by " +
                                            "Furkan Karabulut, Yazan Alshoroogi, Vijayvenkat Madapakula");
               // Call the class
                CallGame frame = new CallGame();

            } else if( welcomeUser.charAt(0) != 'y' || welcomeUser.charAt(0) != 'Y')  {
                JOptionPane.showMessageDialog(null, "Your loss!");
                System.exit(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Your loss!");
            System.exit(1);
        }
    }
}
