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
public class _SavingsAccountTest {

    @Test
    public void depositAndWithdrawal() {
        SavingsAccount account = new SavingsAccount();
        account.deposit(100);
        assertEquals("100 dollars after deposit", 100, account.getBalance());
        account.withdraw(50);
        assertEquals("after withdrawal", 50, account.getBalance());
    }

    @Test
    public void negativeBalanceJustFine() {
        SavingsAccount account = new SavingsAccount();
        account.withdraw(75);
        assertEquals(-75, account.getBalance());
    }

}
