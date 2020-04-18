package dev.mohsenkohan.simplebank.loans.authorizers;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

public interface LoanAuthorizer {

    boolean authorizeLoan(int amount);

    static LoanAuthorizer getAuthorizer(BankAccount account) {
        LoanAuthorizer authorizer = new CollateralAuthorizer(account);
        authorizer = new CreditScoreAuthorizer(authorizer);
        authorizer = new GoodCustomerAuthorizer(authorizer);
        return authorizer;
    }
}
