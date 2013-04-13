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
	public void WithdrawNegativeAmount() {
		double amount = -2050.0;
		account.withdraw(amount);
	}

}
