package com.inbbank.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.AdminDao;
import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Admin;
import com.inbbank.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired AdminDao adminDao;

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Admin authonticateAdmin(String userName, String password) throws InvalidUserException{
		List<Admin> adminList =  adminDao.authonticateAdmin(userName, password);
		if(adminList.isEmpty()){
			throw new InvalidUserException("Invalid Username and Password");
		}
		return adminList.get(0);
		
	}

}
