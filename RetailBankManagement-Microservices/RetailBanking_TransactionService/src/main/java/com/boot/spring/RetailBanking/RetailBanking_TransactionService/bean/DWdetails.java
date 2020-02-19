package com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean;

public class DWdetails {
	private long accountId;
	private double amount;
	private double proviousAmount;
	private double presentAmount;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getProviousAmount() {
		return proviousAmount;
	}

	public void setProviousAmount(double proviousAmount) {
		this.proviousAmount = proviousAmount;
	}

	public double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(double presentAmount) {
		this.presentAmount = presentAmount;
	}

}
