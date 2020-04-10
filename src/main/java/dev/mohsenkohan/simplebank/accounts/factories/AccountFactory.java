package dev.mohsenkohan.simplebank.accounts.factories;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public interface AccountFactory {

    BankAccount create(int acctNum);

    static BankAccount createAccount(int type, int acctNum) {
        AccountFactory factory;

        if (type == 1)
            factory = new SavingsFactory();
        else if (type == 2)
            factory = new RegularCheckingFactory();
        else
            factory = new InterestCheckingFactory();

        return factory.create(acctNum);
    }
}
