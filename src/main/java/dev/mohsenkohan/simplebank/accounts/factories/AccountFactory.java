package dev.mohsenkohan.simplebank.accounts.factories;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.accounts.InterestChecking;
import dev.mohsenkohan.simplebank.accounts.RegularChecking;
import dev.mohsenkohan.simplebank.accounts.SavingsAccount;

public interface AccountFactory {

    static SavingsAccount createSavings(int acctNum) {
        return new SavingsAccount(acctNum);
    }

    static RegularChecking createRegularChecking(int acctNum) {
        return new RegularChecking(acctNum);
    }

    static InterestChecking createInterestChecking(int acctNum) {
        return new InterestChecking(acctNum);
    }

    static BankAccount createAccount(int type, int acctNum) {
        BankAccount account;

        if (type == 1)
            account = createSavings(acctNum);
        else if (type == 2)
            account = createRegularChecking(acctNum);
        else
            account = createInterestChecking(acctNum);

        return account;
    }
}
