package com.company;

import java.awt.*;

public class Airbus extends Airplane {

    public Color dopColor;
    public boolean backTurbine;
    public boolean sideTurbine;
    public boolean marketLine;
    public boolean regulTail;
    private Additions additions;

    public Color getDopColor() {
        return dopColor;
    }

    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    public boolean isBackTurbine() {
        return backTurbine;
    }

    public void setBackTurbine(boolean backTurbine) {
        this.backTurbine = backTurbine;
    }

    public boolean isSideTurbine() {
        return sideTurbine;
    }

    public void setSideTurbine(boolean sideTurbine) {
        this.sideTurbine = sideTurbine;
    }

    public boolean isMarketLine() {
        return marketLine;
    }

    public void setMarketLine(boolean marketLine) {
        this.marketLine = marketLine;
    }

    public boolean isRegulTail() {
        return regulTail;
    }

    public void setRegulTail(boolean regulTail) {
        this.regulTail = regulTail;
    }

    public void setAdditions(Additions additions) {
        this.additions = additions;
    }

    public Airbus(int maxSpeed, float weight, Color mainColor, Color dopColor,
                  boolean backTurbine, boolean sideTurbine, boolean marketLine, boolean regulTail) {
        super(maxSpeed, weight, mainColor, 230, 130);
        this.dopColor = dopColor;
        this.backTurbine = backTurbine;
        this.sideTurbine = sideTurbine;
        this.marketLine = marketLine;
        this.regulTail = regulTail;
    }

    public Airbus(String info){
        String[] args = info.split(separator);
        if (args.length == 9)
        {
            maxSpeed = Integer.parseInt(args[0]);
            weight = Float.parseFloat(args[1]);
            mainColor = new Color(Integer.parseInt(args[2]));
            dopColor = new Color(Integer.parseInt(args[3]));
            backTurbine = Boolean.parseBoolean(args[4]);
            sideTurbine = Boolean.parseBoolean(args[5]);
            marketLine = Boolean.parseBoolean(args[6]);
            regulTail = Boolean.parseBoolean(args[7]);
            if (args[8].contains("null")) {
                additions = null;
            } else {
                String[] argsAddition = args[8].split("\\.");
                int digit = Integer.parseInt(argsAddition[1]);
                switch (argsAddition[0]) {
                    case "SquareIlluminate" -> additions = new SquareIlluminate(digit);
                    case "CircleIlluminate" -> additions = new CircleIlluminate(digit);
                    case "ArcIlluminate" -> additions = new ArcIlluminate(digit);
                }
            }
        }
    }

    @Override
    public void DrawTransport(Graphics g) {
        g.setColor(Color.BLACK);
        if (backTurbine) {
            g.fillOval((int) (_startPosX + airplaneWidth * 0.1), (int) (_startPosY + airplaneHeight * 0.4),
                    (int) (airplaneWidth * 0.2), (int) (airplaneHeight * 0.2));
            g.setColor(dopColor);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.1), (int) (_startPosY + airplaneHeight * 0.5),
                    (int) (_startPosX + airplaneWidth * 0.3), (int) (_startPosY + airplaneHeight * 0.5));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.2), (int) (_startPosY + airplaneHeight * 0.4),
                    (int) (_startPosX + airplaneWidth * 0.2), (int) (_startPosY + airplaneHeight * 0.6));
        }
        if (sideTurbine) {
            g.setColor(Color.BLACK);
            g.fillOval((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.01),
                    (int) (airplaneWidth * 0.1), (int) (airplaneHeight * 0.1));
            g.setColor(dopColor);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.06),
                    (int) (_startPosX + airplaneWidth * 0.5), (int) (_startPosY + airplaneHeight * 0.06));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.01),
                    (int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.11));
            g.setColor(Color.BLACK);
            g.fillOval((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.87),
                    (int) (airplaneWidth * 0.1), (int) (airplaneHeight * 0.1));
            g.setColor(dopColor);
            g.drawLine((int) (_startPosX + airplaneWidth * 0.4), (int) (_startPosY + airplaneHeight * 0.92),
                    (int) (_startPosX + airplaneWidth * 0.5), (int) (_startPosY + airplaneHeight * 0.92));
            g.drawLine((int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.87),
                    (int) (_startPosX + airplaneWidth * 0.45), (int) (_startPosY + airplaneHeight * 0.97));
        }

        super.DrawTransport(g);

        g.setColor(dopColor);
        if (regulTail) {
            int[] pointSportTailX =
                    {
                            ((int) (_startPosX + airplaneWidth * 0.08)),
                            ((int) (_startPosX + airplaneWidth * 0.0)),
                            ((int) (_startPosX + airplaneWidth * 0.03)),
                            ((int) (_startPosX + airplaneWidth * 0.1)),
                            ((int) (_startPosX + airplaneWidth * 0.15)),
                            ((int) (_startPosX + airplaneWidth * 0.25)),
                            ((int) (_startPosX + airplaneWidth * 0.23)),
                            ((int) (_startPosX + airplaneWidth * 0.13))
                    };
            int[] pointSportTailY =
                    {
                            ((int) (_startPosY + airplaneHeight * 0.09)),
                            ((int) (_startPosY + airplaneHeight * 0.11)),
                            ((int) (_startPosY + airplaneHeight * 0.14)),
                            ((int) (_startPosY + airplaneHeight * 0.18)),
                            ((int) (_startPosY + airplaneHeight * 0.16)),
                            ((int) (_startPosY + airplaneHeight * 0.1)),
                            ((int) (_startPosY + airplaneHeight * 0.07)),
                            ((int) (_startPosY + airplaneHeight * 0.09))
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
        g.setColor(dopColor);

        //Door
        g.drawLine((int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.47),
                (int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.37));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.37),
                (int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.37));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.37),
                (int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.47));
        g.drawLine((int) (_startPosX + airplaneWidth * 0.75), (int) (_startPosY + airplaneHeight * 0.47),
                (int) (_startPosX + airplaneWidth * 0.72), (int) (_startPosY + airplaneHeight * 0.47));

        if (additions != null) {
            additions.DrawEntity(g, getDopColor(), _startPosX, _startPosY, airplaneWidth, airplaneHeight);
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB() + separator + dopColor.getRGB() + separator
                + backTurbine + separator + sideTurbine + separator + marketLine + separator + regulTail + separator + additions;
    }
}

