package com.bean;

import java.util.ArrayList;

public class Customer {
	
	private long customerId;
	private long ssnId;
	private String customerName;
	private int age;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	
	ArrayList<Account> accounts = new ArrayList<Account>();

	
	public Customer(long ssnId, String customerName, int age, String addressLine1, String addressLine2, String city,
			String state) {
		super();
		this.ssnId = ssnId;
		this.customerName = customerName;
		this.age = age;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
	}

	// Constructor with Accounts' array
	public Customer(long customerId, long ssnId, String customerName, int age, String addressLine1, String addressLine2,
			String city, String state, ArrayList<Account> accounts) {
		this.customerId = customerId;
		this.ssnId = ssnId;
		this.customerName = customerName;
		this.age = age;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.accounts = accounts;
	}

	// Constructor without Accounts' array
	public Customer(long customerId, long ssnId, String customerName, int age, String addressLine1, String addressLine2,
			String city, String state) {
		
		this.customerId = customerId;
		this.ssnId = ssnId;
		this.customerName = customerName;
		this.age = age;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
	}


	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getSsnId() {
		return ssnId;
	}

	public void setSsnId(long ssnId) {
		this.ssnId = ssnId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
	

}
