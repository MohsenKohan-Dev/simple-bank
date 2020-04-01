package dev.mohsenkohan.simplebank;

public class CheckingAccount implements BankAccount {

    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;

    public CheckingAccount(int acctNum) {
        this.acctNum = acctNum;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isForeign() {
        return isForeign;
    }

    public void setForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    public void deposit(int amt) {
        balance += amt;
    }

    public boolean hasEnoughCollateral(int loanAmt) {
        return balance >= 2 * loanAmt / 3;
    }

    @Override
    public String toString() {
        return "Checking Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
