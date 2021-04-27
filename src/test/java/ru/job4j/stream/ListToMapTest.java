package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ListToMapTest {

    @Test
    public void convertToMap() {
        List<Student> input = List.of(
                new Student(70, "Иванов"),
                new Student(60, "Иванов"),
                new Student(10, "Петров"),
                new Student(20, "Сидоров"),
                new Student(30, "Петров"),
                new Student(20, "Сидоров")
        );
        Map<String, Student> rsl = ListToMap.convertToMap(input);
        Map<String, Student> expect = Map.of(
                "Иванов", new Student(60, "Иванов"),
                "Петров", new Student(30, "Петров"),
                "Сидоров", new Student(20, "Сидоров")
        );
        assertThat(rsl, is(expect));
    }

}