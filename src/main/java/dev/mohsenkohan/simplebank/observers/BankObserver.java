package dev.mohsenkohan.simplebank.observers;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public interface BankObserver {

    void update(BankEvent event, BankAccount account, int depositAmt);
}
