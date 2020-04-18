package dev.mohsenkohan.simplebank.loans.authorizers;

public abstract class AbstractLoanAuthorizerDecorator implements LoanAuthorizer {

    protected final LoanAuthorizer authorizer;

    protected AbstractLoanAuthorizerDecorator(LoanAuthorizer authorizer) {
        this.authorizer = authorizer;
    }

    @Override
    public boolean authorizeLoan(int amount) {
        return authorizer.authorizeLoan(amount);
    }
}
