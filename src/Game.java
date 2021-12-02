import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This class is the heart of the game
 * Contains the constants and variables used
 * @author Furkan Karabulut
 */
public class Game extends JPanel implements ActionListener {
    /** Total width of the screen */
    public static final int WIDTH  = 900;

    /** Total height of the screen */
    public static final int HEIGHT  = 700;

    /** size of the squares on the board of the game */
    public static final int SQUARE_SIZE = 25;

    /** creates a square from the board */
    public static final int GAME_SQUARES = (HEIGHT * WIDTH) / SQUARE_SIZE;

    /** time until game starts*/
    public static final int PAUSE = 75;

    /** ascii number for the left arrow key*/
    public static final char LEFT = 37;

    /** ascii number for the up arrow key*/
    public static final char UP = 38;

    /** ascii number for the right arrow key*/
    public static final char RIGHT = 39;

    /** ascii number for the down arrow key*/
    public static final char DOWN = 40;

    /** beginning color of snake */
    public static final int GREN = 81;

    /** starting number of additional body parts for snake */
    public static final int START_PARTS = 6;

    /** maximum rgb value for the rgb colors */
    public static final int MAX_RGB = 255;

    /** size of the score font */
    public static final int SCORE_FONT_SIZE = 40;

    /** size of the gameover font */
    public static final int GAMEOVER_FONT_SIZE = 62;

    /** The color for the snake body when snake eats apples, its color changes */
    int red = 0;

    /** Color for snake body and head */
    int green = GREN;

    /** Color for snake body and head */
    int blue = 0;

    /** number of squares in the x-axis*/
    public static final int X[] = new int[GAME_SQUARES];

    /** number of squares in the y-axis*/
    public static final int Y[] = new int[GAME_SQUARES];

    /** the length of the snake */
    int bodyParts = START_PARTS;

    /** apples eaten by snake */
    int applesEaten;

    /** the position on the x axis of an apple */
    int appleX;

    /** the position on the y axis of an apple */
    int appleY;

    /** Initialize the snake direction */
    char direction = RIGHT;

    /** Initialize the snake movements */
    boolean running = false;

    /** Creating timer object */
    Timer timer;

    /** Creating the random object for the color */
    Random random;

    /**
     * Our constructor
     */
    public Game() {

        random = new Random();

        //Create the dimension
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);

        // Set the color for background
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // Key listener
        MyKeyAdapter key = new MyKeyAdapter();
        this.addKeyListener(key);

        // start the game
        runGame();
    }

    /**
     * this method will start the game
     */
    public void runGame() {
        
        randomApple();
        running = true;
        timer = new Timer(PAUSE, this);
        timer.start();
    }

    /**
     * This method will pain the board, snake and the apple
     * @param g Graphics to color for the particles
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * This method will create the shape of the particles in the game 
     * and it will color those particles. 
     * @param g Graphics to color for the particles
     */
    public void draw (Graphics g) {
        // If the snake is moving
        if(running) {

            /*
            for (int i = 0; i < HEIGHT / SQUARE_SIZE; i++) {
                g.drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, HEIGHT);
                g.drawLine(0, i * SQUARE_SIZE, WIDTH, i * SQUARE_SIZE);
            } */
            
            // The color of the apples 
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, SQUARE_SIZE, SQUARE_SIZE);
            
            Color color = new Color(red, green, blue);
            g.setColor(color);
            
            // The loop for the body and head of the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(color);
                    g.fillRect(X[i], Y[i], SQUARE_SIZE, SQUARE_SIZE);
                } else {
                    g.setColor(color);
                    g.fillRect(X[i], Y[i], SQUARE_SIZE, SQUARE_SIZE);
                }
            }
            // Set the colors and font on the score
            g.setColor(Color.RED);
            Font font = new Font("Helvetica", Font.BOLD, SCORE_FONT_SIZE);
            g.setFont(font);
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, 
                    (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
                        g.getFont().getSize());
            
        } else {
            gameOver(g);
        }
    }

    /**
     * This method spawns the new apple on the grid
     */
    public void randomApple() {
        appleX = random.nextInt(WIDTH / SQUARE_SIZE) * SQUARE_SIZE;
        appleY = random.nextInt(HEIGHT / SQUARE_SIZE) * SQUARE_SIZE;
    }

    /**
     * This method moves the snake to cross the baord
     * based on the inputs
     */
    public void move() {
        for(int i = bodyParts; i > 0; i--) {
            X[i] = X[i - 1];
            Y[i] = Y[i - 1];
        }
        if (direction == UP) {
            Y[0] = Y[0] - SQUARE_SIZE;
        } else if(direction == RIGHT) {
            X[0] = X[0] + SQUARE_SIZE;
        } else if(direction == LEFT) {
            X[0] = X[0] - SQUARE_SIZE;
        } else if (direction == DOWN) {
            Y[0] = Y[0] + SQUARE_SIZE;
        }
    }

    /**
     * This method checks if the apple is eaten by the snake
     */
    public void verifyApple() {

        if((X[0] == appleX) && (Y[0] == appleY)) {
            //body gets longer
            bodyParts++;
            applesEaten++;

            // the color of the snake changes randomly after each apple is eaten
            red = random.nextInt(MAX_RGB);
            green = random.nextInt(MAX_RGB);
            blue = random.nextInt(MAX_RGB);
            randomApple();
        }
    }

    /**
     * This is the method to check if the snake collides with either
     * its own body or the walls. We found that if you spam the controls
     * the system overflows, and it gives you a game over.
     *
     */
    public void collisions() {

        //Checks if head collides body
        for(int i = bodyParts; i > 0; i--) {
            if((X[0] == X[i]) && (Y[0] == Y[i])) {
                running = false;
            }
        }
        // Checks if head touches border
        if(X[0] < 0) {
            running = false;
        } else if (X[0] > WIDTH) {
            running = false;
        } else if (Y[0] < 0) {
            running = false;
        } else if (Y[0] > HEIGHT) {
            running = false;
        }
        // Stops updating the draw method
        if(running == false) {
            timer.stop();
        }
    }

    /**
     * This method stops the game
     * @param g Graphics displays the score and displays the end message
     */
    public void gameOver(Graphics g) {

        //Score Text
        g.setColor(Color.RED);
        Font font = new Font("Helvetica", Font.BOLD, SCORE_FONT_SIZE);
        g.setFont(font);
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,
                (WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        //Game Over Text
        g.setColor(Color.RED);
        Font font2 = new Font("Helvetica", Font.BOLD, GAMEOVER_FONT_SIZE);
        g.setFont(font2);
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("You are suck",
                (WIDTH - metrics2.stringWidth("You are suck")) / 2, HEIGHT / 2);
    }


    /**
     * actionPerformed refreshes the move, verifyApple, and collisions method if
     * the timer is not stopped.
     * @param e If the action is performed the event happens
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        if(running) {
            move();
            verifyApple();
            collisions();
        }
        repaint();
    }

    /**
     * This class is to get the key inputs from the arrow keys
     */
    public class MyKeyAdapter extends KeyAdapter {

        /**
         * keyPressed checks which arrow key is pressed and makes sure
         * the snake is not already going the opposite direction so the body
         * does not overlap.
         * @param e When a key is pressed
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                // Checks the key event to see if any of the arrow keys are pressed
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
                default:
                    break;
            }
        }
    }
}
