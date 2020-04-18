package dev.mohsenkohan.simplebank.accounts.decorators;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public class UnmodifiableBankAccount extends AbstractBankAccountDecorator {

    public UnmodifiableBankAccount(BankAccount account) {
        super(account);
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
    public void addInterest() {
        throw new UnsupportedOperationException();
    }
}
