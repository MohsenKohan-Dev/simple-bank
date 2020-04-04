package dev.mohsenkohan.simplebank.accounts;

public class SavingsAccount implements BankAccount {

    private int acctNum;
    private int balance = 0;
    private boolean isForeign = false;
    private double rate = 0.01;

    public SavingsAccount(int acctNum) {
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

    @Override
    public int compareTo(BankAccount bankAccount) {
        return getBalance() - bankAccount.getBalance();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof SavingsAccount))
            return false;
        SavingsAccount savingsAccount = (SavingsAccount) obj;
        return getAcctNum() == savingsAccount.getAcctNum();
    }
}
