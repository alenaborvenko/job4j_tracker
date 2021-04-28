package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OrderConvertTest {
    @Test
    public void whenSingleOrderThen1ElementInHashMap() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void when3DifferentOrderThen3ElementInHashMap() {
        List<Order> orders = new ArrayList<>(List.of(
                new Order("t4543", "dyhhg"),
                new Order("76tfj", "gvnh"),
                new Order("665576", "lyug")
        ));
        HashMap<String, Order> actual = OrderConvert.process(orders);
        HashMap<String, Order> expected = new HashMap<>();
        expected.put("t4543", new Order("t4543", "dyhhg"));
        expected.put("76tfj", new Order("76tfj", "gvnh"));
        expected.put("665576", new Order("665576", "lyug"));
        assertThat(actual, is(expected));
    }

    @Test
    public void when3SameOrderThen1LastElemInHashMap() {
        List<Order> orders = new ArrayList<>(List.of(
                new Order("t4543", "dyhhg"),
                new Order("t4543", "next Order"),
                new Order("t4543", "Last order")
        ));
        HashMap<String, Order> actual = OrderConvert.process(orders);
        HashMap<String, Order> expected = new HashMap<>();
        expected.put("t4543", new Order("t4543", "Last order"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenEmptyOrderThenEmptyHashMap() {
        List<Order> orders = new ArrayList<>();
        HashMap<String, Order> actual = OrderConvert.process(orders);
        assertTrue(actual.isEmpty());
    }
}