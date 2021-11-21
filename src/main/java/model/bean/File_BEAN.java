package model.bean;

import java.sql.Date;

public class File_BEAN {
	private int File_ID;
	private String File_Link;
	private Date DateUpload;
	private int Account_ID;
	public int getFile_ID()
	{
		return File_ID;
	}
	public void setFile_ID(int ID)
	{
		this.File_ID = ID;
	}
	public String getFile_Link()
	{
		return File_Link;
	}
	public void setFile_Link(String File_Link)
	{
		this.File_Link = File_Link;
	}
	public Date getDateUpload()
	{
		return DateUpload;
	}
	public void setDateUpload(Date DateUpload)
	{
		this.DateUpload = DateUpload;
	}
	public int getAccount_ID()
	{
		return Account_ID;
	}
	public void setAccount_ID(int ID)
	{
		this.Account_ID = ID;
	}
}