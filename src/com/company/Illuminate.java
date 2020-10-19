package com.company;

import java.awt.*;
import java.util.Objects;

public class Illuminate implements Additions {

    private CountOfIlluminate count;

    public Illuminate(int digit) {
        setDigit(digit);
    }


    @Override
    public void setDigit(int digit) {
        this.count = CountOfIlluminate.getCount(digit);
    }

    @Override
    public void DrawEntity(Graphics g, Color color, float _startPosX, float _startPosY, int airplaneWidth, int airplaneHeight) {
        g.setColor(color);
        switch (Objects.requireNonNull(count)) {
            case thirty:
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillOval((int) (_startPosX + airplaneWidth * (0.15 + i)),
                            (int) (_startPosY + airplaneHeight * 0.34), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
            case twenty:
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
