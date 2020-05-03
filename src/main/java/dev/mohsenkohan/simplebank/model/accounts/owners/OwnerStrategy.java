package dev.mohsenkohan.simplebank.model.accounts.owners;

public interface OwnerStrategy {

    boolean isForeign();

    int fee();
}
