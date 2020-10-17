package com.company;

public enum CountIlluminate {
    ten,
    twenty,
    thirty;

    public static CountIlluminate getCount(int N) {
        switch (N) {
            case 10:
                return CountIlluminate.ten;
            case 20:
                return CountIlluminate.twenty;
            case 30:
                return CountIlluminate.thirty;
            default:
                return null;
        }
    }
}
