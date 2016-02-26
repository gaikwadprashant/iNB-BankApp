package com.inbbank.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
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
import com.inbbank.model.Branchmanager;
import com.inbbank.model.Status;
import com.inbbank.service.ManagerService;
import com.inbbank.wsentity.WSAdminLogout;
import com.inbbank.wsentity.WSBranchManager;

@RestController
@CrossOrigin
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private Gson gson;
	private String logoutMsg = "{\"logoutMsg\" : \"Successfully Loged Out\"}";
	@RequestMapping(value = "/branchmanager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSBranchManager createBranchManager(@RequestBody Branchmanager branchmanager)throws Exception {
			return managerService.createBranchManager(branchmanager);

	}

	@RequestMapping(value = "/branchmanager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchManager> getBranchManagers() {
		List<Branchmanager> branchManagers = null;
		List<WSBranchManager> wsBranchManagers = new ArrayList<WSBranchManager>();
		try {
			branchManagers = managerService.getBranchManagers();
			for (Branchmanager branchmanager : branchManagers) {
				wsBranchManagers.add(mapper.map(branchmanager, WSBranchManager.class));
			}
		} catch (Exception e) {
		}
		return wsBranchManagers;
	}

	@RequestMapping(value = "/branchmanager/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String branchManagerLogin(@RequestBody UserDto userDto) {
		try {
			Branchmanager branchmanager = managerService.authenticateBranchManager(userDto.getUserName(),
					userDto.getPassword(), userDto.getBranchName());
			WSBranchManager wsBranchManagers = mapper.map(branchmanager, WSBranchManager.class);
			String branchManagerJson = gson.toJson(wsBranchManagers);
			return branchManagerJson;
		} catch (InvalidUserException e) {
			ExceptionDto ExceptionDto = new ExceptionDto(e.getMessage());
			String exceptionJson = gson.toJson(ExceptionDto);
			return exceptionJson;
		}

	}
	
	@RequestMapping(value="/branchmanager/logout", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public String BranchManagerLogOut(@RequestBody WSAdminLogout adminlogout){
		
		return logoutMsg;
	}
}
