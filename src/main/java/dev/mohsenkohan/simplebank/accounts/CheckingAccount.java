package dev.mohsenkohan.simplebank.accounts;

public abstract class CheckingAccount extends AbstractBankAccount {

    public CheckingAccount(int acctNum) {
        super(acctNum);
    }

    @Override
    protected double collateralRatio() {
        return 2.0 / 3.0;
    }

    protected abstract double interestRate();
    protected abstract String accountType();
}
