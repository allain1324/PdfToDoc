package model.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;

public class DataAccessSupport {
	private static String url = "jdbc:mysql://localhost:3306/db_ltmang";
	private static String user = "root";
	private static String password = "";
	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public DataAccessSupport() {} 
	
	public Connection getConnection() throws SQLException 
	{
		return DriverManager.getConnection(url, user, password);
	}
	
	public void LoadJDBC()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = this.getConnection();
		}
		catch (Exception e) {}
	}
	
	public void CloseConnection() 
	{
		try 
		{
			if (this.conn != null) 
			{
				this.conn.close();
			}
		} 
		catch (Exception e) {}
	}
}
