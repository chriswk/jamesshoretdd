package com.chriswk.financing;

/**
 * Created by IntelliJ IDEA.
 * User: chriswk
 * Date: 06.06.11
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main (String[] args) {
        SavingsAccountYear account = new SavingsAccountYear();
        account.deposit(10000);
        for(int i = 0; i< 60; i++) {
            System.out.println(i + ": $ " +account.getStartingBalance());
            account = account.nextYear();
        }
    }
}
