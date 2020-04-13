package dev.mohsenkohan.simplebank.stats;

import dev.mohsenkohan.simplebank.Bank;
import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AccountStats {

    private final Bank bank;

    public AccountStats(Bank bank) {
        this.bank = bank;
    }

    public void printAccounts1() {
        Iterator<BankAccount> iterator = bank.iterator();
        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            System.out.println(account);
        }
    }

    public int maxBalance1() {
        Iterator<BankAccount> iterator = bank.iterator();
        int max = 0;
        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            int balance = account.getBalance();
            if (balance > max)
                max = balance;
        }
        return max;
    }

    public void printAccounts2() {
        for (BankAccount account : bank) {
            System.out.println(account);
        }
    }

    public int maxBalance2() {
        int max = 0;
        for (BankAccount account : bank) {
            int balance = account.getBalance();
            if (balance > max)
                max = balance;
        }
        return max;
    }

    public void printAccounts3() {
        Consumer<BankAccount> action = System.out::println;
        bank.forEach(action);
    }

    public int maxBalance3a() {
        Visitor<BankAccount, Integer> visitor = new MaxBalanceVisitor();
        bank.forEach(visitor);
        return visitor.result();
    }

    public int maxBalance3b() {
        Visitor<BankAccount, Integer> visitor = new Visitor<>() {
            private int max = 0;

            @Override
            public void accept(BankAccount account) {
                int balance = account.getBalance();
                if (balance > max)
                    max = balance;
            }

            @Override
            public Integer result() {
                return max;
            }
        };
        bank.forEach(visitor);
        return visitor.result();
    }

    public void visit1(Consumer<? super BankAccount> action) {
        bank.forEach(action);
    }

    public <R> R visit2(Visitor<? super BankAccount, R> visitor) {
        bank.forEach(visitor);
        return visitor.result();
    }

    public int maxBalance3c() {
        return visit2(new MaxBalanceVisitor());
    }

    public void printAccounts4(Predicate<? super BankAccount> predicate) {
        for (BankAccount ba : bank)
            if (predicate.test(ba))
                System.out.println(ba);
    }

    public int maxBalance4(Predicate<? super BankAccount> predicate) {
        int max = 0;
        for (BankAccount ba : bank) {
            if (predicate.test(ba)) {
                int balance = ba.getBalance();
                if (balance > max)
                    max = balance;
            }
        }
        return max;
    }

    public void printAccounts5(Predicate<? super BankAccount> predicate) {
        bank.forEach(account -> {
            if (predicate.test(account))
                System.out.println(account);
        });
    }

    public int maxBalance5(Predicate<? super BankAccount> predicate) {
        Visitor<BankAccount, Integer> visitor = new MaxBalanceVisitor();
        bank.forEach(account -> {
            if (predicate.test(account))
                visitor.accept(account);
        });
        return visitor.result();
    }

    public void visit3(Predicate<? super BankAccount> predicate, Consumer<? super BankAccount> action) {
        bank.forEach(account -> {
            if (predicate.test(account))
                action.accept(account);
        });
    }

    public <R> R visit4(Predicate<? super BankAccount> predicate, Visitor<? super BankAccount, R> action) {
        bank.forEach(account -> {
            if (predicate.test(account))
                action.accept(account);
        });
        return action.result();
    }
}
