package com.neha.empmgmt.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.dao.mapper.EmployeeMapper;
import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Employee findById(int id) {
		Employee employee = new Employee();
		String sql = "Select * from employee where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new EmployeeMapper(departmentDao));
	}

	@Override
	public Employee findByEmail(String email) {
		String sql = "Select * from employee where email = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, new EmployeeMapper(departmentDao));

	}

	@Override
	public List<Employee> findAll() {
		String sql = "Select * from employee";
		return jdbcTemplate.query(sql, new EmployeeMapper(departmentDao));
	}

	@Override
	public boolean save(Employee employee) {
		// get the departmentId from database to save it in the employee table
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		int departmentId = department.getId();
		String sql = "Insert into employee(firstname,lastname,email,age,salary,fulltime,joindate,department)"
				+ "values(?,?,?,?,?,?,?,?)";

		int noOfRows = jdbcTemplate.update(sql,
				new Object[] { employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getAge(),
						employee.getSalary(), employee.isFullTime(), (new Date((employee.getJoinDate()).getTime())),
						departmentId });
		if (noOfRows > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "Delete from employee where id=?";
		int noOfRows = jdbcTemplate.update(sql, new Object[] { id });
		if (noOfRows > 0)
			return true;
		return false;
	}

	@Override
	public boolean update(Employee employee) {
		String sql = "Update employee "
				+ "set firstname=?,lastname=?,email=?,age=?,salary=?,fulltime=?,joindate=?,department=?"
				+ " where id =? ";
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		int departmentId = department.getId();
		int noOfRows = jdbcTemplate.update(sql,
				new Object[] { employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getAge(),
						employee.getSalary(), employee.isFullTime(), (new Date((employee.getJoinDate()).getTime())),
						departmentId, employee.getId() });
		if (noOfRows > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteAll() {
		String sql = "Delete from employee";
		int noOfRows = jdbcTemplate.update(sql);
		if (noOfRows > 0)
			return true;
		return false;
	}

}
