package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    /**
     * Метод, вычисляющий общий средний балл
     * @param stream - поток учеников
     * @return - средний балл
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(sub -> sub.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average().orElse(0);
    }

    /**
     *Метод, вычисляющий средний балл ученика по предметам
     * @param stream - поток ученика
     * @return - средние оценки в разрезе предметов
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(tuple ->
                        new Tuple(tuple.getName(),
                                tuple.getSubjects()
                                        .stream()
                                        .mapToInt(Subject::getScore)
                                        .average().orElse(0)))
                .collect(Collectors.toList());
    }

    /**
     *вычисляет средний балл по всем предметам для каждого ученика
     * @param stream - поток учеников
     * @return - Возвращает список из объекта Tuple (название предмета и средний балл)
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.flatMap(sub -> sub.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map(tuple -> new Tuple(tuple.getKey(), tuple.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * ученик с наибольшим баллом по всем предметам
     * @param stream - поток учеников
     * @return - ученик с наибольшим баллом по всем предметам
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(sub ->
                        new Tuple(sub.getName(),
                                sub.getSubjects()
                                        .stream()
                                        .mapToInt(Subject::getScore)
                                        .sum()))
                .max(Comparator.comparing(Tuple::getScore)).orElse(null);
    }

    /**
     *возвращает предмет с наибольшим баллом для всех студентов
     * @param stream - поток учеников
     * @return - возвращает предмет с наибольшим баллом для всех студентов
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(sub -> sub.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map(tuple -> new Tuple(tuple.getKey(), tuple.getValue()))
                .max(Comparator.comparing(Tuple::getScore)).orElse(null);
    }
}
