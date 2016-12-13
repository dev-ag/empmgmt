package com.neha.empmgmt.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	private DepartmentDao departmentDao;

	public EmployeeMapper(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setFirstName(rs.getString("firstname"));
		employee.setLastName(rs.getString("lastname"));
		employee.setEmail(rs.getString("email"));
		employee.setAge(rs.getInt("age"));
		employee.setSalary(rs.getDouble("salary"));
		employee.setFullTime(rs.getBoolean("fulltime"));
		employee.setJoinDate(rs.getDate("joindate"));
		int departmentId = rs.getInt("department");
		employee.setDepartment(departmentDao.findById(departmentId));
		return employee;
	}
}
