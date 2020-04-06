package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class ShowCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.println(bank.toString());
        return current;
    }

    @Override
    public String toString() {
        return "show";
    }
}
