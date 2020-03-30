package dev.mohsenkohan.simplebank;

public class BankAccount {

    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;

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
}
