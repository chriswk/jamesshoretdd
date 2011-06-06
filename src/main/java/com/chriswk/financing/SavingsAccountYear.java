package com.chriswk.financing;


public class SavingsAccountYear {
    private int startingBalance = 0;
    private int interestRate = 0;
    private int capitalGainsAmount = 0;
    private int totalWithdrawn = 0;
    public SavingsAccountYear() {
    }

    public SavingsAccountYear(int startingBalance, int interestRate) {
        this.startingBalance = startingBalance;
        this.interestRate = interestRate;
    }

    public SavingsAccountYear(int startingBalance, int capitalGainsAmount, int interestRate) {
        this.startingBalance = startingBalance;
        this.interestRate = interestRate;
        this.capitalGainsAmount = capitalGainsAmount;
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(this.endingBalance(), this.interestRate());
    }

    public int endingBalance() {
        int modifiedStart = startingBalance - totalWithdrawn;
        return modifiedStart + (modifiedStart * this.interestRate / 100);
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int interestRate() {
        return interestRate;
    }

    public void withdraw(int amount) {
        this.totalWithdrawn += amount;
    }

    public int startingPrincipal() {
        return startingBalance - capitalGainsAmount;
    }
    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawn;
        return (result < 0) ? 0 : result;

    }

    public int totalWithdrawn() {
        return totalWithdrawn;
    }
}
