package dev.mohsenkohan.simplebank.accounts.owners;

public interface OwnerStrategy {

    boolean isForeign();

    int fee();
}
