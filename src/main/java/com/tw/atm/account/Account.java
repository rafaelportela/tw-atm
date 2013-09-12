package com.tw.atm.account;

import java.util.Calendar;

import com.tw.atm.exceptions.AccountManagementException;

public class Account {
	private long id;
	private String holderName;
	private String holderCPF;
	private double balance;
	private AccountType accountType;
	private boolean shouldSave;
	private AccountStatement accountStatement;

	public Account(long id, String holderName, String holderCPF,
			AccountType accountType) {
		this.id = id;
		this.holderName = holderName;
		this.holderCPF = holderCPF;
		this.accountType = accountType;
		accountStatement = new AccountStatement();
		shouldSave = true;
	}

	public long getId() {
		return id;
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
			if (shouldSave)
				saveTransaction(new Transaction(amount, id,
						TransactionType.DEPOSIT, Calendar.getInstance()));
		}
	}

	public void withdraw(double amount) throws AccountManagementException {
		if (amount > balance) {
			throw new AccountManagementException("Insufficient balance.");
		} else if (amount <= 0) {
			throw new AccountManagementException(
					"You must insert a value greater than zero.");
		} else {
			balance -= amount;
			if (shouldSave)
				saveTransaction(new Transaction(amount, id,
						TransactionType.WITHDRAW, Calendar.getInstance()));
		}
	}

	public void transfer(Account destinationAccount, double amount)
			throws AccountManagementException {
		if (destinationAccount == null)
			throw new AccountManagementException(
					"The destination account doesn't exist.");
		try {
			shouldSave = false;
			this.withdraw(amount);
			destinationAccount.deposit(amount);
			shouldSave = true;
			saveTransaction(new Transaction(amount, destinationAccount.getId(),
					TransactionType.TRANSFER, Calendar.getInstance()));

		} catch (AccountManagementException e) {
			System.out.println(e.getMessage());
		}
	}

	private void saveTransaction(Transaction transaction) {
		accountStatement.saveTransaction(transaction);
	}

	public Transaction getLastTransaction() {
		return accountStatement.getLastTransaction();
	}

}