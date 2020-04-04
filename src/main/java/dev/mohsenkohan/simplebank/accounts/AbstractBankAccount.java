package dev.mohsenkohan.simplebank.accounts;

public abstract class AbstractBankAccount implements BankAccount {

    protected int acctNum;
    protected int balance = 0;
    protected boolean isForeign = false;

    public AbstractBankAccount(int acctNum) {
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
        double ratio = collateralRatio();
        return balance >= loanAmt * ratio;
    }

    @Override
    public void addInterest() {
        balance += (int) (balance * interestRate());
    }

    @Override
    public int compareTo(BankAccount bankAccount) {
        return balance - bankAccount.getBalance();
    }

    @Override
    public String toString() {
        String type = accountType();
        return type + " Account " + acctNum + ": balance=" + balance
                + ", is " + (isForeign ? "foreign" : "domestic");
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof BankAccount))
            return false;
        BankAccount bankAccount = (BankAccount) obj;
        return acctNum == bankAccount.getAcctNum();
    }

    protected abstract double collateralRatio();
    protected abstract double interestRate();
    protected abstract String accountType();
}
