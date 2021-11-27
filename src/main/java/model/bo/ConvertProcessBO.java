package model.bo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.fontbox.ttf.CmapSubtable;
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
	
	public ConvertProcessBO() 
	{
		CPDAO = new ConvertProcessDAO();
	}
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
	            String res = clientFileName.substring(i + 1);
	            System.out.println("extractFileName function: " + res);
	            return res;
	        }
	    }
	    return null;
	}
	
	public void ConvertPdfToDoc(String filename, InputStream is_para, int idAccount)
	{	
		try
		{
			//Tạo File input
			this.fi = new File("C:\\InputFile\\" + filename);
			this.fi.createNewFile();
//			this.fi.mkdir();
			System.out.println("tạo file input");
			System.out.println(this.fi);
//			if (this.fi.createNewFile()) 
//			{
//				System.out.println("File created: " + this.fi.getName());
//			} 
//			else 
//			{
//				System.out.println("File already exists.");
//			}
//			this.fis = new FileInputStream(this.fi);
//			System.out.println("tạo file input stream");
//			this.bis = new BufferedInputStream(this.fis);
//			System.out.println("tạo buffered input stream");
			
			//Tạo fileoutputstream
			this.fo = new File("C:\\OutputFile\\" + filename + ".docx");
			this.fo.createNewFile();
			this.fos = new FileOutputStream(this.fo);
			System.out.println("tạo file output stream");
			
			//Convert
//			int len;
//	        byte[] buffer = new byte[1024];
//	        while (-1 != (len = is_para.read(buffer))) 
//	        {
//	        	this.fos.write(buffer, 0, len);
//	        }
//	        System.out.println("write buffer");
//	        try {
//	            Files.copy(is, fi.toPath());
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            fis.close();
//	        }
	        
//	        Files.copy(is_para, this.fo.toPath());
//	        this.fis.close();
//	        System.out.println("file copy input stream");
	        
	        PDDocument pdfDocument = null;
	        pdfDocument = PDDocument.load(is_para);
//	        this.fi.delete();
//	        System.out.println("this.fi.delete();");
	        
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
//	        this.fo = new File("C:\\OutputFile\\" + "filename" + ".docx");
//			if (f1.createNewFile()) {
//	            System.out.println("File is created!");
//	        } else {
//	            System.out.println("File already exists.");
//	        }

//	        File doc_file = this.fo;
//	        fos = new FileOutputStream();
//	        IOUtils.copyLarge(is_para, this.fos);
	        document.write(this.fos);
//	        System.out.println(doc_file.getAbsolutePath());
	        document.close();
	        pdfDocument.close();
//	        this.fos.close();
	        System.out.println("Finish Convert Pdf to Doc");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception Convert pdf to doc");
		}
	}
	public int ConvertProcess(String filename, InputStream is_para, int idAccount)
	{
		try
		{
			File theDir1 = new File("C:\\InputFile\\");
			File theDir2 = new File("C:\\OutputFile\\");
			if (!theDir1.exists()){
			    theDir1.mkdirs();
			    System.out.println("theDir1.mkdirs();");
			}
			if (!theDir2.exists()){
			    theDir2.mkdirs();
			    System.out.println("theDir2.mkdirs();");
			}
			System.out.println("Convert Process BO");
			this.ConvertPdfToDoc(filename, is_para, idAccount);
			java.util.Date utilDate = new java.util.Date();
			System.out.println("utilDate: " + utilDate);
//			IOUtils.copyLarge(this.fos, this.is);
			this.is = FileUtils.openInputStream(this.fo);
			return CPDAO.SaveFileToDB(filename, this.is, idAccount, new java.sql.Date(utilDate.getTime()));
		}
		catch (Exception e) {}
		return 0;
	}
	public fileInfor getFileFromDB(int idfile)
	{
		try 
		{
			return CPDAO.getFileFromDB(idfile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
