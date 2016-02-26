package com.inbbank.dao;


import java.util.List;

import com.inbbank.model.Admin;


public interface AdminDao {

	
 List<Admin> authonticateAdmin(String username,String password);
		
}
