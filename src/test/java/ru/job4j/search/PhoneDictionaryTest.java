package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByNameAndSurname() {
        PhoneDictionary phones = new PhoneDictionary();
        Person person1 = new Person("Petr", "Arsentev", "534872", "Bryansk");
        Person person2 = new Person("Den", "Petr", "765547", "Nsk");
        phones.add(person1);
        phones.add(person2);
        ArrayList<Person> persons = phones.find("Petr");
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person2);
        assertThat(persons,is(expected));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("534872");
        assertThat(persons.get(0).getPhone(), is("534872"));
    }

    @Test
    public void whenFindByAdress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Bryansk");
        assertThat(persons.get(0).getAddress(), is("Bryansk"));
    }

    @Test
    public void whenFindByAdressContains() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Rex", "Smitt", "865765", "nsk")
        );
        ArrayList<Person> persons = phones.find("Bry");
        assertThat(persons.get(0).getAddress(), is("Bryansk"));
    }
}