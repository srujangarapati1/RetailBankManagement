package com.service;

import java.util.ArrayList;

import com.bean.Account;
import com.bean.Customer;
import com.bean.Transfer;
import com.dao.CashierdaoInterface;
import com.dao.DAO;
import com.dao.ExecutivedaoInterface;
import com.exceptions.AccountDoesnotExist;
import com.exceptions.CostumerAlreadyExist;
import com.exceptions.CustomerIdDoesnotExists;
import com.exceptions.GlobalExceptions;
import com.factory.BankFactory;

public class ServiceImplementation implements Service {
		DAO d=BankFactory.getDaoInstance();
		CashierdaoInterface cd=BankFactory.CashierdaoInstance();
		ExecutivedaoInterface ed=BankFactory.ExecutiveInstance();
	public String login(String userId,String Password){
		String s=d.getPassword(userId);
		if(s!=null&&s.equalsIgnoreCase(Password))
		{
			int count=d.updateTime(userId);
			if(count>0)
			{
				String role=d.getRole(userId);
				return role;
			}
		}
		return null;
	}
	public long addCustomer(Customer c) throws CostumerAlreadyExist,GlobalExceptions
	{
		
	
		try{
	int id=ed.createCustomerAccount(c);
	long custId;
	if(id>0)
	{
		custId=ed.getCustId(c);
		return custId;
	}
}
catch(CostumerAlreadyExist e)
{
	throw new CostumerAlreadyExist();
}
catch(Exception e)
{
	throw new GlobalExceptions();
}
	
		return 0;
	}
	public long addAccount(long custid, Account a) throws CustomerIdDoesnotExists 
	{
		// TODO Auto-generated method stub
		
		if(ed.checkCustomerId(custid)){
		int count=ed.accountregister(custid,a);
		long id=00;
		if(count>0)
		{
			id=ed.getAccountId();
		}
		return id;}
		else {
			throw new CustomerIdDoesnotExists();
		}
	}
	public double getBalalnce(int accountId) throws AccountDoesnotExist
	{
		boolean x=cd.checkAccountId(accountId);
		if(x==true)
		{
			return cd.getBalance(accountId);
		}
		else
		{
			throw new AccountDoesnotExist();
		}
		
	}
	public boolean transfer(Transfer t) throws AccountDoesnotExist,GlobalExceptions
	{
		// TODO Auto-generated method stub
		int i,j;
		try
		{
			boolean x=cd.checkAccountId(t.getSource());
			if(x==true)
			{
				if(cd.getBalance(t.getSource())>=t.getAmount())
				{
					 i=cd.deductAmount(t);
					 j=cd.trnasferAmount(t);
					 if(i>0 && j>0)
					 {
						 if(cd.setAmount(t)>0)
						 {
							 return true;
						 }
					 }
					 
				}
					return false;
			}
			else
			{
				throw new AccountDoesnotExist();
			}
			
			
		}
		catch(AccountDoesnotExist e)
		{
			throw new AccountDoesnotExist();
		}
		catch(Exception e)
		{
			throw new GlobalExceptions();
		}
		
		
	}
	public ArrayList<Transfer> lastTransactions(int accountId,int count) throws AccountDoesnotExist
	{
		boolean x=cd.checkAccountId(accountId);
		if(x==true)
		{
			return cd.lastTransactions(accountId, count);
		}
		else
		{
			throw new AccountDoesnotExist();
		}
	}
}
