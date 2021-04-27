package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ProfilesTest {

    @Test
    public void whenFewProfile() {
        List<Profile> input = new ArrayList<>(Arrays.asList(
                new Profile(new Address("Nsk", "Lenina", 154, 56)),
                new Profile(new Address("Moskow", "Lenina", 67, 89))
        ));
        List<Address> expect = new ArrayList<>(Arrays.asList(
                new Address("Nsk", "Lenina", 154, 56),
                new Address("Moskow", "Lenina", 67, 89)
        ));
        List<Address> actual = Profiles.collect(input);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenEmptyProfile() {
        List<Profile> input = new ArrayList<>();
        List<Address> actual = Profiles.collect(input);
        assertTrue(actual.isEmpty());
    }
}