package org.example;

public class Main {
    private static Frame frame;
    public static void main(String[] args) {
        frame = new Frame();
    }

    public static Frame getFrame(){
        return frame;
    }
}