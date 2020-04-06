package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class SelectCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.print("Enter account number: ");
        current = scanner.nextInt();

        int balance = bank.getBalance(current);
        System.out.println("The balance of account " + current + " is " + balance);

        return current;
    }

    @Override
    public String toString() {
        return "select";
    }
}
