package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item;
        item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenReplaceNotFindId() {
        Tracker tracker = new Tracker();
        int id = 0;
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        boolean rsl = tracker.replace(id, bugWithDesc);
        assertThat(rsl, is(false));
    }

    @Test
    public void whenReplaceNotFindIdAfterCheckId() {
        Tracker tracker = new Tracker();
        int id = 0;
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        int rsl = bugWithDesc.getId();
        assertThat(rsl, is(0));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteNotFoundId() {
        Tracker tracker = new Tracker();
        int id = 0;
        assertThat(tracker.delete(id),  is(false));
    }

    @Test
    public void whenDeleteMiddle() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("third");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        int id = second.getId();
        tracker.delete(id);
        assertThat(tracker.findById(first.getId()), is(first));
        assertThat(tracker.findById(third.getId()), is(third));
        assertThat(tracker.findById(id), is(nullValue()));
    }
}