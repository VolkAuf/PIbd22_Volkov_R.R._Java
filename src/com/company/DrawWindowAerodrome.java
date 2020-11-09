package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawWindowAerodrome extends JPanel {

    private final AerodromeCollection aerodromeCollection;
    private String selectedItem = null;

    public DrawWindowAerodrome(AerodromeCollection aerodromeCollection) {
        this.aerodromeCollection = aerodromeCollection;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (selectedItem != null) {
            Graphics2D g2 = (Graphics2D) g;
            if (aerodromeCollection != null) aerodromeCollection.get(selectedItem).Draw(g2);
        }
    }
}
