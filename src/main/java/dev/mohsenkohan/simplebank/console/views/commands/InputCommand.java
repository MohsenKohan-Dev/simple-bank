package dev.mohsenkohan.simplebank.console.views.commands;

import dev.mohsenkohan.simplebank.console.controllers.InputController;

import java.util.Scanner;

public interface InputCommand {

    String execute(Scanner scanner, InputController controller);
}
