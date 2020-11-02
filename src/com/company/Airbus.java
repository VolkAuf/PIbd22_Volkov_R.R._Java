package com.company;

import java.awt.*;

public class Airbus extends Airplane {

    public Color dopColor;
    public boolean backTurbine;
    public boolean sideTurbine;
    public boolean marketLine;
    public boolean regulTail;
    private Additions additions;

    public Airbus(int maxSpeed, float weight, Color mainColor, Color dopColor, int countIlluminate,
                  boolean backTurbine, boolean sideTurbine, boolean marketLine, boolean regulTail,
                  int addition, int digit) {
        super(maxSpeed, weight, mainColor, 150, 80);
        this.dopColor = dopColor;
        this.backTurbine = backTurbine;
        this.sideTurbine = sideTurbine;
        this.marketLine = marketLine;
        this.regulTail = regulTail;

        switch (addition) {
            case 0 -> additions = new SquareIlluminate(digit);
            case 1 -> additions = new CircleIlluminate(digit);
            case 2 -> additions = new ArcIlluminate(digit);
        }

    }

    @Override
    public void DrawTransport(Graphics g) {
        g.setColor(Color.BLACK);
        if (backTurbine) {
            g.fillOval((int) (_startPosX + airplaneWidth * 0.1), (int) (_startPosY + airplaneHeight * 0.4),
                    (int) (airplaneWidth * 0.2), (int) (airplaneHeight * 0.2));
            g.setColor(Color.WHITE);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.1), (int) (_startPosY + airplaneHeight * 0.5),
                    (int) (_startPosX + airplaneWidth * 0.3), (int) (_startPosY + airplaneHeight * 0.5));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.2), (int) (_startPosY + airplaneHeight * 0.4),
                    (int) (_startPosX + airplaneWidth * 0.2), (int) (_startPosY + airplaneHeight * 0.6));
        }
        if (sideTurbine) {
            g.setColor(Color.BLACK);
            g.fillOval((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.01),
                    (int) (airplaneWidth * 0.1), (int) (airplaneHeight * 0.1));
            g.setColor(Color.WHITE);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.06),
                    (int) (_startPosX + airplaneWidth * 0.5), (int) (_startPosY + airplaneHeight * 0.06));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.01),
                    (int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.11));
            g.setColor(Color.BLACK);
            g.fillOval((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.87),
                    (int) (airplaneWidth * 0.1), (int) (airplaneHeight * 0.1));
            g.setColor(Color.WHITE);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.92),
                    (int) (_startPosX + airplaneWidth * 0.5), (int) (_startPosY + airplaneHeight * 0.92));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.87),
                    (int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.97));
        }

        super.DrawTransport(g);

        if (regulTail) {
            int[] pointSportTailX =
                    {
                            ((int) (_startPosX + airplaneWidth * 0.18)),
                            ((int) (_startPosX + airplaneWidth * 0.1)),
                            ((int) (_startPosX + airplaneWidth * 0.13)),
                            ((int) (_startPosX + airplaneWidth * 0.2)),
                            ((int) (_startPosX + airplaneWidth * 0.25)),
                            ((int) (_startPosX + airplaneWidth * 0.35)),
                            ((int) (_startPosX + airplaneWidth * 0.33)),
                            ((int) (_startPosX + airplaneWidth * 0.23))
                    };
            int[] pointSportTailY =
                    {
                            ((int) (_startPosY + airplaneHeight * 0.19)),
                            ((int) (_startPosY + airplaneHeight * 0.21)),
                            ((int) (_startPosY + airplaneHeight * 0.24)),
                            ((int) (_startPosY + airplaneHeight * 0.28)),
                            ((int) (_startPosY + airplaneHeight * 0.26)),
                            ((int) (_startPosY + airplaneHeight * 0.2)),
                            ((int) (_startPosY + airplaneHeight * 0.17)),
                            ((int) (_startPosY + airplaneHeight * 0.19))
                    };
            g.fillPolygon(pointSportTailX, pointSportTailY, 8);// STail
        }

        g.setColor(mainColor);

        int[] pointSecondFloorX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.15)),
                        ((int) (_startPosX + airplaneWidth * 0.1)),
                        ((int) (_startPosX + airplaneWidth * 0.8)),
                        ((int) (_startPosX + airplaneWidth * 0.9))
                };
        int[] pointSecondFloorY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.47)),
                        ((int) (_startPosY + airplaneHeight * 0.33)),
                        ((int) (_startPosY + airplaneHeight * 0.33)),
                        ((int) (_startPosY + airplaneHeight * 0.47))
                };

        g.fillPolygon(pointSecondFloorX, pointSecondFloorY, 4);// Korpus
        g.setColor(Color.BLACK);

        //Door
        g.drawLine((int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.47),
                (int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.37));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.37),
                (int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.37));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.37),
                (int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.47));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.47),
                (int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.47));
        g.setColor(Color.WHITE);

        additions.DrawEntity(g, dopColor, _startPosX, _startPosY, airplaneWidth, airplaneHeight);
    }
}

