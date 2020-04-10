package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public enum InputCommands implements InputCommand {

    QUIT("quit", (scanner, bank, current) -> {
        scanner.close();
        System.out.println("Goodbye!");
        return -1;
    }),

    NEW("new", (scanner, bank, current) -> {
        System.out.print("Enter account type (1=savings, 2=regular checking, 3=interest checking): ");
        int type = scanner.nextInt();

        boolean isForeign = requestForeign(scanner);

        current = bank.newAccount(type, isForeign);
        System.out.println("Your new account number is " + current);

        return current;
    }),

    SELECT("select", (scanner, bank, current) -> {
        System.out.print("Enter account number: ");
        current = scanner.nextInt();

        int balance = bank.getBalance(current);
        System.out.println("The balance of account " + current + " is " + balance);

        return current;
    }),

    DEPOSIT("deposit", (scanner, bank, current) -> {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        bank.deposit(current, amt);
        return current;
    }),

    LOAN("loan", (scanner, bank, current) -> {
        System.out.print("Enter loan amount: ");
        int loanAmt = scanner.nextInt();

        if (bank.authorizeLoan(current, loanAmt))
            System.out.println("Your loan is approved.");
        else
            System.out.println("Your loan is denied.");

        return current;
    }),

    SHOW("show", (scanner, bank, current) -> {
        System.out.println(bank.toString());
        return current;
    }),

    INTEREST("interest", (scanner, bank, current) -> {
        bank.addInterest();
        return current;
    }),

    FOREIGN("foreign", (scanner, bank, current) -> {
        boolean isForeign = requestForeign(scanner);
        bank.setForeign(current, isForeign);
        return current;
    });

    private static boolean requestForeign(Scanner scanner) {
        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        return val == 1;
    }

    private final String name;
    private final InputCommand command;

    InputCommands(String name, InputCommand command) {
        this.name = name;
        this.command = command;
    }

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        return command.execute(scanner, bank, current);
    }

    @Override
    public String toString() {
        return name;
    }
}
