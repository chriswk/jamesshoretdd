package com.chriswk.financing;

import org.junit.Test;

import static org.junit.Assert.*;

public class SavingsAccountYearTest {

    @Test
    public void startingBalanceMatchesConstructor() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(10000, year.startingBalance());
    }

    @Test
    public void startingPrincipalMatchesConstructor() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(3000, year.startingPrincipal());
    }

    @Test
    public void interestRateMatchesConstructor() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(year.interestRate(), 10);
    }

    @Test
    public void startingCapitalGainsIsStartringBalanceMinusStartingPrincipal() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(7000, year.startingCapitalGains());
    }

    @Test
    public void endingPrincipalConsidersWithdrawals() {
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
    public void endingCapitalGainsIncludesInterestEarned() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(7000, year.startingCapitalGains());
        assertEquals(4000, year.endingCapitalGains());
    }
    @Test
    public void endingBalanceAppliesInterestRate() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(11000, year.endingBalance(25));
    }

    @Test
    public void withdrawnFundsDoNotEarnInterest() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        assertEquals(9900, year.endingBalance(25));
    }

    @Test
    public void multipleWithdrawalsInAYearAreTotaled() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        year.withdraw(2000);
        assertEquals(3000, year.totalWithdrawn());
    }

    @Test
    public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        assertEquals(0, year.capitalGainsWithdrawn());
        year.withdraw(3000);
        assertEquals(1000, year.capitalGainsWithdrawn());
    }

    @Test
    public void capitalGainTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(5000);
        assertEquals(2000, year.capitalGainsWithdrawn());
        assertEquals(666, year.capitalGainsTaxIncurred(25));
    }

    @Test
    public void capitalGainsTaxIsIncludedInEndingBalance() {
        int amountWithdrawn = 5000;
        int expectedCapitalGainsTax = 666;
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(amountWithdrawn);
        int expectedStartingBalanceAfterWithdrawals = 10000 - amountWithdrawn - expectedCapitalGainsTax;
        assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
        assertEquals((int) (expectedStartingBalanceAfterWithdrawals * 1.10), year.endingBalance(25));
        //TODO : Need to withdraw enough money to incur capital tax
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

    private SavingsAccountYear newAccount() {
        return new SavingsAccountYear(10000, 3000, 10);
    }
}
