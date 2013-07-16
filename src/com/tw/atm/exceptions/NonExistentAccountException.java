package com.tw.atm.exceptions;

public class NonExistentAccountException extends Exception {
	public NonExistentAccountException(String message) {
		super(message);
	}
}
