package com.inbbank.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.BranchDao;
import com.inbbank.model.Branch;
import com.inbbank.service.BranchService;
import com.inbbank.wsentity.WSBranch;

@Service
public class BranchServiceImpl implements BranchService  {

	@Autowired BranchDao branchDao;
	@Autowired
	private DozerBeanMapper mapper;
	
	public Branch getBranchById() {
		//branchDao.createBranch();
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public WSBranch createBranch(Branch branch)  throws Exception{
		
		branchDao.createBranch(branch);
		WSBranch branch2  = mapper.map(branch, WSBranch.class);
		return branch2;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Branch> getBranches() throws Exception{
		// TODO Auto-generated method stub
		return branchDao.getBranches();
	}

}
