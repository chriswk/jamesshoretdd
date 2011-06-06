package com.chriswk.financing;


public class SavingsAccountYear {
    private int startingBalance = 0;
    private int startingPrincipal = 0;
    private int interestRate = 0;
    private int capitalGainsAmount = 0;
    private int totalWithdrawn = 0;
    public SavingsAccountYear() {
    }
    
    public SavingsAccountYear(int startingBalance, int startingPrincipal, int interestRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.capitalGainsAmount = startingBalance - startingPrincipal;
    }

    public SavingsAccountYear nextYear(int capitalGainsTaxRate) {
        return new SavingsAccountYear(this.endingBalance(25), 0, interestRate);
    }

    public int endingBalance(int capitalGainsTaxRate) {
        int modifiedStart = startingBalance - totalWithdrawn() - capitalGainsTaxIncurred(capitalGainsTaxRate);
        return modifiedStart + (modifiedStart * this.interestRate / 100);
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int interestRate() {
        return interestRate;
    }
    
    public int startingCapitalGains() {
        return startingBalance - startingPrincipal;
    }

    public void withdraw(int amount) {
        this.totalWithdrawn += amount;
    }

    public int startingPrincipal() {
        return startingPrincipal;
    }
    public int endingPrincipal() {
        return zeroOrPositive(startingPrincipal() - totalWithdrawn());
    }

    private int zeroOrPositive(int result) {
        return Math.max(0, result);
    }

    public int totalWithdrawn() {
        return totalWithdrawn;
    }

    public int capitalGainsWithdrawn() {
        return zeroOrPositive(-1 * (startingPrincipal() - totalWithdrawn()));

    }

    public int capitalGainsTaxIncurred(int taxRate) {
        double dblTaxRate = taxRate / 100.0;
        double dblCapGains = capitalGainsWithdrawn();
        return (int) ((dblCapGains / (1 - dblTaxRate)) - dblCapGains);
    }

}
