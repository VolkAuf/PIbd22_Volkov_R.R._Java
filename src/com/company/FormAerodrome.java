package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        frame = new JFrame("Aerodrome");
        frame.setSize(820, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        aerodromeCollection = new AerodromeCollection(650, 550);

        drawWindowAerodrome = new DrawWindowAerodrome(aerodromeCollection);

        JButton buttonCreateAirTransport = new JButton("AirTransport");

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

        frame.getContentPane().add(buttonCreateAirTransport);
        frame.getContentPane().add(buttonTakeInLinkedList);
        frame.getContentPane().add(drawWindowAerodrome);

        groupboxTakeIt.add(fieldTakeIndex);
        groupboxTakeIt.add(buttonTakeInLinkedList);
        groupboxTakeIt.add(buttonGetFromLinkedList);
        groupboxTakeIt.add(labelPlaceNumber);
        frame.getContentPane().add(groupboxTakeIt);

        groupboxAerodrome.add(labelAerodromeName);
        groupboxAerodrome.add(listBoxAerodrome);
        groupboxAerodrome.add(fieldAerodromeName);
        groupboxAerodrome.add(buttonCreateAerodrome);
        groupboxAerodrome.add(buttonDeleteAerodrome);
        frame.getContentPane().add(groupboxAerodrome);

        drawWindowAerodrome.setBounds(0, 0, 650, 550);
        buttonCreateAirTransport.setBounds(570, 305, 200, 60);

        groupboxTakeIt.setBounds(570, 370, 200, 150);
        groupboxTakeIt.setLayout(null);
        labelPlaceNumber.setBounds(20, 20, 85, 30);
        fieldTakeIndex.setBounds(105, 20, 75, 30);
        buttonTakeInLinkedList.setBounds(20, 60, 160, 30);
        buttonGetFromLinkedList.setBounds(20, 95, 160, 30);

        groupboxAerodrome.setBounds(570, 0, 200, 300);
        groupboxAerodrome.setLayout(null);
        labelAerodromeName.setBounds(20, 20, 85, 30);
        fieldAerodromeName.setBounds(105, 20, 75, 30);
        buttonCreateAerodrome.setBounds(20, 60, 160, 30);
        buttonDeleteAerodrome.setBounds(20, 95, 160, 30);
        listBoxAerodrome.setBounds(20, 140, 160, 150);

        buttonCreateAirTransport.addActionListener(e -> setAirTransport());
        buttonTakeInLinkedList.addActionListener(e -> takeAirplane());
        buttonGetFromLinkedList.addActionListener(e -> moveToFrame());
        buttonCreateAerodrome.addActionListener(e -> addAerodrome());
        buttonDeleteAerodrome.addActionListener(e -> deleteAerodrome());
        listBoxAerodrome.addListSelectionListener(e -> listListener());

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileItemSave = new JMenuItem("Save");
        fileItemSave.addActionListener(e -> saveFile());
        JMenuItem fileItemLoad = new JMenuItem("Download");
        fileItemLoad.addActionListener(e -> loadFile());
        JMenu menuAerodrome = new JMenu("Aerodrome");
        JMenuItem aerodromeItemSave = new JMenuItem("Save Aerodrome");
        aerodromeItemSave.addActionListener(e -> saveAerodrome());
        JMenuItem aerodromeItemLoad = new JMenuItem("Download Aerodrome");
        aerodromeItemLoad.addActionListener(e -> loadAerodrome());
        fileMenu.add(fileItemSave);
        fileMenu.add(fileItemLoad);
        menuAerodrome.add(aerodromeItemSave);
        menuAerodrome.add(aerodromeItemLoad);
        menuBar.add(fileMenu);
        menuBar.add(menuAerodrome);

        frame.repaint();
    }

    private void setAirTransport() {
        if (listBoxAerodrome.getSelectedIndex() >= 0) {
            FormAirplaneConfig formAirplaneConfig = new FormAirplaneConfig(frame);
            Airplane airplane = formAirplaneConfig.getAirplane();

            if (airplane != null) {
                if (aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).plus(airplane)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Aerodrome is overflow");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select aerodrome!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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
                            colorDialog.getColor(), true, true, true, true);
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
            int result = JOptionPane.showConfirmDialog(frame, "Delete aerodrome "
                            + listBoxAerodrome.getSelectedValue() + "?", "Deleted",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                aerodromeCollection.deleteAerodrome(listBoxAerodrome.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selected aerodrome!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listListener() {
        drawWindowAerodrome.setSelectedItem(listBoxAerodrome.getSelectedValue());
        frame.repaint();
    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (aerodromeCollection.saveFile(fileSaveDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Save complete", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Save not complete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (aerodromeCollection.loadFile(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Load complete", "Result", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Load not complete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveAerodrome() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        if (listBoxAerodrome.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Change aerodrome", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (aerodromeCollection.saveAerodrome(fileSaveDialog.getSelectedFile().getPath(), listBoxAerodrome.getSelectedValue())) {
                JOptionPane.showMessageDialog(frame, "File saved", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "File not saved", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadAerodrome() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (aerodromeCollection.loadAerodrome(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Download complete", "Result", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Download not complete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}