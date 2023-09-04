package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuPanel extends JPanel {
    private static final int PANEL_WIDTH = 1000;
    private static final int PANEL_HEIGHT = (int) (PANEL_WIDTH * (5.0 / 9.0));
    private static final Dimension SCREEN_DIMENSION = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private static final Font titleFont = new Font("Consolas", Font.BOLD, 100);
    private static final Font controlFont = new Font("Consolas", Font.BOLD, 60);
    private boolean isSound = true;
    private int speed = 5;
    private JButton bSound;
    private Integer[] speedOptions = {3, 4, 5, 6, 7, 8, 9, 10};

    public MenuPanel() {
        this.setPreferredSize(SCREEN_DIMENSION);
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
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panelControl.setBackground(Color.BLACK);
        JButton bStart = new JButton("START");
        bStart.setPreferredSize(new Dimension(200, 80));
        bStart.setFont(controlFont);
        bStart.setForeground(Color.WHITE);
        bStart.setMargin(new Insets(20, 0, 0, 0));
        bStart.setOpaque(true);
        bStart.setBorderPainted(false);
        bStart.setBackground(Color.BLACK);
        bStart.addActionListener(e -> Main.getFrame().createGamePanel());
        panelControl.add(bStart);
        bSound = new JButton();
        bSound.setPreferredSize(new Dimension(70, 70));
        bSound.setMargin(new Insets(0, 0, 0, 0));
        bSound.setBorderPainted(false);
        bSound.setMargin(new Insets(0, 0, 0, 0));
        bSound.setOpaque(true);
        bSound.setFocusPainted(false);
        bSound.setBorderPainted(false);
        bSound.setBackground(Color.BLACK);
        bSound.addActionListener(e -> controlSound());
        updateBSoundImg();
        panelControl.add(bSound);

        JComboBox<Integer> jComboBox = new JComboBox(speedOptions);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setBackground(Color.DARK_GRAY);
        jComboBox.setPreferredSize(new Dimension(190, 70));
        jComboBox.setFont(controlFont);
        jComboBox.setForeground(Color.WHITE);
        jComboBox.setRenderer(new MyComboBoxRenderer("Speed"));
        jComboBox.addActionListener(e -> speedChanged(e));
        panelControl.add(jComboBox);
        this.add(panelControl);
    }

    private void speedChanged(ActionEvent e) {
        JComboBox<Integer> temp = (JComboBox<Integer>) e.getSource();
        speed = speedOptions[temp.getSelectedIndex()];
    }

    private void updateBSoundImg() {
        try {
            String path = "src/main/resources/soundOff.jpg";
            if (isSound) path = "src/main/resources/soundOn.jpg";
            Image img = ImageIO.read(new File(path));
            bSound.setIcon(new ImageIcon(img.getScaledInstance(70, 70, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
        this.revalidate();
    }

    private void createTitle() {
        JLabel title = new JLabel("PONG", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension((SCREEN_DIMENSION.width), 200));
        title.setFont(titleFont);
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

    public int getSpeed(){
        return speed;
    }

    class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}
