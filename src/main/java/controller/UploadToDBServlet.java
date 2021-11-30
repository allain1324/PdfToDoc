package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.*;
import model.bo.*;

@WebServlet("/UploadToDBServlet")
@MultipartConfig(	fileSizeThreshold = 1024 * 1024 * 2, // 2MB
       				maxFileSize = 1024 * 1024 * 10, // 10MB
       				maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UploadToDBServlet extends HttpServlet 
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
   {
       RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/clientForm.jsp");
       dispatcher.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
	   ConvertProcessBO CPBO = new ConvertProcessBO();
	   try 
	   {
		   System.out.println("Upload to DB Servlet");
    	   
    	   // Get idAccount
    	   account ac = (account) request.getSession().getAttribute("Account");
    	   int idAccount = ac.getIdAccount();
    	   System.out.println("Get idAccount:" + idAccount);
    	   int idFile = 0;
           // Duyet qua file upload
           for (Part part : request.getParts()) 
           {
        	   String filePath = request.getParameter("file");
               String fileName = CPBO.extractFileName(part);
               System.out.println("fileName: " + fileName);
               System.out.println("filePath: " + filePath);
               if (fileName != null && fileName.length() > 0) 
               {
					// Get du lieu noi dung file.
					InputStream is = part.getInputStream();

					// Chuyen doi pdf -> doc & ghi du lieu noi dung vao database
					idFile = CPBO.ConvertProcess(fileName, is, idAccount);
               }
           }
           
           // Tra ve response de thong bao upload thanh cong.
           request.getSession().setAttribute("idFile",idFile);
           System.out.println("idFile: " + idFile);
           response.sendRedirect(request.getContextPath() + "/clientForm.jsp");
       } 
	   catch (Exception e) 
	   {
           e.printStackTrace();
           request.setAttribute("errorMessage", "Error: " + e.getMessage());
           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientForm.jsp");
           dispatcher.forward(request, response);
       } 
   } 
}