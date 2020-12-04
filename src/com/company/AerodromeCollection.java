package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AerodromeCollection {
    private final Map<String, Aerodrome<Airplane, Additions>> aerodromeStages;

    private final int pictureWidth;
    private final int pictureHeight;
    private final String separator = ":";

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

    public boolean saveFile(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("AerodromeCollection\n");
            for (Map.Entry<String, Aerodrome<Airplane, Additions>> level : aerodromeStages.entrySet()) {
                fileWriter.write("Aerodrome" + separator + level.getKey() + '\n');

                Airplane airplane;
                for (int i = 0; (airplane = level.getValue().get(i)) != null; i++) {
                    if (airplane.getClass().getSimpleName().equals("Airplane")) {
                        fileWriter.write("Airplane" + separator);
                    } else if (airplane.getClass().getSimpleName().equals("Airbus")) {
                        fileWriter.write("Airbus" + separator);
                    }
                    fileWriter.write(airplane.toString() + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadFile(String filename) {
        if (!(new File(filename).exists())) {
            return false;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("AerodromeCollection")) {
                aerodromeStages.clear();
            } else {
                return false;
            }

            Airplane airplane = null;
            String key = "";
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Aerodrome")) {
                    key = line.split(separator)[1];
                    aerodromeStages.put(key, new Aerodrome<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("Airplane")) {
                        airplane = new Airplane(line.split(separator)[1]);
                    } else if (line.contains("Airbus")) {
                        airplane = new Airbus(line.split(separator)[1]);
                    }
                    if (!(aerodromeStages.get(key).plus(airplane))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveAerodrome(String filename, String key) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        if (aerodromeStages.containsKey(key)) {
            try (FileWriter fileWriter = new FileWriter(filename, false)) {
                fileWriter.write("Aerodrome" + separator + key + '\n');

                Airplane airplane;
                for (int i = 0; (airplane = aerodromeStages.get(key).get(i)) != null; i++) {
                    if (airplane.getClass().getSimpleName().equals("Airplane")) {
                        fileWriter.write("Airplane" + separator);
                    } else if (airplane.getClass().getSimpleName().equals("Airbus")) {
                        fileWriter.write("Airbus" + separator);
                    }
                    fileWriter.write(airplane.toString() + '\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean loadAerodrome(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;

            line = scanner.nextLine();
            if (line.contains("Aerodrome:")) {
                key = line.split(separator)[1];
                if (aerodromeStages.containsKey(key)) {
                    aerodromeStages.get(key).clear();
                } else {
                    aerodromeStages.put(key, new Aerodrome<>(pictureWidth, pictureHeight));
                }
            } else {
                return false;
            }

            Airplane airplane = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("Airplane")) {
                        airplane = new Airplane(line.split(separator)[1]);
                    } else if (line.contains("Airbus")) {
                        airplane = new Airbus(line.split(separator)[1]);
                    }
                    if (!(aerodromeStages.get(key).plus(airplane))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Airplane get(String name, int index) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name).get(index);
        }
        return null;
    }
}

