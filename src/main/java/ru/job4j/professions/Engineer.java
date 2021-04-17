package ru.job4j.professions;

public class Engineer extends Profession {
    private Gender gender;

    public Engineer(String name, String surName, String education, String birthday, Gender gender) {
        super(name, surName, education, birthday);
        this.gender = gender;
    }

    /**
     * Метод, описывающий работу инженера
     */
    public void work() {

    }
}
