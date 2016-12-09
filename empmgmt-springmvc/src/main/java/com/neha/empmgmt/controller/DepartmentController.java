package com.neha.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.service.DepartmentService;

@Controller
@RequestMapping(path="/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showAddFormAndListDepartments(ModelMap modelMap){
		//set model for add part of the page
		modelMap.addAttribute("department", new Department());
		//findall departments
		List<Department> departments = departmentService.findAll();
		//set model for the list
		modelMap.addAttribute("departments", departments);
		//Show btnLabel dynamically
		modelMap.addAttribute("btnLabel", "Add Department");
		return "departments";
	}
	
	@RequestMapping(path="/add", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute("department") Department department, ModelMap modelMap){
		if(department.getId() == 0){
			//new department hence add it
			departmentService.save(department);
			return "redirect:/departments?added=true";
		} else {
			//update it
			departmentService.update(department);
			return "redirect:/departments?updated=true";
		}
	}
	
	@RequestMapping(path="/edit", method = RequestMethod.GET)
	public String populateEditFormAndListDepartments(@RequestParam (required = true) String id, ModelMap modelMap){
		Department department = departmentService.findById(Integer.parseInt(id));
		//set model for add part of the page
		modelMap.addAttribute("department", department);
		//findall departments
		List<Department> departments = departmentService.findAll();
		//set model for the list
		modelMap.addAttribute("departments", departments);
		//Show btnLabel dynamically
		modelMap.addAttribute("btnLabel", "Update Department");
		return "departments";
	}
	
	@RequestMapping(path="/delete", method = RequestMethod.GET)
	public String removeById(@RequestParam (required = true) String id, ModelMap modelMap){
		departmentService.deleteById(Integer.parseInt(id));
		//set model for add part of the page
		modelMap.addAttribute("department", new Department());
		//findall departments
		List<Department> departments = departmentService.findAll();
		//set model for the list
		modelMap.addAttribute("departments", departments);
		return "redirect:/departments?deleted=true";
	}
}
