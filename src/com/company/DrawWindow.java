package com.company;

import javax.swing.*;
;import java.awt.*;

public class DrawWindow extends JPanel {

    private Airbus air;

    public void paintComponent(Graphics g) {
        if (air != null) air.DrawTransport(g);
    }

    public void setAir(Airbus air) {
        this.air = air;
    }

    public Airbus getAir() {
        return air;
    }
}
