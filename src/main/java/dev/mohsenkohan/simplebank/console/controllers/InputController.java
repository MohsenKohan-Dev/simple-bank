package dev.mohsenkohan.simplebank.console.controllers;

import dev.mohsenkohan.simplebank.model.Bank;

public class InputController {

    private int current;
    private final Bank bank;

    public InputController(Bank bank) {
        this.bank = bank;
    }

    public String newCommand(int type, boolean isForeign) {
        current = bank.newAccount(type, isForeign);
        return "Your new account number is " + current;
    }

    public String selectCommand(int acctNum) {
        current = acctNum;
        int balance = bank.getBalance(acctNum);
        return "The balance of account " + current + " is " + balance;
    }

    public String depositCommand(int amount) {
        bank.deposit(current, amount);
        return "Amount deposited.";
    }

    public String loadCommand(int amount) {
        boolean ok = bank.authorizeLoan(current, amount);
        return ok ? "Your loan is approved." : "Your loan is denied.";
    }

    public String showCommand() {
        return bank.toString();
    }

    public String interestCommand() {
        bank.addInterest();
        return "Interest added.";
    }

    public String foreignCommand(boolean isForeign) {
        bank.setForeign(current, isForeign);
        return "Account is now " + (isForeign ? "foreign" : "domestic");
    }
}
