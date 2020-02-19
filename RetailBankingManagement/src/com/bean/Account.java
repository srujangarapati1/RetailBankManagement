package com.bean;

public class Account {

	private int accountNo;
	private String accountType;
	private String status;
	private double amount;
	private long custId;

	public long getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public Account(int accountNo, String accountType, String status, double amount, long custId) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.status = status;
		this.amount = amount;
		this.custId = custId;
	}

	public Account(String accountType, double amount) {

		// this.accountNo = accountNo;
		this.accountType = accountType;
		// this.status = status;
		this.amount = amount;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
