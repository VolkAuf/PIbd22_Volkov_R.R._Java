package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FormAirplaneConfig extends JDialog {
    private Airplane airplane;
    private Color colorAirplane;
    private Additions additionsAirplane;
    private boolean acceptCheck;
    private final DrawWindowAirplane drawWindowAirplane;

    public FormAirplaneConfig(Frame owner) {
        super(owner, true);
        setSize(800, 500);
        setLayout(null);
        setTitle("Airplane config");
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTypes = new JPanel();
        panelTypes.setLayout(null);
        Border centerBorder = BorderFactory.createTitledBorder("Type of AirTransport");
        panelTypes.setBorder(centerBorder);

        JLabel labelAirplane = new JLabel("Airplane");
        labelAirplane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAirplane.setBounds(10, 15, 190, 85);
        labelAirplane.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelAirbus = new JLabel("Airbus");
        labelAirbus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAirbus.setBounds(10, 115, 190, 85);
        labelAirbus.setHorizontalAlignment(SwingConstants.CENTER);

        JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(80, 80, 120, 10));
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(900, 900, 1100, 30));
        JLabel labelMaxSpeed = new JLabel("Max speed");
        JLabel labelWight = new JLabel("Wight");
        JCheckBox checkBoxSecondFloor = new JCheckBox("Second Floor", true);
        JCheckBox checkBoxSideTurbine = new JCheckBox("Side Turbine", true);
        JCheckBox checkBoxBackTurbine = new JCheckBox("Back Turbine", true);
        JCheckBox checkBoxRegTail = new JCheckBox("Regular Tail", true);
        JCheckBox checkBoxMarketLine = new JCheckBox("Market Line", true);
        JCheckBox checkBoxIlluminate = new JCheckBox("Illuminate", true);

        MouseAdapter listenerType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                switch (label.getText()) {
                    case "Airplane" -> airplane = new Airplane((Integer) spinnerMaxSpeed.getValue(),
                            (Integer) spinnerWeight.getValue(), Color.LIGHT_GRAY);
                    case "Airbus" -> airplane = new Airbus((Integer) spinnerMaxSpeed.getValue(),
                            (Integer) spinnerWeight.getValue(), Color.LIGHT_GRAY, Color.DARK_GRAY,
                            checkBoxBackTurbine.isSelected(), checkBoxSideTurbine.isSelected(),
                            checkBoxMarketLine.isSelected(), checkBoxRegTail.isSelected());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + panelTypes.getX() >= drawWindowAirplane.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + panelTypes.getX() <= drawWindowAirplane.getX()
                                + drawWindowAirplane.getWidth() && e.getY() + ((JComponent) e.getSource()).getY() +
                        panelTypes.getY() >= drawWindowAirplane.getY() && e.getY() + ((JComponent) e.getSource()).getY()
                        + panelTypes.getY() <= drawWindowAirplane.getY() + drawWindowAirplane.getHeight()) {
                    airplane.setPosition(30, 30, drawWindowAirplane.getWidth(), drawWindowAirplane.getHeight());
                    drawWindowAirplane.setTransport(airplane);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                airplane = null;
            }
        };


        labelAirplane.addMouseListener(listenerType);
        labelAirbus.addMouseListener(listenerType);
        panelTypes.add(labelAirplane);
        panelTypes.add(labelAirbus);

        add(panelTypes);
        panelTypes.setBounds(10, 5, 210, 210);

        //Панель отрисовки
        drawWindowAirplane = new DrawWindowAirplane();
        getContentPane().add(drawWindowAirplane);
        drawWindowAirplane.setBounds(240, 15, 275, 200);
        drawWindowAirplane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        MouseListener listenerView = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (airplane != null || (drawWindowAirplane.getTransport() instanceof Airbus && additionsAirplane != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (airplane != null || additionsAirplane != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };
        drawWindowAirplane.addMouseListener(listenerView);

        JPanel panelSettings = new JPanel();
        panelSettings.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Settings");
        panelSettings.setBorder(centerBorder);

        panelSettings.add(spinnerMaxSpeed);
        panelSettings.add(spinnerWeight);
        panelSettings.add(labelMaxSpeed);
        panelSettings.add(labelWight);
        panelSettings.add(checkBoxBackTurbine);
        panelSettings.add(checkBoxSideTurbine);
        panelSettings.add(checkBoxMarketLine);
        panelSettings.add(checkBoxRegTail);

        add(panelSettings);
        panelSettings.setBounds(10, 220, 120, 205);

        labelMaxSpeed.setBounds(10, 20, 100, 15);
        spinnerMaxSpeed.setBounds(10, 35, 100, 25);
        labelWight.setBounds(10, 60, 100, 15);
        spinnerWeight.setBounds(10, 75, 100, 25);
        checkBoxBackTurbine.setBounds(5, 105, 110, 20);
        checkBoxSideTurbine.setBounds(5, 130, 110, 20);
        checkBoxMarketLine.setBounds(5, 155, 110, 20);
        checkBoxRegTail.setBounds(5, 180, 110, 20);


        JPanel panelColors = new JPanel();
        panelColors.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Color");
        panelColors.setBorder(centerBorder);

        JLabel labelMainColor = new JLabel("Main color");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(10, 105, 95, 70);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDopColor = new JLabel("Dop color");
        labelDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelDopColor.setBounds(120, 105, 95, 70);
        labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                colorAirplane = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawWindowAirplane.getTransport() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        drawWindowAirplane.getTransport().setMainColor(colorAirplane);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelDopColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelDopColor.getX() + labelDopColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelDopColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelDopColor.getY() + labelDopColor.getHeight() &&
                            drawWindowAirplane.getTransport() instanceof Airbus) {
                        Airbus airbus = (Airbus) drawWindowAirplane.getTransport();
                        airbus.setDopColor(colorAirplane);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                colorAirplane = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Main color" -> {
                        if (drawWindowAirplane.getTransport() != null && colorAirplane != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    case "Dop color" -> {
                        if (drawWindowAirplane.getTransport() instanceof Airbus && colorAirplane != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (colorAirplane != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelDopColor.addMouseListener(listenerColorLabel);

        JPanel panelRed = new JPanel();
        panelRed.setBackground(new Color(255, 0, 51));
        panelRed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelRed.setBounds(10, 15, 40, 40);
        panelRed.addMouseListener(listenerColor);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(new Color(102, 204, 0));
        panelGreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGreen.setBounds(65, 15, 40, 40);
        panelGreen.addMouseListener(listenerColor);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(new Color(51, 102, 204));
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setBounds(120, 15, 40, 40);
        panelBlue.addMouseListener(listenerColor);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelWhite.setBounds(175, 15, 40, 40);
        panelWhite.addMouseListener(listenerColor);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(new Color(44, 44, 44));
        panelBlack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlack.setBounds(10, 60, 40, 40);
        panelBlack.addMouseListener(listenerColor);

        JPanel panelGray = new JPanel();
        panelGray.setBackground(new Color(204, 204, 204));
        panelGray.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGray.setBounds(65, 60, 40, 40);
        panelGray.addMouseListener(listenerColor);

        JPanel panelPurple = new JPanel();
        panelPurple.setBackground(new Color(255, 0, 153));
        panelPurple.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPurple.setBounds(120, 60, 40, 40);
        panelPurple.addMouseListener(listenerColor);

        JPanel panelPink = new JPanel();
        panelPink.setBackground(new Color(255, 102, 102));
        panelPink.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPink.setBounds(175, 60, 40, 40);
        panelPink.addMouseListener(listenerColor);

        panelColors.setBounds(540, 5, 225, 210);
        add(panelColors);
        panelColors.add(labelMainColor);
        panelColors.add(labelDopColor);
        panelColors.add(panelBlack);
        panelColors.add(panelBlue);
        panelColors.add(panelGray);
        panelColors.add(panelGreen);
        panelColors.add(panelPink);
        panelColors.add(panelPurple);
        panelColors.add(panelRed);
        panelColors.add(panelWhite);


        JPanel panelAdditions = new JPanel();
        panelAdditions.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Additions");
        panelAdditions.setBorder(centerBorder);

        add(panelAdditions);
        panelAdditions.setBounds(240, 220, 275, 140);

        JLabel labelSquare = new JLabel("Square");
        labelSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelSquare.setBounds(10, 20, 80, 80);
        labelSquare.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelCircle = new JLabel("Circle");
        labelCircle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelCircle.setBounds(97, 20, 80, 80);
        labelCircle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelArc = new JLabel("Arc");
        labelArc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelArc.setBounds(184, 20, 80, 80);
        labelArc.setHorizontalAlignment(SwingConstants.CENTER);

        panelAdditions.add(labelSquare);
        panelAdditions.add(labelCircle);
        panelAdditions.add(labelArc);

        JSpinner spinnerIlluminateCount = new JSpinner(new SpinnerNumberModel(10, 10, 30, 10));
        JLabel labelIlluminateCount = new JLabel("Illuminate count");

        labelIlluminateCount.setBounds(10, 110, 100, 15);
        spinnerIlluminateCount.setBounds(110, 105, 50, 25);

        panelAdditions.add(labelIlluminateCount);
        panelAdditions.add(spinnerIlluminateCount);

        MouseAdapter listenerAdditions = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel labelAdditions = (JLabel) e.getSource();
                switch (labelAdditions.getText()) {
                    case "Square" -> additionsAirplane =
                            new SquareIlluminate(((Integer) spinnerIlluminateCount.getValue() / 10) - 1);
                    case "Circle" -> additionsAirplane =
                            new CircleIlluminate(((Integer) spinnerIlluminateCount.getValue() / 10) - 1);
                    case "Arc" -> additionsAirplane =
                            new ArcIlluminate(((Integer) spinnerIlluminateCount.getValue() / 10) - 1);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawWindowAirplane.getTransport() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelAdditions.getX()
                            >= drawWindowAirplane.getX() && e.getX() + ((JComponent) e.getSource()).getAlignmentX()
                            + panelAdditions.getX() <= drawWindowAirplane.getX() + drawWindowAirplane.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAdditions.getY()
                                    >= drawWindowAirplane.getY() && e.getY() + ((JComponent) e.getSource()).getY()
                            + panelAdditions.getY() <= drawWindowAirplane.getY() + drawWindowAirplane.getHeight() &&
                            drawWindowAirplane.getTransport() instanceof Airbus) {
                        Airbus airbus = (Airbus) drawWindowAirplane.getTransport();
                        airbus.setAdditions(additionsAirplane);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                additionsAirplane = null;
            }
        };

        labelSquare.addMouseListener(listenerAdditions);
        labelCircle.addMouseListener(listenerAdditions);
        labelArc.addMouseListener(listenerAdditions);

        JButton buttonAdd = new JButton("Add");
        JButton buttonClear = new JButton("Cancel");
        add(buttonAdd);
        add(buttonClear);
        buttonAdd.setBounds(540, 230, 110, 80);
        buttonClear.setBounds(655, 230, 110, 80);
        buttonAdd.addActionListener(e -> {
            acceptCheck = true;
            dispose();
        });
        buttonClear.addActionListener(e -> dispose());
        setVisible(true);
    }

    public Airplane getAirplane() {
        if (acceptCheck) {
            return (Airplane) drawWindowAirplane.getTransport();
        } else {
            return null;
        }
    }

}
