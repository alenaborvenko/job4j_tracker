package ru.job4j.bank;

import java.util.*;

/**
 * Класс, описывающий упрощенную работу банковской системы по поиску пользователя в системе,
 * поиска банковского счета и перевода денег с одного счета на другой
 *  * @author Alena Borvenko
 *  * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей, зарегистрированных в системе осуществляется в HashMap
     * Пользователь ассоциирован со списком его счетов
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляющий нового пользователя в систему.
     * Если пользователь уже существует, то повторное добавление не производится
     * @param user - добавляемый пользователь типа User
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляющий к существующему пользователю новый счет
     * Если такой счет уже у пользователя существует, то счет не добавляется
     * @param passport - паспорт пользователя, которому нужно добавить счет
     * @param account - какой счет добавить
     * @throws UserNotFoundException - если пользователя с таким паспортом в системе
     *                   не зарегистрировано, то выкидывается UserNotFoundException
     */
    public void addAccount(String passport, Account account) throws UserNotFoundException {
        User user;
        user = findByPassport(passport);
        List<Account> accountUser = users.get(user);
        if (!accountUser.contains(account)) {
            accountUser.add(account);
        }
    }

    /**
     * Метод, ищущий пользователя в системе по его паспорту
     * @param passport - паспорт
     * @return - найденный пользователь типа User
     * @throws UserNotFoundException - если пользователя с таким паспортом не найдено в системе, то
     *                                      выкидывается UserNotFoundException
     */
    public User findByPassport(String passport) throws UserNotFoundException {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Cant found user by " + passport
                                        + " passport")
                );
    }

    /**
     * Метод, ищущий счет по паспорту и реквизитам счета
     * @param passport - паспорт пользователя
     * @param requisite - реквизиты счета
     * @return найденный счет типа Account
     * @throws AccountNotFoundException - если у пользователя нет счета с заданными реквизитами, то
     *                                      выкидывается AccountNotFoundException
     * @throws UserNotFoundException - если в системе нет пользователя с таким паспортом, то
     *                                  выкидывается UserNotFoundException
     */
    public Account findByRequisite(String passport, String requisite)
            throws AccountNotFoundException, UserNotFoundException {
        return users.get(findByPassport(passport))
                .stream()
                .filter(s -> s.getRequisite().equals(requisite))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account by " + requisite
                        + " not found")
                );
    }

    /**
     * Метод для перевода денег с одного счета на другой.
     * Если счет, с которого переводят или на который переводят не найден или недостаточно
     * средств у счета с которого переводят, то метод возращает false
     * ( не успешное выполнение метода) и изменение баланса ни у одного из счетов не происходит.
     * Иначе, с одного счета списывается сумма денег, а на другой добавляется эта же сумма.
     * @param srcPassport - паспорт пользователя со счета которого требуется перевести
     * @param srcRequisite - реквизиты счета с которого требуется перевести
     * @param destPassport - паспорт пользователя на счет которого требуется перевести
     * @param destRequisite - реквизиты счета на который нужно перевести
     * @param amount - сумма перевода
     * @return - true/false (осуществлен ли перевод или нет)
     * @throws UserNotFoundException - если какой-то из пользователей не найден,
     *                                  то выкидывается UserNotFoundException
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount)
            throws UserNotFoundException {
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
        return true;
    }
}
