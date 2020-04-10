package dev.mohsenkohan.simplebank.accounts.factories;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public interface AccountFactory {

    BankAccount create(int acctNum);

    AccountFactory[] factories = AccountFactories.values();

    static BankAccount createAccount(int type, int acctNum) {
        AccountFactory factory = factories[type - 1];

        return factory.create(acctNum);
    }
}
