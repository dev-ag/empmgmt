package com.neha.empmgmt.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(path="/departments", method = RequestMethod.GET)
	public String find(@RequestParam (required = false) String id, ModelMap modelMap){
		if(StringUtils.isEmpty(id)){
			List<Department> departments = departmentService.findAll();
			modelMap.addAttribute("departments", departments);
			return "departments";
		}
		Department department = departmentService.findById(Integer.parseInt(id));
		modelMap.addAttribute("department", department);
		return "department";
	}
	
	@RequestMapping(path="/departments/add", method = RequestMethod.GET)
	public String showAddPage(ModelMap modelMap){
		return "department";
	}
	
	@RequestMapping(path="/departments/add", method = RequestMethod.POST)
	public String save(@ModelAttribute("department") Department department, ModelMap modelMap){
		boolean added = departmentService.save(department);
		if(added){
			return "redirect:/departments?added=true";
		}
		return "redirect:/departments";
	}
	
	@RequestMapping(path="/departments/edit", method = RequestMethod.GET)
	public String showEditPage(@RequestParam (required = true) String id, ModelMap modelMap){
		Department department = departmentService.findById(Integer.parseInt(id));
		modelMap.addAttribute("department", department);
		return "department";
	}
}
