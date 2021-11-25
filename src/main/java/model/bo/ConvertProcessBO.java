package model.bo;

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

import javax.servlet.http.Part;

import model.bean.*;
import model.dao.*;

public class ConvertProcessBO 
{
	private String filepath;
	private String filename;
	private File fi;
	private File fo;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private InputStream is;
	private FileOutputStream fos;
	private static ConvertProcessDAO CPDAO;
	
	public String extractFileName(Part part) 
	{
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
	    for (String s : items) 
	    {
	    	if (s.trim().startsWith("filename")) 
	        {
	    		String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            clientFileName = clientFileName.replace("\\", "/");
	            int i = clientFileName.lastIndexOf('/');
	            return clientFileName.substring(i + 1);
	        }
	    }
	    return null;
	}
	
	public void ConvertPdfToDoc(String filename, InputStream is_para)
	{	
		try
		{
			//Tạo File input
			this.fi = new File(this.filename);			
			this.fis = new FileInputStream(this.fi);
			this.bis = new BufferedInputStream(this.fis);
			
			//Tạo fileoutputstream
			this.fos = new FileOutputStream(this.fi);
			
			//Convert
			int len;
	        byte[] buffer = new byte[1024];
	        while (-1 != (len = this.is.read(buffer))) 
	        {
	        	this.fos.write(buffer, 0, len);
	        }
//	        try {
//	            Files.copy(is, fi.toPath());
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            fis.close();
//	        }
	        
	        Files.copy(this.is, this.fi.toPath());
	        this.fis.close();
	        
	        PDDocument pdfDocument = null;
	        pdfDocument = PDDocument.load(this.fi);
	        this.fi.delete();
	        
//	        if (pdfDocument == null) 
//	        {
//	        	//Tra ve form up file
////	            return ResponseEntity.badRequest().body(new Response(null));
//	        }
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        XWPFDocument document = new XWPFDocument();
	        int no_of_pages = pdfDocument.getNumberOfPages();

	        for (int i = 1; i <= no_of_pages; i++) 
	        {
	            pdfStripper.setStartPage(i);
	            pdfStripper.setEndPage(i);
	            pdfStripper.setSortByPosition(true);
	            String text = pdfStripper.getText(pdfDocument);
	            String[] lines = text.split(System.getProperty("line.separator"));

	            XWPFParagraph paragraph = document.createParagraph();
	            XWPFRun para_run = paragraph.createRun();

	            for (String temp : lines) 
	            {
	                para_run.setText(temp);
	                para_run.addCarriageReturn();
	            }
	        }
	        
			//Tạo file output
	        this.fo = new File("filename" + ".docx");
//			if (f1.createNewFile()) {
//	            System.out.println("File is created!");
//	        } else {
//	            System.out.println("File already exists.");
//	        }

	        File doc_file = this.fo;
	        fos = new FileOutputStream(doc_file);
	        document.write(fos);
	        System.out.println(doc_file.getAbsolutePath());
	        document.close();
	        pdfDocument.close();
	        fos.close();
		}
		catch (Exception e) {}
	}
	public void ConvertProcess(String filename, InputStream is_para)
	{
		try
		{
			this.ConvertPdfToDoc(filename, is_para);
			java.util.Date utilDate = new java.util.Date();
			CPDAO.SaveFileToDB(filename, is_para, 0, new java.sql.Date(utilDate.getTime()));
		}
		catch (Exception e) {}
	}
	public void ReadWritebyByte(String fileName, InputStream is_para, int idAccount) 
	{
//		try 
//		{
////			File f = new File("/home/alain/Downloads/test/sample.pdf");
////			FileInputStream fis = new FileInputStream(f);
////			BufferedInputStream bis = new BufferedInputStream(fis);
//			
//			System.out.println(fileName);
//			// String filename = fileName.split(".")[0];
//			// System.out.println(fileName);
//			File f1 = new File("C://Users/toand/Desktop//" + fileName + ".docx");
//			if (f1.createNewFile()) 
//			{
//				System.out.println("File is created!");
//			} 
//			else 
//			{
//				System.out.println("File already exists.");
//			}
//			FileOutputStream fos = new FileOutputStream(f1);
//			BufferedOutputStream bos = new BufferedOutputStream(fos);
//			int c;
//			while ((c = fis.read()) != -1) 
//			{
//				// System.out.println(c);
//				fos.write(c);
//			}
//			String filename = fileName + ".docx";
//			System.out.println(filename);
//			InputStream is = new FileInputStream(f1);
//			java.util.Date utilDate = new java.util.Date();
//			CPDAO.SaveFileToDB(filename, is, idAccount, new java.sql.Date(utilDate.getDate()));
//		} 
//		catch (Exception e) 
//		{
//			// TODO: handle exception
//			System.out.println(e);
//		}
	}
}
