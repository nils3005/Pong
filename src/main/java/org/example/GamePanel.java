package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {
    private static final int PANEL_WIDTH = 1000;
    private static final int PANEL_HEIGHT = (int) (PANEL_WIDTH * (5.0 / 9.0));
    private static final Dimension SCREEN_DIMENSION = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
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

    public GamePanel(boolean isSound, int speed) {
        this.speed = speed;
        newPaddles();
        newBall();
        if (isSound) sound = new Sound();
        score = new Score(PANEL_WIDTH, PANEL_HEIGHT);
        this.setFocusable(true); //Wenn KeyEvent, dann Fokus
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_DIMENSION);
        thread = new Thread(this);
        thread.start();
    }

    public void newBall() {
        ball = new Ball((PANEL_WIDTH / 2) - (BALL_DIAMETER / 2)
                , (PANEL_HEIGHT / 2) - (BALL_DIAMETER / 2)
                , BALL_DIAMETER, BALL_DIAMETER, speed);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (PANEL_HEIGHT / 2) - (PADDLE_HEIGHT / 2)
                , PADDLE_WIDTH, PADDLE_HEIGHT, 1);

        paddle2 = new Paddle(PANEL_WIDTH - PADDLE_WIDTH
                , (PANEL_HEIGHT / 2) - (PADDLE_HEIGHT / 2)
                , PADDLE_WIDTH, PADDLE_HEIGHT, 2);
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

    public void move() {
        if (p1Up) paddle1.moveUp();
        if (p1Down) paddle1.moveDown();
        if (p2Up) paddle2.moveUp();
        if (p2Down) paddle2.moveDown();
        ball.move();
    }

    public void checkCollision() {
        //Ball
        if (ball.y <= 0 || ball.y >= (PANEL_HEIGHT - BALL_DIAMETER)) ball.setYDirection(-ball.getyDirection());
        if (ball.intersects(paddle1) || ball.intersects(paddle2)){
            ball.setXDirection(-ball.getxDirection());
            if (sound != null){
                playSoundEffect();
            }
        }
        if (ball.x <= 0 || ball.x >= (PANEL_WIDTH - BALL_DIAMETER)) scored();

        //Paddles
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= (PANEL_HEIGHT - PADDLE_HEIGHT)) paddle1.y = PANEL_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= (PANEL_HEIGHT - PADDLE_HEIGHT)) paddle2.y = PANEL_HEIGHT - PADDLE_HEIGHT;
    }

    private void playSoundEffect(){
        sound.setFile();
        sound.play();
    }

    private void scored() {
        if (ball.x <= 0) {
            score.player2Scored();
        } else {
            score.player1Scored();
        }
        newBall();
    }

    public void run() {
        //28 min
        long lastTime = System.currentTimeMillis();
        double ticks = 60.0;
        double ms = 1000 / ticks;
        double delta = 0;
        while (true) {
            long now = System.currentTimeMillis();
            delta += (now - lastTime) / ms;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) p1Up = true;
            if (e.getKeyCode() == KeyEvent.VK_S) p1Down = true;
            if (e.getKeyCode() == KeyEvent.VK_PLUS) p2Up = true;
            if (e.getKeyCode() == KeyEvent.VK_NUMBER_SIGN) p2Down = true;
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) p1Up = false;
            if (e.getKeyCode() == KeyEvent.VK_S) p1Down = false;
            if (e.getKeyCode() == KeyEvent.VK_PLUS) p2Up = false;
            if (e.getKeyCode() == KeyEvent.VK_NUMBER_SIGN) p2Down = false;
        }
    }
}
