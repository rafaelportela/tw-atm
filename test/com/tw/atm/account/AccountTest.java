package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tw.atm.exceptions.InsufficientBalanceException;
import com.tw.atm.exceptions.NegativeValueException;
import com.tw.atm.exceptions.NonExistentAccountException;

public class AccountTest {

	private Account account;

	@Before
	public void setup() {
		account = new Account(001, "Luan", "000.000.000-00",
				AccountType.CURRENT_ACCOUNT);
	}

	@Test
	public void depositAmount() throws Exception {
		double amount = 1000.0;
		account.deposit(amount);
		assertEquals(1000.0, account.getBalance(), 0.1);
	}

	@Test(expected = NegativeValueException.class)
	public void throwsErrorForNegativeDeposit() throws Exception {
		double amount = -1000.0;
		account.deposit(amount);
	}

	@Test
	public void withdrawMoney() throws Exception {
		double amount = 1500.0;
		account.deposit(amount);
		account.withdraw(amount);
		assertEquals(0.0, account.getBalance(), 0.1);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void throwsErrorForInsufficientBalance() throws Exception {
		double amount = 500.0;
		account.withdraw(amount);
	}

	@Test(expected = NegativeValueException.class)
	public void throwsErrorForNegativeWithdraw() throws Exception {
		double amount = -2050.0;
		account.withdraw(amount);
	}

	@Test
	public void transferMoneyBetweenAccounts() throws Exception {
		Account destinationAccount = new Account(002, "Rodrigo",
				"111.111.111-11", AccountType.SAVINGS_ACCOUNT);
		account.deposit(100.0);
		double amount = 50.0;
		account.transferMoney(destinationAccount, amount);
		assertEquals(50.0, account.getBalance(), 0.1);
		assertEquals(50.0, destinationAccount.getBalance(), 0.1);
	}

	@Test(expected = NonExistentAccountException.class)
	public void transferMoneyToNonExistentAccount() throws Exception {
		Account destinationAccount = null;
		account.deposit(100.0);
		double amount = 50.0;
		account.transferMoney(destinationAccount, amount);
	}

}
