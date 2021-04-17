package ru.job4j.professions;

public class Doctor extends Profession {
    /**
     * Стоимость приема
     */
    private int price;
    /**
     * Количество сертификатов повышения квалификации
     */
    private int trainerCourse;

    public Doctor(String name, String surName, String education, String birthday, int price,
                  int trainerCourse) {
        super(name, surName, education, birthday);
        this.price = price;
        this.trainerCourse = trainerCourse;
    }

    /**Метод для получения диагноза
     *
     * @param patient - пациент
     * @return - диагноз
     */
    public Diagnosis heal(Patient patient) {
        return new Diagnosis();
    }

    /**
     * Метод, описывающий прием пациента
     * @param pation - пациент
     */
    public void patientReception(Patient pation) {

    }

    /**
     * Прохождение повышения квалификации
     */
    public void passTrainerCourse() {

    }
}
