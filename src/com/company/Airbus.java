package com.company;

import java.awt.*;

public class Airbus {

    private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private final int airplaneWidth = 150;
    private final int airplaneHeight = 80;
    public int maxSpeed;
    public float weight;
    public Color mainColor;
    public Color dopColor;
    public boolean backTurbine;
    public boolean sideTurbine;
    public boolean marketLine;
    public boolean regulTail;
    public boolean secondFloor;
    private final Illuminate illuminate;

    public Airbus(int maxSpeed, float weight, Color mainColor, Color dopColor, int countIlluminate,
                  boolean backTurbine, boolean sideTurbine, boolean marketLine, boolean regulTail,
                  boolean secondFloor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.backTurbine = backTurbine;
        this.sideTurbine = sideTurbine;
        this.marketLine = marketLine;
        this.regulTail = regulTail;
        this.secondFloor = secondFloor;
        illuminate = new Illuminate();
        illuminate.setDigit(countIlluminate);
    }

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public void MoveAirTransport(Direction direction) {
        int step = (int) (maxSpeed * 100 / weight);
        switch (direction) {
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - airplaneHeight) {
                    _startPosY += step;
                }
                break;
            case Right:
                if (_startPosX + step < _pictureWidth - airplaneWidth) {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
        }
    }

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
        g.setColor(mainColor);

        int[] pointKorpusX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.15)),
                        ((int) (_startPosX + airplaneWidth * 0.2)),
                        ((int) (_startPosX + airplaneWidth * 0.9)),
                        ((int) (_startPosX + airplaneWidth * 0.8))
                };
        int[] pointKorpusY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.47)),
                        ((int) (_startPosY + airplaneHeight * 0.63)),
                        ((int) (_startPosY + airplaneHeight * 0.63)),
                        ((int) (_startPosY + airplaneHeight * 0.47))
                };
        g.fillPolygon(pointKorpusX, pointKorpusY, 4);// Korpus

        //Door
        g.setColor(Color.BLACK);
        g.drawLine((int) (_startPosX + airplaneWidth * 0.78), (int) (_startPosY + airplaneHeight * 0.63),
                (int) (_startPosX + airplaneWidth * 0.78), (int) (_startPosY + airplaneHeight * 0.5));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.78), (int) (_startPosY + airplaneHeight * 0.5),
                (int) (_startPosX + airplaneWidth * 0.81), (int) (_startPosY + airplaneHeight * 0.5));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.81), (int) (_startPosY + airplaneHeight * 0.5),
                (int) (_startPosX + airplaneWidth * 0.81), (int) (_startPosY + airplaneHeight * 0.63));
        g.setColor(Color.WHITE);

        int[] pointDnoX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.20)),
                        ((int) (_startPosX + airplaneWidth * 0.25)),
                        ((int) (_startPosX + airplaneWidth * 0.9)),
                        ((_startPosX + airplaneWidth))
                };
        int[] pointDnoY =
                {
                        (int) (_startPosY + airplaneHeight * 0.63),
                        (int) (_startPosY + airplaneHeight * 0.70),
                        (int) (_startPosY + airplaneHeight * 0.70),
                        (int) (_startPosY + airplaneHeight * 0.63)
                };
        g.fillPolygon(pointDnoX, pointDnoY, 4);// Dno

        g.setColor(mainColor);

        int[] pointRightWingX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.55)),
                        ((int) (_startPosX + airplaneWidth * 0.4)),
                        ((int) (_startPosX + airplaneWidth * 0.5)),
                        ((int) (_startPosX + airplaneWidth * 0.7))
                };
        int[] pointRightWingY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.50)),
                        ((int) (_startPosY + airplaneHeight * 0.05)),
                        ((int) (_startPosY + airplaneHeight * 0.05)),
                        ((int) (_startPosY + airplaneHeight * 0.50))
                };
        g.fillPolygon(pointRightWingX, pointRightWingY, 4);// Krilo

        int[] pointLeftWingX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.55)),
                        ((int) (_startPosX + airplaneWidth * 0.4)),
                        ((int) (_startPosX + airplaneWidth * 0.5)),
                        ((int) (_startPosX + airplaneWidth * 0.7))
                };
        int[] pointLeftWingY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.6)),
                        ((_startPosY + airplaneHeight - 10)),
                        ((_startPosY + airplaneHeight - 10)),
                        ((int) (_startPosY + airplaneHeight * 0.6))
                };
        g.fillPolygon(pointLeftWingX, pointLeftWingY, 4);// Krilo
        g.setColor(Color.BLACK);

        int[] pointBamperX =
                {
                        ((_startPosX + airplaneWidth)),
                        ((int) (_startPosX + airplaneWidth * 0.9)),
                        ((int) (_startPosX + airplaneWidth * 0.8)),
                        ((int) (_startPosX + airplaneWidth * 0.9))
                };
        int[] pointBamperY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.63)),
                        ((int) (_startPosY + airplaneHeight * 0.47)),
                        ((int) (_startPosY + airplaneHeight * 0.47)),
                        ((int) (_startPosY + airplaneHeight * 0.63))
                };
        g.fillPolygon(pointBamperX, pointBamperY, 4);// bamper
        g.setColor(mainColor);

        int[] pointTailX =
                {
                        ((int) (_startPosX + airplaneWidth * 0.30)),
                        ((int) (_startPosX + airplaneWidth * 0.15)),
                        ((int) (_startPosX + airplaneWidth * 0.13)),
                        ((int) (_startPosX + airplaneWidth * 0.25))
                };
        int[] pointTailY =
                {
                        ((int) (_startPosY + airplaneHeight * 0.4)),
                        ((int) (_startPosY + airplaneHeight * 0.03)),
                        ((int) (_startPosY + airplaneHeight * 0.03)),
                        ((int) (_startPosY + airplaneHeight * 0.4))
                };
        g.fillPolygon(pointTailX, pointTailY, 4);// Tail

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
        if (marketLine) {
            g.setColor(dopColor);
            int[] pointSportLineX =
                    {
                            ((int) (_startPosX + airplaneWidth * 0.15)),
                            ((int) (_startPosX + airplaneWidth * 0.64)),
                            ((int) (_startPosX + airplaneWidth * 0.9)),
                            ((int) (_startPosX + airplaneWidth * 0.37))
                    };
            int[] pointSportLineY =
                    {
                            ((int) (_startPosY + airplaneHeight * 0.47)),
                            ((int) (_startPosY + airplaneHeight * 0.63)),
                            ((int) (_startPosY + airplaneHeight * 0.63)),
                            ((int) (_startPosY + airplaneHeight * 0.47))
                    };
            g.fillPolygon(pointSportLineX, pointSportLineY, 4);// SLine
        }

        g.setColor(mainColor);
        if (secondFloor) {
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
            illuminate.DrawIlluminate(g, Color.WHITE, _startPosX, _startPosY, airplaneWidth, airplaneHeight);
        }
    }
}

