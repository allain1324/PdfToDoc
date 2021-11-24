package model.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;

public class DataAccessSupport {
	public static String url = "jdbc:mysql://localhost:3306/db_ltmang";
	private static String user = "root";
	private static String password = "";
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	
	public Connection getConnection() throws SQLException 
	{
		return DriverManager.getConnection(url, user, password);
	}
	
	public void LoadJDBC()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = this.getConnection();
		}
		catch (Exception e) {}
		
	}
	public void DataAccessSupport() {}
}
