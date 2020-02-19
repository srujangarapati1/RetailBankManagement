package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.util.DatabaseUtil;

public class BankDaoImplementation implements DAO 
{
	//this is login method
	public  String getPassword(String userId)
	{
		Connection con;
		ResultSet rs;
		PreparedStatement pstmt;
		String pass=null;
		con=DatabaseUtil.getConnection();
		try {
		pstmt=con.prepareStatement("select password from Login_E where UserId=?");
		pstmt.setString(1, userId);
		rs=pstmt.executeQuery();
		
		while(rs.next())
		{
				pass=rs.getString(1);
				return pass;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally
		{
			DatabaseUtil.closeConnection(con);

		}
		
		return null;
		
	
    }
	public int updateTime(String userId) {
		Connection con;
		PreparedStatement ps2;
		con=DatabaseUtil.getConnection();
		int count;
		try 
		{
			ps2=con.prepareStatement("update Login_E set loginTime=SYSDATE where UserId=?");
			ps2.setString(1, userId);
			count=ps2.executeUpdate();
			return count;
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
	public String getRole(String userId)
	{
		Connection con;
		ResultSet rs;
		PreparedStatement pstmt;
		
		String role=null;
		con=DatabaseUtil.getConnection();
		try {
		pstmt=con.prepareStatement("select role from Login_E where UserId=?");
		pstmt.setString(1, userId);
		rs=pstmt.executeQuery();
		
		while(rs.next())
		{
				role=rs.getString(1);
				return role;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally
		{
			DatabaseUtil.closeConnection(con);

		}
		
		return null;
	}
	
}
