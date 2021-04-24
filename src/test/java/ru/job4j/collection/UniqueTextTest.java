package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueTextTest {

    @Test
    public void isEquals() {
        String origin = "My cat eats a mouse and milk";
        String text = "My cat eats milk and a mouse";
        boolean sameText = UniqueText.isEquals(origin, text);
        assertTrue(sameText);
    }

    @Test
    public void isNotEquals() {
        String origin = "My cat eats a mouse";
        String text = "A mouse is eaten by a cat";
        boolean diffText = UniqueText.isEquals(origin, text);
        assertFalse(diffText);
    }
}