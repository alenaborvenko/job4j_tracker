package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.proxy.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItemTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("test"));
            assertThat(tracker.findByName("test").size(), is(1));
        }
    }

    @Test
    public void replaceItemTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test");
            tracker.add(item);
            tracker.replace(item.getId(), new Item("new test"));
            assertThat(tracker.findById(item.getId()).getName(), is("new test"));
        }
    }

    @Test
    public void deleteItemTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test");
            tracker.add(item);
            tracker.delete(item.getId());
            assertThat(tracker.findAll().size(), is(0));
        }
    }

    @Test
    public void findByIdTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test");
            tracker.add(item);
            assertThat(tracker.findById(item.getId()), is(item));
        }
    }

    @Test
    public void findByNameTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item first = new Item("test");
            Item second = new Item("test");
            Item third = new Item("not test");
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            assertThat(tracker.findByName("test"), is(new ArrayList<>(List.of(
                    first,
                    second
            ))));
        }
    }

    @Test
    public void findAllTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item first = new Item("test");
            Item second = new Item("test");
            Item third = new Item("not test");
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            assertThat(tracker.findAll(), is(new ArrayList<>(List.of(
                    first,
                    second,
                    third
            ))));
        }
    }
}