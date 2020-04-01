package dev.mohsenkohan.simplebank.accounts;

public class CheckingAccount implements BankAccount {

    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;

    public CheckingAccount(int acctNum) {
        this.acctNum = acctNum;
    }

    @Override
    public int getAcctNum() {
        return acctNum;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean isForeign() {
        return isForeign;
    }

    @Override
    public void setForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    @Override
    public void deposit(int amt) {
        balance += amt;
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

    @Override
    public int compareTo(BankAccount bankAccount) {
        return getBalance() - bankAccount.getBalance();
    }
}
