package dev.mohsenkohan.simplebank.stats.visitors;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

public class ShowAccountVisitor implements Visitor<BankAccount, String> {

    private final StringBuilder result = new StringBuilder();

    @Override
    public void accept(BankAccount account) {
        result.append(account).append("\n");
    }

    @Override
    public String result() {
        return result.toString();
    }
}
