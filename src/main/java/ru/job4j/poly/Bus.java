package ru.job4j.poly;

public class Bus implements Transport {
    private static int priceForLit = 45;
    private int numPassengers;

    public static int getPriceForLit() {
        return priceForLit;
    }

    @Override
    public void drive() {
        System.out.println("The bus start moving");
    }

    @Override
    public void passengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    @Override
    public int fill(int volume) {
        return Bus.getPriceForLit() * volume;
    }
}
