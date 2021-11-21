package model.bean;

public class Account_BEAN {
	private int Account_ID;
	private String Username;
	private String Password;
	//private int UserType;
	public int getAccount_ID()
	{
		return Account_ID;
	}
	public void setAccount_ID(int ID)
	{
		this.Account_ID = ID;
	}
	public String getUsername()
	{
		return Username;
	}
	public void setUsername(String username)
	{
		this.Username = username;
	}
	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}
//	public int getUserType()
//	{
//		return UserType;
//	}
//	public void setUserType(int UserType)
//	{
//		this.UserType = UserType;
//	}
	
	public Account_BEAN(int id, String username, String password) 
	{
		this.Account_ID = id;
	    this.Username = username;
	    this.Password = password;
	    //this.UserType = usertype;
	 }
}