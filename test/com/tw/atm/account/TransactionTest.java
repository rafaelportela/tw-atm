package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.easymock.EasyMock;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void transactionCurrentDate() {
		Transaction transaction = EasyMock.createNiceMock(Transaction.class);
		Calendar cal = Calendar.getInstance();
		EasyMock.expect(transaction.getDate()).andReturn(cal.getTime());
		EasyMock.replay(transaction);
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
