package model.dao;
//import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

import model.bean.account;

public class checkLoginDAO {
	public static account getAccountDAO(String username, String password){
		try {
			 java.sql.Connection con;
			 
			 String query = "SELECT * FROM account where username= ? AND password = ?";
			 Class.forName("com.mysql.cj.jdbc.Driver");
//			 Class.forName("com.mysql.jdbc.Driver");
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
		    	 boolean admin = Boolean.valueOf(rs.getString(4));
		    	 account ac = new account(idAccount,user,pass,admin);
		    	 return ac;
		     }else{
		    	 return null;
		     }
		}catch(Exception e) {
			
		}
		return null;
	}
}
