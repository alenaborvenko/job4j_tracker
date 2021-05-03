package ru.job4j.exam;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        char[] leftChar = left.toCharArray();
        char[] rightChar = right.toCharArray();
        Map<Character, Integer> rslMap = new HashMap<>();
        for (char c : leftChar) {
            rslMap.compute(c, (key, val) -> val != null ? val + 1 : 1);
        }
        for (char c : rightChar) {
            if (!rslMap.containsKey(c)) {
                return false;
            } else {
                if (rslMap.get(c) > 1) {
                    rslMap.put(c, rslMap.get(c) - 1);
                } else {
                    rslMap.remove(c);
                }
            }
        }
        return rslMap.size() == 0;
    }
}
