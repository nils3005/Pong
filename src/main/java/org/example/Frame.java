package org.example;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;

    public Frame() {
        createMenuPanel();
        setDefaultJFrame();
    }

    private void createMenuPanel() {
        menuPanel = new MenuPanel();
        this.add(menuPanel);
        this.pack();
    }

    public void createGamePanel() {
        gamePanel = new GamePanel(menuPanel.isSound(), menuPanel.getSpeed());
        this.pack(); //passt das JPanel dem JFrame an
        this.add(gamePanel);
        setDefaultJFrame();
        this.remove(menuPanel);
        gamePanel.requestFocus();
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void setDefaultJFrame(){
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}
