package dev.mohsenkohan.simplebank.stats;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.SavedBankInfo;
import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.io.File;
import java.util.Map;

public class StatProgram {

    public static void main(String[] args) {
        SavedBankInfo info = new SavedBankInfo("target" + File.separator + "bank.info");
        Map<Integer, BankAccount> accounts = info.getAccounts();
        int nextAcct = info.nextAcctNum();
        Bank bank = new Bank(accounts, nextAcct);
        AccountStats stats = new AccountStats(bank);

        // external iteration
        System.out.println("\nHere are the accounts:");
        stats.printAccounts1();
        System.out.println("The max balance of all accounts is " + stats.maxBalance1());

        System.out.println("\nHere are the accounts:");
        stats.printAccounts2();
        System.out.println("The max balance of all accounts is " + stats.maxBalance2());

        // internal iteration
        System.out.println("\nHere are the accounts:");
        stats.printAccounts3();
        System.out.println("The max balance of all accounts is " + stats.maxBalance3a());
        System.out.println("The max balance of all accounts is " + stats.maxBalance3b());

        System.out.println("\nHere are the accounts:");
        stats.visit1(System.out::println);
        System.out.println("The max balance of all accounts is " + stats.maxBalance3c());
    }
}
