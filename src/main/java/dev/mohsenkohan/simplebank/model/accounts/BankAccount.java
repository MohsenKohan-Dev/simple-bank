package dev.mohsenkohan.simplebank.model.accounts;

import dev.mohsenkohan.simplebank.model.accounts.factories.AccountFactories;

public interface BankAccount extends Comparable<BankAccount> {

    int getAcctNum();

    int getBalance();

    boolean isForeign();

    void setForeign(boolean isForeign);

    void deposit(int amt);

    boolean hasEnoughCollateral(int loanAmt);

    void addInterest();

    String toString();

    int fee();

    static BankAccount createSavingsWithDeposit(int acctNum, int amt) {
        BankAccount bankAccount = AccountFactories.SAVINGS.create(acctNum);
        bankAccount.deposit(amt);
        return bankAccount;
    }

    default boolean isEmpty() {
        return getBalance() == 0;
    }
}
