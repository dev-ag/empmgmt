package com.neha.empmgmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.dao.EmployeeDao;
import com.neha.empmgmt.model.Department;
import com.neha.empmgmt.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	@Transactional
	public Employee findById(int id) {
		return (Employee) manager.find(Employee.class, id);
	}

	@Override
	@Transactional
	public Employee findByEmail(String email) {
		Employee employee = (Employee) manager.createQuery("Select e from Employee e where e.email like :email")
				.setParameter("email", email);
		return employee;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> employees = (List<Employee>) (manager.createQuery("Select e from Employee e", Employee.class)
				.getResultList());
		return employees;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean save(Employee employee) {
		// get the departmentId from database to save it in the employee table
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		employee.setDepartment(department);
		manager.persist(employee);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int id) {
		Employee employee = this.findById(id);
		if (employee != null) {
			manager.remove(employee);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Employee employee) {
		Department department = departmentDao.findByName(employee.getDepartment().getName());
		employee.setDepartment(department);
		manager.merge(employee);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAll() {
		manager.createQuery("Delete from Employee", Employee.class);
		return true;
	}
}
