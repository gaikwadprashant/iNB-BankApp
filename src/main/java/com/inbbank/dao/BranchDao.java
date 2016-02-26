package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.Branch;

public interface BranchDao {

	void createBranch(Branch branch) throws Exception;

	List<Branch> getBranches() throws Exception;

}
