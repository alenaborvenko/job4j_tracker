package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Edit item ====");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (memTracker.replace(id, item)) {
            out.println("Edit was success.");
        } else {
            out.println("Edit wasn't success. No item with " + id + " id.");
        }
        return true;
    }
}
