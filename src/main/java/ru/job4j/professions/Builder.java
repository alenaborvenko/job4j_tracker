package ru.job4j.professions;

public class Builder extends Engineer {
    /**
     * Гражданское строительство
     */
    private boolean isCivilBuild;

    public Builder(String name, String surName, String education, String birthday, Gender gender,
                   boolean isCivilBuild) {
        super(name, surName, education, birthday, gender);
        this.isCivilBuild = isCivilBuild;
    }

    /**
     * Метод, описывающий строительство дома
     * @return - новый экземпляр типа House
     */
    public House buildHouse() {
        return new House(1, 1);
    }
}
