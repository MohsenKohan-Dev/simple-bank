package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.commands.*;

import java.util.Scanner;

public class BankClient {

    private int current = 0;
    private boolean done = false;
    private Scanner scanner;
    private Bank bank;
    private InputCommand[] commands = InputCommands.values();

    public BankClient(Scanner scanner, Bank bank) {
        this.scanner = scanner;
        this.bank = bank;
    }

    public void run() {
        String promptMessage = constructMessage();

        while (!done) {
            System.out.print(promptMessage);
            int cmdNum = scanner.nextInt();
            processCommand(cmdNum);
        }

        scanner.close();
    }

    private void processCommand(int cmdNum) {
        try {

            InputCommand command = commands[cmdNum];
            current = command.execute(scanner, bank, current);

            if (current < 0)
                done = true;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Illegal command!\nTry again.");
        }
    }

    private String constructMessage() {
        int last = commands.length-1;

        String result = "Enter command (";
        for (int i = 0; i < last; i++)
            result += i + "=" + commands[i] + ", ";
        result += last + "=" + commands[last] + "): ";

        return result;
    }
}
