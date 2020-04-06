package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public class LoanCmd implements InputCommand {

    @Override
    public int execute(Scanner scanner, Bank bank, int current) {
        System.out.print("Enter loan amount: ");
        int loanAmt = scanner.nextInt();

        if (bank.authorizeLoan(current, loanAmt))
            System.out.println("Your loan is approved.");
        else
            System.out.println("Your loan is denied.");

        return current;
    }

    @Override
    public String toString() {
        return "loan";
    }
}
