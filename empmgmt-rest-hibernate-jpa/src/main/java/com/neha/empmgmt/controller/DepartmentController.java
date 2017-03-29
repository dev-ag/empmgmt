package com.neha.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Department> findAll() {
		return departmentService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Department findByID(@PathVariable int id) {
		return departmentService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public String save(@RequestBody Department department) {
		boolean created = departmentService.save(department);
		if (created) {
			return "Department created successfully";
		}
		return "could not save department";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String update(@RequestBody Department department) {
		boolean created = departmentService.update(department);
		if (created) {
			return "Department updated successfully";
		}
		return "could not update department";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteByID(@PathVariable int id) {
		boolean deleted = departmentService.deleteById(id);
		if (deleted) {
			return "Department deleted successfully";
		}
		return "Could not delete department";
	}
}
