package dev.mohsenkohan.simplebank.fbi.mvc;

import dev.mohsenkohan.simplebank.fbi.adapters.BankAccountAdapter;
import dev.mohsenkohan.simplebank.fbi.adapters.FBIAcctInfo;
import dev.mohsenkohan.simplebank.fbi.adapters.LoanAdapter;
import dev.mohsenkohan.simplebank.model.Bank;
import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.loans.Loan;

import java.util.ArrayList;
import java.util.List;

public class FBIModel {

    private final Bank bank;
    private final List<FBIAcctInfo> acctInfos = new ArrayList<>();

    public FBIModel(Bank bank) {
        this.bank = bank;
        init();
    }

    private void init() {
        for (BankAccount account : bank) {
            acctInfos.add(new BankAccountAdapter(account));
        }

        for (Loan loan : bank.loans()) {
            acctInfos.add(new LoanAdapter(loan));
        }
    }

    public String processInfo() {
        int count = 0;
        for (FBIAcctInfo acctInfo : acctInfos) {
            if (acctInfo.isForeign() && acctInfo.balance() > 1000.0)
                count++;
        }
        return "The count is " + count;
    }
}
