package com.chriswk.financing;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsAccountYearTest {

    @Test
	  public void startingBalanceMatchesConstructor() {
	    assertEquals(10000, newAccount().startingBalance());
	  }

	  @Test
	  public void startingPrincipalMatchesConstructor() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    assertEquals(3000, year.startingPrincipal());
	  }

	  @Test
	  public void interestRateMatchesConstructor() {
	    assertEquals(10, newAccount().interestRate());
	  }

	  @Test
	  public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    assertEquals(7000, year.startingCapitalGains());
	  }

	  @Test
	  public void endingPrincipalConsidersWithdrawals() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    year.withdraw(2000);
	    assertEquals(1000, year.endingPrincipal(), "ending principal");
	  }

	  @Test
	  public void endingPrincipalNeverGoesBelowZero() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    year.withdraw(4000);
	    assertEquals(0, year.endingPrincipal(), "ending principal");
	  }

	  @Test
	  public void interestEarnedIsStartingBalanceCombinedWithInterestRate() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    assertEquals(1000, year.interestEarned(25));
	  }

	  @Test
	  public void withdrawnFundsDoNotEarnInterest() {
	    SavingsAccountYear year = newAccount();
	    year.withdraw(1000);
	    assertEquals(900, year.interestEarned(25));
	  }

	  @Test
	  public void totalWithdrawnIncludingCapitalGains() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 0, 10);
	    year.withdraw(1000);
	    assertEquals(333, year.capitalGainsTaxIncurred(25), "capital gains tax");
	    assertEquals( 1333, year.totalWithdrawn(25), "total withdrawn");
	  }

	  @Test
	  public void capitalGainsTaxesDoNotEarnInterest() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 0, 10);
	    year.withdraw(1000);
	    assertEquals( 1000, year.capitalGainsWithdrawn(), "capital gains withdrawn");
	    assertEquals( 333, year.capitalGainsTaxIncurred(25), "capital gains tax");
	    assertEquals( 1333, year.totalWithdrawn(25), "total withdrawn");
	    assertEquals( 866, year.interestEarned(25), "interest earned");
	  }

	  @Test
	  public void endingCapitalGainsIncludesInterestEarned() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    assertEquals( 7000, year.startingCapitalGains(), "starting capital gains");
	    assertEquals(8000, year.endingCapitalGains(25), "ending capital gains");
	  }

	  @Test
	  public void endingCapitalGainsIncludesCapitalGainsWithdrawn() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 0, 10);
	    assertEquals( 10000, year.startingCapitalGains(), "starting capital gains");
	    year.withdraw(1000);
	    assertEquals( 1000, year.capitalGainsWithdrawn(), "capital gains withdrawn");
	    assertEquals( 333, year.capitalGainsTaxIncurred(25), "capital gains tax");
	    assertEquals( 866, year.interestEarned(25), "interest earned");
	    assertEquals( 9533, year.endingCapitalGains(25), "ending capital gains");
	  }

	  @Test
	  public void endingBalanceAppliesInterestRate() {
	    assertEquals(11000, newAccount().endingBalance(25));
	  }

	  @Test
	  public void multipleWithdrawalsInAYearAreTotaled() {
	    SavingsAccountYear year = newAccount();
	    year.withdraw(1000);
	    year.withdraw(2000);
	    assertEquals(3000, year.totalWithdrawn(25));
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
	  public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    year.withdraw(5000);
	    assertEquals(2000, year.capitalGainsWithdrawn());
	    assertEquals(666, year.capitalGainsTaxIncurred(25));
	  }

	  @Test
	  public void capitalGainsTaxIsIncludedInEndingBalance() {
	    SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
	    int amountWithdrawn = 5000;
	    year.withdraw(amountWithdrawn);
	    int expectedCapitalGainsTax = 666;
	    assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
	    int expectedStartingBalanceAfterWithdrawals = 10000 - amountWithdrawn - expectedCapitalGainsTax;
	    assertEquals((int)(expectedStartingBalanceAfterWithdrawals * 1.10), year.endingBalance(25));
	  }



	  @Test
	  public void nextYearsStartingBalanceEqualsThisYearsEndingBalance() {
	    SavingsAccountYear thisYear = newAccount();
	    assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
	  }

	  @Test
	  public void nextYearsInterestRateEqualsThisYearsInterestRate() {
	    SavingsAccountYear thisYear = newAccount();
	    assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
	  }




	  private SavingsAccountYear newAccount() {
	    SavingsAccountYear account = new SavingsAccountYear(10000, 10000, 10);
	    return account;
	  }
}
