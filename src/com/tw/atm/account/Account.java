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

	public void deposit(double amount, boolean save)
			throws AccountManagementException {
		if (amount <= 0) {
			throw new AccountManagementException(
					"You must insert a value greater than zero.");
		} else {
			balance += amount;
			if (save)
				saveTransaction(new Transaction(amount, TransactionType.DEPOSIT));
		}
	}

	public void withdraw(double amount, boolean save)
			throws AccountManagementException, AccountManagementException {
		if (amount > balance) {
			throw new AccountManagementException("Insufficient balance.");
		} else if (amount <= 0) {
			throw new AccountManagementException(
					"You must insert a value greater than zero.");
		} else {
			balance -= amount;
			if (save)
				saveTransaction(new Transaction(amount,
						TransactionType.WITHDRAW));
		}
	}

	public void transfer(Account destinationAccount, double amount, boolean save)
			throws AccountManagementException {
		if (destinationAccount == null)
			throw new AccountManagementException(
					"The destination account doesn't exist.");
		try {
			this.withdraw(amount, false);
			destinationAccount.deposit(amount, false);
			if (save)
				saveTransaction(new Transaction(amount, destinationAccount,
						TransactionType.TRANSFER));
			
		} catch (AccountManagementException e) {
			System.out.println(e.getMessage());
		}
	}

	private void saveTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public Transaction getLastTransaction() {
		return transactions.get(transactions.size() - 1);
	}

}