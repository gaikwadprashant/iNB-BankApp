package com.inbbank.dao.impl;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.CustomerDao;
import com.inbbank.model.Branch;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.util.GenerateUUID;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	@Transactional(propagation=Propagation.REQUIRED)
	public Customer createCustomer(Customer customer) throws Exception {
		
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Branch.class);
			criteria.add(Restrictions.eq("branchName", customer.getBranch().getBranchName()));
			Branch branch = (Branch) criteria.uniqueResult();
			customer.setBranch(branch);
			customer.setId(GenerateUUID.getRendomString());
			customer.setApplicationStatus("Pending");
			sessionFactory.getCurrentSession().save(customer);
		
		return customer;
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Customer> getCustomer() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts",JoinType.LEFT_OUTER_JOIN);
		List<Customer> custmerList = criteria.list();
		return custmerList;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public boolean unregistereduserEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("email", email));
		return criteria.list().isEmpty();
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean uploadDocument(CustDocument custDocument) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("email", custDocument.getEmail()));
		Customer customer = (Customer) criteria.uniqueResult();
		custDocument.setCustomer(customer);
		custDocument.setId(GenerateUUID.getRendomString());
		sessionFactory.getCurrentSession().save(custDocument);
		return true;
	}

}
