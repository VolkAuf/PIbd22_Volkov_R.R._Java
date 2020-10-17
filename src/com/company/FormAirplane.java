package com.company;

import javax.swing.*;
import java.awt.*;

public class FormAirplane {

    private final JFrame frame;
    private DrawWindow drawPanel;
    private final JComboBox<String> list;

    public FormAirplane() {
        frame = new JFrame("Аэроплан");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        Icon up = new ImageIcon("IconButton/strUp.png");
        Icon down = new ImageIcon("IconButton/strDown.png");
        Icon left = new ImageIcon("IconButton/strLeft.png");
        Icon right = new ImageIcon("IconButton/strRight.png");

        JButton buttonCreate = new JButton("Создать");
        JButton buttonUp = new JButton(up);
        buttonUp.setName("Up");
        JButton buttonDown = new JButton(down);
        buttonDown.setName("Down");
        JButton buttonLeft = new JButton(left);
        buttonLeft.setName("Left");
        JButton buttonRight = new JButton(right);
        buttonRight.setName("Right");

        frame.getContentPane().add(buttonCreate);
        frame.getContentPane().add(buttonUp);
        frame.getContentPane().add(buttonDown);
        frame.getContentPane().add(buttonLeft);
        frame.getContentPane().add(buttonRight);

        buttonCreate.setBounds(10, 10, 90, 30);
        buttonUp.setBounds(805, 375, 30, 30);
        buttonDown.setBounds(805, 410, 30, 30);
        buttonLeft.setBounds(770, 410, 30, 30);
        buttonRight.setBounds(840, 410, 30, 30);

        buttonCreate.addActionListener(e -> setAir());
        buttonUp.addActionListener(e -> setDirection(buttonUp));
        buttonDown.addActionListener(e -> setDirection(buttonDown));
        buttonLeft.addActionListener(e -> setDirection(buttonLeft));
        buttonRight.addActionListener(e -> setDirection(buttonRight));

        list = new JComboBox<>(new String[]{"10 Иллюминаторов", "20 Иллюминаторов", "30 Иллюминаторов"});
        frame.getContentPane().add(list);
        list.setBounds(10, 45, 90, 30);
    }

    public void addDrawWindow(DrawWindow panel) {
        drawPanel = panel;
        frame.getContentPane().add(drawPanel);
        drawPanel.setBounds(0, 0, 1000, 500);
        frame.repaint();
    }

    private void setDirection(JButton button) {
        try {
            String name = button.getName();
            switch (name) {
                case "Up" -> drawPanel.getAirbus().MoveAirTransport(Direction.Up);
                case "Down" -> drawPanel.getAirbus().MoveAirTransport(Direction.Down);
                case "Left" -> drawPanel.getAirbus().MoveAirTransport(Direction.Left);
                case "Right" -> drawPanel.getAirbus().MoveAirTransport(Direction.Right);
            }
            frame.repaint();
        } catch (Exception ignored) {

        }
    }

    private void setAir() {
        drawPanel.setAirbus(new Airbus(225, 1500, Color.LIGHT_GRAY, Color.RED,
                (list.getSelectedIndex() + 1) * 10, true, true, true,
                true, true));
        drawPanel.getAirbus().SetPosition((int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 100),
                1000, 470);
        frame.repaint();
    }
}