package org.example;

import java.awt.*;
import java.net.URISyntaxException;

public class Main {
    private static Frame frame;
    public static final int PANEL_WIDTH = 1000;
    public static final int PANEL_HEIGHT = (int) (PANEL_WIDTH * (5.0 / 9.0));
    public static final Dimension SCREEN_DIMENSION = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);

    public static void main(String[] args) {
        frame = new Frame();
    }

    public static Frame getFrame(){
        return frame;
    }
}