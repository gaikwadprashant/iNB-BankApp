package com.inbbank.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.CustomerDocumentDAO;
import com.inbbank.model.CustDocument;
import com.inbbank.service.CustomerDocumentService;
import com.inbbank.wsentity.WSCustomerDocument;



@Service("customerDocumentService")
public class CustomerDocumentServiceImpl implements CustomerDocumentService{
	
	@Autowired
	private CustomerDocumentDAO customerDocumentDAO;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public byte[] getAddressProofDocumentById(String customerid) {
		CustDocument custDocument = customerDocumentDAO.getAddressProofDocumentById(customerid);
		WSCustomerDocument wsCustomerDocument = null;
		if(custDocument != null){
			wsCustomerDocument = mapper.map(custDocument, WSCustomerDocument.class);
			return wsCustomerDocument.getImageaddress();
		}
		else
			return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public byte[] getAgeProofDocumentById(String customerid) {
		CustDocument custDocument = customerDocumentDAO.getAgeProofDocumentById(customerid);
		WSCustomerDocument wsCustomerDocument = null;
		if(custDocument != null){
			wsCustomerDocument = mapper.map(custDocument, WSCustomerDocument.class);
			return wsCustomerDocument.getImageage();
		}
		else
			return null;
	}

}
