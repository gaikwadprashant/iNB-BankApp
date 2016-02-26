package com.inbbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.inbbank.dto.ExceptionDto;
import com.inbbank.dto.UserDto;
import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Admin;
import com.inbbank.service.AdminService;
import com.inbbank.wsentity.WSAdminLogout;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired Gson gson;
	private String logoutMsg = "{\"logoutMsg\" : \"Successfully Loged Out\"}";
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public String adminlogin(@RequestBody UserDto userDto) {
		try{
		Admin admin = adminService.authonticateAdmin(userDto.getUserName(),userDto.getPassword());
		String branchManagerJson = gson.toJson(admin);
		return branchManagerJson;
		}catch(InvalidUserException e){
			ExceptionDto ExceptionDto = new ExceptionDto(e.getMessage());
			String exceptionJson = gson.toJson(ExceptionDto);
			return exceptionJson;
		}
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String adminlogout(@RequestBody WSAdminLogout adminlogout ) {
		return logoutMsg;
	}

}
