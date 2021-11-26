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
import model.bo.*;

@WebServlet("/downloadFile")
public class DownloadFileServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConvertProcessBO CPBO = new ConvertProcessBO();
		try {
			int idFile = 0;
			try {
				idFile = Integer.parseInt(request.getParameter("idFile"));
			} catch (Exception e) {

			}
			fileInfor filedownload = CPBO.getFileFromDB(idFile);

			if (filedownload == null) {
				response.getWriter().write("No data found");
				return;
			}

			// file1.zip, file2.zip
			String fileName = filedownload.getfilename();
			System.out.println("File Name: " + fileName);

			String contentType = this.getServletContext().getMimeType(fileName);
			System.out.println("Content Type: " + contentType);

			response.setHeader("Content-Type", contentType);

			response.setHeader("Content-Length", String.valueOf(filedownload.getfileData().length()));

			response.setHeader("Content-Disposition", "inline; filename=\"" + filedownload.getfilename() + "\"");

			Blob fileData = filedownload.getfileData();
			InputStream is = fileData.getBinaryStream();

			byte[] bytes = new byte[1024];
			int bytesRead;

			while ((bytesRead = is.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, bytesRead);
			}
			is.close();

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
