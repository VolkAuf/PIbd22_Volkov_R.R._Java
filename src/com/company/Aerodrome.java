package com.company;

import java.awt.*;

public class Aerodrome<T extends AirTransport, I extends Additions> {
    private final Object[] places;
    private final int _pictureWidth;
    private final int _pictureHeight;
    private final int placeSizeWidth = 240;
    private final int placeSizeHeight = 140;
    private final int size;

    public Aerodrome(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        places = new Object[width * height];
        size = width * height;
        _pictureWidth = picWidth;
        _pictureHeight = picHeight;
    }

    public boolean plus(T airtransport) {
        int edge = 15;
        int marginY = 5;
        int rowsCount = _pictureHeight / placeSizeHeight;
        for (int i = 0; i < places.length; i++) {
            if (places[i] == null) {
                airtransport.setPosition(edge + placeSizeWidth * (i / rowsCount), edge + marginY + placeSizeHeight * (i % rowsCount), _pictureWidth, _pictureHeight);
                places[i] = airtransport;
                return true;
            }
        }
        return false;
    }

    public T minus(int index) {
        if (index >= 0 && index < places.length && places[index] != null) {
            Object temp = places[index];
            places[index] = null;
            return (T) (temp);
        } else {
            return null;
        }
    }

    public boolean Equals(int count) {
        int placesCount = 0;
        for (Object o : places) {
            if (o != null) {
                placesCount++;
            }
        }
        return placesCount == count;
    }

    public boolean unEquals(int count) {
        return !Equals(count);
    }

    public void Draw(Graphics2D g) {
        DrawMarking(g);
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.DrawTransport(g);
            }
        }
    }

    private void DrawMarking(Graphics2D g) {
        int edge = 15;
        int rowsCount = _pictureHeight / placeSizeHeight;
        int columnsCount = _pictureWidth / placeSizeWidth;
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                g.drawLine(edge + j * placeSizeWidth, edge + i * placeSizeHeight,
                        edge + (j + 1) * placeSizeWidth, edge + i * placeSizeHeight);
                if (j > 0) {
                    g.drawLine(edge + j * placeSizeWidth, edge + i * placeSizeHeight,
                            edge + j * placeSizeWidth, edge + (i + 1) * placeSizeHeight);
                }
            }
        }
        for (int j = 0; j < columnsCount; j++) {
            g.drawLine(edge + j * placeSizeWidth, edge + rowsCount * placeSizeHeight,
                    edge + (j + 1) * placeSizeWidth, edge + rowsCount * placeSizeHeight);
        }
    }
}