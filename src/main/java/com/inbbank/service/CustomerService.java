package com.inbbank.service;

import java.util.List;

import com.inbbank.model.Customer;
//@Service
public interface CustomerService {

	boolean createCustomer(Customer customer) throws Exception;

	List<Customer>  getCustomer();

	String unregistereduserEmail(String email)throws Exception;;

}
