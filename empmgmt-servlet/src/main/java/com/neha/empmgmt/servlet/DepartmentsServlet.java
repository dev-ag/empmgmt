/**
 * © 2016 Early Warning Services, LLC.
 * All Rights Reserved.
 * Confidential and proprietary.
 */
package com.neha.empmgmt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;
import com.neha.empmgmt.service.impl.DepartmentServiceImpl;

@WebServlet(urlPatterns = "/departments.html")
public class DepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DepartmentService departmentService = new DepartmentServiceImpl();
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		System.out.println(servletConfig.getServletName() + " initialized..");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Service method called for " + request.getRequestURI());
		if (request.getMethod().equalsIgnoreCase(HttpMethod.GET)) {
			doGet(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.POST)) {
			doPost(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT)) {
			doPut(request, response);
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.DELETE) || (request.getParameter("action") != null
				&& request.getParameter("action").equalsIgnoreCase("delete"))) {
			doDelete(request, response);
		} else {
			if (request.getParameter("action") != null) {
				throw new RuntimeException(
						request.getMethod() + " is not supported for action = " + request.getParameter("action"));
			}
			throw new RuntimeException(request.getMethod() + " is not supported");
		}
	}
	
	/**
	 * This method is performing dual duty. 1. If an action is specified, it will merely re-route to add/edit page
	 * In case of edit, it will also populate the page with data.
	 * If action is not present and id is present => findById
	 * If action if not present and id is also not present => findAll.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Check if parameter name action is present
		String action = request.getParameter("action");
		//Step 1: Get the id from the request param
		String id = request.getParameter("id");
		if(action != null){
			//This means we have to merely forward it to appropriate page
			if(action.equalsIgnoreCase("ADD")){
				request.getRequestDispatcher("WEB-INF/views/department.jsp?action=add").forward(request,response);
				return;
			} else if(action.equalsIgnoreCase("edit")){
				//find the department by id to edit
				Department departmentToEdit = departmentService.findById(Integer.parseInt(id));
				request.setAttribute("department", departmentToEdit);
				request.getRequestDispatcher("WEB-INF/views/department.jsp?action=edit&id="+id).forward(request,response);
				return;
			}
		}
		//Step 2: If id is present => findById
		if(id != null){
			Department department = departmentService.findById(Integer.parseInt(id));
			request.setAttribute("department", department);
			request.getRequestDispatcher("WEB-INF/views/departments.jsp?id="+id).forward(request,response);
		} else {
			//findAll
			List<Department> departments = departmentService.findAll();
			request.setAttribute("departments", departments);
			request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 1: Read httpServletRequest and populate department Object from the form values
		Department department = new Department();
		department.setName(request.getParameter("name"));
		//Step 2: Validate the input parameters
		
		//Step 3: Now call appropriate service method to save this department object
		boolean saved = departmentService.save(department);
		if(saved)
			System.out.println("Department "+department.getName()+" was saved successfully");
		else
			System.out.println("Failed to save Department "+department.getName());
		//Step 4: set a attribute in the response which can be fetched in the JSP and a message can be displayed
		request.setAttribute("saved", saved);
		request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 1: Read httpServletRequest and populate department Object from the form values
		Department department = new Department();
		//Update will have id present so find and set
		department.setId(Integer.parseInt(request.getParameter("id")));
		department.setName(request.getParameter("name"));
		//Step 2: Validate the input parameters
		
		//Step 3: Now call appropriate service method to update this department object
		boolean updated = departmentService.update(department);
		if(updated)
			System.out.println("Department "+department.getName()+" was updated successfully");
		else
			System.out.println("Failed to update Department "+department.getName());
		//Step 4: set a attribute in the response which can be fetched in the JSP and a message can be displayed
		request.setAttribute("updated", updated);
		request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 1: Get the id from the request param
		String id = request.getParameter("id");
		//Step 2: If id is present => deleteById
		if(id != null){
			boolean deleted = departmentService.deleteById(Integer.parseInt(id));
			request.setAttribute("deleted", deleted);
			request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,response);
		} else {
			//findAll
			boolean deleted = departmentService.deleteAll();
			request.setAttribute("deleted", deleted);
			request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request,response);
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet getting destroyed");
		super.destroy();
	}

}
