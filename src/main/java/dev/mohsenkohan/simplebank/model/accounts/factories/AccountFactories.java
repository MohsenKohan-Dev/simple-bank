package dev.mohsenkohan.simplebank.model.accounts.factories;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.accounts.InterestChecking;
import dev.mohsenkohan.simplebank.model.accounts.RegularChecking;
import dev.mohsenkohan.simplebank.model.accounts.SavingsAccount;

public enum AccountFactories implements AccountFactory {

    SAVINGS("savings", SavingsAccount::new),

    REGULAR_CHECKING("regular checking", RegularChecking::new),

    INTEREST_CHECKING("interest checking", InterestChecking::new);

    private final String name;
    private final AccountFactory accountFactory;

    AccountFactories(String name, AccountFactory accountFactory) {
        this.name = name;
        this.accountFactory = accountFactory;
    }

    @Override
    public BankAccount create(int acctNum) {
        return accountFactory.create(acctNum);
    }

    @Override
    public String toString() {
        return name;
    }
}
