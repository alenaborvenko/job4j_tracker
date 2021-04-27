package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PositiveNums {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(
                1, -10, 0, 15, 60, -32, 8, -4, 2
        ));
        System.out.println("Первоначальный массив:");
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        List<Integer> afterFilter = nums.stream()
                .filter(i -> i > 0)
                .collect(Collectors.toList());
        System.out.println("После фильтрации:");
        for (Integer num : afterFilter) {
            System.out.print(num + " ");
        }
    }
}
