package com.service;

import java.util.ArrayList;

import com.bean.Account;
import com.bean.Customer;
import com.bean.Transfer;
import com.exceptions.AccountDoesnotExist;
import com.exceptions.CostumerAlreadyExist;
import com.exceptions.CustomerIdDoesnotExists;
import com.exceptions.GlobalExceptions;

public interface Service {
	public String login(String userId,String Password);
	public long addCustomer(Customer c) throws CostumerAlreadyExist,GlobalExceptions;
	public long addAccount(long custid,Account a) throws CustomerIdDoesnotExists;
	public double getBalalnce(int accountid) throws AccountDoesnotExist;
	public boolean transfer(Transfer t) throws AccountDoesnotExist, GlobalExceptions;
	public ArrayList<Transfer> lastTransactions(int accountId,int count) throws AccountDoesnotExist;
}
