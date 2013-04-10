package com.tw.atm;

public class Account {
	private double balance;

	public Account(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException(
					"You can't deposit a negative value");
		} else
			balance += amount;
	}
}