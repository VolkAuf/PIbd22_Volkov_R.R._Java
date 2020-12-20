package com.company;

import java.util.Comparator;

public class AirplaneComparer implements Comparator<AirTransport> {
    @Override
    public int compare(AirTransport x, AirTransport y) {
        if (!x.getClass().getSimpleName().equals(y.getClass().getSimpleName())) {
            return x.getClass().getSimpleName().compareTo(y.getClass().getSimpleName());
        }

        int result;
        switch (x.getClass().getSimpleName()) {
            case "Airplane" -> {
                result = comparerAirplane((Airplane) x, (Airplane) y);
                return result;
            }
            case "Airbus" -> {
                result = comparerAirbus((Airbus) x, (Airbus) y);
                return result;
            }
        }
        return 1;
    }

    private int comparerAirplane(Airplane x, Airplane y) {
        if (x.getMaxSpeed() != y.getMaxSpeed()) {
            return Integer.compare(x.getMaxSpeed(), y.getMaxSpeed());
        }
        if (x.getWeight() != y.getWeight()) {
            return Float.compare(x.getWeight(), y.getWeight());
        }
        if (x.getMainColor() != y.getMainColor()) {
            return Integer.compare(x.getMainColor().getRGB(), y.getMainColor().getRGB());
        }
        return 0;
    }

    private int comparerAirbus(Airbus x, Airbus y) {
        int result = comparerAirplane(x, y);
        if (result != 0) {
            return result;
        }

        if (x.getDopColor() != y.getDopColor()) {
            return Integer.compare(x.getDopColor().getRGB(), y.getDopColor().getRGB());
        }
        if (x.isBackTurbine() != y.isBackTurbine()) {
            return Boolean.compare(x.isBackTurbine(), y.isBackTurbine());
        }
        if (x.isSideTurbine() != y.isSideTurbine()) {
            return Boolean.compare(x.isSideTurbine(), y.isSideTurbine());
        }
        if (x.isMarketLine() != y.isMarketLine()) {
            return Boolean.compare(x.isMarketLine(), y.isMarketLine());
        }
        if (x.isRegulTail() != y.isRegulTail()) {
            return Boolean.compare(x.isRegulTail(), y.isRegulTail());
        }
        if (x.getAdditions() != null && y.getAdditions() != null
                && !(x.getAdditions().toString().equals(y.getAdditions().toString()))) {
            return x.getAdditions().toString().compareTo(y.getAdditions().toString());
        }
        if (x.getAdditions() == null && y.getAdditions() != null) {
            return 1;
        }
        if (x.getAdditions() != null && y.getAdditions() == null) {
            return -1;
        }
        return 0;
    }
}
