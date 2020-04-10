package dev.mohsenkohan.simplebank.accounts.factories;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.accounts.InterestChecking;

public class InterestCheckingFactory implements AccountFactory {

    @Override
    public BankAccount create(int acctNum) {
        return new InterestChecking(acctNum);
    }
}
