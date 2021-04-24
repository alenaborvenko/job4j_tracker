package ru.job4j.collection;

import java.util.HashMap;

public class UsageHashMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("alenaborvenko@gmail.com", "Borvenko");
        map.put("ghvfhn@gmail.com", "Smitt Bob");
        map.put("alenaborvenko@gmail.com", "Borvenko Alena");
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
