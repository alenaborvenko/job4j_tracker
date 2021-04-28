package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MatrixToListTest {

    @Test
    public void matrixConvertToList() {
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11}
        };
        List<Integer> expect = new ArrayList<>(List.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        ));
        List<Integer> actual = MatrixToList.convertMatrixToList(matrix);
        assertThat(actual, is(expect));
    }
}