package com.tw.atm.account;

import java.util.ArrayList;
import java.util.List;

import com.tw.atm.exceptions.InsufficientBalanceException;
import com.tw.atm.exceptions.NegativeValueException;
import com.tw.atm.exceptions.NonExistentAccountException;

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

	public int getId() {
		return id;
	}

	public String getHolderName() {
		return holderName;
	}

	public String getHolderCPF() {
		return holderCPF;
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