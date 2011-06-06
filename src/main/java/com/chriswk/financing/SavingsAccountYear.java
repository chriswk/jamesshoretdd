package com.chriswk.financing;


public class SavingsAccountYear {
    private int startingBalance = 0;
    private int interestRate = 0;
    public SavingsAccountYear() {
    }

    public SavingsAccountYear(int startingBalance, int interestRate) {
        this.startingBalance = startingBalance;
        this.interestRate = interestRate;
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(this.endingBalance(), this.interestRate());
    }

    public int endingBalance() {
        return startingBalance() + (startingBalance() * this.interestRate / 100);
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int interestRate() {
        return interestRate;
    }
}
