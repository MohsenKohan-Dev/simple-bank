package dev.mohsenkohan.simplebank.accounts;

public abstract class CheckingAccount extends AbstractBankAccount {

    public CheckingAccount(int acctNum) {
        super(acctNum);
    }

    @Override
    public boolean hasEnoughCollateral(int loanAmt) {
        return balance >= 2 * loanAmt / 3;
    }

    public abstract void addInterest();
    public abstract String toString();
}
