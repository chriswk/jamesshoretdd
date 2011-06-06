package com.chriswk.financing;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: chriswk
 * Date: 06.06.11
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccountYearTest {

    @Test
    public void depositAndWithdrawal() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.deposit(100);
        assertEquals("100 dollars after deposit", 100, account.getBalance());
        account.withdraw(50);
        assertEquals("after withdrawal", 50, account.getBalance());
    }

    @Test
    public void negativeBalanceJustFine() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.withdraw(75);
        assertEquals(-75, account.getBalance());
    }

    @Test
    public void nextYear() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.deposit(10000);
        SavingsAccountYear nextYear = account.nextYear(10);
        assertEquals("Should accrue 10% interest", 11000, nextYear.getBalance());

    }

}
