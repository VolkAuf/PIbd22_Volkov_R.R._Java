package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawWindow extends JPanel {

    private ITramsport transport;

    public void paintComponent(Graphics g) {
        if (transport != null) transport.DrawTransport(g);
    }

    public void setTransport(ITramsport transport) {
        this.transport = transport;
    }

    public ITramsport getTransport() {
        return transport;
    }
}
