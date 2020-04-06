package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.Bank;

import java.util.Scanner;

public interface InputCommand {

    int execute(Scanner scanner, Bank bank, int current);
}
