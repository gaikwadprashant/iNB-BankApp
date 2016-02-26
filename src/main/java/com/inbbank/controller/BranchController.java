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

import com.inbbank.model.Branch;
import com.inbbank.model.Status;
import com.inbbank.service.BranchService;
import com.inbbank.wsentity.WSBranch;

@RestController
@CrossOrigin
public class BranchController {

	@Autowired
	BranchService branchService;

	@Autowired
	private DozerBeanMapper mapper;

	@RequestMapping(value = "/branch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status createBranch(@RequestBody Branch branch) {
		try {
			branchService.createBranch(branch);
			return new Status(1, "Branch Created Successfully !!");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/branch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranch> getBranch() {
		List<Branch> branches = null;
		List<WSBranch> branchList = new ArrayList<WSBranch>();
		try {

			branches = branchService.getBranches();
			for (Branch customer : branches) {
				branchList.add(mapper.map(customer, WSBranch.class));
			}

		} catch (Exception e) {
			
		}
		return branchList;
	}
	
	@RequestMapping(value = "/branch/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranch> getAllBranch() {
		List<Branch> branches = null;
		List<WSBranch> branchList = new ArrayList<WSBranch>();
		try {

			branches = branchService.getBranches();
			for (Branch customer : branches) {
				branchList.add(mapper.map(customer, WSBranch.class));
			}

		} catch (Exception e) {
			
		}
		return branchList;
	}
}
