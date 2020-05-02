package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.commands.InputCommand;
import dev.mohsenkohan.simplebank.commands.InputCommands;

import java.util.Scanner;

public class BankClient {

    private final Scanner scanner;
    private final InputController controller;
    private final InputCommand[] commands = InputCommands.values();

    public BankClient(Scanner scanner, InputController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void run() {
        String promptMessage = constructMessage();
        String response = "";

        while (!response.equals("Goodbye!")) {
            System.out.print(promptMessage);
            int cmdNum = scanner.nextInt();
            response = processCommand(cmdNum);
            System.out.println(response);
        }

        scanner.close();
    }

    private String processCommand(int cmdNum) {
        try {

            InputCommand command = commands[cmdNum];
            return command.execute(scanner, controller);

        } catch (IndexOutOfBoundsException e) {
            return "Illegal command!\nTry again.";
        }
    }

    private String constructMessage() {
        int last = commands.length - 1;

        String result = "Enter command (";
        for (int i = 0; i < last; i++)
            result += i + "=" + commands[i] + ", ";
        result += last + "=" + commands[last] + "): ";

        return result;
    }
}
