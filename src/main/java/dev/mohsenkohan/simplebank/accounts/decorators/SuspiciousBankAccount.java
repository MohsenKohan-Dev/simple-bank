package dev.mohsenkohan.simplebank.accounts.decorators;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.time.LocalDateTime;

public class SuspiciousBankAccount extends AbstractBankAccountDecorator {

    public SuspiciousBankAccount(BankAccount account) {
        super(account);
    }

    @Override
    public void deposit(int amt) {
        LocalDateTime date = LocalDateTime.now();
        String msg = "On " + date + " account #" + account.getAcctNum() + " deposited " + amt;
        System.out.println(msg);
        account.deposit(amt);
    }

    @Override
    public String toString() {
        return "## " + account.toString();
    }
}
