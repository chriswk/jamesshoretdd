package com.chriswk.financing;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class SavingsAccountYearTest {


    @Test
    public void startingBalance() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(10000, account.startingBalance());
    }
    @Test
    public void endingBalance() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(11000, account.endingBalance());
    }

    @Test
    public void nextYearStartingBalanceShouldEqualThisYearsEndingBalance() {
        SavingsAccountYear thisYear = new SavingsAccountYear(10000, 10);
        assertEquals(thisYear.endingBalance(), thisYear.nextYear().startingBalance());
    }

    @Test
    public void nextYearsInterestRateEqualsThisYearsInterestRate() {
        SavingsAccountYear thisYear = new SavingsAccountYear(10000, 10);
        assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
    }
}
