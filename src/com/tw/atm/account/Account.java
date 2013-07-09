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

	public void deposit(double amount) throws NegativeValueException {
		if (amount < 0) {
			throw new NegativeValueException(
					"You can't deposit a negative amount.");
		} else
			balance += amount;
	}

	public void withdraw(double amount) throws NegativeValueException,
			InsufficientBalanceException {
		if (amount > balance) {
			throw new InsufficientBalanceException("Insufficient balance.");
		} else if (amount < 0) {
			throw new NegativeValueException(
					"You can't withdraw a negative amount.");
		} else
			balance -= amount;
	}

	public void transferMoney(Account destinationAccount, double amount) {
		try {
			this.withdraw(amount);
			destinationAccount.deposit(amount);
		} catch (NegativeValueException | InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
	}
}