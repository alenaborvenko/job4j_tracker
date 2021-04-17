package ru.job4j.professions;

public class Dentist extends Doctor {

    public Dentist(String name, String surName, String education, String birthday, int price,
                   int trainerCourse) {
        super(name, surName, education, birthday, price, trainerCourse);
    }

    /**
     * Метод, описывающий лечение стоматита
     * @param patient - пациент
     */
    public void treatmentStomatitis(Patient patient) {

    }
}
