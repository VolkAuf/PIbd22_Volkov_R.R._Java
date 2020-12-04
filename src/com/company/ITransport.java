package com.company;

import java.awt.*;

public interface ITransport {
    void setPosition(int x, int y, int width, int height);

    void MoveTransport(Direction direction);

    void DrawTransport(Graphics g);

    void setMainColor(Color color);
}
