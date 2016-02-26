package com.inbbank.service;

import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Admin;

public interface AdminService {

	Admin authonticateAdmin(String userName, String password) throws InvalidUserException;
}
