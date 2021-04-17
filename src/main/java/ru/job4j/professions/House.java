package ru.job4j.professions;

public class House {
    private int countWindow;
    private int countFloor;

    public House(int countWindow, int countFloor) {
        this.countWindow = countWindow;
        this.countFloor = countFloor;
    }

    public int getCountWindow() {
        return countWindow;
    }

    public int getCountFloor() {
        return countFloor;
    }
}
