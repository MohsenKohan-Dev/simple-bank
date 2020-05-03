package dev.mohsenkohan.simplebank.stats.visitors;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

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
