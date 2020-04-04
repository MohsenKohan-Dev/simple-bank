package dev.mohsenkohan.simplebank.accounts;

public class InterestChecking extends CheckingAccount {

    private double rate = 0.01;

    public InterestChecking(int acctNum) {
        super(acctNum);
    }

    @Override
    public void addInterest() {
        int newBalance = (int) (getBalance() * rate);
        deposit(newBalance);
    }

    @Override
    public String toString() {
        return "Interest Checking Account " + getAcctNum() + ": balance=" + getBalance()
                + ", is " + (isForeign() ? "foreign" : "domestic");
    }
}
