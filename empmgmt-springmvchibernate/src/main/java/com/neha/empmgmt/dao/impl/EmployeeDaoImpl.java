package com.neha.empmgmt.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;

@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	public EmployeeDaoImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		return getHibernateTemplate().get(Employee.class, id);
	}

	@Override
	@Transactional
	public Employee findByEmail(String email) {
		Employee employee = (Employee) getHibernateTemplate()
				.find("from Employee where email=?", new Object[] { email }).get(0);
		return employee;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> employees = (List<Employee>) getHibernateTemplate().find("from Employee");
		return employees;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean save(Employee employee) {
		// get the departmentId from database to save it in the employee table
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		employee.setDepartment(department);
		getHibernateTemplate().save(employee);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int id) {
		Employee employee = getHibernateTemplate().load(Employee.class, id);
		if (employee != null) {
			getHibernateTemplate().delete(employee);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Employee employee) {
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		employee.setDepartment(department);
		getHibernateTemplate().update(employee);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAll() {
		List<Employee> employees = this.findAll();
		getHibernateTemplate().delete(employees);
		return true;
	}

}
