package ru.job4j.oop;

public class Bus implements Venicle {
    @Override
    public void move() {
        System.out.println("Автобус начал движение");
    }

    @Override
    public void stop() {
        System.out.println("Автобус остановился");
    }
}
