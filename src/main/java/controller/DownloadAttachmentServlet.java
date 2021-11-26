package controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.bean.*;

@WebServlet("/downloadAttachment")
public class DownloadAttachmentServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;


//   @Override
//   protected void doGet(HttpServletRequest request, HttpServletResponse response)
//           throws ServletException, IOException {
//	   DataAccessSupport das = new DataAccessSupport();
//       try {
//           // Láº¥y ra káº¿t ná»‘i tá»›i Database.
//           // (Xem thÃªm tÃ i liá»‡u JDBC)
//    	   das.LoadJDBC();
//    	   //das.conn.setAutoCommit(false);
////           conn = ConnectionUtils.getMyConnection();
//           Long id = null;
//           try {
//               id = Long.parseLong(request.getParameter("id"));
//           } catch (Exception e) {
//
//           }
//           fileInfor attachment = getAttachmentFromDB(das.conn, id);
//
//           if (attachment == null) {
//               // KhÃ´ng cÃ³ dá»¯ file.
//               response.getWriter().write("No data found");
//               return;
//           }
//
//           // file1.zip, file2.zip
//           String fileName = attachment.getFileName();
//           System.out.println("File Name: " + fileName);
//
//           // abc.txt => text/plain
//           // abc.zip => application/zip
//           // abc.pdf => application/pdf
//           String contentType = this.getServletContext().getMimeType(fileName);
//           System.out.println("Content Type: " + contentType);
//
//           response.setHeader("Content-Type", contentType);
//
//           response.setHeader("Content-Length", String.valueOf(attachment.getFileData().length()));
//
//           response.setHeader("Content-Disposition", "inline; filename=\"" + attachment.getFileName() + "\"");
//
//   
//           // Vá»›i cÃ¡c file attachment dung lÆ°á»£ng lá»›n
//           // nÃªn Ä‘á»�c vÃ  ghi láº§n lÆ°á»£t
//           Blob fileData = attachment.getFileData();
//           InputStream is = fileData.getBinaryStream();
//
//           byte[] bytes = new byte[1024];
//           int bytesRead;
//
//           while ((bytesRead = is.read(bytes)) != -1) {
//               // Ghi dá»¯ liá»‡u áº£nh vÃ o Response.
//               response.getOutputStream().write(bytes, 0, bytesRead);
//           }
//           is.close();
//
//       } catch (Exception e) {
//           throw new ServletException(e);
//       } 
////       finally {
////           this.closeQuietly(das.conn);
////       }
//   }
//
//   private Attachment getAttachmentFromDB(Connection conn, Long id) throws SQLException {
//       String sql = "Select a.Id,a.File_Name,a.File_Data,a.Description "//
//               + " from Attachment a where a.id = ?";
//       PreparedStatement pstm = conn.prepareStatement(sql);
//       pstm.setLong(1, id);
//       ResultSet rs = pstm.executeQuery();
//       if (rs.next()) {
//           String fileName = rs.getString("File_Name");
//           Blob fileData = rs.getBlob("File_Data");
//           String description = rs.getString("Description");
//           return new Attachment(id, fileName, fileData, description);
//       }
//       return null;
//   }
//
//   private void closeQuietly(Connection conn) {
//       try {
//           if (conn != null) {
//               conn.close();
//           }
//       } catch (Exception e) {
//       }
//   }

}
