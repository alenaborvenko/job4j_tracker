package ru.job4j.oop;

public class Transport {
    public static void main(String[] args) {
        Venicle airplane = new Airplane();
        Venicle bus = new Bus();
        Venicle train = new Train();
        Venicle[] trasport = new Venicle[]{airplane, bus, train};
        for (Venicle venicle : trasport) {
            venicle.move();
            venicle.stop();
        }
    }
}
