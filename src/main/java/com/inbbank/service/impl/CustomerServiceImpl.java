package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
