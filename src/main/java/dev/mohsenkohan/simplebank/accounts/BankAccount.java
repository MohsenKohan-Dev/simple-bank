package dev.mohsenkohan.simplebank.accounts;

public interface BankAccount extends Comparable<BankAccount> {

    int getAcctNum();

    int getBalance();

    boolean isForeign();

    void setForeign(boolean isForeign);

    void deposit(int amt);

    boolean hasEnoughCollateral(int loanAmt);

    void addInterest();

    String toString();

    static BankAccount createSavingsWithDeposit(int acctNum, int amt) {
        BankAccount bankAccount = new SavingsAccount(acctNum);
        bankAccount.deposit(amt);
        return bankAccount;
    }

    default boolean isEmpty() {
        return getBalance() == 0;
    }
}
