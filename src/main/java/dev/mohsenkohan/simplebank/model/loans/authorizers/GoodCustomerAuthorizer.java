package dev.mohsenkohan.simplebank.model.loans.authorizers;

import java.util.concurrent.ThreadLocalRandom;

public class GoodCustomerAuthorizer extends AbstractLoanAuthorizerDecorator {

    private final boolean isGood;

    public GoodCustomerAuthorizer(LoanAuthorizer authorizer) {
        super(authorizer);

        // for simplicity, mock up the customer status
        // associated with the owner of the bank account.
        ThreadLocalRandom random = ThreadLocalRandom.current();
        isGood = random.nextBoolean();
    }

    @Override
    public boolean authorizeLoan(int amount) {
        if (isGood && amount < 200000)
            return true;
        else
            return authorizer.authorizeLoan(amount);
    }
}
