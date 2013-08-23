package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void transactionCurrentDate() {
		Transaction transaction = new Transaction(100.0, 001,
				TransactionType.DEPOSIT);
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm")
				.format(new Date());
		assertEquals(date, transaction.getDate());
	}

	@Test
	public void equalsTransaction() {
		double amount = 100.0;
		TransactionType type = TransactionType.DEPOSIT;
		Transaction transaction = new Transaction(amount, 001, type);
		Transaction sameTransaction = new Transaction(amount, 001, type);
		assertEquals(transaction, sameTransaction);
	}

}
