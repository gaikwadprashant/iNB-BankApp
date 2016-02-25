package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createBranch(Branch branch)  throws Exception{
		
		return branchDao.createBranch(branch);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Branch> getBranches() throws Exception{
		// TODO Auto-generated method stub
		return branchDao.getBranches();
	}

}
