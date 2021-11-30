package controller;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.account;
import model.bean.fileInfor;
import model.bo.*;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		ArrayList<fileInfor> listFile = null;
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		account ac = checkLoginBO.getAccountBO(userName,password);
		if(ac != null) {
			try {
				int id = ac.getIdAccount();
				listFile = checkLoginBO.GetRecentFile(id);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			if(ac.isAdmin())
				destination = "adminForm.jsp";
			else
				destination = "clientForm.jsp";
//			request.getSession().setAttribute("listFile", listFile);
			request.getSession().setAttribute("Account", ac);
			response.sendRedirect(destination);
		} else {
			destination = "/loginForm.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

}
