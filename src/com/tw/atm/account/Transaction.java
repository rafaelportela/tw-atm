package com.tw.atm.account;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private String date;
	private double amount;
	private TransactionType type;

	public Transaction(double amount, TransactionType type) {
		date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
		this.amount = amount;
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		boolean status = false;
		if (obj instanceof Transaction) {
			Transaction transaction = (Transaction) obj;
			if (date.equals(transaction.date)) {
				if (amount == transaction.amount) {
					if (type == transaction.type) {
						status = true;
					}
				}
			}
		}
		return status;
	}
}
