package ru.job4j.professions;

public class Surgeon extends Doctor {
    public Surgeon(String name, String surName, String education, String birthday, int price,
                   int trainerCourse) {
        super(name, surName, education, birthday, price, trainerCourse);
    }

    /**
     * Метод, описывающий операцию
      * @param patient - пациент, которому показана операция
     */
    public void operation(Patient patient) {

    }
}
