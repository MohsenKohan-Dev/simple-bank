package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class QuitCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        scanner.close();
        System.out.println("Goodbye!");
        return -1;
    }

    @Override
    public String toString() {
        return "quit";
    }
}
