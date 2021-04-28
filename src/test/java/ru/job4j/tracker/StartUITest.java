package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>(List.of(
                new CreateAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new ReplaceAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new DeleteAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + System.lineSeparator()
                        + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindAllActionNoItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>(List.of(
                new ShowAllAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Show all items ====" + System.lineSeparator()
                + "No items in tracker" + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindAllAction2Item() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        Tracker tracker = new Tracker();
        Item item1 = new Item("test");
        Item item2 = new Item("test1");
        tracker.add(item1);
        tracker.add(item2);
        List<UserAction> actions = new ArrayList<>(List.of(
                new ShowAllAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Show all items ====" + System.lineSeparator()
                + item1 + System.lineSeparator()
                + item2 + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindByIdItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item1 = new Item("test");
        tracker.add(item1);
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item1.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByIdAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find item by id ===" + System.lineSeparator()
                + item1 + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindByIdNoItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        String id = String.valueOf(0);
        Input in = new StubInput(
                new String[]{"0", id, "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByIdAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find item by id ===" + System.lineSeparator()
                + "Item with: " + id + " id not found in tracker." + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindByNameNoItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        String name = "No item";
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByNameAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + "Items with: " + name + " name not found." + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenFindByName2Items() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        String name = "test";
        Item item1 = new Item(name);
        Item item2 = new Item(name);
        tracker.add(item1);
        tracker.add(item2);
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByNameAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + item1 + System.lineSeparator()
                + item2 + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"1", "0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Exit" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit" + ln
                )
        );
    }
}