package ru.job4j.ex;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenArgumentNegative() {
        new Fact().calc(-2);
    }

    @Test
    public void whenN4Then24() {
        Fact fact = new Fact();
        int rsl = fact.calc(4);
        int expected = 24;
        assertThat(expected, is(rsl));
    }

}