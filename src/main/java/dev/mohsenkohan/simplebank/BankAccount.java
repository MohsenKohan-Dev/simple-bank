package dev.mohsenkohan.simplebank;

public interface BankAccount {

    int getAcctNum();

    int getBalance();

    boolean isForeign();

    void setForeign(boolean isForeign);

    void deposit(int amt);

    boolean hasEnoughCollateral(int loanAmt);

    void addInterest();

    String toString();
}
