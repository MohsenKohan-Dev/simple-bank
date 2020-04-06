package dev.mohsenkohan.simplebank.accounts.owner;

public interface OwnerStrategy {

    boolean isForeign();

    int fee();
}
