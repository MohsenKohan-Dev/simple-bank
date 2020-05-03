package dev.mohsenkohan.simplebank.model.loans.authorizers;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;

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
