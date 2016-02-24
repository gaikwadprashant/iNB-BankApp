package com.inbbank.controller;

  
import java.util.ArrayList;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.wsentity.WSCustomer;



@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;

	 @RequestMapping(value="/applicationFormUnregistered",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
		public Status createCustomer(@RequestBody Customer customer) {
		 try{
		 customerService.createCustomer(customer);
//			LOGGER.info("Employee added Successfully !");
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
		//	LOGGER.error(e.getMessage());
			return new Status(0, e.toString());
		}

		}
	 
	 @RequestMapping(value="/viewcustomer",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
		public List<WSCustomer> getCustomer() {
		 List<Customer> customers=null;
		 List<WSCustomer> customerList = new ArrayList<WSCustomer>();
		 try{
			
			 customers= customerService.getCustomer();
			 for (Customer customer : customers) {
				customerList.add(mapper.map(customer, WSCustomer.class));
			}
		
		} catch (Exception e) {
		//	LOGGER.error(e.getMessage());
			
		}
		 return customerList;
		}
}
