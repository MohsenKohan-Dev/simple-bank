package dev.mohsenkohan.simplebank;

public class BankAccount {

    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;
    private double rate = 0.01;

    public BankAccount(int acctNum) {
        this.acctNum = acctNum;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
        return balance >= loanAmt / 2;
    }

    public void addInterest() {
        balance += (int) (balance * rate);
    }

    @Override
    public String toString() {
        return "Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }
}
