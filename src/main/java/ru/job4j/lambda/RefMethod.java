package ru.job4j.lambda;

import java.util.List;
import java.util.function.Consumer;

public class RefMethod {
    public static void main(String[] args) {
        List<String> names = List.of(
                "Ivan",
                "Petr Arsentev"
        );
        Consumer<String> out = RefMethod::cutOut;
        names.forEach(out);
    }

    public static void cutOut(String value) {
        if (value.length() > 10) {
            System.out.println(value.substring(0, 10) + "..");
        } else {
            System.out.println(value);
        }
    }
}
