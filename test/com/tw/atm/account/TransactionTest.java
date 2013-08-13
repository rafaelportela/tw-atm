package com.tw.atm.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.tw.atm.exceptions.AccountManagementException;

public class TransactionTest {

	private Account account;

	@Before
	public void setup() {
		account = new Account(001, "Luan", "000.000.000-00",
				AccountType.CURRENT_ACCOUNT);
	}

	@Test
	public void transactionCurrentDate() {
		Transaction transaction = new Transaction(100.0,
				TransactionType.DEPOSIT);
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm")
				.format(new Date());
		assertEquals(date, transaction.getDate());
	}

	@Test
	public void lastStoredTransaction() throws AccountManagementException {
		double amount = 100.0;
		account.deposit(amount);
		Transaction transaction = new Transaction(100.0,
				TransactionType.DEPOSIT);
		Transaction lastStoredTransaction = account.getLastTransaction();
		assertTrue(transaction.equals(lastStoredTransaction));
	}

}
