package dev.mohsenkohan.simplebank.loans;

public class Loan {

    private double balance, monthlyRate, monthlyPayment;
    private int paymentsLeft;
    private boolean isDomestic;

    public Loan(double amount, double yearlyRate, int numYears, boolean isDomestic) {
        this.balance = amount;
        paymentsLeft = numYears * 12;
        this.isDomestic = isDomestic;
        monthlyRate = yearlyRate / 12.0;
        monthlyPayment = (amount * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -paymentsLeft));
    }

    public double remainingPrincipal() {
        return balance;
    }

    public int paymentsLeft() {
        return paymentsLeft;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public double monthlyPayment() {
        return monthlyPayment;
    }

    public void makePayment() {
        balance = balance + (balance * monthlyRate) - monthlyPayment;
        paymentsLeft--;
    }

    public static void main(String[] args) {
        Loan n = new Loan(100, 1.2, 1, true);
        System.out.println(n.monthlyPayment());
        int count = 0;
        while (n.paymentsLeft() > 0) {
            count++;
            n.makePayment();
            System.out.println(count + ": " + n.remainingPrincipal());
        }
    }
}
