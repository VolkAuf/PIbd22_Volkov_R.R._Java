package com.company;

import java.awt.*;

public class SquareIlluminate implements Additions {

    private CountOfIlluminate countOfIlluminate;

    public SquareIlluminate(int digit) {
        setDigit(digit);
    }

    @Override
    public void setDigit(int digit) {
        this.countOfIlluminate = CountOfIlluminate.getCount(digit);
    }

    @Override
    public void DrawEntity(Graphics g, Color color, float x, float y, int airplaneWidth, int airplaneHeight) {

        double marginY = 0.515;
        double marginX = 0.2;

        for (float i = 0; i < 0.6; i += 0.1) {
            g.fillRect((int) (x + airplaneWidth * (marginX + i)),
                    (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                    (int) (airplaneWidth * 0.04));

        }

        if (countOfIlluminate == CountOfIlluminate.twenty || countOfIlluminate == CountOfIlluminate.thirty) {
            marginY -= 0.085;
            marginX -= 0.025;
            for (float i = 0; i < 0.6; i += 0.1) {
                g.fillRect((int) (x + airplaneWidth * (marginX + i)),
                        (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                        (int) (airplaneWidth * 0.04));
            }
        }

        if (countOfIlluminate == CountOfIlluminate.thirty) {
            marginY -= 0.085;
            marginX -= 0.025;
            for (float i = 0; i < 0.6; i += 0.1) {
                g.fillRect((int) (x + airplaneWidth * (marginX + i)),
                        (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                        (int) (airplaneWidth * 0.04));
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + countOfIlluminate.ordinal();
    }
}
