import java.awt.event.KeyAdapter;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    public static void main(String[] args) {

        // First class should be SnakeFirstPage
        // since it will be our first page
        SnakeFirstPage page = new SnakeFirstPage();

        //Then we need to have other class that should be
        // Gameframe
        GameFrame frame = new GameFrame();
    }
}
