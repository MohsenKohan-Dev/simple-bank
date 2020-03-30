package dev.mohsenkohan.simplebank;

import java.util.Scanner;

public class BankClient {

    private int current = -1;
    private boolean done = false;
    private Scanner scanner = new Scanner(System.in);
    private Bank bank = new Bank();

    public void run() {
        while (!done) {
            System.out.print("Enter command (0=quit, 1=new, 2=select, 3=deposit, " +
                    "4=loan, 5=show, 6=interest, 7=foreign): ");
            int cmdNum = scanner.nextInt();
            processCommand(cmdNum);
        }
        scanner.close();
    }

    private void processCommand(int cmdNum) {
        if      (cmdNum == 0) quit();
        else if (cmdNum == 1) newAccount();
        else if (cmdNum == 2) select();
        else if (cmdNum == 3) deposit();
        else if (cmdNum == 4) authorizeLoan();
        else if (cmdNum == 5) showAll();
        else if (cmdNum == 6) addInterest();
        else if (cmdNum == 7) setForeign();
        else System.out.println("Illegal command!\nTry again.");
    }

    private void quit() {
        done = true;
        System.out.println("Goodbye!");
    }

    private void newAccount() {
        boolean isForeign = requestForeign();
        current = bank.newAccount(isForeign);
        System.out.println("Your new account number is " + current);
    }

    private void select() {
        System.out.print("Enter account number: ");
        current = scanner.nextInt();
        int balance = bank.getBalance(current);
        System.out.println("The balance of account " + current + " is " + balance);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        bank.deposit(current, amt);
    }

    private void authorizeLoan() {
        System.out.print("Enter loan amount: ");
        int loanAmt = scanner.nextInt();
        if (bank.authorizeLoan(current, loanAmt))
            System.out.println("Your loan is approved.");
        else
            System.out.println("Your loan is denied.");
    }

    private void showAll() {
        System.out.println(bank.toString());
    }

    private void addInterest() {
        bank.addInterest();
    }

    private void setForeign() {
        bank.setForeign(current, requestForeign());
    }

    private boolean requestForeign() {
        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        return val == 1;
    }
}
