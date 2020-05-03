package dev.mohsenkohan.simplebank.model.observers;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

public interface BankObserver {

    void update(BankEvent event, BankAccount account, int depositAmt);
}
