package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void distance3d() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(4, 0, 7);
        double rsl = a.distance3d(b);
        double expected = 8.062;
        Assert.assertEquals(expected, rsl, 1e-3);
    }

    @Test
    public void distance3dThen9() {
        Point a = new Point(0, 5, 0);
        Point b = new Point(4, 1, 7);
        double rsl = a.distance3d(b);
        double expected = 9;
        Assert.assertEquals(expected, rsl, 1e-3);
    }
}