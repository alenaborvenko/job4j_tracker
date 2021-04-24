package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SortItemTest {

    @Test
    public void whenCompareTo3415Then1345() {
        List<Item> test = new ArrayList<>(Arrays.asList(
                new Item("3", 3),
                new Item("4", 4),
                new Item("1", 1),
                new Item("5", 5)
        ));
        List<Item> expected = new ArrayList<>(Arrays.asList(
                new Item("1", 1),
                new Item("3", 3),
                new Item("4", 4),
                new Item("5", 5)
        ));
        Collections.sort(test);
        assertThat(test, is(expected));
    }

    @Test
    public void whenCompare3415Then5431() {
        List<Item> test = new ArrayList<>(Arrays.asList(
                new Item("3", 3),
                new Item("4", 4),
                new Item("1", 1),
                new Item("5", 5)
        ));
        List<Item> expected = new ArrayList<>(Arrays.asList(
                new Item("5", 5),
                new Item("4", 4),
                new Item("3", 3),
                new Item("1", 1)
        ));
        test.sort(new SortItemDecrease());
        assertThat(test, is(expected));
    }
}
