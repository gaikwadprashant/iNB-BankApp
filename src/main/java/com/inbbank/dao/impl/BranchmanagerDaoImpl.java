package com.inbbank.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.BranchmanagerDao;
import com.inbbank.model.Branchmanager;
import com.inbbank.util.GenerateUUID;

@Repository
public class BranchmanagerDaoImpl implements BranchmanagerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(propagation = Propagation.REQUIRED)
	public void createBranchManager(Branchmanager branchManager) {
		sessionFactory.getCurrentSession().save(branchManager);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Branchmanager> getBranchManagers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Branchmanager.class);
		criteria.createAlias("branch", "branch");
		List<Branchmanager> branchManagers = criteria.list();
		return branchManagers;
	}

	public List<Branchmanager> getBMByUserNameAndPassword(String userName, String password, String branchName) {
		String queryString = "from Branchmanager where userName = :userName and password = :password and branch.branchName = :branchName";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		query.setParameter("branchName", branchName);
		List<Branchmanager> branchManagers = query.list();
		return branchManagers;
	}

}
