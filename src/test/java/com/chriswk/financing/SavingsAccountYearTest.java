package com.chriswk.financing;

import org.junit.Test;

import static org.junit.Assert.*;

public class SavingsAccountYearTest {


    @Test
    public void startingBalanceMatchesConstructor() {
        assertEquals(10000, newAccount().startingBalance());
    }

    @Test
    public void interestRateMatchesConstructor() {
        assertEquals(newAccount().interestRate(), 10);
    }

    @Test
    public void endingBalanceAppliesInterestRate() {
        assertEquals(11000, newAccount().endingBalance(25));
    }

    @Test
    public void nextYearStartingBalanceShouldEqualThisYearsEndingBalance() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
    }

    @Test
    public void nextYearsInterestRateEqualsThisYearsInterestRate() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
    }

    @Test
    public void withdrawingFundsOccursAtTheBeginningOfTheYear() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 10);
        year.withdraw(1000);
        assertEquals(9900, year.endingBalance(25));
    }

    @Test
    public void startingPrincipal() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(3000, year.startingPrincipal());
    }

    @Test
    public void endingPrincipal() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(2000);
        assertEquals("Ending principal", 1000, year.endingPrincipal());

    }

    @Test
    public void endingPrincipalNeverGoesBelowZero() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(4000);
        assertEquals("ending principal", 0, year.endingPrincipal());

    }


    @Test
    public void multipleWithdrawalsInAYear() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        year.withdraw(2000);
        assertEquals(3000, year.totalWithdrawn());
    }

    @Test
    public void capitalGainsWithdrawn() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        assertEquals(0, year.capitalGainsWithdrawn());
        year.withdraw(3000);
        assertEquals(1000, year.capitalGainsWithdrawn());
    }

    @Test
    public void capitalGainTaxIncurred() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(5000);
        assertEquals(2000, year.capitalGainsWithdrawn());
        assertEquals(500, year.capitalGainsTaxIncurred(25));
    }

    @Test
    public void capitalGainsTaxIsIncludedInEndingBalance() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(5000);
        assertEquals(500, year.capitalGainsTaxIncurred(25));
        assertEquals(10000 - 5000 -500 + 450, year.endingBalance(25));
        //TODO : Need to withdraw enough money to incur capital tax
    }
    /*@Test
    public void withdrawingMoreThanPrincipalIncursCapitalGainsTax() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(3000);
        assertEquals(7700, year.endingBalance());
        year.withdraw(5000);
        assertEquals(2000 + 200 - (1250), year.endingBalance());

    }*/

    private SavingsAccountYear newAccount() {
        return new SavingsAccountYear(10000, 10);
    }
}
