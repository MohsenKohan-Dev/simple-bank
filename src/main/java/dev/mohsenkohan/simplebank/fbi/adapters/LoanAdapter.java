package dev.mohsenkohan.simplebank.fbi.adapters;

import dev.mohsenkohan.simplebank.model.loans.Loan;

public class LoanAdapter implements FBIAcctInfo {

    private final Loan loan;

    public LoanAdapter(Loan loan) {
        this.loan = loan;
    }

    @Override
    public int balance() {
        return (int) (loan.remainingPrincipal() / 100);
    }

    @Override
    public boolean isForeign() {
        return !loan.isDomestic();
    }

    @Override
    public String acctType() {
        return "loan";
    }
}
