package dev.mohsenkohan.simplebank.commands;

import dev.mohsenkohan.simplebank.InputController;

import java.util.Scanner;

public interface InputCommand {

    String execute(Scanner scanner, InputController controller);
}
