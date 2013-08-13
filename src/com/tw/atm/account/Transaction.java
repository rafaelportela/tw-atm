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

}
