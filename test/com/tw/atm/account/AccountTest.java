package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.tw.atm.exceptions.AccountManagementException;

public class AccountTest {

	private Account account;

	@Before
	public void setUp() {
		account = new Account(001, "Luan", "000.000.000-00",
				AccountType.CURRENT_ACCOUNT);
	}

	@Test
	public void depositAmount() throws AccountManagementException {
		double amount = 1000.0;
		account.deposit(amount);
		assertEquals(1000.0, account.getBalance(), 0);
	}

	@Test(expected = AccountManagementException.class)
	public void throwsErrorForInvalidDeposit()
			throws AccountManagementException {
		double zeroAmount = 0.0;
		double negativeAmount = -1000.0;
		account.deposit(zeroAmount);
		account.deposit(negativeAmount);
	}

	@Test
	public void withdrawMoney() throws AccountManagementException {
		double amount = 1500.0;
		account.deposit(amount);
		account.withdraw(amount);
		assertEquals(0.0, account.getBalance(), 0);
	}

	@Test(expected = AccountManagementException.class)
	public void throwsErrorForInsufficientBalance()
			throws AccountManagementException {
		double amount = 500.0;
		account.withdraw(amount);
	}

	@Test(expected = AccountManagementException.class)
	public void throwsErrorForInvalidWithdraw()
			throws AccountManagementException {
		double zeroMmount = 0.0;
		double negativeAmount = -2050.0;
		account.withdraw(zeroMmount);
		account.withdraw(negativeAmount);
	}

	@Test
	public void transferMoneyBetweenAccounts()
			throws AccountManagementException {
		Account destinationAccount = new Account(002, "Rodrigo",
				"111.111.111-11", AccountType.SAVINGS_ACCOUNT);
		account.deposit(100.0);
		double amount = 50.0;
		account.transfer(destinationAccount, amount);
		assertEquals(50.0, account.getBalance(), 0.1);
		assertEquals(50.0, destinationAccount.getBalance(), 0);
	}

	@Test(expected = AccountManagementException.class)
	public void transferMoneyToNonExistentAccount() throws Exception {
		Account destinationAccount = null;
		account.deposit(100.0);
		double amount = 50.0;
		account.transfer(destinationAccount, amount);
	}

	@Test
	public void depositTransaction() throws AccountManagementException {
		double amount = 100.0;
		TransactionType type = TransactionType.DEPOSIT;
		Transaction transaction = new Transaction(amount, account.getId(),
				type, Calendar.getInstance());
		account.deposit(amount);
		assertEquals(transaction, account.getLastTransaction());
	}

	@Test
	public void withdrawTransaction() throws AccountManagementException {
		double amount = 250.0;
		TransactionType type = TransactionType.WITHDRAW;
		Transaction transaction = new Transaction(amount, account.getId(),
				type, Calendar.getInstance());
		account.deposit(amount);
		account.withdraw(amount);
		assertEquals(transaction, account.getLastTransaction());
	}

	@Test
	public void transferTransaction() throws AccountManagementException {
		double amount = 150.0;
		TransactionType type = TransactionType.TRANSFER;
		Account destinationAccount = new Account(002, "Rodrigo",
				"111.111.111-11", AccountType.CURRENT_ACCOUNT);
		Transaction transaction = new Transaction(amount,
				destinationAccount.getId(), type, Calendar.getInstance());
		account.deposit(amount);
		account.transfer(destinationAccount, amount);
		assertEquals(transaction, account.getLastTransaction());
	}

}
