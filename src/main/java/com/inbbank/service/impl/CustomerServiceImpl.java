package com.inbbank.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbbank.dao.CustomerDao;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.wsentity.WSCustomer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	public WSCustomer createCustomer(WSCustomer wscustomer) throws Exception {
		Customer customer = mapper.map(wscustomer, Customer.class);
		 com.inbbank.model.Branch branch = mapper.map(wscustomer.getBranchPOJO(),com.inbbank.model.Branch.class);
		 customer.setBranch(branch);
		 com.inbbank.model.Account account = mapper.map(wscustomer.getAccount(), com.inbbank.model.Account.class);
		 account.setId(com.inbbank.util.GenerateUUID.getRendomString());
		 Set<com.inbbank.model.Account> accounts = new HashSet<com.inbbank.model.Account>();
		 accounts.add(account);
		 customer.setAccounts(accounts);
		 customer= customerDao.createCustomer(customer);
		 wscustomer.setId(customer.getId());
		 
		 wscustomer.setApplicationStatus("Pending");
		// TODO Auto-generated method stub
		return wscustomer;
	}

	public List<Customer>  getCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getCustomer();
	}

	public boolean unregistereduserEmail(String email) throws Exception {
		return customerDao.unregistereduserEmail(email);
	}

	public boolean uploadDocument(CustDocument custDocument) throws Exception {
		// TODO Auto-generated method stub
		return customerDao.uploadDocument(custDocument);
	}

}
