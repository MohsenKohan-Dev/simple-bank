package dev.mohsenkohan.simplebank.gui.controllers;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.observers.BankEvent;
import dev.mohsenkohan.simplebank.observers.BankObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShowAllAccountsController implements BankObserver {

    private final Bank bank;
    private final ObservableList<BankAccount> observableAccounts = FXCollections.observableArrayList();

    public ShowAllAccountsController(Bank bank) {
        this.bank = bank;

        bank.addObserver(BankEvent.NEW, this);
        bank.addObserver(BankEvent.DEPOSIT, this);
        bank.addObserver(BankEvent.INTEREST, this);
        bank.addObserver(BankEvent.FOREIGN, this);

        for (BankAccount account : bank) {
            observableAccounts.add(account);
        }
    }

    public ObservableList<BankAccount> getObservableAccounts() {
        return observableAccounts;
    }

    public void interestButtonPressed() {
        bank.addInterest();
    }

    @Override
    public void update(BankEvent event, BankAccount account, int depositAmt) {
        if (event == BankEvent.INTEREST) {
            refreshAllAccounts();
        } else if (event == BankEvent.NEW) {
            observableAccounts.add(account);
        } else {
            refreshAccount(observableAccounts.indexOf(account));
        }
    }

    private void refreshAccount(int index) {
        observableAccounts.set(index, observableAccounts.get(index));
    }

    private void refreshAllAccounts() {
        for (int index = 0; index < observableAccounts.size(); index++) {
            refreshAccount(index);
        }
    }
}
