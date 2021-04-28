package ru.job4j.stream;

public class Flower {
    private String name;
    private String color;
    private int height;
    private int weight;
    private boolean leaves;
    private boolean exotic;
    private int storageTemper;

    static class Builder {
        private String name;
        private String color;
        private int height;
        private int weight;
        private boolean leaves;
        private boolean exotic;
        private int storageTemper;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        Builder buildHeight(int height) {
            this.height = height;
            return this;
        }

        Builder buildWeight(int weight) {
            this.weight = weight;
            return this;
        }

        Builder buildLeaves(boolean leaves) {
            this.leaves = leaves;
            return this;
        }

        Builder buildExotic(boolean exotic) {
            this.exotic = exotic;
            return this;
        }

        Builder buildStorageTemper(int storageTemper) {
            this.storageTemper = storageTemper;
            return this;
        }

        Flower build() {
            Flower flower = new Flower();
            flower.name = name;
            flower.color = color;
            flower.height = height;
            flower.weight = weight;
            flower.leaves = leaves;
            flower.exotic = exotic;
            flower.storageTemper = storageTemper;
            return flower;
        }
    }

    @Override
    public String toString() {
        return "Flower{"
                + "name='" + name + '\''
                + ", color='" + color + '\''
                + ", height=" + height
                + ", weight=" + weight
                + ", leaves=" + leaves
                + ", exotic=" + exotic
                + ", storageTemper=" + storageTemper
                + '}';
    }
}
