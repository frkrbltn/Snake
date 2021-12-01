import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.*;
import java.awt.*;

public class GameFrame extends JFrame{

    /**
     * Our constructor
     */
    public GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("SNAKE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
