package com.inbbank.controller;

  
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inbbank.model.Account;
import com.inbbank.model.Branch;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.util.GenerateUUID;
import com.inbbank.wsentity.WSCustomer;




@RestController
@CrossOrigin
//@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;

	 @RequestMapping(value="/unregistereduser",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
		public WSCustomer createCustomer(@RequestBody WSCustomer wscustomer) throws Exception {
		 	 return customerService.createCustomer(wscustomer);
		
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
		public Status unregistereduserEmail(@RequestParam("email") String email) {
		 try{
		boolean flag = customerService.unregistereduserEmail(email);
//			LOGGER.info("Employee added Successfully !");
		System.out.println(flag);
		Status status = new Status();
		if(flag){
		status.setAlreadyExists("true");
		}else{
			status.setAlreadyExists("false");
		}
			return status;
		} catch (Exception e) {
		//	LOGGER.error(e.getMessage());
			return new Status(0, e.toString());
		}

		}
	 
	//(@RequestParam("file") MultipartFile file
		@RequestMapping(value = "/document", method = RequestMethod.POST)
		public @ResponseBody Status uploadDocument(@RequestParam("addressProof") MultipartFile addressProof,@RequestParam("ageProof") MultipartFile ageProof,@RequestParam("email") String email) {
			
			try {
				
				CustDocument custDocument = new CustDocument();
				custDocument.setImageaddress(addressProof.getBytes());
				custDocument.setImageage(ageProof.getBytes());
				custDocument.setEmail(email);
				customerService.uploadDocument(custDocument);
				return new Status();
				
			} catch (Exception e) {
				return new Status(0, e.toString());
			}
		}
}
