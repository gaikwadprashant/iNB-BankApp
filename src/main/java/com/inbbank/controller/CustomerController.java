package com.inbbank.controller;

  
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.wsentity.WSCustomer;

import com.inbbank.model.Account;
import com.inbbank.model.Branch;
import com.inbbank.model.CustDocument;
import com.inbbank.util.GenerateUUID;



@RestController
//@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;

	 @RequestMapping(value="//unregistereduser",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
		public Status createCustomer(@RequestBody WSCustomer wscustomer) {
		 try{
			 Customer customer = mapper.map(wscustomer, Customer.class);
			 Branch branch = mapper.map(wscustomer.getBranchPOJO(),Branch.class);
			 customer.setBranch(branch);
			 Account account = mapper.map(wscustomer.getAccount(), Account.class);
			 account.setId(GenerateUUID.getRendomString());
			 Set<Account> accounts = new HashSet<Account>();
			 accounts.add(account);
			 customer.setAccounts(accounts);
			 //customer.setBranch(wscustomer.getBranchPOJO());
		 customerService.createCustomer(customer);
//			LOGGER.info("Employee added Successfully !");
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
		//	LOGGER.error(e.getMessage());
			return new Status(0, e.toString());
		}

		}
	 
	 @RequestMapping(value="/unregistereduser/details",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
		public List<WSCustomer> getCustomer() {
		 List<Customer> customers=null;
		 List<WSCustomer> customerList = new ArrayList<WSCustomer>();
		 try{
			
			 customers= customerService.getCustomer();
			 for (Customer customer : customers) {
				WSCustomer wsCustomer = mapper.map(customer, WSCustomer.class);
				wsCustomer.setAccounthash(wsCustomer.getAccounts());
				customerList.add(wsCustomer);
			}
		
		} catch (Exception e) {
		//	LOGGER.error(e.getMessage());
			
		}
		 return customerList;
		}
	 
	 
//verify email
	 @RequestMapping(value="/unregistereduser",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String unregistereduserEmail(@RequestParam("email") String email) throws Exception {
		
			 String status = customerService.unregistereduserEmail(email);
//			LOGGER.info("Employee added Successfully !");
		
			return status;
	
		}
	 
	//(@RequestParam("file") MultipartFile file
		@RequestMapping(value = "/document", method = RequestMethod.POST)
		public @ResponseBody Status uploadProjectSheet(@RequestParam("addressProof") MultipartFile addressProof,@RequestParam("ageProof") MultipartFile ageProof,@RequestParam("email") String email) {
			
			try {
				
				CustDocument custDocument = new CustDocument();
				custDocument.setImageaddress(addressProof.getBytes());
				custDocument.setImageaddress(ageProof.getBytes());
				return new Status();
				
			} catch (Exception e) {
				return new Status(0, e.toString());
			}
		}}
