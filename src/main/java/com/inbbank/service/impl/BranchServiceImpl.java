package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbbank.dao.BranchDao;
import com.inbbank.model.Branch;
import com.inbbank.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService  {

	@Autowired BranchDao branchDao;
	
	public Branch getBranchById() {
		//branchDao.createBranch();
		return null;
	}

	public boolean createBranch(Branch branch)  throws Exception{
		
		return branchDao.createBranch(branch);
	}

	public List<Branch> getBranches() throws Exception{
		// TODO Auto-generated method stub
		return branchDao.getBranches();
	}

}
