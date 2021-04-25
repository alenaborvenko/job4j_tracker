package ru.job4j.bank;

/**
 * Класс, описывающий исключительную ситуацию, когда пользователь не был найден в системе
 *  * @author Alena Borvenko
 *  * @version 1.0
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
