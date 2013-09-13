package com.tw.atm.account;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.Ignore;

public class TransactionTest {

	@Ignore
	public void transactionCurrentDate() throws ParseException {
		Calendar calendar = createNiceMock(Calendar.class);
		Date date = new SimpleDateFormat("MM/dd/yyyy").parse("10/10/2013");
		expect(calendar.getTime()).andReturn(date);
		replay(calendar);
		Transaction transaction = new Transaction(100.0, 001,
				TransactionType.DEPOSIT, calendar);
		assertEquals(date, transaction.getDate());
	}

	@Test
	public void equalsTransaction() {
		double amount = 100.0;
		TransactionType type = TransactionType.DEPOSIT;
		Transaction transaction = new Transaction(amount, 001, type,
				Calendar.getInstance());
		Transaction sameTransaction = new Transaction(amount, 001, type,
				Calendar.getInstance());
		assertEquals(transaction, sameTransaction);
	}

}
