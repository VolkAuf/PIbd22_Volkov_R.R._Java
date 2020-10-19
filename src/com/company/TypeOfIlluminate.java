package com.company;

import java.awt.*;

public enum TypeOfIlluminate {

    round,
    square;

    public static TypeOfIlluminate getType(int digit) {
        switch (digit) {
            case 0 -> {
                return TypeOfIlluminate.round;
            }
            case 1 -> {
                return TypeOfIlluminate.square;
            }
        }
        return null;
    }

}
