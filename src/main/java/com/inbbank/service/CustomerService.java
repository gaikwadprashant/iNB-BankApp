package com.inbbank.service;

import java.util.List;

import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.wsentity.WSCustomer;
//@Service
public interface CustomerService {

	WSCustomer createCustomer(WSCustomer wsCustomer) throws Exception;

	List<Customer>  getCustomer();

	boolean unregistereduserEmail(String email)throws Exception;

	boolean uploadDocument(CustDocument custDocument)throws Exception;


}
