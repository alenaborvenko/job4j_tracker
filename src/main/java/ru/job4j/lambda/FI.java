package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class FI {
    public static void main(String[] args) {
        String[] atts = {
                "first",
                "second",
                "third",
                "fourth"
        };
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        Arrays.sort(atts, comparator);
        for (String att : atts) {
            System.out.print(att + " ");
        }
        System.out.println();
        comparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        Arrays.sort(atts, comparator);
        for (String att : atts) {
            System.out.print(att + " ");
        }
    }
}
