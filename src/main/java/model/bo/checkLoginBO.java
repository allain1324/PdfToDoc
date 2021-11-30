package model.bo;

import java.util.ArrayList;
import model.bean.*;
import model.dao.checkLoginDAO;

public class checkLoginBO 
{
	private static checkLoginDAO cLogDAO;
	public checkLoginBO()
	{
		cLogDAO = new checkLoginDAO();
	}
	public static account getAccountBO(String username, String password) 
	{
		return checkLoginDAO.getAccountDAO(username, password);
	}
	public ArrayList<fileInfor> GetRecentFile(int idAccount)
	{
		return cLogDAO.GetRecentFile(idAccount);
	}
}
