package dev.mohsenkohan.simplebank.model.loans.authorizers;

import java.util.concurrent.ThreadLocalRandom;

public class CreditScoreAuthorizer extends AbstractLoanAuthorizerDecorator {

    private final int score;

    public CreditScoreAuthorizer(LoanAuthorizer authorizer) {
        super(authorizer);

        // for simplicity, mock up the credit score
        // associated with the owner of the bank account.
        ThreadLocalRandom random = ThreadLocalRandom.current();
        score = 300 + random.nextInt(500);
    }

    @Override
    public boolean authorizeLoan(int amount) {
        if (score > 700 && amount < 1000000)
            return true;
        else if (score < 500)
            return false;
        else
            return authorizer.authorizeLoan(amount);
    }
}
