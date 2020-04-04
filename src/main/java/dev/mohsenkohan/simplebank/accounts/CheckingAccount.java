package dev.mohsenkohan.simplebank.accounts;

public class CheckingAccount extends AbstractBankAccount {

    public CheckingAccount(int acctNum) {
        super(acctNum);
    }

    @Override
    public boolean hasEnoughCollateral(int loanAmt) {
        return balance >= 2 * loanAmt / 3;
    }

    @Override
    public void addInterest() {
    }

    @Override
    public String toString() {
        return "Checking Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
