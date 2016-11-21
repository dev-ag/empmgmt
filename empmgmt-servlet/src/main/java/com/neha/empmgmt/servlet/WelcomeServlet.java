package com.neha.empmgmt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/welcome.do")
public class WelcomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}

}
