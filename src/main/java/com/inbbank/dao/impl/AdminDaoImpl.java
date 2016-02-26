package com.inbbank.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inbbank.dao.AdminDao;
import com.inbbank.model.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Admin> authonticateAdmin(String userName, String password) {
		String queryString = "from Admin admin where admin.userName=:userName and admin.password=:password";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		// Query query = session.createQuery(" from Admin where
		// userName=:userName and password=:password ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		List<Admin> adminList = query.list();
		return adminList;
	}
}
