package com.neha.empmgmt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/welcome.html")
public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("employee") != null) {
			response.sendRedirect("employees.html");
			return;
		} else if (request.getParameter("department") != null) {
			response.sendRedirect("departments.html");
			return;
		} else {
			request.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(request, response);
			return;
		}
	}
}
