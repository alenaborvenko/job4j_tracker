package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Delete item ====");
        int id = input.askInt("Enter id: ");
        if (memTracker.delete(id)) {
            out.println("Delete items was success");
        } else {
            out.println("Error delete item. Not found item with " + id + " id.");
        }
        return true;
    }
}
