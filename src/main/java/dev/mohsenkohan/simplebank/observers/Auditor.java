package dev.mohsenkohan.simplebank.observers;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.accounts.BankAccount;

public class Auditor implements BankObserver {

    public Auditor(Bank bank) {
        bank.addObserver(BankEvent.NEW, this);
        bank.addObserver(BankEvent.FOREIGN, this);
    }

    @Override
    public void update(BankEvent event, BankAccount account, int depositAmt) {
        if (account.isForeign()) {
            if (event == BankEvent.NEW) {
                System.out.println("New Foreign Account: " + account.getAcctNum());
            } else {
                System.out.println("Modified Foreign Account: " + account.getAcctNum());
            }
        }
    }
}
