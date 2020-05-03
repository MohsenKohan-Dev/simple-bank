package dev.mohsenkohan.simplebank.model.accounts.owners;

public enum Owners implements OwnerStrategy {

    DOMESTIC(false, 0, "domestic"), FOREIGN(true, 500, "foreign");

    private final boolean isForeign;
    private final int fee;
    private final String name;

    Owners(boolean isForeign, int fee, String name) {
        this.isForeign = isForeign;
        this.fee = fee;
        this.name = name;
    }

    @Override
    public boolean isForeign() {
        return isForeign;
    }

    @Override
    public int fee() {
        return fee;
    }

    @Override
    public String toString() {
        return name;
    }
}
