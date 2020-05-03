package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.accounts.decorators.SuspiciousBankAccount;
import dev.mohsenkohan.simplebank.accounts.decorators.UnmodifiableBankIterator;
import dev.mohsenkohan.simplebank.accounts.factories.AccountFactory;
import dev.mohsenkohan.simplebank.loans.Loan;
import dev.mohsenkohan.simplebank.loans.authorizers.LoanAuthorizer;
import dev.mohsenkohan.simplebank.observers.BankEvent;
import dev.mohsenkohan.simplebank.observers.BankObserver;

import java.util.*;
import java.util.stream.Stream;

public class Bank implements Iterable<BankAccount> {

    private final Map<Integer, BankAccount> accounts;
    private int nextAcct;
    private final Map<BankEvent, List<BankObserver>> observers = new HashMap<>();

    public Bank(Map<Integer, BankAccount> accounts, int nextAcct) {
        this.accounts = accounts;
        this.nextAcct = nextAcct;
        for (BankEvent event : BankEvent.values()) {
            observers.put(event, new ArrayList<>());
        }
    }

    public void addObserver(BankEvent event, BankObserver observer) {
        observers.get(event).add(observer);
    }

    public void removeObserver(BankEvent event, BankObserver observer) {
        observers.get(event).remove(observer);
    }

    private void notifyObservers(BankEvent event, BankAccount account, int depositAmt) {
        for (BankObserver observer : observers.get(event)) {
            observer.update(event, account, depositAmt);
        }
    }

    public int newAccount(int type, boolean isForeign) {
        int acctNum = nextAcct++;
        BankAccount bankAccount =
                AccountFactory.createAccount(type, acctNum);
        bankAccount.setForeign(isForeign);
        accounts.put(acctNum, bankAccount);
        notifyObservers(BankEvent.NEW, bankAccount, 0);
        return acctNum;
    }

    public int getBalance(int acctNum) {
        BankAccount bankAccount = accounts.get(acctNum);
        return bankAccount.getBalance();
    }

    public void deposit(int acctNum, int amt) {
        BankAccount bankAccount = accounts.get(acctNum);
        bankAccount.deposit(amt);
        notifyObservers(BankEvent.DEPOSIT, bankAccount, amt);
    }

    public boolean authorizeLoan(int acctNum, int loanAmt) {
        BankAccount bankAccount = accounts.get(acctNum);
        LoanAuthorizer authorizer = LoanAuthorizer.getAuthorizer(bankAccount);
        return authorizer.authorizeLoan(loanAmt);
    }

    public void setForeign(int acctNum, boolean isForeign) {
        BankAccount bankAccount = accounts.get(acctNum);
        bankAccount.setForeign(isForeign);
        notifyObservers(BankEvent.FOREIGN, bankAccount, 0);
    }

    public void addInterest() {
        for (BankAccount bankAccount : accounts.values())
            bankAccount.addInterest();
        notifyObservers(BankEvent.INTEREST, null, 0);
    }

    @Override
    public String toString() {
        String result = "The bank has " + accounts.size() + " accounts.";
        for (BankAccount bankAccount : accounts.values())
            result += "\n\t" + bankAccount.toString();
        return result;
    }

    public int nextAcctNum() {
        return nextAcct;
    }

    @Override
    public Iterator<BankAccount> iterator() {
        Iterator<BankAccount> iterator = accounts.values().iterator();
        return new UnmodifiableBankIterator(iterator);
    }

    public Stream<BankAccount> stream() {
        return accounts.values().stream();
    }

    public Collection<Loan> loans() {
        return new ArrayList<>();
    }

    public void makeSuspicious(int acctNum) {
        BankAccount account = accounts.get(acctNum);
        accounts.put(acctNum, new SuspiciousBankAccount(account));
    }

    public boolean isForeign(int acctNum) {
        BankAccount account = accounts.get(acctNum);
        return account.isForeign();
    }
}
