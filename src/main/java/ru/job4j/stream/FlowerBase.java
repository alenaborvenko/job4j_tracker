package ru.job4j.stream;

public class FlowerBase {

    public static void main(String[] args) {
        Flower pion = new Flower.Builder()
                .buildName("Pion")
                .buildColor("Pink")
                .buildLeaves(true)
                .buildWeight(50)
                .buildHeight(60)
                .buildStorageTemper(5)
                .buildExotic(false)
                .build();
        Flower rose = new Flower.Builder()
                .buildName("Rose")
                .buildColor("Red")
                .buildLeaves(true)
                .buildWeight(30)
                .buildHeight(80)
                .buildExotic(false)
                .buildStorageTemper(3)
                .buildExotic(false)
                .build();
        System.out.println(pion);
        System.out.println(rose);
    }
}
