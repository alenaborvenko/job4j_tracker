package ru.job4j.professions;

public class Programmer extends Engineer {
    private String language;

    public Programmer(String name, String surName, String education, String birthday,
                      String language, Gender gender) {
        super(name, surName, education, birthday, gender);
        this.language = language;
    }

    public Programm writeProgramm() {
        return new Programm();
    }
}
