package com.inbbank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;










import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;







import com.inbbank.dao.CustomerDao;
import com.inbbank.model.Account;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.util.GenerateUUID;
import com.inbbank.wsentity.WSAccount;
import com.inbbank.wsentity.WSBranchCustomer;
import com.inbbank.wsentity.WSCustomer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	private DozerBeanMapper mapper;
	String InvalidCustomer = "Invalid credentials";
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
	
	
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomerAccount(int accountId) {
		return customerDao.registeredCustomerAccount(accountId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer registeredCustomer(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = customerDao.getRegisteredCustomer(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer isClientAuthorized(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = customerDao.getClientDetails(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSAccount viewAccountBalance(int clientId) {
		WSAccount wsAccount = null;
		Account account = customerDao.viewAccountBalance(clientId);
		wsAccount = mapper.map(account, WSAccount.class);
		return wsAccount;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Account transferMoney(Account sender, Account reciever) {
		BigDecimal fundTransferBalance = sender.getBalance().subtract(new BigDecimal("50"));
		sender.setBalance(fundTransferBalance);
		return customerDao.viewAccountBalance(sender);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String unregisteredUser(String customer) {
		return customerDao.unregisteredUser(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Customer unregisteredUser(Customer account) {
		account.setId(GenerateUUID.getRendomString());
		return customerDao.unregisteredUser(account);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer getRegisteredCustomer(Customer customer) {

		WSCustomer wsCustomer = null;
		List<WSCustomer> list = new ArrayList<WSCustomer>();
		Customer customerData = customerDao.getValidateCustomer(customer.getUserName(), customer.getPassword());
		// customerData.setBranch(null);
		if (customerData != null) {
			wsCustomer = mapper.map(customerData, WSCustomer.class);
		} else {
			wsCustomer = new WSCustomer();
			wsCustomer.setException(InvalidCustomer);
		}
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<WSBranchCustomer> getAllUnregisteredUsers() {
		List<Customer> customers = customerDao.getAllUnregisteredUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
				for (WSAccount wsAccount : wsBranchCustomer.getAccounts())
					wsBranchCustomer.setAccount(wsAccount);
				wsBranchCustomers.add(wsBranchCustomer);
			}

		}
		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<WSBranchCustomer> getAllRegisteredUsers() {
		List<Customer> customers = customerDao.getAllRegisteredUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		for (Customer customer : customers) {
			WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
			wsBranchCustomer.setAccounthash(wsBranchCustomer.getAccounts());
			wsBranchCustomers.add(wsBranchCustomer);
		}

		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<WSBranchCustomer> getAllRejectedUsers() {
		List<Customer> customers = customerDao.getAllRejectedUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
				for (WSAccount wsAccount : wsBranchCustomer.getAccounts())
					wsBranchCustomer.setAccount(wsAccount);
				wsBranchCustomers.add(wsBranchCustomer);
			}

		}
		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String unregisteredUserVerifyReject(String clientId, String email) {
		return customerDao.unregisteredUserVerifyReject(clientId, email);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public WSBranchCustomer getCustomerDetailsById(String id) {
		Customer customer = customerDao.getCustomerDetailsById(id);
		WSBranchCustomer wsBranchCustomer = new WSBranchCustomer();
		if(customer!=null){
			wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
			Set<WSAccount> wsAccounts = wsBranchCustomer.getAccounts();
			for(WSAccount wsAccount : wsAccounts)
				wsBranchCustomer.setAccount(wsAccount);
			return wsBranchCustomer;
		}else
			return null;
		
	}

}
