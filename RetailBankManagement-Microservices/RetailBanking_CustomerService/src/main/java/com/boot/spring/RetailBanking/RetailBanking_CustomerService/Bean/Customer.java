package com.boot.spring.RetailBanking.RetailBanking_CustomerService.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cu_sq")
	@SequenceGenerator(name = "cu_sq", sequenceName = "cus_sq", initialValue = 500000000, allocationSize = 1)
	@Column(name = "customerId")
	private long customerId;

	@Column(name = "ssnId", unique = true, nullable = false)
	private long ssnId;

	@Column(name = "customerName", nullable = false)
	private String customerName;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "mobile", nullable = false)
	private long mobile;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "gmail", nullable = false)
	private String gmail;

	public Customer() {

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

}
