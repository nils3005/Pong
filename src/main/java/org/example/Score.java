package org.example;

import java.awt.*;

public class Score extends Rectangle {
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private int pointsP1;
    private int pointsP2;

    public Score(int gameWidth, int gameHeight) {
        GAME_HEIGHT = gameHeight;
        GAME_WIDTH = gameWidth;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 60));
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawString(String.valueOf(pointsP1 / 10) + pointsP1 % 10, (GAME_WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(pointsP2 / 10) + pointsP2 % 10, (GAME_WIDTH / 2) + 20, 50);
    }

    public void player1Scored() {
        pointsP1++;
    }

    public void player2Scored() {
        pointsP2++;
    }
}
