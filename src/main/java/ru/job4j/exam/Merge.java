package ru.job4j.exam;

public class Merge {
    public static int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int countRight = 0;
        int countLeft = 0;
        for (int i = 0; i < rsl.length; i++) {
            if (countRight == right.length) {
                System.arraycopy(left, countLeft,
                        rsl, countLeft + countRight, left.length - countLeft);
                break;
            }
            if (countLeft == left.length) {
                System.arraycopy(right, countRight,
                        rsl, countLeft + countRight, right.length - countRight);
                break;
            }
            if (left[countLeft] < right[countRight]) {
                rsl[i] = left[countLeft++];
            } else {
                rsl[i] = right[countRight++];
            }
        }
        return rsl;
    }
}
