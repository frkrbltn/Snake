import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MyKeyAdapter extends KeyAdapter {
    public static final char UP = 38;
    public static final char RIGHT = 39;
    public static final char DOWN = 40;
    public static final char LEFT = 37;;
    private GamePanel panel;

    @Override
    public void keyPressed(KeyEvent e) {
       switch (e.getKeyCode()) {
           case KeyEvent.VK_LEFT:
               if(direction != RIGHT) {
                   direction = LEFT;
               }
               break;
           case KeyEvent.VK_RIGHT:
               if(direction != LEFT) {
                   direction = RIGHT;
               }
               break;
           case KeyEvent.VK_UP:
               if(direction != DOWN) {
                   direction = UP;
               }
               break;
           case KeyEvent.VK_DOWN:
               if(direction != UP) {
                   direction = DOWN;
               }
               break;
       }
    }
}
