package com.inbbank.service;

import java.util.List;

import com.inbbank.model.Branch;

public interface BranchService {

	Branch getBranchById();

	boolean createBranch(Branch branch)  throws Exception;

	List<Branch> getBranches() throws Exception;
}
