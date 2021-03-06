package dev.mohsenkohan.simplebank.model.accounts;

import dev.mohsenkohan.simplebank.model.accounts.owners.OwnerStrategy;
import dev.mohsenkohan.simplebank.model.accounts.owners.Owners;

public abstract class AbstractBankAccount implements BankAccount {

    protected int acctNum;
    protected int balance = 0;
    protected OwnerStrategy owner = Owners.DOMESTIC;

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
        return owner.isForeign();
    }

    @Override
    public void setForeign(boolean isForeign) {
        owner = isForeign ? Owners.FOREIGN : Owners.DOMESTIC;
    }

    @Override
    public void deposit(int amt) {
        balance += amt;
    }

    @Override
    public int fee() {
        return owner.fee();
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
                + ", is " + owner + ", fee=" + fee();
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
