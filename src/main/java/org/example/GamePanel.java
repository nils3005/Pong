package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private static final long BALL_PADDLE_COLLISION_DELAY = 500;
    public static final int FPS = 60;
    private boolean p1Up, p1Down;
    private boolean p2Up, p2Down;
    private Thread thread;
    private Image image;
    private Graphics graphics;
    private Sound sound;
    private Paddle paddle1, paddle2;
    private Ball ball;
    private Score score;
    private int speed;
    private boolean isSound;
    private boolean isRunning;
    private long lastTime = System.currentTimeMillis();

    public GamePanel(boolean pIsSound, int speed) {
        this.speed = speed;
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(Main.SCREEN_DIMENSION);
        thread = new Thread(this);
        thread.start();
        isRunning = true;
        sound = new Sound();
        isSound = pIsSound;
        score = new Score(Main.PANEL_WIDTH, Main.PANEL_HEIGHT);
        newBall();
        newPaddles();
    }

    public void move() {
        if (p1Up) paddle1.moveUp();
        if (p1Down) paddle1.moveDown();
        if (p2Up) paddle2.moveUp();
        if (p2Down) paddle2.moveDown();
        ball.move();
    }

    public void checkCollision() {
        if (ball.y <= 0 || ball.y >= (Main.PANEL_HEIGHT - BALL_DIAMETER)) ball.setYDirection(-ball.getyDirection());
        if (ball.x <= 0 || ball.x >= (Main.PANEL_WIDTH - BALL_DIAMETER)) scored();
        if (System.currentTimeMillis() - lastTime >= BALL_PADDLE_COLLISION_DELAY && (ball.intersects(paddle1) || ball.intersects(paddle2))) {
            ball.setXDirection(-ball.getxDirection());
            playSoundEffect(Sound.SE.PADDLE);
            lastTime = System.currentTimeMillis();
        }
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= (Main.PANEL_HEIGHT - PADDLE_HEIGHT)) paddle1.y = Main.PANEL_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= (Main.PANEL_HEIGHT - PADDLE_HEIGHT)) paddle2.y = Main.PANEL_HEIGHT - PADDLE_HEIGHT;
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                if (isRunning){
                    move();
                    checkCollision();
                }
                repaint();
                delta--;
            }
        }
    }

    private void scored() {
        if (ball.x <= 0) {
            score.player2Scored();
        } else {
            score.player1Scored();
        }
        playSoundEffect(Sound.SE.SCORE);
        newBall();
    }

    private void playSoundEffect(Sound.SE se) {
        if (isSound) sound.play(se);
    }

    public void newBall() {
        ball = new Ball((Main.PANEL_WIDTH / 2) - (BALL_DIAMETER / 2)
                , (Main.PANEL_HEIGHT / 2) - (BALL_DIAMETER / 2)
                , BALL_DIAMETER, BALL_DIAMETER, speed);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (Main.PANEL_HEIGHT / 2) - (PADDLE_HEIGHT / 2)
                , PADDLE_WIDTH, PADDLE_HEIGHT);

        paddle2 = new Paddle(Main.PANEL_WIDTH - PADDLE_WIDTH
                , (Main.PANEL_HEIGHT / 2) - (PADDLE_HEIGHT / 2)
                , PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) p1Up = true;
            if (e.getKeyCode() == KeyEvent.VK_S) p1Down = true;
            if (e.getKeyCode() == KeyEvent.VK_PLUS) p2Up = true;
            if (e.getKeyCode() == KeyEvent.VK_NUMBER_SIGN) p2Down = true;
            if (e.getKeyCode() == KeyEvent.VK_SPACE) isRunning = !isRunning;
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) p1Up = false;
            if (e.getKeyCode() == KeyEvent.VK_S) p1Down = false;
            if (e.getKeyCode() == KeyEvent.VK_PLUS) p2Up = false;
            if (e.getKeyCode() == KeyEvent.VK_NUMBER_SIGN) p2Down = false;
        }
    }
}
