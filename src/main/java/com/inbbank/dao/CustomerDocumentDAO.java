package com.inbbank.dao;

import com.inbbank.model.CustDocument;



public interface CustomerDocumentDAO {

	CustDocument getAddressProofDocumentById(String customerid);

	CustDocument getAgeProofDocumentById(String customerid);

}
