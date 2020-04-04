package dev.mohsenkohan.simplebank.accounts;

public class SavingsAccount extends AbstractBankAccount {

    private double rate = 0.01;

    public SavingsAccount(int acctNum) {
        super(acctNum);
    }

    @Override
    public boolean hasEnoughCollateral(int loanAmt) {
        return balance >= loanAmt / 2;
    }

    @Override
    public void addInterest() {
        balance += (int) (balance * rate);
    }

    @Override
    public String toString() {
        return "Savings Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
