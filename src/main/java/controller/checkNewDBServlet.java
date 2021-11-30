package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.account;
import model.bean.fileInfor;
import model.bo.checkLoginBO;

/**
 * Servlet implementation class checkNewDBServlet
 */
@WebServlet("/checkNewDBServlet")
public class checkNewDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkNewDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	   account ac = (account) request.getSession().getAttribute("Account");
 	   int idAccount = ac.getIdAccount();
 	   ArrayList<fileInfor> listFile = null;
 	   listFile = checkLoginBO.GetRecentFile(idAccount);
		request.getSession().setAttribute("listFile", listFile);
		response.sendRedirect("clientForm.jsp");
	}

}
