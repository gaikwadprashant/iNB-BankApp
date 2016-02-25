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

import com.inbbank.model.Branchmanager;
import com.inbbank.model.Customer;
import com.inbbank.model.Status;
import com.inbbank.service.ManagerService;
import com.inbbank.wsentity.WSBranchManager;
import com.inbbank.wsentity.WSCustomer;

@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private DozerBeanMapper mapper;

	@RequestMapping(value = "/branchmanager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status createBranchManager(@RequestBody Branchmanager branchmanager) {
		try {
			managerService.createBranchManager(branchmanager);
			return new Status(1, "Branch Manager created Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}

	@RequestMapping(value = "/branchmanager", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
}
