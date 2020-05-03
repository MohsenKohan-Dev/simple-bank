package dev.mohsenkohan.simplebank.gui.controllers;

import dev.mohsenkohan.simplebank.gui.views.AccountInfoView;
import dev.mohsenkohan.simplebank.model.Bank;
import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.observers.BankEvent;
import dev.mohsenkohan.simplebank.model.observers.BankObserver;

public class AccountInfoController implements BankObserver {

    private final Bank bank;
    private AccountInfoView view;
    private int current = -1;

    public AccountInfoController(Bank bank) {
        this.bank = bank;
        bank.addObserver(BankEvent.DEPOSIT, this);
        bank.addObserver(BankEvent.INTEREST, this);
        bank.addObserver(BankEvent.FOREIGN, this);
    }

    public void setView(AccountInfoView view) {
        this.view = view;
    }

    public void selectButtonPressed(String acctNum) {
        current = Integer.parseInt(acctNum);
        view.setBalance(Integer.toString(bank.getBalance(current)));
        view.setForeign(bank.isForeign(current));
    }

    public void depositButtonPressed(String amount) {
        int amt = Integer.parseInt(amount);
        bank.deposit(current, amt);
    }

    public String loanButtonPressed(String amount) {
        int amt = Integer.parseInt(amount);
        boolean result = bank.authorizeLoan(current, amt);
        return result ? "APPROVED" : "DENIED";
    }

    public void foreignButtonPressed(String isForeign) {
        boolean is = isForeign.equals("Foreign");
        bank.setForeign(current, is);
    }

    @Override
    public void update(BankEvent event, BankAccount account, int depositAmt) {
        if (event == BankEvent.FOREIGN && account.getAcctNum() == current) {
            view.setForeign(account.isForeign());
        } else if (event == BankEvent.DEPOSIT && account.getAcctNum() == current) {
            view.setBalance(Integer.toString(account.getBalance()));
        } else if (event == BankEvent.INTEREST && current >= 0) {
            view.setBalance(Integer.toString(bank.getBalance(current)));
        }
    }
}
