package com.neha.empmgmt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neha.empmgmt.model.Employee;

@WebServlet(urlPatterns = "/welcome.do")
public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("employee") != null) {
			request.getRequestDispatcher("WEB-INF/views/employees.jsp").forward(request, response);
			System.out.println(request.getParameter("employee"));
		}
		if (request.getParameter("department") != null)
			request.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(request, response);
		// TODO :
		// request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,
		// response);
	}

}
