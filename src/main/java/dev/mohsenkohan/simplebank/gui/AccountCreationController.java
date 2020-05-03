package dev.mohsenkohan.simplebank.gui;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.observers.BankEvent;
import dev.mohsenkohan.simplebank.observers.BankObserver;

public class AccountCreationController implements BankObserver {

    private final Bank bank;
    private AccountCreationView view;

    public AccountCreationController(Bank bank) {
        this.bank = bank;
        bank.addObserver(BankEvent.NEW, this);
    }

    void setView(AccountCreationView view) {
        this.view = view;
    }

    public void buttonPressed(int type, boolean isForeign) {
        bank.newAccount(type + 1, isForeign);
    }

    @Override
    public void update(BankEvent event, BankAccount account, int depositAmt) {
        view.setTitle("Account #" + account.getAcctNum() + " created");
    }
}
