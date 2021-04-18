package ru.job4j.pojo;

import java.time.LocalDate;

public class College {
    public static void main(String[] args) {
        Student ron = new Student();
        ron.setName("Ron Smitt");
        ron.setGroup("PM-85");
        ron.setEnrollment(LocalDate.of(2021, 4,  9));
        System.out.println(ron.getName() + " study in " + ron.getGroup()
                            + " group. Date of enrollment: " + ron.getEnrollment());
    }
}
