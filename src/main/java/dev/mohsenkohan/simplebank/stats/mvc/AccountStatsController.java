package dev.mohsenkohan.simplebank.stats.mvc;

import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.stats.visitors.MaxBalanceVisitor;
import dev.mohsenkohan.simplebank.stats.visitors.ShowAccountVisitor;

import java.util.function.Predicate;

public class AccountStatsController {

    private final AccountStatsModel model;
    private final Predicate<BankAccount> predicate = account -> account.fee() == 0;

    public AccountStatsController(AccountStatsModel model) {
        this.model = model;
    }

    public String getAccounts1() {
        return model.getAccounts1();
    }

    public int maxBalance1() {
        return model.maxBalance1();
    }

    public String getAccounts2() {
        return model.getAccounts2();
    }

    public int maxBalance2() {
        return model.maxBalance2();
    }

    public String getAccounts3() {
        return model.getAccounts3();
    }

    public int maxBalance3a() {
        return model.maxBalance3a();
    }

    public int maxBalance3b() {
        return model.maxBalance3b();
    }

    public String visit1() {
        var visitor = new ShowAccountVisitor();
        model.visit1(visitor);
        return visitor.result();
    }

    public int maxBalance3c() {
        return model.maxBalance3c();
    }

    public String getAccounts4() {
        return model.getAccounts4(predicate);
    }

    public int maxBalance4() {
        return model.maxBalance4(predicate);
    }

    public String getAccounts5() {
        return model.getAccounts5(predicate);
    }

    public int maxBalance5() {
        return model.maxBalance5(predicate);
    }

    public String visit3() {
        var visitor = new ShowAccountVisitor();
        model.visit3(predicate, visitor);
        return visitor.result();
    }

    public int visit4() {
        return model.visit4(predicate, new MaxBalanceVisitor());
    }

    public String getAccounts6() {
        return model.getAccounts6(predicate);
    }

    public int maxBalance6() {
        return model.maxBalance6(predicate);
    }
}
