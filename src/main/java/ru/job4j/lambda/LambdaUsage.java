package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaUsage {
    public static void main(String[] args) {
        String[] atts = {
                "first",
                "second",
                "third",
                "fourth"
        };
        Comparator<String> comparator = (s1, s2) -> {
            System.out.println("compare - " + s1.length() + " : " + s2.length());
            return Integer.compare(s2.length(), s1.length());
        };
        Arrays.sort(atts, comparator);
        for (String att : atts) {
            System.out.println(att);
        }
        String[] names = {
                "Ivan",
                "Petr"
        };
        Comparator<String> lengthCmp = (left, right) -> {
            System.out.println("execute comparator");
            return left.length() - right.length();
        };
        Arrays.sort(names, lengthCmp);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
