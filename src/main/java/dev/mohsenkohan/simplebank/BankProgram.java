package dev.mohsenkohan.simplebank;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BankProgram {

    private HashMap<Integer,Integer> accounts = new HashMap<>();
    private double rate = 0.01;
    private int nextAcct = 0;
    private int current = -1;
    private Scanner scanner;
    private boolean done = false;

    public static void main(String[] args) {
        BankProgram program = new BankProgram();
        program.run();
    }

    public void run() {
        scanner = new Scanner(System.in);
        while (!done) {
            System.out.print("Enter command (0=quit, 1=new, 2=select, 3=deposit, 4=loan, 5=show, 6=interest): ");
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
        else System.out.println("Illegal command!\nTry again.");
    }

    private void quit() {
        done = true;
        System.out.println("Goodbye!");
    }

    private void newAccount() {
        current = nextAcct++;
        accounts.put(current, 0);
        System.out.println("Your new account number is " + current);
    }

    private void select() {
        System.out.print("Enter account number: ");
        current = scanner.nextInt();
        int balance = accounts.get(current);
        System.out.println("The balance of account " + current + " is " + balance);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        int balance = accounts.get(current);
        accounts.put(current, balance + amt);
    }

    private void authorizeLoan() {
        System.out.print("Enter loan amount: ");
        int loanAmt = scanner.nextInt();
        int balance = accounts.get(current);
        if (balance >= loanAmt / 2)
            System.out.println("Your loan is approved.");
        else
            System.out.println("Your loan is denied.");
    }

    private void showAll() {
        Set<Integer> acctNums = accounts.keySet();
        System.out.println("The bank has " + acctNums.size() + " accounts.");
        for (int i : acctNums)
            System.out.println("\tAccount " + i + ": balance=" + accounts.get(i));
    }

    private void addInterest() {
        Set<Integer> acctNums = accounts.keySet();
        for (int i : acctNums) {
            int balance = accounts.get(i);
            int newBalance = (int) (balance * (1 + rate));
            accounts.put(i, newBalance);
        }
    }
}
