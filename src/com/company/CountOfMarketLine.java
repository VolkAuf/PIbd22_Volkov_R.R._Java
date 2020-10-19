package com.company;

public enum CountOfMarketLine {
    one,
    two;

    public static CountOfMarketLine getCount(int digit) {
        switch (digit) {
            case 0 -> {
                return CountOfMarketLine.one;
            }
            case 1 -> {
                return CountOfMarketLine.two;
            }
        }
        return null;
    }
}
