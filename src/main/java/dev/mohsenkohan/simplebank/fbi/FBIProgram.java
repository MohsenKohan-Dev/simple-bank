package dev.mohsenkohan.simplebank.fbi;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.SavedBankInfo;
import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.io.File;
import java.util.Map;

public class FBIProgram {

    public static void main(String[] args) {
        SavedBankInfo info = new SavedBankInfo("target" + File.separator + "bank.info");
        Map<Integer, BankAccount> accounts = info.getAccounts();
        int nextAcct = info.nextAcctNum();

        Bank bank = new Bank(accounts, nextAcct);
        FBIModel model = new FBIModel(bank);

        FBIController controller = new FBIController(model);

        FBIView view = new FBIView(controller);
        view.showResult();
    }
}
