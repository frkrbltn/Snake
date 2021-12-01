import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    public static final int SCREEN_WIDTH  = 600;
    public static final int SCREEN_HEIGHT  = 600;
    public static final int UNIT_SIZE = 25;
    public static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT_SIZE;
    public static final int DELAY = 75;
    public static final char LEFT = 37;
    public static final char UP = 38;
    public static final char RIGHT = 39;
    public static final char DOWN = 40;
    public static final int RED = 0;
    public static final int BLUE = 81;
    public static final int GREEN = 0;
    int Red = 0;
    int Green = 81;
    int Blue = 0;

    public static final int x[] = new int[GAME_UNITS];
    public static final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = RIGHT;
    boolean running = false;
    Timer timer;
    Random random;
    /**
     * Our constructor
     */
    public GamePanel() {
        random = new Random();
        Dimension dimension = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        MyKeyAdapter key = new MyKeyAdapter();
        this.addKeyListener(key);
        startGame();
    }

    /**
     * this method will start the game
     * @param
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {
        if(running) {
            /*
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            } */
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);



            Color color = new Color(Red, Green, Blue);
            g.setColor(color);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(color);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(color);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            Font font = new Font("Helvetica", Font.BOLD, 40);
            g.setFont(font);
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }
    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {
        for(int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (direction == UP) {
            y[0] = y[0] - UNIT_SIZE;
        } else if(direction == RIGHT) {
            x[0] = x[0] + UNIT_SIZE;
        } else if(direction == LEFT) {
            x[0] = x[0] - UNIT_SIZE;
        } else if (direction == DOWN) {
            y[0] = y[0] + UNIT_SIZE;
        }
    }

    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            Red = random.nextInt(255);
            Green = random.nextInt(255);
            Blue = random.nextInt(255);
            newApple();
        }
    }

    public void checkCollusions() {

        //Checks if head collides body
        for(int i = bodyParts; i > 0; i--) {
            if((x[0] == x[i] && y[0] == y[i])) {
                running = false;
            }
        }
        // Cheks if head touches boreder
        if(x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if(running == false) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //Score Text
        g.setColor(Color.RED);
        Font font = new Font("Helvetica", Font.BOLD, 40);
        g.setFont(font);
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        //Game Over Text
        g.setColor(Color.RED);
        Font font2 = new Font("Helvetica", Font.BOLD, 62);
        g.setFont(font2);
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("You are suck", (SCREEN_WIDTH - metrics2.stringWidth("You are suck"))/2, SCREEN_HEIGHT / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkApple();
            checkCollusions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {


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
}


