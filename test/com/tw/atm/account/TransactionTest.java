package com.tw.atm.account;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void currentDate() {
		Transaction transaction = new Transaction(0.0, 0.0, 0.0);
		Date date = Calendar.getInstance().getTime();
		assertEquals(date, transaction.getDate());
	}
}
