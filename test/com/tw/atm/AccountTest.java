package com.tw.atm;

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
}
