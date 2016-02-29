package com.inbbank.controller;

  
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;












import com.inbbank.model.Account;
import com.inbbank.model.CustDocument;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.CustomerService;
import com.inbbank.service.IBankMailService;
import com.inbbank.wsentity.WSAccount;
import com.inbbank.wsentity.WSBranchCustomer;
import com.inbbank.wsentity.WSCustomer;

@RestController
@CrossOrigin
//@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;
	@Autowired
	IBankMailService iBankMailService;
	
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
		 
		
		
		
		@RequestMapping(value = "/authorisation/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public WSCustomer login(@PathVariable(value = "clientId") int clientId) {
			// DONE
			return customerService.isClientAuthorized(clientId);
		}

		@RequestMapping(value = "/registeredcustomer/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public WSCustomer registeredCustomer(@PathVariable(value = "clientId") int clientId) {
			// DONE
			return customerService.registeredCustomer(clientId);
		}
		
		@RequestMapping(value = "/registeredcustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
		public WSCustomer getRegisteredCustomer(@RequestBody Customer customer) {
			// DONE
			WSCustomer wSCustomer = customerService.getRegisteredCustomer(customer);
			
			if (wSCustomer.getEmail() != null) {
				iBankMailService.sendMail("info.inbbank@gmail.com", "arun.agrawal@Xoriant.Com"/*customer.getEmail()*/, "Wel Come to IBank",
						"See you after mail ");
			}
			return wSCustomer;
		}
		
		@RequestMapping(value = "/registeredcustomer/account/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public WSAccount viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
			// DONE
			return customerService.viewAccountBalance(clientId);
		}
		
		
		@RequestMapping(value = "/clientHome/transferMoney", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Account transferMoney(@RequestBody Account sender,@RequestBody Account reciever) {
			System.out.println("sender.getBalance() is  " + sender.getBalance());
			Account account = customerService.transferMoney(sender,reciever);
			return account;
		}
		

		
		/**
		 * For Branch Manager Viewing UnRegistered User Details
		 * @return
		 */
		@RequestMapping(value = "/unregistereduser/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<WSBranchCustomer> getAllUnregisteredUsers(){
			return customerService.getAllUnregisteredUsers();
		}
		
		/**
		 * For Branch Manager Viewing Registered User Details
		 * @return
		 */
		@RequestMapping(value = "/registeredcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<WSBranchCustomer> getAllRegisteredUsers(){
			return customerService.getAllRegisteredUsers();
		}
		
		/**
		 * For Branch Manager Viewing Rejected User Details
		 * @return
		 */
		@RequestMapping(value = "/rejectededcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<WSBranchCustomer> getAllRejectedUsers(){
			return customerService.getAllRejectedUsers();
		}
		
		@RequestMapping(value = "/unregistereduser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public WSBranchCustomer getCustomerDetailsById(@PathVariable(value="id") String id){
			
			return customerService.getCustomerDetailsById(id);
		}
}
