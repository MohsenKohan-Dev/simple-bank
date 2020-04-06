package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class ForeignCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        boolean isForeign = (val == 1);
        bank.setForeign(current, isForeign);
        return current;
    }

    @Override
    public String toString() {
        return "foreign";
    }
}
