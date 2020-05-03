package dev.mohsenkohan.simplebank.stats.mvc;

import dev.mohsenkohan.simplebank.model.Bank;
import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.stats.visitors.MaxBalanceVisitor;
import dev.mohsenkohan.simplebank.stats.visitors.Visitor;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AccountStatsModel {

    private final Bank bank;

    public AccountStatsModel(Bank bank) {
        this.bank = bank;
    }

    public String getAccounts1() {
        StringBuilder result = new StringBuilder();
        Iterator<BankAccount> iterator = bank.iterator();
        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            result.append(account).append("\n");
        }
        return result.toString();
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

    public String getAccounts2() {
        StringBuilder result = new StringBuilder();
        for (BankAccount account : bank) {
            result.append(account).append("\n");
        }
        return result.toString();
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

    public String getAccounts3() {
        StringBuilder result = new StringBuilder();
        Consumer<BankAccount> action = account -> result.append(account).append("\n");
        bank.forEach(action);
        return result.toString();
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

    public String getAccounts4(Predicate<? super BankAccount> predicate) {
        StringBuilder result = new StringBuilder();
        for (BankAccount ba : bank)
            if (predicate.test(ba))
                result.append(ba).append("\n");
        return result.toString();
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

    public String getAccounts5(Predicate<? super BankAccount> predicate) {
        StringBuilder result = new StringBuilder();
        bank.forEach(account -> {
            if (predicate.test(account))
                result.append(account).append("\n");
        });
        return result.toString();
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

    public String getAccounts6(Predicate<? super BankAccount> predicate) {
        return bank.stream()
                .filter(predicate)
                .map(account -> account.toString() + "\n")
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public int maxBalance6(Predicate<? super BankAccount> predicate) {
        return bank.stream()
                .filter(predicate)
                .map(BankAccount::getBalance)
                .reduce(0, Math::max);
    }
}
