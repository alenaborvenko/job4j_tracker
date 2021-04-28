package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class PassportOfficeTest {

    @Test
    public void whenAddFewNewCitizenThenSuccessAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen2 = new Citizen("4443f", "Alena Borvenko");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        office.add(citizen2);
        List<Citizen> expected = new ArrayList<>(List.of(citizen, citizen2));
        List<Citizen> rsl = new ArrayList<>();
        rsl.add(office.get(citizen.getPassport()));
        rsl.add(office.get(citizen2.getPassport()));
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenAddNotNewCitizenThenNotSuccessAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        boolean add = office.add(citizen);
        assertFalse(add);
    }

    @Test
    public void whenGetNotFoundPassportThenNullValue() {
        PassportOffice office = new PassportOffice();
        Citizen actual = office.get("jhbj");
        assertThat(actual, is(nullValue()));
    }
}