package bank;

import Exceptions.AmountException;

public class Account {

	private int id;
	private String type;
	private double balance;

	public Account(int id, String type, double balance) {
		this.id = id;
		this.type = type;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit(double amount) throws AmountException {
		if (amount < 1) {
			throw new AmountException("Invalid amount.");
		} else {
			double newBalance = balance + amount;
			setBalance(newBalance);
		}
	}

	public void withdraw(double amount) throws AmountException {
		if (amount < 1 || amount > balance) {
			throw new AmountException("Invalid amount.");
		} else {
			double newBalance = balance - amount;
			setBalance(newBalance);
		}
	}

}
