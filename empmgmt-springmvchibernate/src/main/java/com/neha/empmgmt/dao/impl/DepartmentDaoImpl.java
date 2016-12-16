package com.neha.empmgmt.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neha.empmgmt.dao.DepartmentDao;
import com.neha.empmgmt.model.Department;

@Repository
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Autowired
	public DepartmentDaoImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	@Transactional
	public Department findById(int id) {
		return (Department) getHibernateTemplate().get(Department.class, id);
	}

	@Override
	@Transactional
	public Department findByName(String name) {
		return (Department) getHibernateTemplate().get(Department.class, name);
	}

	@Override
	@Transactional
	public List<Department> findAll() {
		List<Department> departments = (List<Department>) (getHibernateTemplate().find("from Department"));
		return departments;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean save(Department department) {
		getHibernateTemplate().save(department);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int id) {
		Department department = getHibernateTemplate().load(Department.class, id);
		if (department != null) {
			getHibernateTemplate().delete(department);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Department department) {
		getHibernateTemplate().update(department);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAll() {
		List<Department> departments = this.findAll();
		getHibernateTemplate().delete(departments);
		return false;
	}

}
