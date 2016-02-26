package com.inbbank.dao;

import java.util.List;



import com.inbbank.model.Account;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;

public interface CustomerDao {

	Customer createCustomer(Customer customer)throws Exception;

	List<Customer> getCustomer();

	boolean unregistereduserEmail(String email)throws Exception;

	boolean uploadDocument(CustDocument custDocument)throws Exception;
/////////////////
	
public Customer getClientDetails(int id) ;

	
	public Customer getRegisteredCustomer(int id) ;
	
	public Customer registeredCustomerAccount(int id) ;

	public Account viewAccountBalance(int id) ;

	public Account viewAccountBalance(Account account) ;
	
	/**
	 * This will deal save new account opening request.
	 * @param customer
	 * @return
	 */
	public String unregisteredUser(String value) ;
	
	public Customer unregisteredUser(Customer customer) ;
	
	public Customer  getValidateCustomer(String userName,String password);
	
	public List<Customer> getAllUnregisteredUsers();


	public List<Customer> getAllRegisteredUsers();


	public List<Customer> getAllRejectedUsers();
	
	public String unregisteredUserVerifyReject(String clientId,String email);
}
