package com.neha.empmgmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public Department findById(int id) {
		return (Department) manager.find(Department.class, id);
	}

	@Override
	@Transactional
	public Department findByName(String name) {
		Department department = (Department) (manager
				.createQuery("Select d from Department d where d.name like :departmentName")
				.setParameter("departmentName", name)).getSingleResult();
		return department;
	}

	@Override
	@Transactional
	public List<Department> findAll() {
		List<Department> departments = (List<Department>) (manager.createQuery("Select d from Department d",
				Department.class)).getResultList();
		return departments;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean save(Department department) {
		manager.persist(department);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int id) {
		Department department = this.findById(id);
		if (department != null) {
			try {
				manager.remove(department);
				return true;
			} catch (DataIntegrityViolationException dive) {
				System.out.println(
						"DataIntegrityViolationException....The department has one or more Employees. Could not be deleted");
				return false;
			}
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Department department) {
		manager.merge(department);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAll() {
		manager.createQuery("Delete from Department", Department.class);
		return true;
	}

}
