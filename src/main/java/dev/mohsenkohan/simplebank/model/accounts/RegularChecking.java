package dev.mohsenkohan.simplebank.model.accounts;

public class RegularChecking extends CheckingAccount {

    public RegularChecking(int acctNum) {
        super(acctNum);
    }

    @Override
    protected double interestRate() {
        return 0.0;
    }

    @Override
    protected String accountType() {
        return "Regular Checking";
    }
}
