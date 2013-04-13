package com.tw.atm.account;

public class Account {
	private double balance;

	public Account(double balance) {
		if (balance < 0) {
			throw new IllegalArgumentException(
					"Can't initialize an account with a negative balance.");
		} else
			this.balance = balance;
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

	public void transferMoney(double amount, Account account) {
		this.withdraw(amount);
		account.deposit(amount);
	}
}