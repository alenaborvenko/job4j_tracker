package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankServiceTest {

    @Test
    public void addUser() throws UserNotFoundException {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434"), is(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenEnterInvalidPassport() throws UserNotFoundException, AccountNotFoundException {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertNull(bank.findByRequisite("34", "5546"));
    }

    @Test
    public void addAccount() throws UserNotFoundException, AccountNotFoundException {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("3434", "5546").getBalance(), is(150D));
    }

    @Test
    public void transferMoney() throws UserNotFoundException, AccountNotFoundException {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").getBalance(), is(200D));
    }

    @Test
    public void add2Accounts() throws UserNotFoundException, AccountNotFoundException {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("1111", 150D));
        bank.addAccount(user.getPassport(), new Account("1122", 300D));
        assertThat(bank.findByRequisite("3434", "1122").getBalance(), is(300D));
    }

    @Test
    public void whenTransferAmountMoreBalanceThenFalse() throws UserNotFoundException {
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
    public void whenTransferAnotherUser() throws UserNotFoundException, AccountNotFoundException {
        User user = new User("45", "Bob");
        User user2 = new User("23", "Stive");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user2.getPassport(), new Account("1122", 50D));
        bank.transferMoney(user.getPassport(), "1111",
                user2.getPassport(), "1122", 10);
        assertThat(bank.findByRequisite(user2.getPassport(), "1122").getBalance(), is(60D));
    }

    @Test
    public void whenTransferAccountNotFound() throws UserNotFoundException {
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

    @Test(expected = UserNotFoundException.class)
    public void whenTransferUserNotFound() throws UserNotFoundException {
        User user = new User("45", "Bob");
        User user2 = new User("23", "Stive");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccount(user.getPassport(), new Account("1111", 30D));
        bank.addAccount(user2.getPassport(), new Account("1122", 50D));
        bank.transferMoney(user.getPassport(), "1111", "67", "1132", 10);
    }
}