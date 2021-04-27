package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProfilesTest {

    @Test
    public void whenFewProfile() {
        List<Profile> input = new ArrayList<>(Arrays.asList(
                new Profile(new Address("Nsk", "Lenina", 154, 56)),
                new Profile(new Address("Moskow", "Lenina", 67, 89))
        ));
        List<Address> expect = new ArrayList<>(Arrays.asList(
                new Address("Moskow", "Lenina", 67, 89),
                new Address("Nsk", "Lenina", 154, 56)
        ));
        List<Address> actual = Profiles.collect(input);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenFewProfileNotUniqAdress() {
        List<Profile> input = new ArrayList<>(Arrays.asList(
                new Profile(new Address("Nsk", "Lenina", 154, 56)),
                new Profile(new Address("Moskow", "Lenina", 67, 89)),
                new Profile(new Address("Nsk", "Lenina", 154, 56))
        ));
        List<Address> expect = new ArrayList<>(Arrays.asList(
                new Address("Moskow", "Lenina", 67, 89),
                new Address("Nsk", "Lenina", 154, 56)
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