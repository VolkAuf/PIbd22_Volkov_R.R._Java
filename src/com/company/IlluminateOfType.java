package com.company;

import java.awt.*;

public class IlluminateOfType implements Additions {

    TypeOfIlluminate type;

    public IlluminateOfType(int digit) {
        setDigit(digit);
    }

    @Override
    public void setDigit(int digit) {
        type = TypeOfIlluminate.getType(digit);
    }

    @Override
    public void DrawEntity(Graphics g, Color color, float x, float y, int airplaneWidth, int airplaneHeight) {

        g.setColor(color);
        switch (type) {
            case round:
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillOval((int) (x + airplaneWidth * (0.2 + i)),
                            (int) (y + airplaneHeight * 0.52), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
                break;
            case square:
                for (float i = 0; i < 0.6; i += 0.1) {
                    g.fillRect((int) (x + airplaneWidth * (0.2 + i)),
                            (int) (y + airplaneHeight * 0.52), (int) (airplaneWidth * 0.04),
                            (int) (airplaneWidth * 0.04));
                }
                break;
        }
    }
}
