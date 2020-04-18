package dev.mohsenkohan.simplebank.accounts.decorators;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public class UnmodifiableBankAccount implements BankAccount {

    private final BankAccount account;

    public UnmodifiableBankAccount(BankAccount account) {
        this.account = account;
    }

    @Override
    public int getAcctNum() {
        return account.getAcctNum();
    }

    @Override
    public int getBalance() {
        return account.getBalance();
    }

    @Override
    public boolean isForeign() {
        return account.isForeign();
    }

    @Override
    public void setForeign(boolean isForeign) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deposit(int amt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasEnoughCollateral(int loanAmt) {
        return account.hasEnoughCollateral(loanAmt);
    }

    @Override
    public void addInterest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int fee() {
        return account.fee();
    }

    @Override
    public int compareTo(BankAccount o) {
        return account.compareTo(o);
    }

    @Override
    public String toString() {
        return account.toString();
    }
}
