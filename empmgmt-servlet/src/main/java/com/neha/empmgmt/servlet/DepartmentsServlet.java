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

import org.apache.commons.lang3.StringUtils;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;
import com.neha.empmgmt.service.impl.DepartmentServiceImpl;
import com.neha.empmgmt.validator.Validator;
import com.neha.empmgmt.validator.impl.DepartmentValidator;

@WebServlet(urlPatterns = "/departments.html")
public class DepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DepartmentService departmentService = new DepartmentServiceImpl();
	private Validator<Department> departmentValidator = new DepartmentValidator();

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		System.out.println(servletConfig.getServletName() + " initialized..");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Service method called for " + request.getRequestURI());
		String action = request.getParameter("action");
		// If the method is GET, simply route to doGet
		if (request.getMethod().equalsIgnoreCase(HttpMethod.GET)) {
			doGet(request, response);
		}
		// If the method is POST, check if it is Add or Edit
		// Add would mean doPost -> A new entry will be created. POST is
		// equivalent to SAVE.
		// Edit would mean doPut - > PUT is equivalent to Update
		else if (request.getMethod().equalsIgnoreCase(HttpMethod.POST)) {
			if(StringUtils.isEmpty(action)){
				//do a POST if no action => Add
				doPost(request, response);
			}
			else if (action != null && action.equalsIgnoreCase("add")) {
				doPost(request, response);
			} else if (action != null && action.equalsIgnoreCase("edit")) {
				doPut(request, response);
			} else {
				throw new RuntimeException(request.getMethod() + " is not supported for action=" + action);
			}

		}
		// If the update request came via form and method type = PUT
		else if (request.getMethod().equalsIgnoreCase(HttpMethod.PUT)) {
			doPut(request, response);
		}
		// Delete via form or action = delete
		else if (request.getMethod().equalsIgnoreCase(HttpMethod.DELETE)
				|| (action != null && action.equalsIgnoreCase("delete"))) {
			doDelete(request, response);
		} else {
			if (action != null) {
				throw new RuntimeException(request.getMethod() + " is not supported for action = " + action);
			}
			throw new RuntimeException(request.getMethod() + " is not supported");
		}
	}

	/**
	 * This method is performing dual duty. 1. If an action is specified, it
	 * will merely re-route to add/edit/delete page In case of edit, it will
	 * also populate the page with data. If action is not present and id is
	 * present => findById If action if not present and id is also not present
	 * => findAll.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Step 1: We have only one page and will have to populate the list always so, get the list
		List<Department> departments = departmentService.findAll();
		request.setAttribute("departments", departments);
		//Step 2: Set default label to the btn
		request.setAttribute("btnLabel", "Add Department");
		// Step 3: Check if parameter name action is present
		String action = request.getParameter("action");
		// Step 4: Get the id from the request param
		String id = request.getParameter("id");
		// Step 5: evaluate action
		if (action != null) {
			// This means we have to show the page on add/edit mode or after delete
			if (action.equalsIgnoreCase("ADD")) {
				// forward to add page
				request.getRequestDispatcher("WEB-INF/views/departments.jsp?action=add").forward(request, response);
				return;
			} else if (action.equalsIgnoreCase("edit")) {
				// find the department by id to edit
				Department departmentToEdit = departmentService.findById(Integer.parseInt(id));
				request.setAttribute("department", departmentToEdit);
				//showing btnLable
				request.setAttribute("btnLabel", "Update Department");
				// forward to edit page
				request.getRequestDispatcher("WEB-INF/views/departments.jsp?action=edit&id=" + id).forward(request,
						response);
				return;
			} else if (action.equalsIgnoreCase("delete")) {
				// simply delegate to doDelete to delete
				doDelete(request, response);
				return;
			}
		}
		// Step 6: If id is present => findById
		if (id != null) {
			Department department = departmentService.findById(Integer.parseInt(id));
			request.setAttribute("department", department);
			request.getRequestDispatcher("WEB-INF/views/departments.jsp?id=" + id).forward(request, response);
		} else {
			//findAll
			request.getRequestDispatcher("WEB-INF/views/departments.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 1: Read httpServletRequest and populate department Object from
		// the form values
		Department department = new Department();
		department.setName(request.getParameter("name"));
		// TODO: Step 2: Validate the input parameters
		List<String> errors = departmentValidator.validate(department);
		if (errors.size() > 0) {
			// This means validation had errors
			request.setAttribute("errors", errors);
			//populate list
			List<Department> departments = departmentService.findAll();
			request.setAttribute("departments", departments);
			//showing btnLable
			request.setAttribute("btnLabel", "Add Department");
			request.getRequestDispatcher("WEB-INF/views/departments.jsp?action=add").forward(request, response);
			return;
		}
		// Step 3: Now call appropriate service method to save this department
		// object
		boolean saved = departmentService.save(department);
		if (saved)
			System.out.println("Department " + department.getName() + " was saved successfully");
		else
			System.out.println("Failed to save Department " + department.getName());
		// Step 4: set a attribute in the response which can be fetched in the
		// JSP and a message can be displayed
		response.sendRedirect("departments.html?added=true");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 1: Read httpServletRequest and populate department Object from
		// the form values
		Department department = new Department();
		// Update will have id present so find and set
		department.setId(Integer.parseInt(request.getParameter("id")));
		department.setName(request.getParameter("name"));
		// Step 2: Validate the input parameters
		List<String> errors = departmentValidator.validate(department);
		if (errors.size() > 0) {
			// This means validation had errors
			request.setAttribute("errors", errors);
			request.setAttribute("department", department);
			//showing btnLable
			request.setAttribute("btnLabel", "Update Department");
			request.getRequestDispatcher("WEB-INF/views/departments.jsp?action=edit&id=" + request.getParameter("id"))
					.forward(request, response);
			return;
		}
		// Step 3: Now call appropriate service method to update this department
		// object
		boolean updated = departmentService.update(department);
		if (updated)
			System.out.println("Department " + department.getName() + " was updated successfully");
		else
			System.out.println("Failed to update Department " + department.getName());
		// Step 4: set a attribute in the response which can be fetched in the
		// JSP and a message can be displayed
		response.sendRedirect("departments.html?updated=true");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 1: Get the id from the request param
		String id = request.getParameter("id");
		// Step 2: If id is present => deleteById
		if (id != null) {
			departmentService.deleteById(Integer.parseInt(id));
		} else {
			// findAll
			departmentService.deleteAll();
		}
		response.sendRedirect("departments.html?deleted=true");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet getting destroyed");
		super.destroy();
	}

}
