package model.bo;

import java.util.ArrayList;
import model.bean.*;
import model.dao.checkLoginDAO;

public class checkLoginBO 
{
	public static account getAccountBO(String username, String password) 
	{
		return checkLoginDAO.getAccountDAO(username, password);
	}
	public static ArrayList<fileInfor> GetRecentFile(int idAccount)
	{
		System.out.println(idAccount + "idAccount");
		return checkLoginDAO.GetRecentFile(idAccount);
	}
}
