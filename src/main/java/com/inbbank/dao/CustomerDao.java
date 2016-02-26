package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;

public interface CustomerDao {

	Customer createCustomer(Customer customer)throws Exception;

	List<Customer> getCustomer();

	boolean unregistereduserEmail(String email)throws Exception;

	boolean uploadDocument(CustDocument custDocument)throws Exception;

}
