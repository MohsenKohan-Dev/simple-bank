package dev.mohsenkohan.simplebank.accounts.factories;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.accounts.SavingsAccount;

public class SavingsFactory implements AccountFactory {

    @Override
    public BankAccount create(int acctNum) {
        return new SavingsAccount(acctNum);
    }
}
