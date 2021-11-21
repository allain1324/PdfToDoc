package model.bean;
import java.time.LocalDate;

public class fileInfor {
	private int iD;
	private int idAccount;
	private LocalDate dateUpload;
	private String linkFile;
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public LocalDate getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(LocalDate dateUpload) {
		this.dateUpload = dateUpload;
	}
	public String getLinkFile() {
		return linkFile;
	}
	public void setLinkFile(String linkFile) {
		this.linkFile = linkFile;
	}
	
	public fileInfor(int id,int idAcc, LocalDate date, String link) {
		this.iD = id;
		this.idAccount = idAcc;
		this.dateUpload = date;
		this.linkFile = link;
	}
}
