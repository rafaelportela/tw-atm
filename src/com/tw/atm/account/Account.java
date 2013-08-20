package com.tw.atm.account;

import java.util.ArrayList;
import java.util.List;

import com.tw.atm.exceptions.AccountManagementException;

public class Account {
	private int id;
	private String holderName;
	private String holderCPF;
	private double balance;
	private AccountType accountType;
	private List<Transaction> transactions;

	public Account(int id, String holderName, String holderCPF,
			AccountType accountType) {
		this.id = id;
		this.holderName = holderName;
		this.holderCPF = holderCPF;
		this.accountType = accountType;
		transactions = new ArrayList<>();
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) throws AccountManagementException {
		if (amount <= 0) {
			throw new AccountManagementException(
					"You must insert a value greater than zero.");
		} else {
			balance += amount;
			storeTransaction(amount, TransactionType.DEPOSIT);
		}
	}

	public void withdraw(double amount) throws AccountManagementException,
			AccountManagementException {
		if (amount > balance) {
			throw new AccountManagementException("Insufficient balance.");
		} else if (amount <= 0) {
			throw new AccountManagementException(
					"You must insert a value greater than zero.");
		} else
			balance -= amount;
	}

	public void transferMoney(Account destinationAccount, double amount)
			throws AccountManagementException {
		if (destinationAccount == null)
			throw new AccountManagementException(
					"The destination account doesn't exist.");

		try {
			this.withdraw(amount);
			destinationAccount.deposit(amount);
		} catch (AccountManagementException e) {
			System.out.println(e.getMessage());
		}
	}

	private void storeTransaction(double amount, TransactionType type) {
		Transaction transaction = new Transaction(amount, type);
		transactions.add(transaction);
	}

	public Transaction getLastTransaction() {
		return transactions.get(transactions.size() - 1);
	}

}