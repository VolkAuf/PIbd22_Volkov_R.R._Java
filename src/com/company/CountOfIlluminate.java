package com.company;

public enum CountOfIlluminate {
    ten,
    twenty,
    thirty;

    public static CountOfIlluminate getCount(int N) {
        return switch (N) {
            case 0 -> CountOfIlluminate.ten;
            case 1 -> CountOfIlluminate.twenty;
            case 2 -> CountOfIlluminate.thirty;
            default -> null;
        };
    }
}
