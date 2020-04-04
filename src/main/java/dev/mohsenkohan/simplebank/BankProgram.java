package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class BankProgram {

    public static void main(String[] args) {
        SavedBankInfo info = new SavedBankInfo("target" + File.separator + "bank.info");

        Map<Integer, BankAccount> accounts = info.getAccounts();
        int nextAcct = info.nextAcctNum();

        Bank bank = new Bank(accounts, nextAcct);

        InputStream inputStream = System.in;
        Scanner scanner = new Scanner(inputStream);

        BankClient client = new BankClient(scanner, bank);
        client.run();

        info.saveInfo(accounts, bank.nextAcctNum());
    }
}
