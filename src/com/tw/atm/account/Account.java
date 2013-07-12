package com.tw.atm.account;

public class Account {
	private int id;
	private String holderName;
	private String holderCPF;
	private double balance;
	private AccountType accountType;
	private AccountStatement accountStatement;

	public Account(AccountType accountType) {
		this.balance = 0.0;
		this.accountType = accountType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderCPF() {
		return holderCPF;
	}

	public void setHolderCPF(String holderCPF) {
		this.holderCPF = holderCPF;
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

	public void transferMoney(Account destinationAccount, double amount)
			throws NonExistentAccountException {
		if (destinationAccount == null)
			throw new NonExistentAccountException(
					"The destination account doesn't exist.");

		try {
			this.withdraw(amount);
			destinationAccount.deposit(amount);
		} catch (NegativeValueException | InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
	}
}