package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.bean.Transfer;
import com.exceptions.AccountDoesnotExist;
import com.exceptions.GlobalExceptions;
import com.util.DatabaseUtil;

public class Cashierdao implements CashierdaoInterface{

	public int deductAmount(Transfer t)  throws AccountDoesnotExist,GlobalExceptions
	{
		Connection con;
		con=DatabaseUtil.getConnection();
		try
		{
			PreparedStatement pstmt=con.prepareStatement("update Account_E set  Amount=? where AccountId=?");
			pstmt.setDouble(1,getBalance(t.getSource())-t.getAmount());
			pstmt.setInt(2, t.getSource());
			int i=pstmt.executeUpdate();
			return i;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountDoesnotExist();
			
		}  
		catch(Exception e)
		{
			throw new GlobalExceptions();
		}
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
	
	}

	public int trnasferAmount(Transfer t) throws AccountDoesnotExist,GlobalExceptions
	{
		Connection con;
		con=DatabaseUtil.getConnection();
		try
		{
			PreparedStatement pstmt=con.prepareStatement("update Account_E set  Amount=? where AccountId=?");
			pstmt.setDouble(1, getBalance(t.getTarget())+t.getAmount());
			pstmt.setInt(2, t.getTarget());
			int i=pstmt.executeUpdate();
			return i;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountDoesnotExist();
		}  
		catch(Exception e)
		{
			throw new GlobalExceptions();
		}
		
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		
	}

	public double setAmount(Transfer t)
	{
		Connection con;
		con=DatabaseUtil.getConnection();
		try
		{
			PreparedStatement ps3=con.prepareStatement("insert into accountstatement values(?,?,?,SYSDATE)");
			ps3.setInt(1, t.getSource());
			ps3.setInt(2, t.getTarget());
			ps3.setDouble(3, t.getAmount());
			
			return ps3.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		return 0.0;
	}

	public ArrayList<Transfer> lastTransactions(int accountId,int count)
	{
		Connection con;
		ResultSet rs=null;
		ArrayList<Transfer> t1=new ArrayList<Transfer>();
		con=DatabaseUtil.getConnection();
		try
		{
			PreparedStatement ps=con.prepareStatement("select SOURCEACCOUNTID,TARGETACCOUNTID,AMOUNT,TO_CHAR(TRANSACTIONDATE,'YYYY-MM-DD'),TO_CHAR(TRANSACTIONDATE,'HH24:MI:SS') from AccountStatement where SourceAccountId=? OR TargetAccountId=? order by TransactionDate DESC");
			ps.setInt(1,accountId);
			ps.setInt(2, accountId);
			rs=ps.executeQuery();
			while(rs.next()&&count!=0)
			{
				int sourceaccId=rs.getInt(1);
				int targetaccId=rs.getInt(2);
				double amount=rs.getDouble(3);
				Date date=rs.getDate(4);
				Time time=rs.getTime(5);
				Transfer t=new Transfer(sourceaccId,targetaccId,amount,date,time);
				t1.add(t);	
				--count;
				
			}
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		
		return t1;

	}

	public double getBalance(int accountId) {
		// TODO Auto-generated method stub
		Connection con;
		ResultSet rs=null;
		double amount=0.0;
		con=DatabaseUtil.getConnection();
		try
		{
			PreparedStatement ps=con.prepareStatement("select amount from account_e where accountid=?");
			ps.setDouble(1, accountId);
			rs=ps.executeQuery();
			while(rs.next()){
				amount=rs.getDouble(1);
	
		}
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		return amount;
	
	}

	public boolean checkAccountId(int accountId)
	{
		Connection con;
		con=DatabaseUtil.getConnection();
		ResultSet rs=null;
		try
		{
			PreparedStatement ps=con.prepareStatement("select accountId from Account_E ");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(accountId==rs.getInt(1))
					return true;
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		return false;
			
	}

}
