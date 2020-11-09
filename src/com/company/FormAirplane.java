package com.company;

import javax.swing.*;
import java.awt.*;

public class FormAirplane {

    private final JFrame frame;
    private DrawWindowAirplane drawWindowAirplane;
    private final JComboBox<String> listOfCount;
    private final JComboBox<String> listOfAdditions;

    public FormAirplane() {
        frame = new JFrame("Аэроплан");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        Icon up = new ImageIcon("IconButton/strUp.png");
        Icon down = new ImageIcon("IconButton/strDown.png");
        Icon left = new ImageIcon("IconButton/strLeft.png");
        Icon right = new ImageIcon("IconButton/strRight.png");

        JButton buttonCreateAirplane = new JButton("Airplane");
        JButton buttonCreateAirbus = new JButton("Airbus");
        JButton buttonUp = new JButton(up);
        buttonUp.setName("Up");
        JButton buttonDown = new JButton(down);
        buttonDown.setName("Down");
        JButton buttonLeft = new JButton(left);
        buttonLeft.setName("Left");
        JButton buttonRight = new JButton(right);
        buttonRight.setName("Right");

        frame.getContentPane().add(buttonCreateAirplane);
        frame.getContentPane().add(buttonCreateAirbus);
        frame.getContentPane().add(buttonUp);
        frame.getContentPane().add(buttonDown);
        frame.getContentPane().add(buttonLeft);
        frame.getContentPane().add(buttonRight);

        buttonCreateAirplane.setBounds(10, 10, 90, 30);
        buttonCreateAirbus.setBounds(110, 10, 90, 30);
        buttonUp.setBounds(805, 375, 30, 30);
        buttonDown.setBounds(805, 410, 30, 30);
        buttonLeft.setBounds(770, 410, 30, 30);
        buttonRight.setBounds(840, 410, 30, 30);

        buttonCreateAirplane.addActionListener(e -> setAirplane());
        buttonCreateAirbus.addActionListener(e -> setAirbus());
        buttonUp.addActionListener(e -> setDirection(buttonUp));
        buttonDown.addActionListener(e -> setDirection(buttonDown));
        buttonLeft.addActionListener(e -> setDirection(buttonLeft));
        buttonRight.addActionListener(e -> setDirection(buttonRight));

        listOfAdditions = new JComboBox<>(new String[]{"Квадратный", "Круглый", "Полукруглый"});
        frame.getContentPane().add(listOfAdditions);
        listOfAdditions.setBounds(110, 45, 90, 30);

        listOfCount = new JComboBox<>(new String[]{"10 Иллюминаторов", "20 Иллюминаторов", "30 Иллюминаторов"});
        frame.getContentPane().add(listOfCount);
        listOfCount.setBounds(10, 45, 90, 30);

        drawWindowAirplane = new DrawWindowAirplane();
        frame.getContentPane().add(drawWindowAirplane);
        drawWindowAirplane.setBounds(0, 0, 1000, 500);
        frame.repaint();
    }

    public void addDrawWindow(DrawWindowAirplane panel) {
        drawWindowAirplane = panel;
        frame.getContentPane().add(drawWindowAirplane);
        drawWindowAirplane.setBounds(0, 0, 1000, 500);
        frame.repaint();
    }

    private void setDirection(JButton button) {
        try {
            String name = button.getName();
            switch (name) {
                case "Up" -> drawWindowAirplane.getTransport().MoveTransport(Direction.Up);
                case "Down" -> drawWindowAirplane.getTransport().MoveTransport(Direction.Down);
                case "Left" -> drawWindowAirplane.getTransport().MoveTransport(Direction.Left);
                case "Right" -> drawWindowAirplane.getTransport().MoveTransport(Direction.Right);
            }
            frame.repaint();
        } catch (Exception ignored) {

        }
    }

    private void setAirplane() {
        drawWindowAirplane.setTransport(new Airplane(225, 1500, Color.LIGHT_GRAY));
        drawWindowAirplane.getTransport().setPosition((int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 100),
                1000, 470);
        frame.repaint();
    }

    private void setAirbus() {
        drawWindowAirplane.setTransport(new Airbus(225, 1500, Color.LIGHT_GRAY, Color.RED,
                true, true,
                true, true, listOfAdditions.getSelectedIndex(), listOfCount.getSelectedIndex()));
        drawWindowAirplane.getTransport().setPosition((int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 100),
                1000, 470);
        frame.repaint();
    }

    public void setAirTransport(AirTransport airTransport) {
        airTransport.setPosition((int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 100),
                1000, 470);
        drawWindowAirplane.setTransport(airTransport);
        frame.repaint();
    }
}