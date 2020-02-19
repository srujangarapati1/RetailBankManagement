package com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TransactionBank")
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name = "transId")
	private long transId;

	@Column(name = "accId")
	private long accId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "status")
	private String status;

	@Column(name = "timeStamp")
	private String timeStamp;

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
