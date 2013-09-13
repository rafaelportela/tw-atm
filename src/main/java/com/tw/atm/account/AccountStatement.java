package com.tw.atm.account;

import java.util.ArrayList;
import java.util.List;

public class AccountStatement {
	private List<Transaction> transactions;

	public AccountStatement() {
		transactions = new ArrayList();
	}

	public Transaction getLastTransaction() {
		return transactions.get(transactions.size() - 1);
	}

	public void saveTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
}
