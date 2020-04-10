package dev.mohsenkohan.simplebank.accounts.owners;

public enum Foreign implements OwnerStrategy {

    INSTANCE;

    @Override
    public boolean isForeign() {
        return true;
    }

    @Override
    public int fee() {
        return 500;
    }

    @Override
    public String toString() {
        return "foreign";
    }
}
