package dev.mohsenkohan.simplebank;

public class SavingsAccount implements BankAccount {
    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;
    private double rate = 0.01;

    public SavingsAccount(int acctNum) {
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
        return balance >= loanAmt / 2;
    }

    @Override
    public String toString() {
        return "Savings Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }

    public void addInterest() {
        balance += (int) (balance * rate);
    }
}
