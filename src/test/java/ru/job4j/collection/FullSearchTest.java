package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FullSearchTest {

    @Test
    public void extractNumber() {
        List<Task> tasks = List.of(
                new Task("1", "First desc"),
                new Task("2", "Second desc"),
                new Task("1", "First desc")
        );
        Set<String> expected = new HashSet<>(List.of("1", "2"));
        assertThat(FullSearch.extractNumber(tasks), is(expected));
    }

    @Test
    public void whenTaskIsEmptyThenEmptyHashSet() {
        List<Task> tasks = new ArrayList<>();
        Set<String> rsl = FullSearch.extractNumber(tasks);
        assertTrue(rsl.isEmpty());
    }
}