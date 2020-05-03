package dev.mohsenkohan.simplebank.console;

import dev.mohsenkohan.simplebank.SavedBankInfo;
import dev.mohsenkohan.simplebank.console.controllers.InputController;
import dev.mohsenkohan.simplebank.console.views.BankClient;
import dev.mohsenkohan.simplebank.model.Bank;
import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.observers.Auditor;
import dev.mohsenkohan.simplebank.model.observers.BankEvent;
import dev.mohsenkohan.simplebank.model.observers.BankObserver;

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
