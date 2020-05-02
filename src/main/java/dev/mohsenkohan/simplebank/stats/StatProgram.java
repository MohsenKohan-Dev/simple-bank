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
        AccountStatsModel model = new AccountStatsModel(bank);

        AccountStatsController controller = new AccountStatsController(model);

        AccountStatsView view = new AccountStatsView(controller);
        view.showStats();
    }
}
