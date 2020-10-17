package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawWindow extends JPanel {

    private Airbus airbus;

    public void paintComponent(Graphics g) {
        if (airbus != null) airbus.DrawTransport(g);
    }

    public void setAirbus(Airbus airbus) {
        this.airbus = airbus;
    }

    public Airbus getAirbus() {
        return airbus;
    }
}
