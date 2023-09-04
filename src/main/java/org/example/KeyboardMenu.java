package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class KeyboardMenu extends JPanel {
    public KeyboardMenu(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        this.setPreferredSize(Main.SCREEN_DIMENSION);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        JLabel label = new JLabel("KEYBOARD", SwingConstants.CENTER);
        label.setFont(MenuPanel.TITLE_FONT);
        label.setPreferredSize(new Dimension(600, 100));
        label.setForeground(Color.WHITE);

        JButton bReturn = new JButton();
        bReturn.setFocusPainted(false);
        bReturn.addActionListener(e -> Main.getFrame().createMenuPanel());
        bReturn.setFont(MenuPanel.CONTROL_FONT);
        bReturn.setForeground(Color.WHITE);
        bReturn.setPreferredSize(new Dimension(70, 70));
        bReturn.setOpaque(true);
        bReturn.setBorderPainted(false);
        try {
            String path = "src/main/resources/return.jpg";
            Image img = ImageIO.read(new File(path));
            bReturn.setIcon(new ImageIcon(img.getScaledInstance(70, 70, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(label);
        this.add(bReturn);
    }
}
