package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

public class FormAerodrome {

    private final JFrame frame;
    private final LinkedList<Airplane> airplaneLinkedList;
    private final AerodromeCollection aerodromeCollection;
    private final DefaultListModel<String> aerodromeList;
    private final JList<String> listBoxAerodrome;
    private final JTextField fieldAerodromeName;
    private final JTextField fieldTakeIndex;
    private final DrawWindowAerodrome drawWindowAerodrome;
    private final Logger logger;

    public FormAerodrome() {

        airplaneLinkedList = new LinkedList<>();
        frame = new JFrame("Aerodrome");
        frame.setSize(820, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        logger = LogManager.getLogger(FormAerodrome.class);
        PropertyConfigurator.configure("src/com/company/log4j2.properties");

        aerodromeCollection = new AerodromeCollection(650, 550);

        drawWindowAerodrome = new DrawWindowAerodrome(aerodromeCollection);

        JButton buttonCreateAirTransport = new JButton("AirTransport");
        JButton buttonSort = new JButton("Сортировать");

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
        frame.getContentPane().add(buttonSort);

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
        buttonSort.setBounds(570, 260, 200, 40);

        groupboxAerodrome.setBounds(570, 0, 200, 250);
        groupboxAerodrome.setLayout(null);
        labelAerodromeName.setBounds(20, 20, 85, 30);
        fieldAerodromeName.setBounds(105, 20, 75, 30);
        buttonCreateAerodrome.setBounds(20, 60, 160, 30);
        buttonDeleteAerodrome.setBounds(20, 95, 160, 30);
        listBoxAerodrome.setBounds(20, 140, 160, 100);

        buttonCreateAirTransport.addActionListener(e -> setAirTransport());
        buttonTakeInLinkedList.addActionListener(e -> takeAirplane());
        buttonGetFromLinkedList.addActionListener(e -> moveToFrame());
        buttonCreateAerodrome.addActionListener(e -> addAerodrome());
        buttonDeleteAerodrome.addActionListener(e -> deleteAerodrome());
        listBoxAerodrome.addListSelectionListener(e -> listListener());
        buttonSort.addActionListener(e -> sort());

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
        if (listBoxAerodrome.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Aerodrome not selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            FormAirplaneConfig formAirplaneConfig = new FormAirplaneConfig(frame);
            Airplane airplane = formAirplaneConfig.getAirplane();

            if (airplane == null) {
                return;
            }
            if (aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).plus(airplane)) {
                logger.info("Airplane added from aerodrome " + listBoxAerodrome.getSelectedValue() + airplane);
                frame.repaint();
            }
        } catch (AerodromeOverflowException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Overflow", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (AerodromeAlreadyHaveException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Duplication", JOptionPane.ERROR_MESSAGE);
            logger.warn("Duplication");
        } catch (
                Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Unknown error", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
        }

    }


    public void takeAirplane() {
        if (listBoxAerodrome.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Aerodrome not selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Airplane airplane = aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).minus(Integer.parseInt(fieldTakeIndex.getText()));
            if (airplane != null) {
                airplaneLinkedList.add(airplane);
                frame.repaint();
                logger.info("from aerodrome " + listBoxAerodrome.getSelectedValue() + " take airplane " + airplane + " and put in collection");
            }
        } catch (AerodromeNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Not found", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Unknown error", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
        }
    }

    private void moveToFrame() {
        if (!airplaneLinkedList.isEmpty()) {
            FormAirplane formAirplane = new FormAirplane();
            AirTransport airplane = airplaneLinkedList.poll();
            formAirplane.setAirTransport(Objects.requireNonNull(airplane));
            logger.info("Airplane " + airplane + " take in aerodrome");
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Collection is empty", "Error", JOptionPane.ERROR_MESSAGE);
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
            logger.info("Aerodrome is added " + fieldAerodromeName.getText());
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
                logger.info("Aerodrome " + listBoxAerodrome.getSelectedValue() + " deleted");
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
        if (listBoxAerodrome.getSelectedValue() != null) {
            logger.info("Aerodrome " + listBoxAerodrome.getSelectedValue() + " is selected");
        }
        frame.repaint();
    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                aerodromeCollection.saveFile(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "File saved", "Result", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Data is saved " + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error saved", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Unknown saved", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                aerodromeCollection.loadFile(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "File load", "Result", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Date load from file " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (AerodromeOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Overflow", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Uncorrected argument", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Unknown error", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
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
            try {
                aerodromeCollection.saveAerodrome(fileSaveDialog.getSelectedFile().getPath(),listBoxAerodrome.getSelectedValue());
                JOptionPane.showMessageDialog(frame, "File saved", "Result", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Data is saved" + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error saved", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Unknown error", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadAerodrome() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                aerodromeCollection.loadAerodrome(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "File is load", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Data load from file" + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (AerodromeOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Overflow", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void sort() {
        if (listBoxAerodrome.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Aerodrome not selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        aerodromeCollection.get(listBoxAerodrome.getSelectedValue()).sort();
        frame.repaint();
        logger.info("Airplane " + listBoxAerodrome.getSelectedValue() + " is sorted");
    }
}