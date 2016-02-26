package com.inbbank.service;

import java.util.List;





import com.inbbank.model.Account;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.wsentity.WSAccount;
import com.inbbank.wsentity.WSBranchCustomer;
import com.inbbank.wsentity.WSCustomer;
//@Service
public interface CustomerService {

	WSCustomer createCustomer(WSCustomer wsCustomer) throws Exception;

	List<Customer>  getCustomer();

	boolean unregistereduserEmail(String email)throws Exception;

	boolean uploadDocument(CustDocument custDocument)throws Exception;

////////////////////////////
public WSCustomer isClientAuthorized(int clientId);
	
	public WSAccount viewAccountBalance(int clientId);
	// sender account will be returned
	public Account transferMoney(Account sender,Account reciever);
	// new account opening request
	public String unregisteredUser(String account);
	
	public Customer unregisteredUser(Customer account);

	public Customer registeredCustomerAccount(int clientId);
	
	public WSCustomer registeredCustomer(int clientId);
	
	public WSCustomer getRegisteredCustomer(Customer account);
	
	public List<WSBranchCustomer> getAllUnregisteredUsers();

	public List<WSBranchCustomer> getAllRegisteredUsers();

	public List<WSBranchCustomer> getAllRejectedUsers();
	
	public String unregisteredUserVerifyReject(String clientId,String email);
}
