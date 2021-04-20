package ru.job4j.oop;

public class Airplane implements Venicle {
    @Override
    public void move() {
        System.out.println("Самолет полетел");
    }

    @Override
    public void stop() {
        System.out.println("Самолет приземлился");
    }
}
