package ru.job4j.bank;

import java.util.*;

/**
 * Класс, описывающий упрощенную работу банковской системы по поиску пользователя в системе,
 * поиска банковского счета и перевода денег с одного счета на другой
 * * @author Alena Borvenko
 * * @version 2.0
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
     * @param account  - какой счет добавить
     */
    public void addAccount(String passport, Account account) {
        User user;
        user = findByPassport(passport);
        List<Account> accountUser = users.get(user);
        if (accountUser != null && !accountUser.contains(account)) {
            accountUser.add(account);
        }
    }

    /**
     * Метод, ищущий пользователя в системе по его паспорту
     * @param passport - паспорт
     * @return - найденный пользователь типа User или null, если пользователь не найден
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод, ищущий счет по паспорту и реквизитам счета
     * @param passport  - паспорт пользователя
     * @param requisite - реквизиты счета
     * @return найденный счет типа Account или null если аккаунт не найден
     */
    public Account findByRequisite(String passport, String requisite) {
        User userFindByPasport = findByPassport(passport);
        if (userFindByPasport != null) {
            return users.get(findByPassport(passport))
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод для перевода денег с одного счета на другой.
     * Если счет, с которого переводят или на который переводят не найден или недостаточно
     * средств у счета с которого переводят, то метод возращает false
     * ( не успешное выполнение метода) и изменение баланса ни у одного из счетов не происходит.
     * Иначе, с одного счета списывается сумма денег, а на другой добавляется эта же сумма.
     *
     * @param srcPassport   - паспорт пользователя со счета которого требуется перевести
     * @param srcRequisite  - реквизиты счета с которого требуется перевести
     * @param destPassport  - паспорт пользователя на счет которого требуется перевести
     * @param destRequisite - реквизиты счета на который нужно перевести
     * @param amount        - сумма перевода
     * @return - true/false (осуществлен ли перевод или нет)
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account sourceAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (sourceAccount != null && destAccount != null
                && sourceAccount.getBalance() >= amount) {

            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return false;
    }
}
