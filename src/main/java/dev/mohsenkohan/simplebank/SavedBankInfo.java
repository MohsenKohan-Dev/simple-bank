package dev.mohsenkohan.simplebank;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.accounts.RegularChecking;
import dev.mohsenkohan.simplebank.model.accounts.SavingsAccount;
import dev.mohsenkohan.simplebank.model.accounts.factories.AccountFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class SavedBankInfo {

    private String file;
    private Map<Integer, BankAccount> accounts = new HashMap<>();
    private int nextAcct = 0;
    private ByteBuffer buffer = ByteBuffer.allocate(16);

    public SavedBankInfo(String file) {
        this.file = file;

        if (!new File(file).exists())
            return;

        try (InputStream inputStream = new FileInputStream(file)) {

            readMap(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("bank file read exception");
        }
    }

    public Map<Integer, BankAccount> getAccounts() {
        return accounts;
    }

    public int nextAcctNum() {
        return nextAcct;
    }

    public void saveInfo(Map<Integer, BankAccount> accounts, int nextAcctNum) {
        try (OutputStream outputStream = new FileOutputStream(file)) {

            writeMap(outputStream, accounts, nextAcctNum);

        } catch (IOException e) {
            throw new RuntimeException("bank file write exception");
        }
    }

    private void writeMap(OutputStream outputStream, Map<Integer, BankAccount> accounts, int nextAcctNum) throws IOException {
        writeNextAcct(outputStream, nextAcctNum);

        for (BankAccount account : accounts.values()) {
            writeAccount(outputStream, account);
        }
    }

    private void writeNextAcct(OutputStream outputStream, int nextAcctNum) throws IOException {
        buffer.putInt(0, nextAcctNum);
        outputStream.write(buffer.array(), 0, 4);
    }

    private void writeAccount(OutputStream outputStream, BankAccount account) throws IOException {
        int type = account instanceof SavingsAccount ? 1 :
                (account instanceof RegularChecking ? 2 : 3);

        buffer.putInt(0, account.getAcctNum());
        buffer.putInt(4, type);
        buffer.putInt(8, account.getBalance());
        buffer.putInt(12, account.isForeign() ? 1 : 2);

        outputStream.write(buffer.array());
    }

    private void readMap(InputStream inputStream) throws IOException {
        nextAcct = readNextAcct(inputStream);

        BankAccount account = readAccount(inputStream);
        while (account != null) {
            accounts.put(account.getAcctNum(), account);
            account = readAccount(inputStream);
        }
    }

    private int readNextAcct(InputStream inputStream) throws IOException {
        inputStream.read(buffer.array(), 0, 4);
        return buffer.getInt(0);
    }

    private BankAccount readAccount(InputStream inputStream) throws IOException {
        int n = inputStream.read(buffer.array());

        if (n < 0)
            return null;

        int acctNum = buffer.getInt(0);
        int type = buffer.getInt(4);
        int balance = buffer.getInt(8);
        int isForeign = buffer.getInt(12);

        BankAccount account =
                AccountFactory.createAccount(type, acctNum);

        account.deposit(balance);
        account.setForeign(isForeign == 1);

        return account;
    }
}
