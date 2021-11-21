package model.bean;

public class account {
	private int idAccount;
	private String username;
	private String password;
	private boolean admin;
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public account(int id,String user,String pass, boolean admin) {
		this.idAccount = id;
		this.username = user;
		this.password = pass;
		this.admin = admin;
	}
}
