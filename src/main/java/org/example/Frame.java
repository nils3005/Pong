package org.example;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private KeyboardMenu keyboardMenu;

    public Frame() {
        createMenuPanel();
        setDefaultJFrame();
    }

    public void createMenuPanel() {
        if (keyboardMenu != null){
            this.remove(keyboardMenu);
            keyboardMenu = null;
        }
        menuPanel = new MenuPanel();
        this.add(menuPanel);
        this.pack();
        menuPanel.requestFocus();
        menuPanel.repaint();
        menuPanel.revalidate();
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

    public void createKeyboardPanel(){
        this.remove(menuPanel);
        keyboardMenu = new KeyboardMenu();
        this.add(keyboardMenu);
        keyboardMenu.requestFocus();
        keyboardMenu.repaint();
        keyboardMenu.revalidate();
    }

    private void setDefaultJFrame(){
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
