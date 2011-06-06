package com.chriswk.financing;


public class SavingsAccountYear {
    private int balance = 0;

    public SavingsAccountYear() {
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public SavingsAccountYear nextYear(int interestRate) {
        SavingsAccountYear result = new SavingsAccountYear();
        result.deposit(getBalance() + (getBalance() * interestRate / 100));
        return result;
    }
}
