package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account;

	@Before
	public void setup() {
		account = new Account(0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAccountWithNegativeBalance() {
		Account account = new Account(-1000.0);
	}

	@Test
	public void testDepositAmount() {
		double amount = 1000.0;
		account.deposit(amount);
		assertEquals(1000.0, account.getBalance(), 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDepositNegativeAmount() {
		double amount = -1000.0;
		account.deposit(amount);
	}

	@Test
	public void testWithdrawMoney() {
		double amount = 1500.0;
		account.deposit(amount);
		account.withdraw(amount);
		assertEquals(0.0, account.getBalance(), 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawMoneyWithInsufficientBalance() {
		double amount = 500.0;
		account.withdraw(amount);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawNegativeAmount() {
		double amount = -2050.0;
		account.withdraw(amount);
	}

	@Test
	public void testTransferMoneyBetweenAccounts() {
		Account sourceAccount = new Account(100.0);
		Account destinationAccount = new Account(0.0);
		double amount = 50.0;
		sourceAccount.transferMoney(amount, destinationAccount);
		assertEquals(50.0, sourceAccount.getBalance(), 0.1);
		assertEquals(50.0, destinationAccount.getBalance(), 0.1);
	}

}
