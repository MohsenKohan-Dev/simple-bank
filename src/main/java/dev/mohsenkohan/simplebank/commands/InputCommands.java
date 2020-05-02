package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.InputController;
import dev.mohsenkohan.simplebank.accounts.factories.AccountFactories;
import dev.mohsenkohan.simplebank.accounts.factories.AccountFactory;

import java.util.Scanner;

public enum InputCommands implements InputCommand {

    QUIT("quit", (scanner, controller) -> {
        scanner.close();
        return "Goodbye!";
    }),

    NEW("new", (scanner, controller) -> {
        printMessage();
        int type = scanner.nextInt();
        boolean isForeign = requestForeign(scanner);
        return controller.newCommand(type, isForeign);
    }),

    SELECT("select", (scanner, controller) -> {
        System.out.print("Enter account number: ");
        int acctNum = scanner.nextInt();
        return controller.selectCommand(acctNum);
    }),

    DEPOSIT("deposit", (scanner, controller) -> {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        return controller.depositCommand(amt);
    }),

    LOAN("loan", (scanner, controller) -> {
        System.out.print("Enter loan amount: ");
        int loanAmt = scanner.nextInt();
        return controller.loadCommand(loanAmt);
    }),

    SHOW("show", (scanner, controller) -> {
        return controller.showCommand();
    }),

    INTEREST("interest", (scanner, controller) -> {
        return controller.interestCommand();
    }),

    FOREIGN("foreign", (scanner, controller) -> {
        boolean isForeign = requestForeign(scanner);
        return controller.foreignCommand(isForeign);
    });

    private static boolean requestForeign(Scanner scanner) {
        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        return val == 1;
    }

    private static void printMessage() {
        System.out.print(message);
    }

    private static String message;

    static {
        AccountFactory[] factories = AccountFactories.values();

        message = "Enter account type (";
        for (int i = 0; i < factories.length - 1; i++)
            message += (i + 1) + "=" + factories[i] + ", ";
        message += factories.length + "=" + factories[factories.length - 1] + "): ";
    }

    private final String name;
    private final InputCommand command;

    InputCommands(String name, InputCommand command) {
        this.name = name;
        this.command = command;
    }

    @Override
    public String execute(Scanner scanner, InputController controller) {
        return command.execute(scanner, controller);
    }

    @Override
    public String toString() {
        return name;
    }
}
