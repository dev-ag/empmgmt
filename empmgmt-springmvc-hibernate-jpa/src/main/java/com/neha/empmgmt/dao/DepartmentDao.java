package com.neha.empmgmt.dao;

import java.util.List;

import com.neha.empmgmt.model.Department;

public interface DepartmentDao {
	Department findById(int id);

	Department findByName(String name);

	List<Department> findAll();

	boolean save(Department department);

	boolean deleteById(int id);
	
	boolean deleteAll();

	boolean update(Department department);

}