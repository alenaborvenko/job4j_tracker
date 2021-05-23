package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker memTracker = new MemTracker();
        Item item;
        item = new Item();
        item.setName("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenReplaceNotFindId() {
        MemTracker memTracker = new MemTracker();
        int id = 0;
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        boolean rsl = memTracker.replace(id, bugWithDesc);
        assertThat(rsl, is(false));
    }

    @Test
    public void whenReplaceNotFindIdAfterCheckId() {
        MemTracker memTracker = new MemTracker();
        int id = 0;
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        memTracker.replace(id, bugWithDesc);
        int rsl = bugWithDesc.getId();
        assertThat(rsl, is(0));
    }

    @Test
    public void whenDelete() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteNotFoundId() {
        MemTracker memTracker = new MemTracker();
        int id = 0;
        assertThat(memTracker.delete(id),  is(false));
    }

    @Test
    public void whenDeleteMiddle() {
        MemTracker memTracker = new MemTracker();
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("third");
        memTracker.add(first);
        memTracker.add(second);
        memTracker.add(third);
        int id = second.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(first.getId()), is(first));
        assertThat(memTracker.findById(third.getId()), is(third));
        assertThat(memTracker.findById(id), is(nullValue()));
    }
}