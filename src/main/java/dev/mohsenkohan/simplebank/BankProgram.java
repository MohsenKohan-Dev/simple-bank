package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.observers.Auditor;
import dev.mohsenkohan.simplebank.observers.BankEvent;
import dev.mohsenkohan.simplebank.observers.BankObserver;

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

        BankObserver auditor = new Auditor(bank);

        bank.addObserver(BankEvent.DEPOSIT,
                (event, account, depositAmt) -> {
                    if (depositAmt > 10000000)
                        bank.makeSuspicious(account.getAcctNum());
                }
        );

        InputStream inputStream = System.in;
        Scanner scanner = new Scanner(inputStream);

        InputController controller = new InputController(bank);

        BankClient client = new BankClient(scanner, controller);
        client.run();

        info.saveInfo(accounts, bank.nextAcctNum());
    }
}
