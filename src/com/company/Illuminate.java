package com.company;

import java.awt.*;
import java.util.Objects;

public class Illuminate {

    private int digit;

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public void DrawIlluminate(Graphics g, Color color, int _startPosX, int _startPosY,
                               int airplaneWidth, int airplaneHeight) {
        CountIlluminate count = CountIlluminate.getCount(digit);

        switch (Objects.requireNonNull(count)) {
            case thirty:
                g.setColor(color);
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillOval((int) (_startPosX + airplaneWidth * (0.15 + i)),
                            (int) (_startPosY + airplaneHeight * 0.34), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
            case twenty:
                g.setColor(color);
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillOval((int) (_startPosX + airplaneWidth * (0.18 + i)),
                            (int) (_startPosY + airplaneHeight * 0.42), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
            case ten:
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillOval((int) (_startPosX + airplaneWidth * (0.2 + i)),
                            (int) (_startPosY + airplaneHeight * 0.52), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
                break;
        }
    }
}
