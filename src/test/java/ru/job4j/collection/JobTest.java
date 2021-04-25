package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenCompatorByNameIncreaseFirstGreater() {
        Comparator<Job> cmpNameIncrease = new JobNameIncrease();
        int rsl = cmpNameIncrease.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByNameIncreaseFirstLess() {
        Comparator<Job> cmpNameIncrease = new JobNameIncrease();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 0),
                new Job("Def", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByPriorityIncreaseFirstLess() {
        Comparator<Job> cmpNameIncrease = new JobPriorityIncrease();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 0),
                new Job("Def", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByPriorityIncreaseFirstGreater() {
        Comparator<Job> cmpNameIncrease = new JobPriorityIncrease();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 10),
                new Job("Def", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByPriorityDescSecondGreater() {
        Comparator<Job> cmpNameIncrease = new JobPriorityDesc();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 1),
                new Job("Def", 10)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByPriorityDescSecondLess() {
        Comparator<Job> cmpNameIncrease = new JobPriorityDesc();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 10),
                new Job("Def", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameDescSecondLess() {
        Comparator<Job> cmpNameIncrease = new JobPriorityDesc();
        int rsl = cmpNameIncrease.compare(
                new Job("Def", 10),
                new Job("Abc", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameDescSecondGreater() {
        Comparator<Job> cmpNameIncrease = new JobPriorityDesc();
        int rsl = cmpNameIncrease.compare(
                new Job("Abc", 0),
                new Job("Def", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByNameDescThenPriorityIncrease() {
        Comparator<Job> cmpNameDescThenPriorityInc = new JobNameDesc()
                .thenComparing(new JobPriorityIncrease());
        int rsl = cmpNameDescThenPriorityInc.compare(
                new Job("Abc", 0),
                new Job("Abc", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameDescThenPriorityIncreaseThenGreater() {
        Comparator<Job> cmpNameDescThenPriorityInc = new JobNameDesc()
                .thenComparing(new JobPriorityIncrease());
        int rsl = cmpNameDescThenPriorityInc.compare(
                new Job("Abc", 0),
                new Job("Abc", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameIncreaseThenPriorityIncreaseThenless() {
        Comparator<Job> cmpNameDescThenPriorityInc = new JobNameIncrease()
                .thenComparing(new JobPriorityIncrease());
        int rsl = cmpNameDescThenPriorityInc.compare(
                new Job("Abc", 0),
                new Job("Abc", 20)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByPriorityIncreaseThenNameIncreaseThenless() {
        Comparator<Job> cmpNameDescThenPriorityInc = new JobPriorityIncrease()
                .thenComparing(new JobNameIncrease());
        int rsl = cmpNameDescThenPriorityInc.compare(
                new Job("Abc", 20),
                new Job("Def", 20)
        );
        assertThat(rsl, lessThan(0));
    }
}