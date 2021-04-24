package ru.job4j.bank;

import java.util.*;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) throws UserNotFoundException {
        User user;
        user = findByPassport(passport);
        List<Account> accountUser = users.get(user);
        if (!accountUser.contains(account)) {
            accountUser.add(account);
        }
    }

    public User findByPassport(String passport) throws UserNotFoundException {
        User userFound = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                userFound = user;
                break;
            }
        }
        if (userFound == null) {
            throw new UserNotFoundException("Cant found user by " + passport + " passport");
        }
        return userFound;
    }

    public Account findByRequisite(String passport, String requisite)
            throws AccountNotFoundException, UserNotFoundException {
        Account accountFound = null;
        User userFound;
        userFound = findByPassport(passport);
        List<Account> accountUser = users.get(userFound);
        for (Account account : accountUser) {
            if (account.getRequisite().equals(requisite)) {
                accountFound = account;
                break;
            }
        }
        if (accountFound == null) {
            throw new AccountNotFoundException("Account by " + requisite + " not found");
        }
        return accountFound;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount)
            throws UserNotFoundException {
        boolean rsl = true;
        Account sourceAccount;
        Account destAccount;
        try {
            sourceAccount = findByRequisite(srcPassport, srcRequisite);
            destAccount = findByRequisite(destPassport, destRequisite);
            if (sourceAccount.getBalance() < amount) {
                return false;
            }
        } catch (AccountNotFoundException ex) {
            return false;
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        User sourceUser = findByPassport(srcPassport);
        User destUser = findByPassport(destPassport);
        List<Account> sourceAccountList = users.get(sourceUser);
        int index = sourceAccountList.indexOf(sourceAccount);
        sourceAccountList.set(index, sourceAccount);
        List<Account> destAccountList = users.get(destUser);
        index = destAccountList.indexOf(destAccount);
        destAccountList.set(index, destAccount);
        users.put(sourceUser, sourceAccountList);
        users.put(destUser, destAccountList);
        return rsl;
    }
}
