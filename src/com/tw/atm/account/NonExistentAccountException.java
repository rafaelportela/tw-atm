package com.tw.atm.account;

public class NonExistentAccountException extends Exception {
	public NonExistentAccountException(String message) {
		super(message);
	}
}
