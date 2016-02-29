package com.inbbank.service;

public interface CustomerDocumentService {

	byte[] getAddressProofDocumentById(String customerid);

    byte[] getAgeProofDocumentById(String customerid);

}
