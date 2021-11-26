package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.ooxml.POIXMLDocumentPart;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.*;

public class ConvertProcessDAO
{
	private static DataAccessSupport das;
	
	public ConvertProcessDAO() {}
	
	public void querySetLimit() 
	{
		try
		{
			// Set limit cho mysql de tang kich thuoc file co the luu vao database
			String querySetLimit = "SET GLOBAL max_allowed_packet=30000000;"; // 30 MB
			Statement stSetLimit = das.conn.createStatement();
			stSetLimit.execute(querySetLimit);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("querySetLimit function throws exception!");
		}
	}
	
	public Long GenFileID() throws SQLException 
	{
		// Generate id file moi bang cach tim max id cua file da upload
		String sql = "Select max(a.iDRow) from fileinfor a";
		PreparedStatement pstm = das.conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) 
		{
			long max = rs.getLong(1);
			return max;
		}
		return 0L;
	}

	public void SaveFileToDB(String fileName, InputStream is_para, int idAccount, Date dateupload) throws SQLException 
	{
		try 
		{
			// Ket noi JDBC
			das.LoadJDBC();
	    	
			// Set limit cho mysql
			querySetLimit();
			
			// Luu file vao database
			das.conn.commit();
			String sql = "Insert into fileinfor(iDRow,idAccount,dateUpload,nameFileUpload,nameFileDownload,fileDownload) values (?,?,?,?,?,?) ";
			PreparedStatement pstm = das.conn.prepareStatement(sql);
			Long id = this.GenFileID() + 1;
			pstm.setLong(1, id);
			pstm.setInt(2, idAccount);
			pstm.setDate(3, dateupload);
			pstm.setString(4, fileName);
			pstm.setString(5, fileName);
			pstm.setBinaryStream(6, is_para);
			pstm.executeUpdate();
			das.conn.commit();
			das.CloseConnection();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("SaveFileToDB function throws exception!");
		}
	}
}
