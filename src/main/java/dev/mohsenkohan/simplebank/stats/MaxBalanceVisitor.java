package dev.mohsenkohan.simplebank.stats;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public class MaxBalanceVisitor implements Visitor<BankAccount, Integer> {

    private int max = 0;

    @Override
    public void accept(BankAccount account) {
        int balance = account.getBalance();
        if (balance > max)
            max = balance;
    }

    @Override
    public Integer result() {
        return max;
    }
}
