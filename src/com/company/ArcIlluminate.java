package com.company;

import java.awt.*;

public class ArcIlluminate implements Additions {

    private CountOfIlluminate countOfIlluminate;

    public ArcIlluminate(int digit) {
        setDigit(digit);
    }

    @Override
    public void setDigit(int digit) {
        this.countOfIlluminate = CountOfIlluminate.getCount(digit);
    }

    @Override
    public void DrawEntity(Graphics g, Color color, float x, float y, int airplaneWidth, int airplaneHeight) {

        int arcStart = 0;
        int arcFinish = 180;
        double marginY = 0.515;
        double marginX = 0.2;

        for (float i = 0; i < 0.6; i += 0.1) {
            g.fillArc((int) (x + airplaneWidth * (marginX + i)),
                    (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                    (int) (airplaneWidth * 0.04), arcStart, arcFinish);
        }

        if (countOfIlluminate == CountOfIlluminate.twenty || countOfIlluminate == CountOfIlluminate.thirty) {
            marginY -= 0.085;
            marginX -= 0.025;
            for (float i = 0; i < 0.6; i += 0.1) {
                g.fillArc((int) (x + airplaneWidth * (marginX + i)),
                        (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                        (int) (airplaneWidth * 0.04), arcStart, arcFinish);
            }
        }

        if (countOfIlluminate == CountOfIlluminate.thirty) {
            marginY -= 0.085;
            marginX -= 0.025;
            for (float i = 0; i < 0.6; i += 0.1) {
                g.fillArc((int) (x + airplaneWidth * (marginX + i)),
                        (int) (y + airplaneHeight * marginY), (int) (airplaneWidth * 0.04),
                        (int) (airplaneWidth * 0.04), arcStart, arcFinish);
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + countOfIlluminate.ordinal();
    }
}
