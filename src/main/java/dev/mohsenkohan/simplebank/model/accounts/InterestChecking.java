package dev.mohsenkohan.simplebank.model.accounts;

public class InterestChecking extends CheckingAccount {

    public InterestChecking(int acctNum) {
        super(acctNum);
    }

    @Override
    protected double interestRate() {
        return 0.01;
    }

    @Override
    protected String accountType() {
        return "Interest Checking";
    }
}
