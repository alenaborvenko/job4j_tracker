package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode = new Book("Clean code", 464);
        Book spring = new Book("Spring in action", 748);
        Book philosophy = new Book("Philosophy java", 750);
        Book master = new Book("The Master and Margarita", 512);
        Book[] allBook = new Book[4];
        allBook[0] = spring;
        allBook[1] = philosophy;
        allBook[2] = master;
        allBook[3] = cleanCode;
        for (int i = 0; i < allBook.length; i++) {
            Book book = allBook[i];
            System.out.println(book.getName() + " " + book.getPages() + " pages");
        }
        Book book = allBook[0];
        allBook[0] = allBook[3];
        allBook[3] = book;
        for (int i = 0; i < allBook.length; i++) {
            book = allBook[i];
            System.out.println(book.getName() + " " + book.getPages() + " pages");
        }
        for (int i = 0; i < allBook.length; i++) {
            book = allBook[i];
            if (book.getName().equals("Clean code")) {
                System.out.println(book.getName() + " " + book.getPages() + " pages");
            }
        }
    }
}
