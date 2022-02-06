package skirental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SkiRental {
    private Map<String, Equipment> rentals = new TreeMap<>();

    public void loadFromFile(Path path) {
        List<String> fromFileToList;
        try {
            fromFileToList = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + ioe.getMessage());
        }
        makeMap(fromFileToList);
    }

    private void makeMap(List<String> data) {
        for (int i = 1; i < data.size(); i++) {
            String[] tempArr = data.get(i).split(";");
            String[] sizeArr = tempArr[1].split(" ");
            rentals.put(tempArr[0], new Equipment(Integer.parseInt(sizeArr[0]), Integer.parseInt(sizeArr[1])));
        }
    }

    public Map<String, Equipment> getRentals() {
        return new TreeMap<>(rentals);
    }

    public List<String> listChildren() {
        return rentals.entrySet().stream().
                filter(e -> e.getValue().getSizeOfBoot() < 38 && e.getValue().getSizeOfSkis() < 121).
                filter(e -> e.getValue().getSizeOfBoot() > 0 && e.getValue().getSizeOfSkis() > 0).map(e -> e.getKey()).toList();
    }

    public String getNameOfPeopleWithBiggestFoot() {
        Set<String> names = new TreeSet<>();
        int calcMax = Integer.MIN_VALUE;
        for (Map.Entry<String, Equipment> entry : rentals.entrySet()) {
            if (entry.getValue().getSizeOfBoot() > calcMax && entry.getValue().getSizeOfBoot() != 0 && entry.getValue().getSizeOfSkis() != 0) {
                calcMax = entry.getValue().getSizeOfBoot();
            }
        }
        for (Map.Entry<String, Equipment> entry : rentals.entrySet()) {
            if (entry.getValue().getSizeOfBoot() == calcMax && entry.getValue().getSizeOfBoot() != 0 && entry.getValue().getSizeOfSkis() != 0) {
                names.add(entry.getKey());
            }
        }
        return names.stream().findFirst().get();
    }
}
