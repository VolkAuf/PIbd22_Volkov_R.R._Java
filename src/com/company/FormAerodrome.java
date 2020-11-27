package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class FormAerodrome {

    private final JFrame frame;
    private final LinkedList<Airplane> airplaneLinkedList;
    private final AerodromeCollection aerodromeCollection;
    private final DefaultListModel<String> aerodromeList;
    private final JList<String> listBoxAerodrome;
    private final JTextField fieldAerodromeName;
    private final JTextField fieldTakeIndex;
    private final DrawWindowAerodrome drawWindowAerodrome;

    public FormAerodrome() {

        airplaneLinkedList = new LinkedList<>();
        frame = new JFrame("Аэродром");
        frame.setSize(820, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        aerodromeCollection = new AerodromeCollection(650, 550);

        drawWindowAerodrome = new DrawWindowAerodrome(aerodromeCollection);

        JButton buttonCreateAirplane = new JButton("Airplane");
        JButton buttonCreateAirbus = new JButton("Airbus");

        JPanel groupboxTakeIt = new JPanel();
        Border mainBorder = BorderFactory.createTitledBorder("Take AirTransport");
        groupboxTakeIt.setBorder(mainBorder);
        JLabel labelPlaceNumber = new JLabel("Place Number!");
        fieldTakeIndex = new JFormattedTextField();
        JButton buttonTakeInLinkedList = new JButton("Take in list");
        JButton buttonGetFromLinkedList = new JButton("Get from List");

        JPanel groupboxAerodrome = new JPanel();
        mainBorder = BorderFactory.createTitledBorder("Aerodrome");
        groupboxAerodrome.setBorder(mainBorder);
        JLabel labelAerodromeName = new JLabel("Aerodrome name");
        aerodromeList = new DefaultListModel<>();
        listBoxAerodrome = new JList<>(aerodromeList);
        fieldAerodromeName = new JFormattedTextField();
        JButton buttonCreateAerodrome = new JButton("Create aerodrome");
        JButton buttonDeleteAerodrome = new JButton("Delete aerodrome");

        frame.getContentPane().add(buttonCreateAirbus);
        frame.getContentPane().add(buttonCreateAirplane);
        frame.getContentPane().add(buttonTakeInLinkedList);
        frame.getContentPane().add(drawWindowAerodrome);

        groupboxTakeIt.add(fieldTakeIndex);
        groupboxTakeIt.add(buttonTakeInLinkedList);
        groupboxTakeIt.add(buttonGetFromLinkedList);
        groupboxTakeIt.add(labelPlaceNumber);
        groupboxTakeIt.add(fieldTakeIndex);
        frame.getContentPane().add(groupboxTakeIt);

        groupboxAerodrome.add(labelAerodromeName);
        groupboxAerodrome.add(listBoxAerodrome);
        groupboxAerodrome.add(fieldAerodromeName);
        groupboxAerodrome.add(buttonCreateAerodrome);
        groupboxAerodrome.add(buttonDeleteAerodrome);
        frame.getContentPane().add(groupboxAerodrome);

        drawWindowAerodrome.setBounds(0, 0, 650, 550);
        buttonCreateAirplane.setBounds(570, 320, 200, 30);
        buttonCreateAirbus.setBounds(570, 360, 200, 30);

        groupboxTakeIt.setBounds(570, 400, 200, 150);
        groupboxTakeIt.setLayout(null);
        labelPlaceNumber.setBounds(20, 20, 85, 30);
        fieldTakeIndex.setBounds(105, 20, 75, 30);
        buttonTakeInLinkedList.setBounds(20, 60, 160, 30);
        buttonGetFromLinkedList.setBounds(20, 95, 160, 30);

        groupboxAerodrome.setBounds(570, 5, 200, 300);
        groupboxAerodrome.setLayout(null);
        labelAerodromeName.setBounds(20, 20, 85, 30);
        fieldAerodromeName.setBounds(105, 20, 75, 30);
        buttonCreateAerodrome.setBounds(20, 60, 160, 30);
        buttonDeleteAerodrome.setBounds(20, 95, 160, 30);
        listBoxAerodrome.setBounds(20, 140, 160, 150);

        buttonCreateAirplane.addActionListener(e -> setAirplane());
        buttonCreateAirbus.addActionListener(e -> setAirbus());
        buttonTakeInLinkedList.addActionListener(e -> takeAirplane());
        buttonGetFromLinkedList.addActionListener(e -> moveToFrame());
        buttonCreateAerodrome.addActionListener(e -> addAerodrome());
        buttonDeleteAerodrome.addActionListener(e -> deleteAerodrome());
        listBoxAerodrome.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void setAirplane() {
        if (listBoxAerodrome.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                Airplane airplane = new Airplane(225, 1500, colorDialog.getColor());
                if (aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).plus(airplane)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Aerodrome is overflow");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select aerodrome!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setAirbus() {
        if (listBoxAerodrome.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            Color tempColor = colorDialog.getColor();
            if (colorDialog.getColor() != null) {
                JOptionPane.showMessageDialog(frame, colorDialog);
                if (colorDialog.getColor() != null) {
                    Airplane airplane = new Airbus(225, 1500, tempColor,
                            colorDialog.getColor(), true, true, true, true,
                            0, 0);
                    if (aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).plus(airplane)) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Aerodrome is overflow");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selected aerodrome", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void takeAirplane() {
        if (listBoxAerodrome.getSelectedIndex() >= 0) {
            if (!fieldTakeIndex.getText().equals("")) {
                try {
                    Airplane airplane = aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).
                            minus(Integer.parseInt(fieldTakeIndex.getText()));
                    if (airplane != null) {
                        airplaneLinkedList.add(airplane);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "This index does not exist");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "This index does not exist");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selected aerodrome!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveToFrame() {
        if (!airplaneLinkedList.isEmpty()) {
            FormAirplane formAirplane = new FormAirplane();
            formAirplane.setAirTransport(Objects.requireNonNull(airplaneLinkedList.poll()));
            frame.repaint();
        }
    }

    private void reloadLevels() {
        int index = listBoxAerodrome.getSelectedIndex();

        aerodromeList.removeAllElements();
        int i = 0;
        for (String name : aerodromeCollection.keySet()) {
            aerodromeList.add(i, name);
            i++;
        }

        int itemsCount = aerodromeList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxAerodrome.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxAerodrome.setSelectedIndex(index);
        }
    }

    private void addAerodrome() {
        if (!fieldAerodromeName.getText().equals("")) {
            aerodromeCollection.addAerodrome(fieldAerodromeName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Write Aerodrome name!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAerodrome() {
        if (listBoxAerodrome.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Delete aerodrome " + listBoxAerodrome.getSelectedValue() + "?", "Deleted",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                aerodromeCollection.deleteAerodrome(listBoxAerodrome.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selected aerodrome!!!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listListener() {
        drawWindowAerodrome.setSelectedItem(listBoxAerodrome.getSelectedValue());
        frame.repaint();
    }
}