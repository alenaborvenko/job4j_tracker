package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            while (select > 6 || select < 0) {
                System.out.print("Enter menu item from 0 to 6: ");
                select = Integer.parseInt(scanner.nextLine());
            }
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("=== Show all items ===");
                Item[] allItem = tracker.findAll();
                if (allItem.length > 0) {
                    for (Item item : allItem) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println(" No item ");
                }
            } else if (select == 2) {
                System.out.println("=== Edit item ===");
                System.out.print("Enter id item: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter name item: ");
                String nameEdit = scanner.nextLine();
                Item itemEdit = new Item(nameEdit);
                if (tracker.replace(idEdit, itemEdit)) {
                    System.out.println("The edit was success");
                } else {
                    System.out.println("The edit wasn't success. Not found item by "
                            + idEdit + " id");
                }
            } else if (select == 3) {
                System.out.println("=== Delete ===");
                System.out.print("Enter id delete item:");
                int idDelete = Integer.parseInt(scanner.nextLine());
                if (tracker.delete(idDelete)) {
                    System.out.println("The delete was success");
                } else {
                    System.out.println("The delete wasn't success. Not found item by "
                            + idDelete + " id");
                }
            } else if (select == 4) {
                System.out.println("=== Find by id ===");
                System.out.print("Enter id item: ");
                int idFind = Integer.parseInt(scanner.nextLine());
                Item itemFind = tracker.findById(idFind);
                if (itemFind != null) {
                    System.out.println(itemFind);
                } else {
                    System.out.println("Item not found with " + idFind + " id");
                }
            } else if (select == 5) {
                System.out.println("=== Find items by name ===");
                System.out.print("Enter name item: ");
                String name = scanner.nextLine();
                Item[] itemsFind = tracker.findByName(name);
                if (itemsFind.length > 0) {
                    for (Item item : itemsFind) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("Not found items with name " + name);
                }
            } else {
                System.out.println("Good Bye!");
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all Items");
        System.out.println("2. Edit Item");
        System.out.println("3. Delete Item");
        System.out.println("4. Find Item by Id");
        System.out.println("5. Find Item by Name");
        System.out.println("6. Exit program");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
