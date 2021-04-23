package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class UsageArrayList {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Petr");
        arrayList.add("Ivan");
        arrayList.add("Stepan");
        for (String elem : arrayList) {
            System.out.println(elem);
        }
    }
}
