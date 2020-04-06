package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class InterestCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        bank.addInterest();
        return current;
    }

    @Override
    public String toString() {
        return "interest";
    }
}
