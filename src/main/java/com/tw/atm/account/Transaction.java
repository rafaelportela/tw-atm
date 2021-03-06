package com.tw.atm.account;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private Date date;
	private double amount;
	private long accountId;
	private TransactionType type;

	public Transaction(double amount, long accountId, TransactionType type,
			Calendar calendar) {
		date = calendar.getTime();
		this.amount = amount;
		this.type = type;
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Transaction transaction = (Transaction) obj;
		return amount == transaction.amount
				&& accountId == transaction.accountId
				&& (type == transaction.type || (type != null && type
						.equals(transaction.type)));
	}

	@Override
	public int hashCode() {
		int hash = 7;
		long bits = Double.doubleToLongBits(amount);
		hash = 31 * hash + (int) (bits ^ (bits >>> 32));
		hash = 31 * hash + (int) (accountId ^ (accountId >>> 32));
		hash = 31 * hash + (type == null ? 0 : type.hashCode());
		return hash;
	}

}
