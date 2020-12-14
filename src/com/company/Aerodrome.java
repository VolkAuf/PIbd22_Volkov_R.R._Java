package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Aerodrome<T extends AirTransport, I extends Additions> {
    private final List<T> places;
    private final int pictureWidth;
    private final int pictureHeight;
    private final int placeSizeWidth = 240;
    private final int placeSizeHeight = 140;
    private final int size;

    public Aerodrome(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        places = new ArrayList<>();
        size = width * height;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public boolean plus(T airTransport) throws AerodromeOverflowException {
        if (places.size() >= size) {
            throw new AerodromeOverflowException();
        }
        places.add(airTransport);
        return true;
    }

    public T minus(int index) throws AerodromeNotFoundException {
        if (index < 0 || index >= places.size()) {
            throw new AerodromeNotFoundException(index);
        }
        T airTransport = places.get(index);
        places.remove(index);
        return airTransport;
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
        int marginY = 20;
        int marginX = 15;
        int rowsCount = pictureHeight / placeSizeHeight;
        for (int i = 0; i < places.size(); i++) {
            places.get(i).setPosition(marginX + placeSizeWidth * (i / rowsCount), marginY + placeSizeHeight *
                    (i % rowsCount), pictureWidth, pictureHeight);
            places.get(i).DrawTransport(g);
        }
    }

    private void DrawMarking(Graphics2D g) {
        int margin = 15;
        int rowsCount = pictureHeight / placeSizeHeight;
        int columnsCount = pictureWidth / placeSizeWidth;
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                g.drawLine(margin + j * placeSizeWidth, margin + i * placeSizeHeight,
                        margin + (j + 1) * placeSizeWidth, margin + i * placeSizeHeight);
                if (j > 0) {
                    g.drawLine(margin + j * placeSizeWidth, margin + i * placeSizeHeight,
                            margin + j * placeSizeWidth, margin + (i + 1) * placeSizeHeight);
                }
            }
        }
        for (int j = 0; j < columnsCount; j++) {
            g.drawLine(margin + j * placeSizeWidth, margin + rowsCount * placeSizeHeight,
                    margin + (j + 1) * placeSizeWidth, margin + rowsCount * placeSizeHeight);
        }
    }

    public T get(int index) {
        if (index >= 0 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }

    public void clear() {
        places.clear();
    }
}