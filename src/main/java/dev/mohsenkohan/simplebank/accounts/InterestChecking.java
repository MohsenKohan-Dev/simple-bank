package dev.mohsenkohan.simplebank.accounts;

public class InterestChecking extends CheckingAccount {

    private double rate = 0.01;

    public InterestChecking(int acctNum) {
        super(acctNum);
    }

    @Override
    public void addInterest() {
        balance += (int) (balance * rate);
    }

    @Override
    public String toString() {
        return "Interest Checking Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
