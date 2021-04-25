package ru.job4j.bank;

/**
 * Класс, описывающий исключительную ситуацию, когда счет не был найден
 *  * @author Alena Borvenko
 *  * @version 1.0
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
