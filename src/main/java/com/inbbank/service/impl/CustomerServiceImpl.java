package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.CustomerDao;
import com.inbbank.model.Customer;
import com.inbbank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	public boolean createCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return customerDao.createCustomer(customer);
	}

	public List<Customer>  getCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getCustomer();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String unregistereduserEmail(String email) throws Exception {
		return customerDao.unregistereduserEmail(email);
	}
}
