package com.tw.atm.account;

public class Account {
	public final static String CURRENT_ACCOUNT = "Current Account";
	public final static String SAVINGS_ACCOUNT = "Savings Account";

	private double balance;
	private String accountType;

	public Account(String accountType) {
		this.balance = 0.0;
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException(
					"You can't deposit a negative amount.");
		} else
			balance += amount;
	}

	public void withdraw(double amount) {
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient balance.");
		} else if (amount < 0) {
			throw new IllegalArgumentException(
					"You can't withdraw a negative amount.");
		} else
			balance -= amount;
	}

	public void transferMoney(double amount, Account destinationAccount) {
		this.withdraw(amount);
		destinationAccount.deposit(amount);
	}
}