package dev.mohsenkohan.simplebank.fbi.adapters;

public interface FBIAcctInfo {

    int balance();

    boolean isForeign();

    String acctType();
}
