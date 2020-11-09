package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawWindowAerodrome extends JPanel {

    private final Aerodrome<Airplane, Additions> aerodrome;

    public DrawWindowAerodrome(Aerodrome<Airplane, Additions> aerodrome) {
        this.aerodrome = aerodrome;
    }

    public Aerodrome<Airplane, Additions> gerAerodrome() {
        return aerodrome;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (aerodrome != null) aerodrome.Draw(g2);
    }
}
