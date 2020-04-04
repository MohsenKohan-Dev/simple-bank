package dev.mohsenkohan.simplebank.accounts;

public class RegularChecking extends CheckingAccount {

    public RegularChecking(int acctNum) {
        super(acctNum);
    }

    @Override
    public void addInterest() {
    }

    @Override
    public String toString() {
        return "Regular Checking Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
