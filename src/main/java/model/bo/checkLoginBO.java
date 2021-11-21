package model.bo;

import model.bean.account;
import model.dao.checkLoginDAO;

public class checkLoginBO {
	public static account getAccountBO(String username, String password) {
		return checkLoginDAO.getAccountDAO(username, password);
	}
}
