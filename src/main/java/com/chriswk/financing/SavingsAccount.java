package com.chriswk.financing;


public class SavingsAccount {
    private int balance = 0;

    public SavingsAccount() {
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
}
