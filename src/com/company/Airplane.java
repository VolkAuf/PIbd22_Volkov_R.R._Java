package com.company;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Airplane extends AirTransport implements Comparable<Airplane>, Iterable<Object>, Iterator<Object> {

    protected int airplaneWidth = 230;
    protected int airplaneHeight = 130;
    protected String separator = ";";
    protected LinkedList<Object> listProperties = new LinkedList<>();
    public int currentIndex = -1;

    public Airplane(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        listProperties.add(maxSpeed);
        this.weight = weight;
        listProperties.add(weight);
        this.mainColor = mainColor;
        listProperties.add(mainColor);
    }

    protected Airplane(int maxSpeed, float weight, Color mainColor, int airplaneWidth, int airplaneHeight) {
        this.maxSpeed = maxSpeed;
        listProperties.add(maxSpeed);
        this.weight = weight;
        listProperties.add(weight);
        this.mainColor = mainColor;
        listProperties.add(mainColor);
        this.airplaneWidth = airplaneWidth;
        this.airplaneHeight = airplaneHeight;
    }

    public Airplane(String info) {
        String[] args = info.split(separator);
        if (args.length == 3) {
            maxSpeed = Integer.parseInt(args[0]);
            listProperties.add(maxSpeed);
            weight = Float.parseFloat(args[1]);
            listProperties.add(weight);
            mainColor = new Color(Integer.parseInt(args[2]));
            listProperties.add(mainColor);
        }
    }

    public Airplane() {
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
        g.fillPolygon(pointBamperX, pointBamperY, 4);// Bamper
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

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Airplane airplaneObject)) {
            return false;
        }
        return equals(airplaneObject);
    }

    public boolean equals(Airplane other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
            return false;
        }
        if (maxSpeed != other.maxSpeed) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        if (mainColor != other.mainColor) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Airplane airplane) {
        if (maxSpeed != airplane.maxSpeed) {
            return Integer.compare(maxSpeed, airplane.maxSpeed);
        }
        if (weight != airplane.weight) {
            return Float.compare(weight, airplane.weight);
        }
        if (mainColor != airplane.mainColor) {
            return Integer.compare(mainColor.getRGB(), airplane.getMainColor().getRGB());
        }
        return 0;
    }

    @Override
    public Iterator<Object> iterator() {
        currentIndex = -1;
        return listProperties.iterator();
    }

    @Override
    public boolean hasNext() {
        return (currentIndex + 1 < listProperties.size());
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return listProperties.get(currentIndex);
    }
}
