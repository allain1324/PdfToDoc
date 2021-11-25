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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.*;

public class ConvertProcessDAO
{
	private static DataAccessSupport das;
	public Long getMaxAttachmentId() throws SQLException 
	{
		String sql = "Select max(a.id) from Attachment a";
		PreparedStatement pstm = das.conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) 
		{
			long max = rs.getLong(1);
			return max;
		}
		return 0L;
	}

	public void writeToDB(String fileName, InputStream fos) throws SQLException 
	{
		try 
		{
			String querySetLimit = "SET GLOBAL max_allowed_packet=30000000;"; // 30 MB
			Statement stSetLimit = das.conn.createStatement();
			stSetLimit.execute(querySetLimit);
			String sql = "Insert into Attachment(Id,File_Name,File_Data) " //
					+ " values (?,?,?) ";
			PreparedStatement pstm = das.conn.prepareStatement(sql);

			Long id = this.getMaxAttachmentId() + 1;
			pstm.setLong(1, id);
			pstm.setString(2, fileName);
			pstm.setBinaryStream(3, fos);
			pstm.executeUpdate();
		} 
		catch (Exception e) {}
	}
}
