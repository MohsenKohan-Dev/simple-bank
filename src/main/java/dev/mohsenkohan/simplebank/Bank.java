package dev.mohsenkohan.simplebank;

import java.util.HashMap;

public class Bank {

    private HashMap<Integer,BankAccount> accounts = new HashMap<>();
    private double rate = 0.01;
    private int nextAcct = 0;

    public int newAccount(boolean isForeign) {
        int acctNum = nextAcct++;
        BankAccount bankAccount = new BankAccount(acctNum);
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
        int balance = bankAccount.getBalance();
        bankAccount.setBalance(balance + amt);
    }

    public boolean authorizeLoan(int acctNum, int loanAmt) {
        BankAccount bankAccount = accounts.get(acctNum);
        int balance = bankAccount.getBalance();
        return balance >= loanAmt / 2;
    }

    public void setForeign(int acctNum, boolean isForeign) {
        BankAccount bankAccount = accounts.get(acctNum);
        bankAccount.setForeign(isForeign);
    }

    public void addInterest() {
        for (BankAccount bankAccount : accounts.values()) {
            int balance = bankAccount.getBalance();
            balance += (int) (balance * rate);
            bankAccount.setBalance(balance);
        }
    }

    @Override
    public String toString() {
        String result = "The bank has " + accounts.size() + " accounts.";
        for (BankAccount bankAccount : accounts.values())
            result += "\n\tAccount " + bankAccount.getAcctNum() + ": balance="
                    + bankAccount.getBalance() + ", is "
                    + (bankAccount.isForeign() ? "foreign" : "domestic");
        return result;
    }
}
