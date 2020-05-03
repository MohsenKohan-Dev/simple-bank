package dev.mohsenkohan.simplebank.fbi.adapters;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

public class BankAccountAdapter implements FBIAcctInfo {

    private final BankAccount account;

    public BankAccountAdapter(BankAccount account) {
        this.account = account;
    }

    @Override
    public int balance() {
        return account.getBalance() / 100;
    }

    @Override
    public boolean isForeign() {
        return account.isForeign();
    }

    @Override
    public String acctType() {
        return "deposit";
    }
}
