package model.bean;

import java.sql.Blob;
import java.sql.Date;

public class fileInfor 
{
	private int iD;
	private int idAccount;
	private Date dateUpload;
	private String filename;
	private Blob fileData;
	
	public int getiD() 
	{
		return iD;
	}
	public void setiD(int iD) 
	{
		this.iD = iD;
	}
	
	public int getIdAccount() 
	{
		return idAccount;
	}
	public void setIdAccount(int idAccount) 
	{
		this.idAccount = idAccount;
	}
	
	public Date getDateUpload() 
	{
		return dateUpload;
	}
	public void setDateUpload(Date dateUpload) 
	{
		this.dateUpload = dateUpload;
	}
	
	public String getfilename() 
	{
		return filename;
	}
	public void setfilename(String filename) 
	{
		this.filename = filename;
	}
	
	public Blob getfileData() 
	{
		return this.fileData;
	}
	public void setfileData(Blob fileData) 
	{
		this.fileData = fileData;
	}
	
	public fileInfor(int id,int idAcc, Date date, String filename, Blob fileData) 
	{
		this.iD = id;
		this.idAccount = idAcc;
		this.dateUpload = date;
		this.filename = filename;
		this.fileData = fileData;
	}
}
