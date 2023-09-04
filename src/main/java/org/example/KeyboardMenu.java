package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;

public class KeyboardMenu extends JPanel {
    public KeyboardMenu() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));
        this.setPreferredSize(Main.SCREEN_DIMENSION);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);

        JLabel label = new JLabel("KEYBOARD", SwingConstants.CENTER);
        label.setFont(MenuPanel.TITLE_FONT);
        label.setPreferredSize(new Dimension(600, 100));
        label.setForeground(Color.WHITE);

        String[] cols = {"KEY", "FUNCTION"};
        String[][] data = {
                {"W", "MOVES LEFT PADDLE UP"},
                {"S", "MOVES LEFT PADDLE DOWN"},
                {"+", "MOVES RIGHT PADDLE UP"},
                {"#", "MOVES RIGHT PADDLE DOWN"},
                {"SPACE", "PAUSE / RESUME"}
        };
        JTable jTable = new JTable(data, cols);
        jTable.getColumnModel().getColumn(0).setMinWidth(150);
        jTable.getColumnModel().getColumn(0).setMaxWidth(200);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(900, 300));
        jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.DARK_GRAY;
            }
        });

        jTable.setForeground(Color.WHITE);
        jTable.setRowHeight(70);
        jTable.getTableHeader().setBackground(Color.BLACK);
        jTable.getTableHeader().setForeground(Color.WHITE);
        jTable.setFont(MenuPanel.CONTROL_FONT);
        jTable.setBackground(Color.BLACK);
        jTable.setOpaque(true);

        JButton bReturn = new JButton();
        bReturn.setFocusPainted(false);
        bReturn.addActionListener(e -> Main.getFrame().createMenuPanel());
        bReturn.setFont(MenuPanel.CONTROL_FONT);
        bReturn.setForeground(Color.WHITE);
        bReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        this.add(jScrollPane);
        this.add(bReturn);
    }
}
