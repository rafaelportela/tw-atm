package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account;

	@Before
	public void setup() {
		account = new Account(AccountType.CURRENT_ACCOUNT);
	}

	@Test
	public void depositAmount() throws NegativeValueException {
		double amount = 1000.0;
		account.deposit(amount);
		assertEquals(1000.0, account.getBalance(), 0.1);
	}

	@Test(expected = NegativeValueException.class)
	public void throwsErrorForNegativeDeposit() throws NegativeValueException {
		double amount = -1000.0;
		account.deposit(amount);
	}

	@Test
	public void withdrawMoney() throws NegativeValueException,
			InsufficientBalanceException {
		double amount = 1500.0;
		account.deposit(amount);
		account.withdraw(amount);
		assertEquals(0.0, account.getBalance(), 0.1);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void throwsErrorForInsufficientBalance()
			throws NegativeValueException, InsufficientBalanceException {
		double amount = 500.0;
		account.withdraw(amount);
	}

	@Test(expected = NegativeValueException.class)
	public void throwsErrorForNegativeWithdraw() throws NegativeValueException,
			InsufficientBalanceException {
		double amount = -2050.0;
		account.withdraw(amount);
	}

	@Test
	public void transferMoneyBetweenAccounts() throws NegativeValueException {
		Account sourceAccount = new Account(AccountType.CURRENT_ACCOUNT);
		Account destinationAccount = new Account(AccountType.SAVINGS_ACCOUNT);

		sourceAccount.deposit(100.0);

		double amount = 50.0;
		sourceAccount.transferMoney(destinationAccount, amount);

		assertEquals(50.0, sourceAccount.getBalance(), 0.1);
		assertEquals(50.0, destinationAccount.getBalance(), 0.1);
	}

}
