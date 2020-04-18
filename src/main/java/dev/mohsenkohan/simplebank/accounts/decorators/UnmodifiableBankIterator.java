package dev.mohsenkohan.simplebank.accounts.decorators;

import dev.mohsenkohan.simplebank.accounts.BankAccount;

import java.util.Iterator;

public class UnmodifiableBankIterator implements Iterator<BankAccount> {

    private final Iterator<BankAccount> iterator;

    public UnmodifiableBankIterator(Iterator<BankAccount> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public BankAccount next() {
        BankAccount account = iterator.next();
        return new UnmodifiableBankAccount(account);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
