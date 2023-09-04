package org.example;

import java.awt.*;

public class Paddle extends Rectangle{
    private int id;
    private int yVelocity;
    private int speed = 10;

    public Paddle(int xPos, int yPos, int paddleWidth, int paddleHeight, int id){
        super(xPos, yPos, paddleWidth, paddleHeight);
        this.id = id;
    }

    public void moveUp(){
        setYDirection(-speed);
        move();
    }

    public void moveDown() {
        setYDirection(speed);
        move();
    }

    public void keyReleased(){
        setYDirection(0);
        move();
    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        y += yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
