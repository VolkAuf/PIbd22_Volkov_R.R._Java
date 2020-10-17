package com.company;

public enum CountIlluminate {
    ten,
    twenty,
    thirty;

    public static CountIlluminate getCount(int N) {
        return switch (N) {
            case 10 -> CountIlluminate.ten;
            case 20 -> CountIlluminate.twenty;
            case 30 -> CountIlluminate.thirty;
            default -> null;
        };
    }
}
