package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс, описывающий бансковский счет (модель банковского счета)
 * У счета есть поля balance и requisite и публичные методы для доступа к ним
 * @author Borvenko Alena
 * @version 1.0
 */
public class Account {
    /**
     * requisite - поле для хранения реквизита счет (номер счета) типа String
     */
    private String requisite;
    /**
     * balance - отражает состояние баланса счета
     */
    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод, получающий значение поля реквизит счета
     * @return - значение поля requisite
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод установки нового значения в поле реквизит
     * @param requisite - String, новое значение поля
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод, узнающий значение баланса счета
     * @return - double баланс счета
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод, изменяющий значение баланса
     * @param balance - double новый баланс
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
