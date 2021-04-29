package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BankServiceTest {

    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434").get(), is(user));
    }

    @Test
    public void whenEnterInvalidPassportThenNullValue() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertFalse(bank.findByRequisite("34", "5546").isPresent());
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("3434", "5546").get().getBalance(), is(150D));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").get().getBalance(), is(200D));
    }

    @Test
    public void add2Accounts() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("1111", 150D));
        bank.addAccount(user.getPassport(), new Account("1122", 300D));
        assertThat(bank.findByRequisite("3434", "1122").get().getBalance(), is(300D));
    }

    @Test
    public void whenTransferAmountMoreBalanceThenFalse() {
        User user = new User("45", "Bob");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user.getPassport(), new Account("1122", 50D));
        boolean actual = bank.transferMoney(user.getPassport(), "1111",
                user.getPassport(), "1122", 40);
        assertFalse(actual);
    }

    @Test
    public void whenTransferAnotherUser() {
        User user = new User("45", "Bob");
        User user2 = new User("23", "Stive");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user2.getPassport(), new Account("1122", 50D));
        bank.transferMoney(user.getPassport(), "1111",
                user2.getPassport(), "1122", 10);
        assertThat(bank.findByRequisite(user2.getPassport(), "1122").get().getBalance(), is(60D));
    }

    @Test
    public void whenTransferAccountNotFound() {
        User user = new User("45", "Bob");
        User user2 = new User("23", "Stive");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user2.getPassport(), new Account("1122", 50D));
        boolean astual = bank.transferMoney(user.getPassport(), "1111",
                user2.getPassport(), "1132", 10);
        assertFalse(astual);
    }

    @Test
    public void whenTransferUserNotFound() {
        User user = new User("45", "Bob");
        User user2 = new User("23", "Stive");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user2.getPassport(), new Account("1122", 50D));
        boolean actual = bank.transferMoney(user.getPassport(), "1111",
                "67", "1132", 10);
        assertFalse(actual);
    }
}