package org.example;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    private Random random;
    private int yDirection;
    private int xDirection;
    private int[] directions = {-1, 1};

    public Ball(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        random = new Random();
        setXDirection(directions[random.nextInt(2)] * speed);
        setYDirection(directions[random.nextInt(2)] * speed);
        System.out.println(speed);
    }

    public void setXDirection(int randomXDirection) {
        xDirection = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yDirection = randomYDirection;
    }

    public void move() {
        x += xDirection;
        y += yDirection;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width);
    }

    public int getyDirection() {
        return yDirection;
    }

    public int getxDirection() {
        return xDirection;
    }
}
