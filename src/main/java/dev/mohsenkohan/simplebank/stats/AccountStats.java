package dev.mohsenkohan.simplebank.stats;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.util.Iterator;

public class AccountStats {

    private final Bank bank;

    public AccountStats(Bank bank) {
        this.bank = bank;
    }

    public void printAccounts1() {
        Iterator<BankAccount> iterator = bank.iterator();
        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            System.out.println(account);
        }
    }

    public int maxBalance1() {
        Iterator<BankAccount> iterator = bank.iterator();
        int max = 0;
        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            int balance = account.getBalance();
            if (balance > max)
                max = balance;
        }
        return max;
    }

    public void printAccounts2() {
        for (BankAccount account : bank) {
            System.out.println(account);
        }
    }

    public int maxBalance2() {
        int max = 0;
        for (BankAccount account : bank) {
            int balance = account.getBalance();
            if (balance > max)
                max = balance;
        }
        return max;
    }
}
