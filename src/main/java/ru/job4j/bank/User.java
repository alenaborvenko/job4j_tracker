package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс модель описывающий пользователя банка
 * У пользователя есть поля паспорт (идентифицирующий его в системе банка) и имя
 * @author Alena Borvenko
 * @version 1.0
 */
public class User {
    /**
     * Поле для хранение значения паспорта, тип Sting
     */
    private String passport;
    /**
     * Поле для хранение значения имя, тип Sting
     */
    private String username;

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * метод получения значения паспорта объекта
     * @return - pasport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * метод установки нового значения паспорта объекта
     @param  passport- новый паспорт (стринг)
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * метод получения имени пользователя
     * @return - имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * метод установки нового имени пользователя
     * @param username - новое имя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
