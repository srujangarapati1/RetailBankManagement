package com.dao;

import com.bean.Account;
import com.bean.Customer;
import com.exceptions.CostumerAlreadyExist;
import com.exceptions.GlobalExceptions;

public interface ExecutivedaoInterface {
	public int createCustomerAccount(Customer customer) throws CostumerAlreadyExist,GlobalExceptions; 
	public long getCustId(Customer c);
	public int accountregister(long custid,Account account);
	public long getAccountId();
	public boolean checkCustomerId(long custId);
}
