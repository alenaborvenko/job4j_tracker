package ru.job4j.lambda;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;

public class FunctionDiaposTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = FunctionDiapos.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> result = FunctionDiapos.diapason(0, 5, x -> x * x + 3 * x);
        List<Double> expected = Arrays.asList(0D, 4D, 10D, 18D, 28D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentFunctionThenExponentResults() {
        List<Double> result = FunctionDiapos.diapason(0, 4, x -> Math.pow(10D, x));
        List<Double> expected = Arrays.asList(1D, 10D, 100D, 1000D);
        assertThat(result, is(expected));
    }
}