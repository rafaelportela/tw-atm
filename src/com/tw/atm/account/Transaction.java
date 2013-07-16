package com.tw.atm.account;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private Date date;
	private double charges;
	private double credits;
	private double lineTotal;

	public Transaction(double charges, double credits, double lineTotal) {
		date = Calendar.getInstance().getTime();
		this.charges = charges;
		this.credits = credits;
		this.lineTotal = lineTotal;
	}

	public Date getDate() {
		return date;
	}

	public double getCharges() {
		return charges;
	}

	public double getCredits() {
		return credits;
	}

	public double getLineTotal() {
		return lineTotal;
	}

}
