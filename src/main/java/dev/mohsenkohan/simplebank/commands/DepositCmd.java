package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class DepositCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        bank.deposit(current, amt);
        return current;
    }

    @Override
    public String toString() {
        return "deposit";
    }
}
