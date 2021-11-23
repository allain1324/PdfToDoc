package model.bean;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

public class ConvertProcess {
	public String filepath = null;
	public String filename = null;
	public File fi = null;
	public File fo = null;
	public FileInputStream fis = null;
	public BufferedInputStream bis = null;
	public InputStream is = null;
	public FileOutputStream fos = null ;
	public void ConvertPdfToDoc()
	{
		filepath = "C:\\\\Users\\\\toand\\\\Desktop\\\\BÃ€I-Táº¬P-THá»°C-HÃ€NH-Láº¬P-TRÃŒNH-Máº NG_2.pdf";
		filename = "BÃ€I-Táº¬P-THá»°C-HÃ€NH-Láº¬P-TRÃŒNH-Máº NG_2";
		
		
		try
		{
			//Tạo File input
			fi = new File(filepath);			
			fis = new FileInputStream(fi);
			bis = new BufferedInputStream(fis);
			
			//Tạo fileoutputstream
			fos = new FileOutputStream(fi);
			//Convert
			int len;
	        byte[] buffer = new byte[1024];
	        while (-1 != (len = is.read(buffer))) {
	            fos.write(buffer, 0, len);
	        }
//	        try {
//	            Files.copy(is, fi.toPath());
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            fis.close();
//	        }
	        
	        Files.copy(is, fi.toPath());
	        fis.close();
	        
	        PDDocument pdfDocument = null;
	        pdfDocument = PDDocument.load(fi);
	        fi.delete();
	        if (pdfDocument == null) {
	        	//Tra ve form up file
//	            return ResponseEntity.badRequest().body(new Response(null));
	        }
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        XWPFDocument document = new XWPFDocument();
	        int no_of_pages = pdfDocument.getNumberOfPages();

	        for (int i = 1; i <= no_of_pages; i++) {
	            pdfStripper.setStartPage(i);
	            pdfStripper.setEndPage(i);
	            pdfStripper.setSortByPosition(true);
	            String text = pdfStripper.getText(pdfDocument);
	            String[] lines = text.split(System.getProperty("line.separator"));

	            XWPFParagraph paragraph = document.createParagraph();
	            XWPFRun para_run = paragraph.createRun();


	            for (String temp : lines) {
	                para_run.setText(temp);
	                para_run.addCarriageReturn();
	            }


	        }
			//Tạo file output
			fo = new File("C://Users/toand/Desktop//" + filename + ".docx");
//			if (f1.createNewFile()) {
//	            System.out.println("File is created!");
//	        } else {
//	            System.out.println("File already exists.");
//	        }

	        File doc_file = fo;
	        FileOutputStream out = new FileOutputStream(doc_file);
	        document.write(out);
	        System.out.println(doc_file.getAbsolutePath());
	        document.close();
	        pdfDocument.close();
	        out.close();
		}catch (Exception e) {}
		

		
        
        
	}
}
