package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormAerodrome {

    private JFrame frame;
    private Aerodrome<Airplane, Additions> aerodrome;
    private JTextField fieldTakeIndex;

    public void iniz() {
        frame = new JFrame("Аэродром");
        frame.setSize(820, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        aerodrome = new Aerodrome<>(650, 450);

        DrawWindowAerodrome drawWindowAerodrome = new DrawWindowAerodrome(aerodrome);

        JButton buttonCreateAirplane = new JButton("Airplane");
        JButton buttonCreateAirbus = new JButton("Airbus");

        JPanel groupboxTakeIt = new JPanel();
        Border mainBorder = BorderFactory.createTitledBorder("Take Airtransport");
        groupboxTakeIt.setBorder(mainBorder);
        JLabel labelPlaceNumber = new JLabel("Place Number");
        fieldTakeIndex = new JFormattedTextField();
        JButton buttonTakeAirplane = new JButton("Take");

        frame.getContentPane().add(buttonCreateAirbus);
        frame.getContentPane().add(buttonCreateAirplane);
        groupboxTakeIt.add(labelPlaceNumber);
        groupboxTakeIt.add(fieldTakeIndex);
        groupboxTakeIt.add(buttonTakeAirplane);
        frame.getContentPane().add(groupboxTakeIt);
        frame.getContentPane().add(drawWindowAerodrome);

        drawWindowAerodrome.setBounds(0, 0, 650, 450);
        buttonCreateAirplane.setBounds(570, 20, 200, 30);
        buttonCreateAirbus.setBounds(570, 60, 200, 30);

        groupboxTakeIt.setBounds(570, 120, 200, 100);
        labelPlaceNumber.setBounds(20, 20, 85, 30);
        fieldTakeIndex.setBounds(105, 20, 75, 30);
        buttonTakeAirplane.setBounds(20, 60, 160, 30);

        buttonCreateAirplane.addActionListener(e -> setAirplane());
        buttonCreateAirbus.addActionListener(e -> setAirbus());
        buttonTakeAirplane.addActionListener(e -> takeAirplane());

        frame.repaint();
    }

    private void setAirplane() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);

        if (colorDialog.getColor() != null) {
            Airplane airplane = new Airplane(225, 1500, colorDialog.getColor());
            if (aerodrome.plus(airplane)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Aerodrome is overflow");
            }
        }
    }

    private void setAirbus() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        Color tempColor = colorDialog.getColor();

        if (colorDialog.getColor() != null) {
            JOptionPane.showMessageDialog(frame, colorDialog);

            if (colorDialog.getColor() != null) {
                Airplane airplane = new Airbus(225, 1500, tempColor,
                        colorDialog.getColor(), true, true, true, true,
                        0, 0);
                if (aerodrome.plus(airplane)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Aerodrome is overflow");
                }
            }
        }
    }

    public void takeAirplane() {
        if (!fieldTakeIndex.getText().equals("")) {
            try {
                Airplane airplane = aerodrome.minus(Integer.parseInt(fieldTakeIndex.getText()));

                if (airplane != null) {
                    FormAirplane formAirplane = new FormAirplane();
                    formAirplane.setAirTransport(airplane);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "This index does not exist");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "This index does not exist");
            }
        }
    }
}
