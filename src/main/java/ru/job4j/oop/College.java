package ru.job4j.oop;

public class College {
    public static void main(String[] args) {
        Freshman freshman = new Freshman();
        // повышающее приведение, так как класс Freshman является наследником Student,
        // т.е. движемся вверх по иерархии
        Student student = freshman;
        // повышающее приведение, так как Student неявно наследуется от класса Object
        Object obStudent = student;

    }
}
