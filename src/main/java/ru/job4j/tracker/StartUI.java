package ru.job4j.tracker;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        item.setName("First item");
        Tracker tracker = new Tracker();
        tracker.add(item);
        Item itemSecond = new Item("Second item");
        tracker.add(itemSecond);
        Item itemFind = tracker.findById(itemSecond.getId());
        System.out.println(itemFind);
        Item[] findAll = tracker.findAll();
        for (Item elem : findAll) {
            System.out.println(elem);
        }
        Item[] findByName = tracker.findByName("first item");
        for (Item elem : findByName) {
            System.out.println(elem);
        }
    }
}
