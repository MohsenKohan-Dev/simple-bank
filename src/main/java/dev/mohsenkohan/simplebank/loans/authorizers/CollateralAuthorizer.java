package dev.mohsenkohan.simplebank.loans.authorizers;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public class CollateralAuthorizer implements LoanAuthorizer {

    private final BankAccount account;

    public CollateralAuthorizer(BankAccount account) {
        this.account = account;
    }

    @Override
    public boolean authorizeLoan(int amount) {
        return account.hasEnoughCollateral(amount);
    }
}
