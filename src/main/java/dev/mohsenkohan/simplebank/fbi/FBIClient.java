package dev.mohsenkohan.simplebank.fbi;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.SavedBankInfo;
import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.fbi.adapters.BankAccountAdapter;
import dev.mohsenkohan.simplebank.fbi.adapters.LoanAdapter;
import dev.mohsenkohan.simplebank.loans.Loan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FBIClient {

    public static void main(String[] args) {
        SavedBankInfo info = new SavedBankInfo("target" + File.separator + "bank.info");
        Map<Integer, BankAccount> accounts = info.getAccounts();
        int nextAcct = info.nextAcctNum();
        Bank bank = new Bank(accounts, nextAcct);

        // put account info into a single list
        List<FBIAcctInfo> acctInfos = new ArrayList<>();

        for (BankAccount account : bank) {
            acctInfos.add(new BankAccountAdapter(account));
        }

        for (Loan loan : bank.loans()) {
            acctInfos.add(new LoanAdapter(loan));
        }

        // then process the list
        int count = 0;

        for (FBIAcctInfo acctInfo : acctInfos) {
            if (acctInfo.isForeign() && acctInfo.balance() > 1000.0)
                count++;
        }

        System.out.println("The count is " + count);
    }
}
