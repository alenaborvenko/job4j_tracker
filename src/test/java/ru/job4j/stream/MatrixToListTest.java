package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class MatrixToListTest {

    @Test
    public void matrixConvertToList() {
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11}
        };
        List<Integer> expect = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        ));
        List<Integer> actual = MatrixToList.convertMatrixToList(matrix);
        assertThat(actual, is(expect));
    }
}