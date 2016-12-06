package com.neha.empmgmt.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.neha.empmgmt.model.Employee;
import com.neha.empmgmt.validator.Validator;

public class EmployeeValidator implements Validator<Employee> {

	@Override
	public List<String> validate(Employee employee) {
		System.out.println("Validating employee...");
		// List to hold errors
		List<String> errors = new ArrayList<String>();
		// now Validate each field using commons validator
		// Null or empty
		if (StringUtils.isEmpty(employee.getFirstName())) {
			errors.add("First Name can not be empty.");
		}
		// Should only contain alphabets and no spaces
		if (!StringUtils.isAlpha(employee.getFirstName())) {
			errors.add("First Name should only contain alphabets.");
		}
		if (StringUtils.isEmpty(employee.getLastName())) {
			errors.add("Last Name can not be empty.");
		}
		// Should only contain alphabets and no spaces
		if (!StringUtils.isAlpha(employee.getLastName())) {
			errors.add("Last Name should only contain alphabets.");
		}
		if (employee.getSalary() < 0) {
			errors.add("Salary should be more than 0.0");
		}
		return errors;
	}
}
