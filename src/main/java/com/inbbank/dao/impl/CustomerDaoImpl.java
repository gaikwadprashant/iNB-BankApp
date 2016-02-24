package com.inbbank.dao.impl;



import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.CustomerDao;
import com.inbbank.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createCustomer(Customer customer) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("wewe");
		try{
			sessionFactory.getCurrentSession().save(customer);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		System.out.println("getCustomer");
		List<Customer> custmerList= sessionFactory.getCurrentSession().createQuery("from Customer ").list();
		return custmerList;
	}

}
