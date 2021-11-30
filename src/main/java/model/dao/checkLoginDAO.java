package model.dao;
//import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import model.bean.*;

public class checkLoginDAO 
{
	private static DataAccessSupport das;
	public checkLoginDAO()
	{
		das = new DataAccessSupport();
	}
	public static account getAccountDAO(String username, String password)
	{
		try 
		{
			 java.sql.Connection con;
			 
			 String query = "SELECT * FROM account where username= ? AND password = ?";
//			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_LTMang","root","");
			 PreparedStatement prepare = con.prepareStatement(query);
			 prepare.setString(1,username);
			 prepare.setString(2,password);
			 //Statement sqlStatement = con.createStatement();
			 ResultSet rs = prepare.executeQuery();
		     if(rs.next()){
		    	 int idAccount = Integer.parseInt(rs.getString(1));
		    	 String user = rs.getString(2);
		    	 String pass = rs.getString(3);
		    	 boolean admin = rs.getString(4).equals("1") ? true : false;
		    	 System.out.println(admin);
		    	 account ac = new account(idAccount,user,pass,admin);
		    	 return ac;
		     }else{
		    	 return null;
		     }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<fileInfor> GetRecentFile(int idAccount)
	{
		ArrayList<fileInfor> res = new ArrayList<fileInfor>();
		try
		{
			// Ket noi JDBC
			das = new DataAccessSupport();
			System.out.println("Get Recent File!");
			das.LoadJDBC();
			System.out.println("Ket noi jdbc");
			
			String sql = "SELECT * FROM fileinfor WHERE idAccount = ? ORDER BY iDRow DESC LIMIT 3;";
			PreparedStatement pstm = das.conn.prepareStatement(sql);
			pstm.setInt(1, idAccount);
			ResultSet rs = pstm.executeQuery();
			while (rs.next())
			{
				fileInfor temp = new fileInfor(rs.getInt("iDRow"),rs.getInt("idAccount"),rs.getDate("dateUpload"),rs.getString("nameFileDownload"),rs.getBlob("fileDownload"));
				res.add(temp);
			}
			return res;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
