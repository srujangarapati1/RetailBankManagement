package com.boot.spring.RetailBanking.RetailBanking_AccountService.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class AccountBean {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountid_generator")
	@SequenceGenerator(name = "accountid_generator", sequenceName = "accountid_generator", initialValue = 100000000, allocationSize = 20)
	@Column(name = "accountId")
	private long accountId;

	@Column(name = "customerId")
	private long customerId;

	@Column(name = "accountType")
	private String accountType;

	@Column(name = "balance")
	private double balance;

	public AccountBean(long accountId, long customerId, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.accountType = accountType;
		this.balance = balance;
	}

	public AccountBean() {

	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
