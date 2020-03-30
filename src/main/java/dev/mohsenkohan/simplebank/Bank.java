package dev.mohsenkohan.simplebank;

import java.util.HashMap;
import java.util.Set;

public class Bank {

    private HashMap<Integer,Integer> accounts = new HashMap<>();
    private double rate = 0.01;
    private int nextAcct = 0;

    public int newAccount() {
        int acctNum = nextAcct++;
        accounts.put(acctNum, 0);
        return acctNum;
    }

    public int getBalance(int acctNum) {
        return accounts.get(acctNum);
    }

    public void deposit(int acctNum, int amt) {
        int balance = getBalance(acctNum);
        accounts.put(acctNum, balance + amt);
    }

    public boolean authorizeLoan(int acctNum, int loanAmt) {
        int balance = accounts.get(acctNum);
        return balance >= loanAmt / 2;
    }

    public void addInterest() {
        Set<Integer> acctNums = accounts.keySet();
        for (int i : acctNums) {
            int balance = accounts.get(i);
            int newBalance = (int) (balance * (1 + rate));
            accounts.put(i, newBalance);
        }
    }

    @Override
    public String toString() {
        Set<Integer> acctNums = accounts.keySet();
        String result = "The bank has " + acctNums.size() + " accounts.";
        for (int i : acctNums)
            result += "\n\tAccount " + i + ": balance=" + accounts.get(i);
        return result;
    }
}
