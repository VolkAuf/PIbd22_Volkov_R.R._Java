package com.company;

import java.awt.*;
import java.util.Objects;

public class MarketLine implements Additions {
    CountOfMarketLine marketLine;

    public MarketLine(int digit) {
        setDigit(digit);
    }

    @Override
    public void setDigit(int digit) {
        marketLine = CountOfMarketLine.getCount(digit);
    }

    @Override
    public void DrawEntity(Graphics g, Color color, float x, float y, int airplaneWidth, int airplaneHeight) {
        g.setColor(color);
        switch (Objects.requireNonNull(marketLine)) {
            case two:
                int[] pointSportLineTwoX =
                        {
                                ((int) (x + airplaneWidth * 0.85)),
                                ((int) (x + airplaneWidth * 0.37)),
                                ((int) (x + airplaneWidth * 0.19)),
                                ((int) (x + airplaneWidth * 0.64))
                        };
                int[] pointSportLineTwoY =
                        {
                                ((int) (y + airplaneHeight * 0.47)),
                                ((int) (y + airplaneHeight * 0.63)),
                                ((int) (y + airplaneHeight * 0.63)),
                                ((int) (y + airplaneHeight * 0.47))
                        };
                g.fillPolygon(pointSportLineTwoX, pointSportLineTwoY, 4);// SLine

            case one:
                int[] pointSportLineX =
                        {
                                ((int) (x + airplaneWidth * 0.19)),
                                ((int) (x + airplaneWidth * 0.64)),
                                ((int) (x + airplaneWidth * 0.85)),
                                ((int) (x + airplaneWidth * 0.37))
                        };
                int[] pointSportLineY =
                        {
                                ((int) (y + airplaneHeight * 0.47)),
                                ((int) (y + airplaneHeight * 0.63)),
                                ((int) (y + airplaneHeight * 0.63)),
                                ((int) (y + airplaneHeight * 0.47))
                        };
                g.fillPolygon(pointSportLineX, pointSportLineY, 4);// SLine
                break;
        }
    }
}
