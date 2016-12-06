package com.neha.empmgmt.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.validator.Validator;

public class DepartmentValidator implements Validator<Department> {

	@Override
	public List<String> validate(Department department) {
		System.out.println("Validating department...");
		//List to hold errors
		List<String> errors = new ArrayList<String>();
		//now Validate each field using commons validator
		//Null or empty
		if(StringUtils.isEmpty(department.getName())){
			errors.add("Name can not be empty.");
		}
		//Should only contain alphabets and space
		if(!StringUtils.isAlphaSpace(department.getName())){
			errors.add("Name should only contain alphabets and space.");
		}
		//You can add more validation here if you want
		return errors;
	}

}
