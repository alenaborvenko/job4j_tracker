package ru.job4j.exam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] originSplit = origin.toLowerCase().split("[^а-яА-ЯЁёa-zA-Z0-9]+");
        Set<String> originSet = new HashSet<>();
        Collections.addAll(originSet, originSplit);
        Set<String> lineSet = new HashSet<>();
        String[] lineSplit = line.toLowerCase().split("[^а-яА-ЯЁёa-zA-Z0-9]+");
        Collections.addAll(lineSet, lineSplit);
        return originSet.containsAll(lineSet);
    }
}
