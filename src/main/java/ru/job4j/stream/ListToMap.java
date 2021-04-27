package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
    public static Map<String, Student> convertToMap(List<Student> student) {
        return student.stream()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        s -> s,
                        (s, f) -> f
                ));
    }
}
