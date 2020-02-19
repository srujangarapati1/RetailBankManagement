package com.dao;

import java.util.ArrayList;

import com.bean.Transfer;
import com.exceptions.AccountDoesnotExist;
import com.exceptions.GlobalExceptions;

public interface CashierdaoInterface {
	public int deductAmount(Transfer t) throws AccountDoesnotExist, GlobalExceptions;
	public int trnasferAmount(Transfer t) throws AccountDoesnotExist, GlobalExceptions;
	public double setAmount(Transfer t);
	public ArrayList<Transfer> lastTransactions(int accountId,int count);
	public double getBalance(int accountId);
	public boolean checkAccountId(int accountId);
}
