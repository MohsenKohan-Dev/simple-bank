package dev.mohsenkohan.simplebank.stats.mvc;

public class AccountStatsView {

    private final AccountStatsController controller;

    public AccountStatsView(AccountStatsController controller) {
        this.controller = controller;
    }

    public void showStats() {
        // external iteration
        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts1());
        System.out.println("The max balance of all accounts is " + controller.maxBalance1());

        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts2());
        System.out.println("The max balance of all accounts is " + controller.maxBalance2());

        // internal iteration
        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts3());
        System.out.println("The max balance of all accounts is " + controller.maxBalance3a());
        System.out.println("The max balance of all accounts is " + controller.maxBalance3b());

        System.out.println("\nHere are the accounts:");
        System.out.println(controller.visit1());
        System.out.println("The max balance of all accounts is " + controller.maxBalance3c());

        // external iteration with predicate
        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts4());
        System.out.println("The max balance of all accounts is " + controller.maxBalance4());

        // internal iteration with predicate
        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts5());
        System.out.println("The max balance of all accounts is " + controller.maxBalance5());

        System.out.println("\nHere are the accounts:");
        System.out.println(controller.visit3());
        System.out.println("The max balance of all accounts is " + controller.visit4());

        // internal iteration using stream
        System.out.println("\nHere are the accounts:");
        System.out.println(controller.getAccounts6());
        System.out.println("The max balance of all accounts is " + controller.maxBalance6());
    }
}
