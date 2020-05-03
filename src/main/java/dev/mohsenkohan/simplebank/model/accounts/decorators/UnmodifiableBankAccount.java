package dev.mohsenkohan.simplebank.model.accounts.decorators;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

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
