package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Account;
import com.bean.Customer;
import com.exceptions.CostumerAlreadyExist;
import com.exceptions.GlobalExceptions;
import com.util.DatabaseUtil;

public class Executivedao implements ExecutivedaoInterface{

	public int createCustomerAccount(Customer customer) throws CostumerAlreadyExist,GlobalExceptions
	{
		Connection con;
		PreparedStatement pstmt;
		con=DatabaseUtil.getConnection();
		
		
		try {
			pstmt=con.prepareStatement("insert into Customer_E values(seq_e.nextval,?,?,?,?,?,?,?)");
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setLong(2, customer.getSsnId());
			pstmt.setInt(3, customer.getAge());
			pstmt.setString(4, customer.getAddressLine1());
			pstmt.setString(5,customer.getAddressLine2());
			pstmt.setString(6, customer.getCity());
			pstmt.setString(7, customer.getState());
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new CostumerAlreadyExist();
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

	public long getCustId(Customer c) 
	{
		Connection con;
		ResultSet rs;
		PreparedStatement pstmt;
		con=DatabaseUtil.getConnection();
		
		try {
			pstmt=con.prepareStatement("select max(custId) from Customer_E");
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
		return 0;
		
	}

	public int accountregister(long custid,Account account) 
	{
		Connection con;
		PreparedStatement pstmt;
	
		con=DatabaseUtil.getConnection();
		try
		{
			pstmt=con.prepareStatement("insert into Account_E(CUSTID,accountid,accounttype,amount) values(?,acc_E.nextval,?,?)");
           pstmt.setLong(1,custid);
           pstmt.setString(2,account.getAccountType());
           pstmt.setDouble(3,account.getAmount());

          return pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally
		{
			DatabaseUtil.closeConnection(con);
			
		}
			
		return 0;
           
         
	}

	public long getAccountId() 
	{
		Connection con;
		//Statement st;
		ResultSet rs;
		PreparedStatement pstmt;
	
		con=DatabaseUtil.getConnection();
		try
		{
			
			pstmt=con.prepareStatement("select max(AccountId) from Account_e");
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	finally
	{
		DatabaseUtil.closeConnection(con);
		
	}
		
	return 0;
}

	public boolean checkCustomerId(long custId) {
		// TODO Auto-generated method stub
		Connection con;
		con=DatabaseUtil.getConnection();
		ResultSet rs=null;
		try
		{
			PreparedStatement ps=con.prepareStatement("select custId from Customer_e ");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(custId==rs.getInt(1))
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
