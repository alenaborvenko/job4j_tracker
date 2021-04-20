package ru.job4j.oop;

public class Train implements Venicle {
    @Override
    public void move() {
        System.out.println("Поезд начал движение");
    }

    @Override
    public void stop() {
        System.out.println("Поезд остановился");
    }
}
