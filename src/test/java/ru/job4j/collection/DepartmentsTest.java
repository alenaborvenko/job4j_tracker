package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenNonChange() {
        List<String> input = List.of("k1", "k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenChangeK2K1SK1thenK2K1K1SK1() {
        List<String> input = List.of("k2", "k1/sk1");
        List<String> expect = List.of("k2", "k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenDepK1SK1K1SKSSK2K2SK1SSK2NaturalOrder() {
        List<String> input = List.of(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
                "K2",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K2/SK2"
        );
        List<String> expect = List.of(
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
                "K2/SK2"
        );
        input =  Departments.fillGaps(input);
        Departments.sortAsc(input);
        assertThat(input, is(expect));
    }

    @Test
    public void whenDepK1SK1K1SKSSK2K2SK1SSK2DescOrder() {
        List<String> input = List.of(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
                "K2",
                "K2/SK2",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
                "K2/SK2",
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2"
        );
        input =  Departments.fillGaps(input);
        Departments.sortDesc(input);
        assertThat(input, is(expect));
    }
}