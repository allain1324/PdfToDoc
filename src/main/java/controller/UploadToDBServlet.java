package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.DataAccessSupport;
//import org.o7planning.tutorial.jdbc.ConnectionUtils;

@WebServlet("/uploadToDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
       maxFileSize = 1024 * 1024 * 10, // 10MB
       maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadToDBServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/clientForm.jsp");

       dispatcher.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   DataAccessSupport das = new DataAccessSupport();
	   try {
           // Káº¿t ná»‘i tá»›i Database
    	   
           // (Xem thÃªm tÃ i liá»‡u JDBC).
    	   
    	   das.LoadJDBC();
    	   das.conn.setAutoCommit(false);
//           conn = ConnectionUtils.getMyConnection();
//           conn.setAutoCommit(false);

           String description = request.getParameter("description");

   
           // Danh má»¥c cÃ¡c pháº§n Ä‘Ã£ upload lÃªn (CÃ³ thá»ƒ lÃ  nhiá»�u file).
           for (Part part : request.getParts()) {
               String fileName = extractFileName(part);
               if (fileName != null && fileName.length() > 0) {
                   // Dá»¯ liá»‡u file.
                   InputStream is = part.getInputStream();
                   ReadWritebyByte(das.conn, fileName, is, description);
                   // Ghi vÃ o file.
//                   this.writeToDB(das.conn, fileName, is, description);
               }
           }
           das.conn.commit();

 
           // Upload thÃ nh cÃ´ng.
           response.sendRedirect(request.getContextPath() + "/clientForm.jsp");
       } catch (Exception e) {
           e.printStackTrace();
           request.setAttribute("errorMessage", "Error: " + e.getMessage());
           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientForm.jsp");
           dispatcher.forward(request, response);
       } 
//	   finally {
//           this.closeQuietly(das.conn);
//       }
   }
   private void ReadWritebyByte(Connection conn, String fileName, InputStream fis, String description)
   {
	   try {
//			File f = new File("/home/alain/Downloads/test/sample.pdf");			
//			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			System.out.println(fileName);
			//String filename = fileName.split(".")[0];
			//ystem.out.println(fileName);
			File f1 = new File("C://Users/toand/Desktop//" + fileName + ".docx");
			if (f1.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
			FileOutputStream fos = new FileOutputStream(f1);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int c; 
			while((c = fis.read())!=-1){
				//System.out.println(c);
				fos.write(c);
			}
			String filename = fileName + ".docx";
			System.out.println(filename);
			InputStream is = new FileInputStream(f1);
			this.writeToDB(conn, filename, is, description);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	   
   }
   private String extractFileName(Part part) {
       // form-data; name="file"; filename="C:\file1.zip"
       // form-data; name="file"; filename="C:\Note\file2.zip"
       String contentDisp = part.getHeader("content-disposition");
       String[] items = contentDisp.split(";");
       for (String s : items) {
           if (s.trim().startsWith("filename")) {
               // C:\file1.zip
               // C:\Note\file2.zip
               String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
               clientFileName = clientFileName.replace("\\", "/");
               int i = clientFileName.lastIndexOf('/');
               // file1.zip
               // file2.zip
               return clientFileName.substring(i + 1);
           }
       }
       return null;
   }

   private Long getMaxAttachmentId(Connection conn) throws SQLException {
       String sql = "Select max(a.id) from Attachment a";
       PreparedStatement pstm = conn.prepareStatement(sql);
       ResultSet rs = pstm.executeQuery();
       if (rs.next()) {
           long max = rs.getLong(1);
           return max;
       }
       return 0L;
   }

   private void writeToDB(Connection conn, String fileName, InputStream fos, String description) throws SQLException {

	   try {
		   String querySetLimit = "SET GLOBAL max_allowed_packet=30000000;";  // 30 MB
           Statement stSetLimit = conn.createStatement();
           stSetLimit.execute(querySetLimit);
		   String sql = "Insert into Attachment(Id,File_Name,File_Data,Description) " //
	               + " values (?,?,?,?) ";
	       PreparedStatement pstm = conn.prepareStatement(sql);

	       Long id = this.getMaxAttachmentId(conn) + 1;
	       pstm.setLong(1, id);
	       pstm.setString(2, fileName);
	       pstm.setBinaryStream(3, fos);
	       pstm.setString(4, description);
	       pstm.executeUpdate();
	   }
	   catch (Exception e) {}
       
   }

   private void closeQuietly(Connection conn) {
       try {
           if (conn != null) {
               conn.close();
           }
       } catch (Exception e) {
       }
   }

}
