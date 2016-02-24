package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.Customer;

public interface CustomerDao {

	boolean createCustomer(Customer customer)throws Exception;

	List<Customer> getCustomer();;

}
