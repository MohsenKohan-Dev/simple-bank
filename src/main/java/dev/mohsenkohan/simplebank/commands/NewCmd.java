package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class NewCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.print("Enter account type (1=savings, 2=regular checking, 3=interest checking): ");
        int type = scanner.nextInt();

        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        boolean isForeign = (val == 1);

        current = bank.newAccount(type, isForeign);
        System.out.println("Your new account number is " + current);

        return current;
    }

    @Override
    public String toString() {
        return "new";
    }
}
