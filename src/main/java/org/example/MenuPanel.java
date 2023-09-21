package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class MenuPanel extends JPanel {
    public static final Font TITLE_FONT = new Font("Consolas", Font.BOLD, 100);
    public static final Font CONTROL_FONT = new Font("Consolas", Font.BOLD, 40);
    private boolean isSound = true;
    private static final String RESOURCES_PATH = "/";
    private static final String KEYBOARD_PATH = RESOURCES_PATH + "keyboard.jpg";
    private static final String SOUND_ON_PATH = RESOURCES_PATH + "soundOn.jpg";
    private static final String SOUND_OFF_PATH = RESOURCES_PATH + "soundOff.jpg";
    private static final String START_PATH = RESOURCES_PATH + "start.jpg";
    private int speed = 5;
    private JButton bSound;
    private Integer[] speedOptions = {3, 4, 5, 6, 7, 8, 9, 10};

    public MenuPanel() {
        this.setPreferredSize(Main.SCREEN_DIMENSION);
        this.setFocusable(true);
        createGUI();
    }

    private void createGUI() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.BLACK);
        createTitle();
        createControlPanel();
        this.repaint();
    }

    private void createControlPanel() {
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        panelControl.setBackground(Color.BLACK);
        JButton bStart = new JButton();
        bStart.setPreferredSize(new Dimension(70, 70));
        bStart.setForeground(Color.WHITE);
        bStart.setMargin(new Insets(0, 0, 0, 0));
        bStart.setOpaque(true);
        bStart.setBorderPainted(false);
        bStart.setFocusPainted(false);
        try {
            //Image img = ImageIO.read(new File(START_PATH));
            InputStream is = MenuPanel.class.getResourceAsStream(START_PATH);
            BufferedImage img = ImageIO.read(is);
            bStart.setIcon(new ImageIcon(img.getScaledInstance(70, 70, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bStart.setBackground(Color.BLACK);
        bStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bStart.addActionListener(e -> Main.getFrame().createGamePanel());
        panelControl.add(bStart);
        bSound = new JButton();
        bSound.setPreferredSize(new Dimension(70, 70));
        bSound.setMargin(new Insets(0, 0, 0, 0));
        bSound.setBorderPainted(false);
        bSound.setMargin(new Insets(0, 0, 0, 0));
        bSound.setOpaque(true);
        bSound.setFocusPainted(false);
        bSound.setBackground(Color.BLACK);
        bSound.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bSound.addActionListener(e -> controlSound());
        updateBSoundImg();
        panelControl.add(bSound);
        JComboBox<Integer> jComboBox = new JComboBox(speedOptions);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setBackground(Color.DARK_GRAY);
        jComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jComboBox.setPreferredSize(new Dimension(190, 70));
        jComboBox.setFont(CONTROL_FONT);
        jComboBox.setForeground(Color.WHITE);
        jComboBox.setRenderer(new MyComboBoxRenderer("Speed"));
        jComboBox.addActionListener(e -> speedChanged(e));
        panelControl.add(jComboBox);
        JButton bKeyboard = new JButton();
        bKeyboard.setPreferredSize(new Dimension(70, 70));
        bKeyboard.setMargin(new Insets(0, 0, 0, 0));
        bKeyboard.setBorderPainted(false);
        bKeyboard.setOpaque(true);
        bKeyboard.setFocusPainted(false);
        bKeyboard.setBackground(Color.BLACK);
        bKeyboard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bKeyboard.addActionListener(e -> Main.getFrame().createKeyboardPanel());
        try {
            InputStream is = MenuPanel.class.getResourceAsStream(KEYBOARD_PATH);
            BufferedImage img = ImageIO.read(is);
            bKeyboard.setIcon(new ImageIcon(img.getScaledInstance(70, 70, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelControl.add(bKeyboard);
        this.add(panelControl);
    }

    private void speedChanged(ActionEvent e) {
        JComboBox<Integer> temp = (JComboBox<Integer>) e.getSource();
        speed = speedOptions[temp.getSelectedIndex()];
    }

    private void updateBSoundImg() {
        try {
            String path = SOUND_OFF_PATH;
            if (isSound) path = SOUND_ON_PATH;
            InputStream is = MenuPanel.class.getResourceAsStream(path);
            BufferedImage img = ImageIO.read(is);
            bSound.setIcon(new ImageIcon(img.getScaledInstance(70, 70, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
        this.revalidate();
    }

    private void createTitle() {
        JLabel title = new JLabel("PONG", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension((Main.SCREEN_DIMENSION.width), 200));
        title.setFont(TITLE_FONT);
        title.setForeground(Color.WHITE);
        this.add(title);
    }

    private void controlSound() {
        isSound = !isSound;
        updateBSoundImg();
    }

    public boolean isSound() {
        return isSound;
    }

    public int getSpeed() {
        return speed;
    }

    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        private String _title;

        public MyComboBoxRenderer(String title) {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}
