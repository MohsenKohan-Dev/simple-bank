package dev.mohsenkohan.simplebank;

import java.util.HashMap;

public class Bank {

    private HashMap<Integer, BankAccount> accounts;
    private int nextAcct;

    public Bank(HashMap<Integer, BankAccount> accounts, int nextAcct) {
        this.accounts = accounts;
        this.nextAcct = nextAcct;
    }

    public int newAccount(int type, boolean isForeign) {
        int acctNum = nextAcct++;
        BankAccount bankAccount;

        if (type == 1)
            bankAccount = new SavingsAccount(acctNum);
        else
            bankAccount = new CheckingAccount(acctNum);

        bankAccount.setForeign(isForeign);
        accounts.put(acctNum, bankAccount);
        return acctNum;
    }

    public int getBalance(int acctNum) {
        BankAccount bankAccount = accounts.get(acctNum);
        return bankAccount.getBalance();
    }

    public void deposit(int acctNum, int amt) {
        BankAccount bankAccount = accounts.get(acctNum);
        bankAccount.deposit(amt);
    }

    public boolean authorizeLoan(int acctNum, int loanAmt) {
        BankAccount bankAccount = accounts.get(acctNum);
        return bankAccount.hasEnoughCollateral(loanAmt);
    }

    public void setForeign(int acctNum, boolean isForeign) {
        BankAccount bankAccount = accounts.get(acctNum);
        bankAccount.setForeign(isForeign);
    }

    public void addInterest() {
        for (BankAccount bankAccount : accounts.values())
            bankAccount.addInterest();
    }

    @Override
    public String toString() {
        String result = "The bank has " + accounts.size() + " accounts.";
        for (BankAccount bankAccount : accounts.values())
            result += "\n\t" + bankAccount.toString();
        return result;
    }
}
