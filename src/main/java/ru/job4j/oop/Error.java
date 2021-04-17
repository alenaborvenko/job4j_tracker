package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Active: " + this.active);
        System.out.println("Status: " + this.status);
        System.out.println("Message: " + this.message);
    }

    public static void main(String[] args) {
        Error errorDefault = new Error();
        Error errorActive = new Error(true, 0, "I'm active");
        Error errorDisable = new Error(false, 3, "I'm not active");
        errorDefault.printInfo();
        errorActive.printInfo();
        errorDisable.printInfo();
    }
}
