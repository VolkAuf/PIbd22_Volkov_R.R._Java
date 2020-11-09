package com.company;

import java.awt.*;

public class Airplane extends AirTransport {

    protected int airplaneWidth = 230;
    protected int airplaneHeight = 130;

    public Airplane(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected Airplane(int maxSpeed, float weight, Color mainColor, int airplaneWidth, int airplaneHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.airplaneWidth = airplaneWidth;
        this.airplaneHeight = airplaneHeight;
    }

    @Override
    public void MoveTransport(Direction direction) {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - airplaneWidth) {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                } else {
                    _startPosX = 0;
                }
                break;
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                } else {
                    _startPosY = 0;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - airplaneHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    @Override
    public void DrawTransport(Graphics g) {
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
                        ((int) (_startPosX + airplaneWidth))
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
                        ((int) (_startPosY + airplaneHeight - 10)),
                        ((int) (_startPosY + airplaneHeight - 10)),
                        ((int) (_startPosY + airplaneHeight * 0.6))
                };
        g.fillPolygon(pointLeftWingX, pointLeftWingY, 4);// Krilo
        g.setColor(Color.BLACK);

        int[] pointBamperX =
                {
                        (int) (_startPosX + airplaneWidth),
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
                        ((int) (_startPosY + airplaneHeight * 0.5)),
                        ((int) (_startPosY + airplaneHeight * 0.13)),
                        ((int) (_startPosY + airplaneHeight * 0.13)),
                        ((int) (_startPosY + airplaneHeight * 0.5))
                };
        g.fillPolygon(pointTailX, pointTailY, 4);// Tail
    }
}
