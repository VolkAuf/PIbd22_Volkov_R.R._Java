package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AerodromeCollection {
    private final Map<String, Aerodrome<Airplane, Additions>> aerodromeStages;

    private final int pictureWidth;
    private final int pictureHeight;

    public AerodromeCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        aerodromeStages = new HashMap<>();
    }

    public Set<String> keySet() {
        return aerodromeStages.keySet();
    }

    public void addAerodrome(String name) {
        if (!aerodromeStages.containsKey(name)) {
            aerodromeStages.put(name, new Aerodrome<>(pictureWidth, pictureHeight));
        }
    }

    public void deleteAerodrome(String name) {
        aerodromeStages.remove(name);
    }

    public Aerodrome<Airplane, Additions> get(String name) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name);
        }
        return null;
    }

    public Airplane get(String name, int index) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name).get(index);
        }
        return null;
    }
}

