package com.tw.atm.account;

public class Account {
	private double balance;
	private AccountType accountType;

	public Account(AccountType accountType) {
		this.balance = 0.0;
		this.accountType = accountType;
	}

	public AccountType getAccountType() {
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